import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Task } from "../types";

const initialState: Task[] = [];

interface SearchPayload {
    tasks: Task[],
    searchValue: string
}

const searchSlice = createSlice({
    name: "search",
    initialState,
    reducers: {
        filterTodo: (state, payload: PayloadAction<SearchPayload>) => payload.payload.tasks.filter(item => {
            const lowerCaseItem = item.name.toLowerCase();
            return lowerCaseItem.includes(payload.payload.searchValue.toLowerCase());
        }),
    }
})

export const { filterTodo } = searchSlice.actions;

export default searchSlice.reducer;
