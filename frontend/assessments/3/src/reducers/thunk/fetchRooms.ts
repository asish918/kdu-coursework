import { createAsyncThunk } from "@reduxjs/toolkit"
import axios from "axios"

export const fetchRooms = createAsyncThunk('todos/fetchTodos', async () => {
    try {
        const response = await axios.get('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json')
        return response.data.roomTypes;
    } catch (error) {
        throw new Error("Failed to load Rooms")
    }
})