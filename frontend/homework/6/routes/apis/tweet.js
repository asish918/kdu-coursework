const express = require('express')
const router = express.Router();

const { v4: uuidv4 } = require('uuid');
const posts = require('../../data/post')

router.get('/', (req, res) => {
    res.send(posts);
})

router.get('/:id', (req, res) => {
    const post = posts.filter(post => post.id === req.params.id);

    if (post.length === 0)
        res.status(404).send("Post with the given id not found");

    res.status(200).send(post);
})

router.post('/', (req, res) => {
    if (!req.body.name || !req.body.username || !req.body.content) {
        res.status(402).send("Invalid Body");
    }

    try {
        posts.push({
            id: uuidv4(),
            name: req.body.name,
            username: req.body.username,
            content: req.body.content,
        });

        res.status(201).send("Post Created successfully");
    } catch (error) {
        res.status(500).send(error);
    }
})

router.put('/', (req, res) => {
    const index = posts.findIndex(post => post.id === req.body.id);

    if (index === -1) {
        res.status(404).send("Post not found!")
    }

    posts[index] = { ...posts[index], ...req.body }
    res.status(201).json({ message: "Update successfull", post: posts[index] });
})

router.delete('/:id', (req, res) => {
    const index = posts.findIndex(post => post.id === req.params.id);

    if (index === -1) {
        res.status(404).send("Post not found!")
    }

    posts.splice(index, 1);
    res.status(200).send("Successfully deleted the post");
})

module.exports = router;