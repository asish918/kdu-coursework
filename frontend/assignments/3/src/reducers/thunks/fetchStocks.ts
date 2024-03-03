import axios from 'axios'
import { createAsyncThunk } from '@reduxjs/toolkit'

export const fetchStocks = createAsyncThunk('fetchStocks', async () => {
    const response = await axios.get('http://localhost:3000/stocks')
    return response.data
})