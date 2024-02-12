const path = require('path');
const fs = require('fs');
const http = require('http');

const os_info = require('./os_info');
const q2 = require('./q2');

require('dotenv').config();

const server = http.createServer((req, res) => {
    switch (req.url) {
        case '/':
            fs.readFile('obj.json', (err, data) => {
                if (err) {
                    res.writeHead(500, { 'Content-Type': 'text/plain' });
                    res.end('Internal Server Error');
                } else {
                    res.writeHead(200, { 'Content-Type': 'application/json' });
                    res.end(data);
                }
            });
            break;

        case '/index.html':
            fs.readFile('obj.json', 'utf-8', (err, data) => {
                if (err) {
                    res.writeHead(500, { 'Content-Type': 'text/plain' });
                    res.end('Internal Server Error');
                } else {
                    data = JSON.parse(data);
                    res.writeHead(200, { 'Content-Type': 'text/html' });
                    res.end(`
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8" />
                            <title>OS Details</title>
                            <link rel="stylesheet" href="./style.css" />
                        </head>
                        <body>
                            <div class="container">
                            <div class="laptop">
                                <div class="laptop__screen">
                                <div>
                                    <p>Hostname - ${data.hostname}</p>
                                    <p>Operating System - ${data.operating_system}</p>
                                    <p>Architecture - ${data.architecture}</p>
                                    <p>OS Release - ${data.os_release}</p>
                                    <p>Uptime - ${data.uptime}</p>
                                    <p>Core Count - ${data.core_count}</p>
                                    <p>Total Memory - ${data.total_memory}</p>
                                    <p>Free Memory - ${data.free_memory}</p>
                                </div>
                                </div>
                                <div class="laptop__bottom">
                                <div class="laptop__under"></div>
                                </div>
                                <div class="laptop__shadow"></div>
                            </div>
                            </div>
                        </body>
                        </html>

                    `);
                }
            });
            break;

        case '/style.css':
            fs.readFile(path.join(__dirname, 'style.css'), (err, data) => {
                if (err) {
                    res.writeHead(500, { 'Content-Type': 'text/plain' });
                    res.end('Internal Server Error');
                } else {
                    res.writeHead(200, { 'Content-Type': 'text/css' });
                    res.end(data);
                }
            });
            break;

        default:
            res.writeHead(404, { 'Content-Type': 'text/plain' });
            res.end('404 Not Found');
            break;
    }
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
    os_info();

    const filePaths = [
        'dir1/dir2/file1.txt',
        'dir1/dir3/file2.txt',
        'dir1/dir3/file3.md',
        'dir4/file4.jpg',
        'dir4/file5.pdf',
    ];

    console.log(q2(filePaths));
});