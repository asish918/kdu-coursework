import { combineReducers } from "@reduxjs/toolkit";
import stockReducer from "./stockSlice";
import userReducer from "./userSlice";
import graphReducer from "./graphSlice";

export const rootReducer = combineReducers({
    stocks: stockReducer,
    user: userReducer,
    graph: graphReducer,
})