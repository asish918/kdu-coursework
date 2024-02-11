const tweetInput = document.querySelector("#tweet-input");
const postButton = document.querySelector("#tweet-btn");
const postWrapper = document.querySelector("#post-wrapper");
const tweetFob = document.querySelector("#tweet-fob")
const profileIcon = document.getElementsByClassName("profile-icon")
const tweetBox = document.getElementsByClassName("tweet-box")
const navSection = document.getElementsByClassName("navigation-section")
const mobileNav = document.getElementsByClassName('mobile-nav')
const mobileNavActions = document.getElementsByClassName('mobile-nav__actions')
const likeCount = document.getElementsByClassName('likes-count')[0]
likeCount.textContent = 0;


postButton.addEventListener('click', () => {
    const tweetValue = tweetInput.value;
    console.log(tweetValue)
    createPostElement(tweetValue);
})

profileIcon[0].addEventListener('click', () => {
    navSection[0].classList.add('display-on-flex')
})

tweetFob.addEventListener('click', () => {
    tweetBox[0].classList.add('display-on-flex')
    mobileNav[0].classList.add('display-none')
    mobileNavActions[0].classList.add('display-on-flex')
})

function extractHashtags(inputString) {
    const hashtagRegex = /#(\S+)/g;

    const hashtags = [];
    let normalText = inputString.replace(hashtagRegex, (match, p1) => {
        hashtags.push(p1);
        return '';
    });

    return [normalText.trim(), ...hashtags];
}

function createPostElement(tweetValue) {
    let liked = false;

    if (tweetValue.length === 0) {
        return;
    }

    const inputArray = extractHashtags(tweetValue);

    const postContainer = document.createElement('div');
    postContainer.classList.add('posts');

    const profileImg = document.createElement('img');
    profileImg.classList.add('posts__profile');
    profileImg.src = '/profile-pic.ad247f4d.png';
    profileImg.alt = '';

    const postInfo = document.createElement('div');
    postInfo.classList.add('posts__info');

    const postTitle = document.createElement('div');
    postTitle.classList.add('posts__title');

    const titleName = document.createElement('h1');
    titleName.textContent = 'Nitesh Gupta';

    const titleHandle = document.createElement('p');
    titleHandle.textContent = '@nit_hck ·';

    const titleTime = document.createElement('span');
    titleTime.textContent = '1s';

    const moreInfoImg = document.createElement('img');
    moreInfoImg.src = '/three_dots.ca3064e4.svg';
    moreInfoImg.alt = 'More Info';

    const postContent = document.createElement('div');
    postContent.classList.add('posts__content');

    const contentText = document.createElement('p');
    contentText.textContent = inputArray[0];

    const postActions = document.createElement('div');
    postActions.classList.add('posts__actions');

    const commentImg = document.createElement('img');
    commentImg.src = '/comment.145a5825.svg';
    commentImg.alt = 'Comment';

    const retweetImg = document.createElement('img');
    retweetImg.src = '/retweet.cc5912f0.svg';
    retweetImg.alt = 'Retweet';

    const likeDiv = document.createElement('div');
    const likeCountSpan = document.createElement('span');
    likeCountSpan.classList.add('likes-count');
    likeCountSpan.textContent = 0;
    likeDiv.classList.add('like-button__wrapper');
    likeDiv.innerHTML = `
        <svg
                    class="like-button"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    aria-hidden="true"
                  >
                    <g>
                      <path
                        fill="#808080"
                        d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
                      ></path>
                    </g>
                  </svg>
    `;

    const statsImg = document.createElement('img');
    statsImg.src = '/stats.e0073341.svg';
    statsImg.alt = 'Stats';

    const subActions = document.createElement('div');
    subActions.classList.add('posts__subactions');

    const bookmarkImg = document.createElement('img');
    bookmarkImg.src = '/bookmark.14f36264.svg';
    bookmarkImg.alt = 'Bookmark';

    const shareImg = document.createElement('img');
    shareImg.src = '/share.0c27b6b5.svg';
    shareImg.alt = 'Share';

    postTitle.appendChild(titleName);
    postTitle.appendChild(titleHandle);
    postTitle.appendChild(titleTime);
    postTitle.appendChild(moreInfoImg);

    postContent.appendChild(contentText);

    for (let i = 1; i < inputArray.length; i++) {
        const contentTags = document.createElement('span');
        contentTags.textContent = '#' + inputArray[i];
        postContent.appendChild(contentTags);
    }

    postActions.appendChild(commentImg);
    postActions.appendChild(retweetImg);

    likeDiv.appendChild(likeCountSpan);
    postActions.appendChild(likeDiv);

    likeDiv.addEventListener('click', () => {
        liked = !liked;
        if (!liked) {
            console.log(likeCountSpan)
            likeCountSpan.textContent = likeCountSpan.textContent * 1 - 1;
            likeDiv.innerHTML = `
            <svg
            class="like-button"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            aria-hidden="true"
            >
            <g>
            <path
            fill="#808080"
            d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
            ></path>
            </g>
            </svg>
            `;

            likeDiv.appendChild(likeCountSpan)
        } else {
            console.log(likeCountSpan)
            likeCountSpan.textContent = likeCountSpan.textContent * 1 + 1;
            likeDiv.innerHTML = `
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" aria-hidden="true"><g><path fill="#ff657e" d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
            `;
            likeDiv.appendChild(likeCountSpan)
        }
    });

    postActions.appendChild(statsImg);

    subActions.appendChild(bookmarkImg);
    subActions.appendChild(shareImg);

    postActions.appendChild(subActions);

    postInfo.appendChild(postTitle);
    postInfo.appendChild(postContent);
    postInfo.appendChild(postActions);

    postContainer.appendChild(profileImg);
    postContainer.appendChild(postInfo);

    postWrapper.appendChild(postContainer);
    postButton.style.backgroundColor = 'rgba(29, 156, 240, 0.429)';
    tweetInput.value = "";
    postButton.disabled = true;
}

tweetInput.addEventListener('keyup', function () {
    let inputValue = tweetInput.value.trim();
    inputValue.length > 0 ? postButton.disabled = false : postButton.disabled = true;

    postButton.style.backgroundColor = !inputValue ? 'rgba(29, 156, 240, 0.429)' : 'rgb(29, 155, 240)';
});

const likeButton = document.getElementsByClassName('like-button');
console.log(likeButton)
let liked = false;

for (const element of likeButton) {
    element.addEventListener('click', () => {
        liked = !liked;
        if (!liked) {
            console.log(likeCount.textContent);
            likeCount.textContent = likeCount.textContent * 1 - 1;
            element.innerHTML = `
            <svg
            class="like-button"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            aria-hidden="true"
            >
            <g>
            <path
            fill="#808080"
            d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
            ></path>
            </g>
            </svg>
            `;
        } else {
            element.innerHTML = `
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" aria-hidden="true"><g><path fill="#ff657e" d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
            `;

            likeCount.textContent = likeCount.textContent * 1 + 1;
        }
    });
}
