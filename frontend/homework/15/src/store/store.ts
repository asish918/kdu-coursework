import { configureStore } from "@reduxjs/toolkit";
import todoReducer from "../reducers/todoSlice";
import searchReducer from "../reducers/searchSlice";

export const store = configureStore({
    reducer: {
        todo: todoReducer,
        search: searchReducer
    }
})

export type RootState = ReturnType<typeof store.getState>;
export type RootDispatch = typeof store.dispatch;
