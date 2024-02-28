import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { APIState, Product } from "../apptypes";
import { getProducts } from "./thunks/getProducts";

interface ProductState {
    products: Product[];
    status: APIState;
}

const initialState: ProductState = {
    products: [],
    status: null,
};

const productSlice = createSlice({
    name: "Product",
    initialState,
    reducers: {
        setProducts: (_state, action: PayloadAction<ProductState>) => {
            return {
                products: [...action.payload.products],
                status: action.payload.status,
            }
        }
    },
    extraReducers: builder => {
        builder
            .addCase(getProducts.pending, (state) => {
                state.status = 'loading'
            })
            .addCase(getProducts.fulfilled, (state, action) => {
                state.products = [...action.payload]
                state.status = 'success'
            })
            .addCase(getProducts.rejected, (state) => {
                state.status = 'error'
            })
    }
})

export const { setProducts } = productSlice.actions;
export default productSlice.reducer;