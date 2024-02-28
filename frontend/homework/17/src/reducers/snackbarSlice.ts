import { createSlice } from "@reduxjs/toolkit";
import { APIState } from "../apptypes";
import { getProducts } from "./thunks/getProducts";

interface SnackbarState {
    message: string;
    status: APIState;
    error?: string;
}

const initialState: SnackbarState = {
    message: "",
    status: null,
};

const snackbarSlice = createSlice({
    name: "Snackbar",
    initialState,
    reducers: {
    },
    extraReducers: builder => {
        builder
            .addCase(getProducts.fulfilled, (state) => {
                state.status = 'success'
                state.message = 'Products Loaded Successfully'
            })
            .addCase(getProducts.rejected, (state) => {
                state.status = 'error'
                state.message = 'Some error occurred in loading products'
            })
    }
})

export default snackbarSlice.reducer;