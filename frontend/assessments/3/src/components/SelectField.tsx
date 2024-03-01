import styled from "styled-components"
import BoxSelection from "./BoxSelection"
import DateSelection from "./DateSelection"
import { useSelector } from "react-redux"
import { RootState } from "../store/store"
import dayjs from "dayjs"

const AppField = styled.div`
    display: flex;
    flex-direction: column;
    gap: 50px;
`

const FieldWrapper = styled.div`
    display: flex;
    flex-direction: column;
`

const FieldHeader = styled.div`
    background-color: ${props => props.theme.colors.primaryColor};
    color: white;
    height: 70px;
    display: flex;
    align-items: center;
    padding: 20px;
    margin-bottom: 20px;
`

const Price = styled.div`
    font-size: 1.5rem;
`

const SubmitButton = styled.button`
    background-color: ${props => props.theme.colors.primaryColor};
    border-radius: 5px;
    width: 150px;
    color: white;
    padding-block: 20px;
    margin-bottom: 10px;
    cursor: pointer;
    
    &:disabled {
        background-color: ${props => props.theme.colors.primaryFaded};
        cursor: default;
    }

    @media (max-width: 400px) {
        width: 100%;
    }

`

export default function SelectField() {
    const userPrice = useSelector((state: RootState) => state.user.data.bookingPrice)
    const userData = useSelector((state: RootState) => state.user.data)
    const startDate: dayjs.Dayjs | null = useSelector((state: RootState) => state.user.data.startDate)
    const endDate: dayjs.Dayjs | null = useSelector((state: RootState) => state.user.data.endDate)

    const bookable = (): boolean => {
        if (userPrice > 0 && endDate!.isAfter(startDate)) return true;
        return false;
    }

    function submitHandler() {
        console.log(userData)
        alert('Congrats!! Booking was successful. Total Amount - ' + userPrice)
    }

    return (
        <AppField>
            <FieldWrapper>
                <FieldHeader>
                    Select Room Type
                </FieldHeader>

                <BoxSelection type="room-type" />
            </FieldWrapper>

            <FieldWrapper>
                <FieldHeader>
                    Select Date
                </FieldHeader>

                <DateSelection />
            </FieldWrapper>

            <FieldWrapper>
                <FieldHeader>
                    Select Additional add ons/preferences
                </FieldHeader>

                <BoxSelection type="add-ons" />
            </FieldWrapper>

            <Price>
                Total Booking Cost + 18%GST = {userPrice}
            </Price>

            <SubmitButton onClick={submitHandler} disabled={!bookable()}>
                Submit
            </SubmitButton>
        </AppField>
    )
}