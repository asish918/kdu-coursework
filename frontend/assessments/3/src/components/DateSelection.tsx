import styled from "styled-components"
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs from "dayjs";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store/store";
import { calculatePrice, setEndDate, setStartDate } from "../reducers/userSlice";
import { useState } from "react";
import { RoomType } from "../types";

const DateSelector = styled.div`
    display: flex;
    gap: 15px;
`

export default function DateSelection() {
    const userActiveRoom: RoomType | null = useSelector((state: RootState) => state.user.data.activeRoom);
    const startDate: dayjs.Dayjs | null = useSelector((state: RootState) => state.user.data.startDate);
    const endDate: dayjs.Dayjs | null = useSelector((state: RootState) => state.user.data.endDate);

    const [currentStartDate, setCurrentStartDate] = useState<dayjs.Dayjs>(dayjs());
    const [currentEndDate, setCurrentEndDate] = useState<dayjs.Dayjs>(dayjs());

    const dispatch = useDispatch();

    function dateValidator(date1: dayjs.Dayjs, date2: dayjs.Dayjs): boolean {
        return date1.isBefore(date2);
    }

    function handleStartChange(value: dayjs.Dayjs | null) {
        if (value && dateValidator(value, endDate!) && userActiveRoom) {
            setCurrentStartDate(value);
            dispatch(setStartDate(value));
            dispatch(calculatePrice());
            return;
        }

        setCurrentStartDate(dayjs());
    }

    function handleEndChange(value: dayjs.Dayjs | null) {
        if (value && dateValidator(startDate!, value) && userActiveRoom) {
            setCurrentEndDate(value);
            dispatch(setEndDate(value));
            dispatch(calculatePrice());
            return;
        }

        setCurrentEndDate(dayjs());
    }

    return (
        <DateSelector>
            <DatePicker onChange={handleStartChange} value={currentStartDate} defaultValue={startDate} label="Start Date" />
            <DatePicker onChange={handleEndChange} value={currentEndDate} defaultValue={endDate} label="End Date" />
        </DateSelector>
    )
}