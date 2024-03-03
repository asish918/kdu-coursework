import axios from 'axios'
import { createAsyncThunk } from '@reduxjs/toolkit'

export const fetchPortfolio = createAsyncThunk('fetchPortfolio', async () => {
    const response = await axios.get('http://localhost:3000/portfolio')
    return response.data
})