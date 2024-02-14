const socket = io();
const chatForm = document.getElementById('chat-form');
const chatMessages = document.querySelector('.chat__messages');
const roomName = document.getElementById('room-name');
const userList = document.getElementById('users');

const { username, room } = Qs.parse(location.search, {
    ignoreQueryPrefix: true,
});

socket.emit('joinRoom', { username, room });

socket.on('roomUsers', ({ room, users }) => {
    outputRoomName(room);
    outputUsers(users);
});

socket.on('message', message => {
    outputMessage(message);

    chatMessages.scrollTop = chatMessages.scrollHeight;
});

chatForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const mssg = e.target.elements.msg.value;

    socket.emit('chatMessage', mssg);

    e.target.elements.msg.value = '';
    e.target.elements.msg.focus();
});

// Append the messages to the DOM.
function outputMessage(message) {
    const outerDiv = document.createElement('div');
    outerDiv.classList.add('message_container');
    const profileP = document.createElement('p');
    profileP.classList.add('message_profile')

    // Check if its the Admin Bot or a normal user
    if (message.username === 'Admin Bot') {
        profileP.innerHTML = `<i class="fa-solid fa-robot"></i>`;
    } else {
        profileP.textContent = message.username.charAt(0).toUpperCase();
    }

    outerDiv.appendChild(profileP);

    const div = document.createElement('div');
    div.classList.add('message');
    div.innerHTML = `
    <p class="meta">${message.username} <span>${message.time}</span></p>
						<p class="text">
							${message.text}
						</p>`
    outerDiv.appendChild(div);
    chatMessages.appendChild(outerDiv);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Render the Room name
function outputRoomName(room) {
    roomName.innerText = room;
}

// Add User to the User List
function outputUsers(users) {
    userList.innerHTML = `
        ${users.map(user => `
        <li><p class="message_profile">${user.username.charAt(0).toUpperCase()}</p> ${user.username}</li>
        `).join('')}
    `
}

// Mutation observer for animating Messages as they appear in the DOM
const animateElement = (element) => {
    element.classList.add('animate');
};

const observer = new MutationObserver(mutationsList => {
    mutationsList.forEach(mutation => {
        mutation.addedNodes.forEach(node => {
            if (node.nodeType === 1 && node.classList.contains('message_container')) {
                animateElement(node);
            }
        });
    });
});

observer.observe(chatMessages, { childList: true });