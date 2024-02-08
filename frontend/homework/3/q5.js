let keys = [];
let values = [];

function outputKeysAndValues(obj) {
    Object.keys(obj).forEach(key => keys.push(key));
    Object.values(obj).forEach(value => {
        if (typeof value === 'object') {
            outputKeysAndValues(value);
        } else {
            values.push(value);
        }
    });
}

const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        Barcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            }
        }
    }
};


outputKeysAndValues(player);

console.log("All keys:");
console.log(keys)
console.log("\nAll values:");
console.log(values)
