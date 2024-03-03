import { Checkbox, FormControlLabel, FormGroup } from "@mui/material"
import { DatePicker } from "@mui/x-date-pickers"
import SearchIcon from '@mui/icons-material/Search';
import styled from "styled-components"
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootDispatch, RootState } from "../store/store";
import { findStocks } from "../reducers/userSlice";
import { Transaction } from "../app";
import moment from 'moment-timezone';
import { GroupedTransaction, groupSortAndSortNewestFirst } from "../utils/filterAction";
import dayjs from "dayjs";

const PortfolioContainer = styled.div`
    max-width: 1200px;
    margin: 30px auto;
    display: flex;
    gap: 40px;

    @media (max-width: 560px) {
        flex-wrap: wrap;
        padding-inline: 20px;
    }
`

const FilterWrapper = styled.div`
    flex-basis: 30%;
    border: 1px solid  ${props => props.theme.colors.greyPrimary};
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    background-color: ${props => props.theme.colors.greySecondary};
    max-height: 500px;
    position: sticky;
    top: 20px;
    
    @media (max-width: 560px) {
        position: inherit;
        flex-basis: 100%;
    }
    `

const FilterHeader = styled.div`
    font-size: 1.2rem;
    display: flex;
    justify-content: space-between;
    padding-inline: 25px;
    padding-block: 10px;
    align-items: center;
    border-bottom: 1px solid ${props => props.theme.colors.greyPrimary};

    button {
        color: ${props => props.theme.colors.primaryColor};
        font-size: 1.2rem;
        font-weight: 400;
        cursor: pointer;
    }
    `

const FilterInputContainer = styled.div`
    border-bottom: 1px solid ${props => props.theme.colors.greyPrimary};
    padding-inline: 10px;
    padding-block: 15px;
    `

const FilterInput = styled.div`
    display: flex;
    border: 1px solid ${props => props.theme.colors.greyPrimary};
    border-radius: 5px;
    align-items: center;
    justify-content: center;
    gap: 30px;
    color: ${props => props.theme.colors.greyPrimary};
`

const FilterDate = styled.div`
    border-bottom: 1px solid ${props => props.theme.colors.greyPrimary};
    display: flex;
    padding: 10px;
    gap: 10px;
`

const StyledDatePicker = styled(DatePicker)`
    .MuiOutlinedInput-root {
        background-color: ${props => props.theme.colors.greySecondary};
  }
`

const FilterCheckbox = styled.div`
    border-bottom: 1px solid ${props => props.theme.colors.greyPrimary};
    padding-inline: 10px;
    color: ${props => props.theme.colors.greyPrimary};
    
    &:last-child {
        border-bottom: none;
        overflow-y: scroll;

        ::-webkit-scrollbar {
             background-color: transparent;
        }

        ::-webkit-scrollbar-thumb {
             background: ${props => props.theme.colors.greyPrimary};
             border-radius: 10px;
        }
    }
`

const TransactionWrapper = styled.div`
    display: flex;
    flex-direction: column;
    flex-grow: 1;
`

const TransactionContainer = styled.div`
    display: flex;
    flex-direction: column;
`

const TransactionDate = styled.div`
    color: ${props => props.theme.colors.greyPrimary};
    border-bottom: 2px dotted ${props => props.theme.colors.greyPrimary};
    padding-bottom: 20px;
    
    width: 100%;
`

const TransactionItem = styled.div`
    margin-top: 20px;
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 1px solid black;
    /* justify-content: space-between; */

    h1 {
        flex-grow: 1;
        font-size: 1.2rem;
        word-wrap: break-word;
    }
    h1:nth-child(1) {
        width: 10%;
    }
    
    p {
        margin-left: auto;
        font-size: 1.2rem;
    }

    &:last-child {
        margin-bottom: 60px;
    }
`

const TransactionItemType = styled.div<{ $type: string; }>`
    width: 15px;
    height: 15px;
    border-radius: 50%;
    margin-left: 20px;
    background-color: ${props => props.$type === "Passed" ? props.theme.colors.greenPrimary : props.theme.colors.redPrimary};
`

export function Portfolio() {
    const dispatch: RootDispatch = useDispatch();
    const transactions: Transaction[] = useSelector((state: RootState) => state.user.allTransactions);
    const [groupedTransactions, setGroupedTransactions] = useState<GroupedTransaction[]>([])
    const transactedStocks: string[] = useSelector((state: RootState) => state.user.transactedStocks);

    const [startDate, setStartDate] = useState<dayjs.Dayjs>();
    const [endDate, setEndDate] = useState<dayjs.Dayjs>();

    const [checkboxValues, setCheckboxValues] = useState({
        Passed: false,
        Failed: false
    });

    const handleCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, checked } = event.target;
        setCheckboxValues({ ...checkboxValues, [name]: checked });
    };

    useEffect(() => {
        dispatch(findStocks());
    }, [])

    useEffect(() => {
        const newTransactions: GroupedTransaction[] = groupSortAndSortNewestFirst(transactions);
        setGroupedTransactions(newTransactions);
    }, [transactions])

    return (
        <PortfolioContainer>
            <FilterWrapper>
                <FilterHeader>
                    Filters
                    <button>Clear All</button>
                </FilterHeader>

                <FilterInputContainer>
                    <FilterInput>
                        <SearchIcon color="secondary" />
                        <input type="text" placeholder="Search for a Stock" />
                    </FilterInput>
                </FilterInputContainer>

                <FilterDate>
                    <StyledDatePicker value={startDate} onChange={(value) => setStartDate(value as dayjs.Dayjs)} label="Start Date" />
                    <StyledDatePicker value={endDate} onChange={(value) => setEndDate(value as dayjs.Dayjs)} label="End Date" />
                </FilterDate>

                <FilterCheckbox>
                    <FormGroup>
                        <FormControlLabel
                            control={<Checkbox checked={checkboxValues.Passed} onChange={handleCheckboxChange} name="Passed" />}
                            label="Passed"
                        />
                        <FormControlLabel
                            control={<Checkbox checked={checkboxValues.Failed} onChange={handleCheckboxChange} name="Failed" />}
                            label="Failed"
                        />
                    </FormGroup>
                </FilterCheckbox>

                <FilterCheckbox>
                    <FormGroup>
                        {transactedStocks.map(item => (
                            <FormControlLabel control={<Checkbox />} label={`${item}`} />
                        ))}
                    </FormGroup>
                </FilterCheckbox>
            </FilterWrapper>

            <TransactionWrapper>
                {groupedTransactions.map(item => (
                    <TransactionContainer>
                        <TransactionDate>
                            {moment(item.date, 'DD/MM/YYYY').format('DD MMM YYYY')}
                        </TransactionDate>

                        {item.transactions.map(item => (
                            <TransactionItem>
                                <h1>{item.stock_name}</h1>
                                <h1>{item.stock_symbol}</h1>
                                <h1>₹{item.transaction_price}</h1>
                                <p>{moment(item.timestamp).format("h:mm A")}</p>
                                <TransactionItemType $type={`${item.status}`} />
                            </TransactionItem>
                        ))}
                    </TransactionContainer>
                ))}
            </TransactionWrapper>
        </PortfolioContainer>
    )
}