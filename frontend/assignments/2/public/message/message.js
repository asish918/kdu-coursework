const socket = io();

const profileInfo = document.querySelector('.navbar__profile-info')
const activeUserContainer = document.querySelector('.main__users')
const mainMessageDiv = document.querySelector('.main__messages')

let activeUsers = [];
let activeChatId;

const { username } = Qs.parse(location.search, {
    ignoreQueryPrefix: true,
});

function populateMessageContainer(messageData) {
    const userHeader = document.createElement('h2');
    userHeader.textContent = messageData.name;

    const messageContainer = document.createElement('div');
    messageContainer.classList.add('message__container');

    messageData.messages.forEach(message => {
        if (message.user_id !== activeChatId) return;
        const messageDiv = document.createElement('div');
        messageDiv.classList.add('message', message.type === 'self' ? 'self' : 'other');

        const content = document.createElement('p');
        content.classList.add('message__content');
        content.textContent = message.content;

        const time = document.createElement('p');
        time.classList.add('message__time');
        time.textContent = message.time;

        messageDiv.appendChild(content);
        messageDiv.appendChild(time);

        messageContainer.appendChild(messageDiv);
    });

    const messageInputContainer = document.createElement('div');
    messageInputContainer.classList.add('message__input');

    const messageInput = document.createElement('input');
    messageInput.placeholder = 'Enter a message..';
    messageInput.type = 'text';
    messageInput.id = 'message-input';

    const sendButton = document.createElement('button');
    sendButton.classList.add('message__button');

    const sendIcon = document.createElement('img');
    sendIcon.src = './assets/icons/send.svg';
    sendIcon.alt = 'Send';

    sendButton.appendChild(sendIcon);

    sendButton.addEventListener('click', () => {
        if (messageInput.value.length === 0) return;

        socket.emit('sendMessage', { id: activeChatId, username, content: messageInput.value })
    });

    messageInputContainer.appendChild(messageInput);
    messageInputContainer.appendChild(sendButton);

    messageContainer.appendChild(messageInputContainer);

    mainMessageDiv.appendChild(userHeader);
    mainMessageDiv.appendChild(messageContainer);
}

function setActiveChat(socketId) {
    activeChatId = socketId;
    console.log("Current Active Chat ID - " + activeChatId);
    socket.emit('chatOpen', { socketId, username });
}

function populateUser(userData) {
    const userContainer = document.createElement('div');
    userContainer.classList.add('user');

    const profilePic = document.createElement('p');
    profilePic.classList.add('navbar__profile-pic');
    profilePic.textContent = userData.name.charAt(0);

    const userName = document.createElement('h1');
    userName.classList.add('user__name');
    userName.textContent = userData.name;

    const userUsername = document.createElement('p');
    userUsername.classList.add('user__username');
    userUsername.textContent = `@${userData.username}`

    userContainer.appendChild(profilePic);
    userContainer.appendChild(userName);
    userContainer.appendChild(userUsername);

    userContainer.addEventListener('click', () => {
        mainMessageDiv.innerHTML = '';
        console.log("Socket id of " + userData.username + " - " + userData.id);
        setActiveChat(userData.id);

    });

    activeUserContainer.appendChild(userContainer);
}

function populateProfileInfo(profileData) {
    const profilePicElement = document.createElement('p');
    profilePicElement.classList.add('navbar__profile-pic');
    profilePicElement.textContent = profileData.name.charAt(0);

    const profileTextDiv = document.createElement('div');
    profileTextDiv.classList.add('navbar__profile-text');

    const nameElement = document.createElement('h1');
    nameElement.textContent = profileData.name;

    const usernameElement = document.createElement('p');
    usernameElement.textContent = `@${profileData.username}`

    const moreOptionsImg = document.createElement('img');
    moreOptionsImg.src = './assets/icons/three_dots.svg';
    moreOptionsImg.alt = 'More Options';

    profileTextDiv.appendChild(nameElement);
    profileTextDiv.appendChild(usernameElement);

    profileInfo.appendChild(profilePicElement);
    profileInfo.appendChild(profileTextDiv);
    profileInfo.appendChild(moreOptionsImg);
}

async function onLoad() {
    socket.emit('joinRoom', { username })

    const res = await fetch(`http://localhost:8000/api/user/${username}`);

    const data = await res.json();

    populateProfileInfo(data);
    socket.emit('messagePage');
    console.log("Socket id of " + username + " - " + socket.id);
}

socket.on('activeUsers', (payload) => {
    const elementsToRemove = activeUserContainer.querySelectorAll('.' + 'user');

    if (elementsToRemove.length !== 0) {
        elementsToRemove.forEach(element => {
            element.parentNode.removeChild(element);
        });
    }

    payload.forEach(u => {
        populateUser(u)
    });

    activeUsers = payload;
})

socket.on('chatDetails', (payload) => {
    console.log(payload);
    mainMessageDiv.innerHTML = '';
    populateMessageContainer(payload);
});

socket.on('updateChatDetails', (payload) => {
    if (payload.otherId == activeChatId) {
        mainMessageDiv.innerHTML = '';
        populateMessageContainer({
            id: payload.otherId,
            name: payload.otherName,
            messages: payload.otherMessages
        });
    }
})

onLoad();