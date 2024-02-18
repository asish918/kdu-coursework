const posts = require('../data/posts')

function addPost(post) {
    posts.unshift(post);
}

function getAllPosts() {
    return posts;
}

function getPostById(id) {
    return posts.filter(p => p.id === id);
}

module.exports = {
    addPost,
    getAllPosts,
    getPostById
}