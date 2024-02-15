interface RecipeAPI {
    recipes: Recipe[],
    total: number,
    skip: number,
    limit: number
}

interface Recipe {
    id: number,
    name: string,
    ingredients: string[],
    instructions: string[],
    prepTimeMinutes: number,
    cookTimeMinutes: number,
    servings: number,
    difficulty: string,
    cuisine: string,
    caloriesPerServing: number,
    tags: string[],
    userId: number,
    image: string,
    rating: number,
    reviewCount: number,
    mealType: string[]
}

interface AppRecipe {
    image: string,
    name: string,
    rating: number,
    cuisine: string,
    ingredients: string[],
    difficulty: string,
    timeTaken: number,
    calorieCount: number,
}

let appRecipes: AppRecipe[] = [];
let searchRecipesArr: AppRecipe[] = [];

function recipeMapper(recipe: Recipe): AppRecipe {
    return {
        image: recipe.image,
        name: recipe.name,
        rating: recipe.rating,
        cuisine: recipe.cuisine,
        ingredients: recipe.ingredients,
        difficulty: recipe.difficulty,
        timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
        calorieCount: recipe.caloriesPerServing
    }
}

async function fetchRecipesFromAPI() {
    const res = await fetch('https://dummyjson.com/recipes');
    const data: RecipeAPI = await res.json();

    data.recipes.forEach((r: Recipe) => {
        appRecipes.push(recipeMapper(r));
    });
}

async function searchRecipes(query: string | undefined) {
    const res = await fetch(`https://dummyjson.com/recipes/search?q=${query}`);
    const data: RecipeAPI = await res.json();

    data.recipes.forEach((r: Recipe) => {
        const recipeDiv = addRecipe(recipeMapper(r));
        modalContent?.appendChild(recipeDiv);
    });

    console.log(modalContent)
}

function printAllRecipes() {
    appRecipes.forEach(r => console.log(r));
    appRecipes.forEach(r => {
        const recipeWrapper = document.querySelector('.recipe__wrapper');
        const recipeDiv = addRecipe(r);
        recipeWrapper?.appendChild(recipeDiv);
    });
}

async function runProgram() {
    fetchRecipesFromAPI().then((res) => printAllRecipes()).catch(e => console.log(e));
}

function addRecipe(recipe: AppRecipe): HTMLDivElement {
    // <i class="fa-solid fa-list"></i>
    const categoryIcon = document.createElement('i');
    categoryIcon.classList.add('fa-solid', 'fa-list');

    // <i class="fa-regular fa-eye"></i>
    const difficultyIcon = document.createElement('i');
    difficultyIcon.classList.add('fa-regular', 'fa-eye');

    // <i class="fa-solid fa-clock"></i>
    const clockIcon = document.createElement('i');
    clockIcon.classList.add('fa-solid', 'fa-clock')

    // <i class="fa-solid fa-fire-flame-simple"></i>
    const calorieIcon = document.createElement('i');
    calorieIcon.classList.add('fa-solid', 'fa-fire-flame-simple');

    // Create a div element to hold the entire recipe
    const recipeDiv = document.createElement('div');
    recipeDiv.classList.add('recipe__item');

    // Create and append the image element
    const img = document.createElement('img');
    img.src = recipe.image;
    img.alt = 'Recipe Image';
    img.classList.add('recipe__image');
    recipeDiv.appendChild(img);

    // Create and append the heading element
    const heading = document.createElement('h1');
    heading.textContent = recipe.name;
    heading.classList.add('recipe__name');
    recipeDiv.appendChild(heading);

    // Create and append the ingredients list
    const ingredientsList = document.createElement('ul');
    ingredientsList.classList.add('recipe__ingredients');
    recipe.ingredients.forEach(ingredient => {
        const li = document.createElement('li');
        li.textContent = ingredient;
        ingredientsList.appendChild(li);
    });
    recipeDiv.appendChild(ingredientsList);

    // Create and append the cuisine element
    const cuisineDiv = document.createElement('div');
    const cuisineP = document.createElement('p')
    cuisineP.textContent = recipe.cuisine;
    cuisineDiv.appendChild(cuisineP);
    cuisineDiv.classList.add('recipe__cuisine');
    cuisineDiv.appendChild(categoryIcon);
    recipeDiv.appendChild(cuisineDiv);

    // Create and append the difficulty element
    const difficultyDiv = document.createElement('div');
    const difficultP = document.createElement('p')
    difficultP.textContent = recipe.difficulty;
    difficultyDiv.appendChild(difficultP);
    difficultyDiv.classList.add('recipe__difficulty');
    difficultyDiv.appendChild(difficultyIcon)
    recipeDiv.appendChild(difficultyDiv);

    // Create and append the time taken element
    const timeTakenDiv = document.createElement('div');
    const timeTakenP = document.createElement('p')
    timeTakenP.textContent = recipe.timeTaken.toString();
    timeTakenDiv.appendChild(timeTakenP);
    timeTakenDiv.classList.add('recipe__time-taken');
    timeTakenDiv.appendChild(clockIcon);
    recipeDiv.appendChild(timeTakenDiv);

    // Create and append the calories element
    const caloriesDiv = document.createElement('div');
    const caloriesP = document.createElement('p')
    caloriesP.textContent = recipe.calorieCount.toString();
    caloriesDiv.appendChild(caloriesP);
    caloriesDiv.classList.add('recipe__calories');
    caloriesDiv.appendChild(calorieIcon);
    recipeDiv.appendChild(caloriesDiv);

    // Create and append the rating element
    const ratingDiv = document.createElement('div');
    const ratingP = document.createElement('p')
    ratingP.textContent = recipe.rating.toString();
    ratingDiv.appendChild(ratingP);

    ratingDiv.classList.add('recipe__rating');
    recipeDiv.appendChild(ratingDiv);

    // Append the recipe div to the document body or any desired parent element
    return recipeDiv;
}

runProgram();

const modal: HTMLElement | null = document.getElementById("myModal");
const modalContent = document.querySelector(".modal-content");
const search = document.getElementById("search");
const input: HTMLInputElement | null = document.getElementById("input-box");

const btn = document.getElementById("openModalBtn");

const span: HTMLSpanElement | null = document.querySelector(".close");

if (search) {
    search.onclick = function () {
        console.log(input?.value)
        searchRecipes(input?.value)

        if (modal) {
            modal.style.display = "block";
        }
    }
}

if (span) {
    span.onclick = function () {
        if (modal) {
            modal.style.display = "none";
        }
    }
}

window.onclick = function (event) {
    if (event.target == modal) {
        if (modal) {
            modal.style.display = "none";
        }
    }
}