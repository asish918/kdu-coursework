const express = require('express');
const { getByUsername } = require('../models/users');
const router = express.Router();

router.get('/user/:username', (req, res) => {
    const username = req.params.username;

    const user = getByUsername(username);

    res.json({
        name: user[0].name,
        username: user[0].user_name,
        profile_url: user[0].profile_url
    });
})

module.exports = router;