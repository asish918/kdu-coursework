import { PayloadAction, createSlice } from '@reduxjs/toolkit'
import { APIState, Stock } from '../app'
import { fetchStocks } from './thunks/fetchStocks'

export interface StockState {
    stocks: Stock[],
    page: Stock[],
    status: APIState
}

const initialState: StockState = {
    stocks: [],
    page: [],
    status: null
}

const stockSlice = createSlice({
    name: 'stocks',
    initialState,
    reducers: {
        setStocks: (state, payload: PayloadAction<Stock[]>) => {
            state.stocks = payload.payload
            state.page = payload.payload
            console.log(payload.payload)
        },
        paginate: (state, payload: PayloadAction<number>) => {
            state.page = state.stocks.slice(payload.payload * 7, (payload.payload + 1) * 7);
        },
        addWishList: (state, payload: PayloadAction<{
            page: number,
            index: number
        }>) => {
            state.stocks[payload.payload.index].wishlist = true;
            state.page = state.stocks.slice(payload.payload.page * 7, (payload.payload.page + 1) * 7);

        },
        removeWishList: (state, payload: PayloadAction<
            {
                page: number,
                index: number
            }
        >) => {
            state.stocks[payload.payload.index].wishlist = false;
            state.page = state.stocks.slice(payload.payload.page * 7, (payload.payload.page + 1) * 7);
        },
        updatePrice: (state, action: PayloadAction<{ stock_symbol: string, price: number }>) => {
            const stockToUpdate = state.stocks.find(stock => stock.stock_symbol === action.payload.stock_symbol);
            if (stockToUpdate) {
                stockToUpdate.current_price = action.payload.price;
            }
        }
    },
    extraReducers: builder => {
        builder
            .addCase(fetchStocks.pending, (state) => {
                state.status = 'loading'
            })
            .addCase(fetchStocks.fulfilled, (state, action: PayloadAction<Stock[]>) => {
                state.page = action.payload.slice(0 * 7, (0 + 1) * 7)
                state.stocks = action.payload
                state.status = 'success'
            })
            .addCase(fetchStocks.rejected, (state) => {
                state.status = 'error'
            })
    }
})

export const { setStocks, paginate, addWishList, removeWishList, updatePrice } = stockSlice.actions
export default stockSlice.reducer