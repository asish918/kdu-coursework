const socket = io();

const profileInfo = document.querySelector('.navbar__profile-info')
const tweetBoxProfile = document.querySelector(".navbar__profile-pic")
const messageButton = document.getElementById('message')
const postButton = document.getElementById('post-button')
const postInput = document.getElementById('post-input')
const postsContainer = document.querySelector('.posts')

const tweetBox = document.querySelector('.main__tweet-input')
const mediaInput = document.getElementById("mediaInput")

let page = 1;

const { username } = Qs.parse(location.search, {
    ignoreQueryPrefix: true,
});

async function populatePostsData(post) {
    console.log(post);
    const postContainer = document.createElement('div');
    postContainer.classList.add('post');

    const profilePic = document.createElement('img');
    profilePic.classList.add('navbar__profile-pic');
    profilePic.src = post.user_image;

    const postInfo = document.createElement('div');
    postInfo.classList.add('post__info');

    const userInfo = document.createElement('div');
    userInfo.classList.add('post__user-info');

    const userName = document.createElement('h1');
    userName.classList.add('post__user');
    userName.textContent = post.name;

    const userUsername = document.createElement('p');
    userUsername.classList.add('post__username');
    userUsername.textContent = `@${post.username}`;

    const postTime = document.createElement('p');
    postTime.classList.add('post__time');
    // postTime.textContent = '·' + post.time;
    postTime.textContent = '· 15h';

    const moreInfoImg = document.createElement('img');
    moreInfoImg.src = './assets/icons/three_dots.svg';
    moreInfoImg.alt = 'More Info';

    const postContent = document.createElement('p');
    postContent.classList.add('post__content');
    postContent.textContent = post.content;

    const postImage = document.createElement('img');
    postImage.classList.add('post__image')

    if (post.imageBlob?.length !== 0) {
        postImage.src = post.imageBlob;
    }

    const postActions = document.createElement('div');
    postActions.classList.add('post__actions');


    const actionIcons = [
        './assets/icons/comment.svg',
        './assets/icons/retweet.svg',
        './assets/icons/heart.svg',
        './assets/icons/stats.svg',
        './assets/icons/bookmark.svg',
        './assets/icons/share.svg'
    ];

    actionIcons.forEach(iconPath => {
        const img = document.createElement('img');
        img.src = iconPath;
        img.alt = 'Action';
        postActions.appendChild(img);
    });

    userInfo.appendChild(userName);
    userInfo.appendChild(userUsername);
    userInfo.appendChild(postTime);
    userInfo.appendChild(moreInfoImg);

    postInfo.appendChild(userInfo);
    postInfo.appendChild(postContent);

    if (post.imageBlob?.length !== 0) {
        postInfo.appendChild(postImage);
    }

    postInfo.appendChild(postActions);

    postContainer.appendChild(profilePic);
    postContainer.appendChild(postInfo);

    postsContainer.appendChild(postContainer);
}

function populateProfileInfo(profileData) {
    console.log(profileData);

    const profilePicElement = document.createElement('img');
    profilePicElement.classList.add('navbar__profile-pic');
    profilePicElement.src = profileData.profile_url;

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

    tweetBoxProfile.src = profileData.profile_url;
}

async function onLoad() {
    const res = await fetch(`http://localhost:8000/api/user/${username}`);
    const data = await res.json();

    populateProfileInfo(data);

    fetchPostData();
}

messageButton.addEventListener('click', () => {
    let baseUrl = 'http://localhost:8000/message.html';

    let queryParams = { username: username };

    let queryString = Object.keys(queryParams).map(key => key + '=' + encodeURIComponent(queryParams[key])).join('&');

    let urlWithParams = baseUrl + '?' + queryString;

    window.location.href = urlWithParams;
})

postButton.addEventListener('click', async () => {
    let imageBlob = "";
    if (mediaInput.files.length !== 0) {
        imageBlob = await getImageBlob(mediaInput.files[0]);
    }

    if (postInput.value.length === 0) {
        return;
    }

    const res = await fetch(`http://localhost:8000/api/user/${username}`);
    const data = await res.json();

    console.log(data);
    console.log(postInput.value)
    console.log(JSON.stringify({
        name: data.name,
        username: data.username,
        content: postInput.value,
        user_image: data.profile_url,
        imageBlob,
    }))
    const postRes = await fetch("http://localhost:8000/api/posts", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: data.name,
            username: data.username,
            content: postInput.value,
            user_image: data.profile_url,
            imageBlob,
        })
    });

    window.location.reload();
})

function fetchPostData() {
    if (page != -1)

        fetch(`http://localhost:8000/api/posts?page=${page}&limit=5`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                data.results.forEach(p => populatePostsData(p))

                if (data.results < 5) {
                    page = -1;
                    return;
                }

                page++;
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
}

function handleScroll() {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        fetchPostData();
    }
}

function displayImage(file) {
    const reader = new FileReader();
    let img = document.createElement('img');
    img.classList.add("uploaded-image")

    reader.onload = function (e) {
        img.src = e.target.result;
        console.log(e.target.result)
        tweetBox.appendChild(img)
    };

    reader.readAsDataURL(file);
}

function getImageBlob(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = function (e) {
            resolve(e.target.result);
        };
        reader.onerror = function (error) {
            reject(error);
        };

        reader.readAsDataURL(file);
    });
}

mediaInput.addEventListener("change", handleFiles, false);

function handleFiles() {
    const fileList = this.files; /* now you can work with the file list */
    console.log(fileList[0]);
    displayImage(fileList[0])
}

window.addEventListener('scroll', handleScroll);

onLoad();