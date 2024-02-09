const inputEl = document.querySelector("input");
const inputButton = document.querySelector("button");
const todoList = document.querySelector("ul");

inputButton.addEventListener('click', () => {
    if (inputEl.value.length === 0) {
        return;
    }

    const liItem = document.createElement("li");
    liItem.textContent = inputEl.value;

    const liButton = document.createElement("button");
    liButton.textContent = "Delete";

    liItem.appendChild(liButton);

    liButton.addEventListener('click', () => {
        todoList.removeChild(liItem)
    })

    todoList.appendChild(liItem);
})