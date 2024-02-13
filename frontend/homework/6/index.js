const express = require('express')
const app = express()
const dotenv = require('dotenv').config();
var morgan = require('morgan')
const tweetApi = require('./routes/apis/tweet')

const port = process.env.PORT || 3000

app.use(express.json());
app.use(morgan('short'))
app.use(express.static('public'))
app.use('/posts', tweetApi);

app.listen(port, () => {
    console.log(`Server listening on port ${port}`)
})