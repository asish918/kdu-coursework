const inputString = '{"firstName": "Alex", "lastName": "Hunter", "email": "alex@yahoo.com", "age":24, "city": "london", "country": "england" }';

const jsonObject = JSON.parse(inputString, (key, value) => {
    if (key !== 'email') {
        return typeof value === 'string' ? value.toUpperCase() : value;
    }
    return value;
});

console.log(jsonObject);

delete jsonObject.email;

const outputString = JSON.stringify(jsonObject);
console.log(outputString);
