const os = require('os')
const fs = require('fs')
const path = require('path')

function os_info() {
    const os_json = {
        hostname: os.hostname(),
        operating_system: os.type(),
        architecture: os.arch(),
        os_release: os.platform(),
        uptime: os.uptime(),
        core_count: os.cpus().length,
        total_memory: os.totalmem(),
        free_memory: os.freemem(),
    }


    fs.writeFileSync(path.join(__dirname + "/obj.json"), JSON.stringify(os_json), (err) => {
        if (err) throw new err;
        console.log("File written successfully..")
    })
}

module.exports = os_info