const { v4: uuidv4 } = require('uuid');

let posts = [
    {
        "id": uuidv4(),
        "name": "Asish Mahapatra",
        "username": "asish918",
        "content": "Hello Peepuls"
    },
    {
        "id": uuidv4(),
        "name": "Arjun Singh",
        "username": "arjun918",
        "content": "Hello Peepuls 1"
    },
    {
        "id": uuidv4(),
        "name": "Aryan",
        "username": "aryan918",
        "content": "Hello Peepuls 2"
    },
];

module.exports = posts;