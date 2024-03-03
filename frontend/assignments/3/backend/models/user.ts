import { addTransaction } from "./transactionMap";

const users: string[] = [];

export function addUser(user: string) {
    users.push(user);
    console.log(user + " user added")
}

export function removeUser(userId: string) {
    const index = users.findIndex(user => user === userId);
    console.log(userId + " user removed")
}