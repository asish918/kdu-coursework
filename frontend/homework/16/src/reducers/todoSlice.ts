import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Task } from "../types";

const initialState: Task[] = [];

const todoSlice = createSlice({
    name: "todo",
    initialState,
    reducers: {
        addTodo: (state, payload: PayloadAction<Task>) => {
            return [
                ...state,
                payload.payload
            ]
        },
        deleteTodo: (state, payload: PayloadAction<number>) => state.filter(item => item.id !== payload.payload),
        checkTodo: (state, payload: PayloadAction<number>) => state.map(task =>
            task.id === payload.payload ? { ...task, done: !task.done } : task
        ),
        clearCompleted: (state) => state.filter(item => !item.done),
        editTodo: (state, payload: PayloadAction<{ id: number; newName: string }>) => state.map(task =>
            task.id === payload.payload.id ? { ...task, name: payload.payload.newName } : task
        ),
    }
})

export const { addTodo, deleteTodo, checkTodo, clearCompleted, editTodo } = todoSlice.actions;
export default todoSlice.reducer;