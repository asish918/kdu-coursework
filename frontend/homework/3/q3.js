const shoes = [
    { type: "sneakers", color: "red", size: 10, price: 50 },
    { type: "boots", color: "black", size: 9, price: 80 }
];

const shirts = [
    { type: "t-shirt", color: "blue", size: "M", price: 20 },
    { type: "polo", color: "white", size: "L", price: 30 },
    { type: "hoodie", color: "blue", size: "XL", price: 40 }
];

const warehouse = [...shoes, ...shirts];

const totalWorth = warehouse.reduce((total, product) => total + product.price, 0);

console.log("Total worth of products in the warehouse:", totalWorth);

warehouse.sort((a, b) => b.price - a.price);

console.log("Warehouse array sorted in descending order of prices:", warehouse);

const blueProducts = warehouse.filter(product => product.color === "blue");

console.log("Warehouse products which are blue in color:", blueProducts);
