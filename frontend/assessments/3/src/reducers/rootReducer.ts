import { combineReducers } from "@reduxjs/toolkit";
import roomReducer from "./roomSlice";
import userReducer from "./userSlice";

const rootReducer = combineReducers({
    rooms: roomReducer,
    user: userReducer
})

export default rootReducer;