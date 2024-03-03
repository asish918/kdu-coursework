const names = ["Rishav", "Aakash", "Shagun", "Anupam", "Nitesh"];

export function getRandomName() {
    const randomIndex = Math.floor(Math.random() * names.length);
    return names[randomIndex];
}