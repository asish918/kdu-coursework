import { createAsyncThunk } from "@reduxjs/toolkit"
import axios from "axios"
import { APIState, Product } from "../../apptypes"

export type ThunkState = {
    products: Product[],
    state: APIState,
    error?: string
}

export const getProducts = createAsyncThunk('getProducts', async () => {
    const response = await axios.get('https://fakestoreapi.com/products')
    return response.data;
})