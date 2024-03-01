import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import { AddOn, RoomType, UserData } from '../types'
import dayjs from 'dayjs'

interface UserState {
    data: UserData,
    bookingStatus: boolean,
    message: string
}

const initialState: UserState = {
    data: {
        activeRoom: null,
        addOns: [],
        bookingPrice: 0,
        endDate: dayjs(),
        startDate: dayjs()
    },
    bookingStatus: false,
    message: ""
}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        setActiveRoom: (state, action: PayloadAction<RoomType>) => {
            return {
                ...state,
                data: {
                    ...state.data,
                    activeRoom: action.payload
                }
            }
        },
        setAddOn: (state, action: PayloadAction<AddOn>) => {
            state.data.addOns?.push(action.payload);
            return state;
        },
        resetAddOns: (state) => {
            return {
                ...state,
                data: {
                    ...state.data,
                    addOns: []
                }
            }
        },
        calculatePrice: (state) => {
            const addOnPrice = state.data.addOns.reduce(
                (accumulator: number, addOn: AddOn) => accumulator + parseInt(addOn.cost),
                0,
            )

            const days = state.data.endDate!.diff(state.data.startDate, "day");

            const totalPrice = parseInt(state.data.activeRoom!.costPerNight) * days + addOnPrice;

            return {
                ...state,
                data: {
                    ...state.data,
                    bookingPrice: 0.18 * totalPrice + totalPrice
                }
            }
        },
        setStartDate: (state, action: PayloadAction<dayjs.Dayjs>) => {
            return {
                ...state,
                data: {
                    ...state.data,
                    startDate: action.payload
                }
            }
        },
        setEndDate: (state, action: PayloadAction<dayjs.Dayjs>) => {
            return {
                ...state,
                data: {
                    ...state.data,
                    endDate: action.payload
                }
            }
        },
        setBookStatus: (state, action: PayloadAction<boolean>) => {
            return {
                ...state,
                bookingStatus: action.payload
            }
        }
    },
})

export const { setActiveRoom, setAddOn, resetAddOns, calculatePrice, setStartDate, setEndDate } = userSlice.actions
export default userSlice.reducer;