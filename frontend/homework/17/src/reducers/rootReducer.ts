import { combineReducers } from "@reduxjs/toolkit";
import productsReducer from "./productsSlice";
import snackbarReducer from "./snackbarSlice";

export const rootReducer = combineReducers({
    product: productsReducer,
    snackbar: snackbarReducer
})