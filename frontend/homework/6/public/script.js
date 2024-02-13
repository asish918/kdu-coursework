document.getElementById('submitButton').addEventListener('click', async function (event) {
    const name = document.getElementById('name').value;
    const username = document.getElementById('username').value;
    const content = document.getElementById('content').value;

    const postData = {
        name: name,
        username: username,
        content: content,
    }

    try {
        const response = await fetch('http://localhost:3000/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });
        if (!response.ok) {
            throw new Error('Failed to submit data');
        }
        console.log('Data submitted successfully');
        document.getElementById('name').value = '';
        document.getElementById('username').value = '';
        document.getElementById('content').value = '';
    } catch (error) {
        console.error('Error:', error);
    }
});