function abbreviateArray(days) {
    const trimmedArray = days.map(day => day.trim().substring(0, 3).toUpperCase());
    return trimmedArray;
}

const days = ["Sunday ", " Monday ", " Tuesday", "Wednesday ", " Thursday ", " Friday", " Saturday "];
const newArray = abbreviateArray(days);
console.log();


function encodeString(str) {
    str = str.trim();
    const charMap = {
        'a': '4',
        'e': '3',
        'i': '1',
        'o': '0',
        's': '5'
    };
    let codedStr = '';
    for (let i = 0; i < str.length; i++) {
        const char = str[i];
        if (charMap[char.toLowerCase()] !== undefined) {
            codedStr += charMap[char.toLowerCase()];
        } else {
            codedStr += char;
        }
    }
    return codedStr;
}

const test1 = "javascript is cool";
const test2 = "programming is fun";
const test3 = " become a coder";

console.log(encodeString(test1));
console.log(encodeString(test2));
console.log(encodeString(test3));
