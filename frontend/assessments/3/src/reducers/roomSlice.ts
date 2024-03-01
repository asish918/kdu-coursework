import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import { APIState, RoomType } from '../types'
import { fetchRooms } from './thunk/fetchRooms'

interface RoomState {
    rooms: RoomType[],
    status: APIState,
    message: string
}

const initialState: RoomState = {
    rooms: [],
    status: null,
    message: ""
}

const roomSlice = createSlice({
    name: 'rooms',
    initialState,
    reducers: {
    },
    extraReducers: builder => {
        builder
            .addCase(fetchRooms.pending, (state) => {
                state.status = 'loading'
            })
            .addCase(fetchRooms.fulfilled, (state, action: PayloadAction<RoomType[]>) => {
                state.rooms = [...action.payload];
                state.status = "success";
                state.message = "Fetched rooms successfully"
            })
            .addCase(fetchRooms.rejected, (state, action) => {
                state.message = "Some error occurred while fetching rooms";
                state.status = "error";
                console.log(action.payload)
            })
    }
})

// export const { incremented, decremented } = roomSlice.actions
export default roomSlice.reducer;