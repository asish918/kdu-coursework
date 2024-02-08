function tipCalculator(bills) {
    let tips = [];
    let finalAmounts = [];

    bills.forEach(element => {
        if (element < 50) {
            tips.push(0.2 * element)
            finalAmounts.push(0.2 * element + element)
        }
        else if (element >= 50 && element <= 200) {
            finalAmounts.push(0.15 * element + element)
            tips.push(0.15 * element)
        }
        else {
            finalAmounts.push(0.1 * element + element)
            tips.push(0.1 * element)
        }
    });

    console.log("Bills - " + bills)
    console.log("Tips for the bills - " + tips);
    console.log("Final amounts paid - " + finalAmounts);
}

const bills = [140, 45, 280];
tipCalculator(bills)
