import styled, { css } from "styled-components"
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import { Stock, StockChangeType, Transaction, TransactionType, UserPortfolio } from "../app";
import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store/store";
import ScrollableDropdown from "../components/Dropdown";
import { useParams } from "react-router-dom";
import GridComponent from "../components/GridComponent";
import { changeCalculator, changeType, generateRandomPrice } from "../utils/stockPrice";
import { updateGraph } from "../reducers/graphSlice";
import { ArrowDownward, ArrowUpward } from "@mui/icons-material";
import { updatePrice } from "../reducers/stockSlice";
import { Socket, io } from "socket.io-client";
import moment from "moment-timezone";
import { validTransaction } from "../utils/userWallet";
import { addStock, addTransaction, removeStock } from "../reducers/userSlice";
import { toast, Toaster } from "react-hot-toast";

const StockInfoContainer = styled.section`
    margin-top: 25px;
    padding-inline: 20px;
    display: flex;
    gap: 15px;
    `

const StockInfoWrapper = styled.div`
        flex-basis: 75%;
        display: flex;
        flex-direction: column;
        gap: 30px;
    `

const StockInfoHeader = styled.div`
    display: flex;
    justify-content: space-between;
    gap: 10px;
    height: 70px;
    `


const StockLogo = styled.div`
    border: 1px solid ${props => props.theme.colors.yellowPrimary};
    text-align: center;
    color: ${props => props.theme.colors.yellowPrimary};
    background-color: ${props => props.theme.colors.yellowSecondary};
    padding: 5px;
`

const StockName = styled.div`
    display: flex;
    gap: 30px;
    align-items: center;
    border: 1px solid black;
    justify-content: center;
    font-size: 1.5rem;
    font-weight: normal;
    height: 100%;
    padding-inline: 10px;
`

const StockPrice = styled.div<{ $change: StockChangeType; }>`
    border: 1px solid black;
    width: 30%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 1.2rem;
    padding-inline: 10px;

    h1 {
        color: ${props => props.$change === 'increase' ? props.theme.colors.greenPrimary : props.theme.colors.redPrimary};
    }

    p {
        align-self: flex-end;
        font-size: 1rem;
    }
`

const StockInput = styled.div`
    border: 1px solid black;
    flex-grow: 1;
    display: flex;
    align-items: center;
    justify-content: center;

    input {
        width: 100%;
        text-align: center;
    }
`

const StockActions = styled.div`
    display: flex;
    gap: 10px;
`

const actionProp = (props: { change: string; }) => {
    if (props.change as TransactionType === 'Buy') {
        return css`
      border: 1px solid ${props => props.theme.colors.greenPrimary};
      color: ${props => props.theme.colors.greenPrimary};
      background-color: ${props => props.theme.colors.greenSecondary};
    `;
    }
    else if (props.change as TransactionType === 'Sell') {
        return css`
      border: 1px solid ${props => props.theme.colors.redPrimary};
      color: ${props => props.theme.colors.redPrimary};
      background-color: ${props => props.theme.colors.redSecondary};
    `;

    }
};

const historyProp = (props: { type: string; }) => {
    if (props.type as TransactionType === 'Buy') {
        return css`
            color: ${props => props.theme.colors.greenPrimary};
        `
    }
    else if (props.type as TransactionType === 'Sell') {
        return css`
            color: ${props => props.theme.colors.redPrimary};
        `
    }
}

const StockAction = styled.div`
    display: flex;
    align-items: center;
    width: 50px;
    justify-content: center;
    cursor: pointer;
    ${actionProp}
    `

const StockHistoryWrapper = styled.div`
    flex-basis: 25%;
    display: flex;
    flex-direction: column;
    gap: 15px;
`

const StockHistory = styled.div`
    border: 1px solid black;
    padding: 10px;
    display: flex;
    flex-direction: column;
    gap: 10px;
    font-size: 1.2rem;
`

const StockHistoryItem = styled.div`
    display: flex;
    align-items: center;
    border: 1px solid black;
    border-radius: 10px;
    padding: 10px;
`

const HistoryItemInfo = styled.div`
    flex-grow: 1;

    h1 {
        font-size: 1.3rem;
    }
    
    p {
        font-size: 0.8rem;
    }
`

const HistoryItemType = styled.div`
    ${historyProp}
`

const StockTransactions = styled.div`
    border: 1px solid black;
    padding: 10px;
    display: flex;
    flex-direction: column;
    gap: 10px;
`

const StockTransaction = styled.div`
    h1 {
        font-size: 1.2rem;
    }

    p {
        font-size: 0.8rem;
    }
`

const StockGraphContainer = styled.div`
    position: relative;
`

const StockGraph = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
    height: 500px;
    max-width: 1020px;
    display: flex;
    align-items: flex-end;
`

const GraphBar = styled.div<{ $value: string; $type: StockChangeType; }>`
    height: ${props => props.$value};
    border: 1px solid ${props => props.$type === "increase" ? props.theme.colors.greenPrimary : props.theme.colors.redPrimary};
    background-color: ${props => props.$type === "increase" ? props.theme.colors.greenSecondary : props.theme.colors.redSecondary};
    width: 34px;
`

export function StockInfo() {
    const [socket, setSocket] = useState<Socket>(null);

    const stocks: Stock[] = useSelector((state: RootState) => state.stocks.stocks);
    const { stockSymbol } = useParams();
    const stockInfo = stocks.filter(stock => stock.stock_symbol === stockSymbol)[0];
    const [activePrice, setActivePrice] = useState<{ value: number, change: StockChangeType, percentage: number }>({ value: stockInfo?.base_price, change: "increase", percentage: 0 })
    const bars = useSelector((state: RootState) => state.graph.graph);
    const userBalance = useSelector((state: RootState) => state.user.walletBalance)
    const userStocks: UserPortfolio[] = useSelector((state: RootState) => state.user.stocks)
    const dispatch = useDispatch();

    const stockInputRef = useRef<HTMLInputElement>(null);

    const [personalHistory, setPersonalHistory] = useState<Transaction[]>([]);
    const [roomHistory, setRoomHistory] = useState<Transaction[]>([]);

    useEffect(() => {
        const socket = io('http://localhost:3000');
        setSocket(socket);
        setRoomHistory([]);

        socket.on('connect', () => {
            console.log('Connected to Socket.IO server');
        });

        socket.emit('joinSocketRoom', stockSymbol)

        socket.on('newTransaction', (payload: Transaction) => {
            setRoomHistory((prev => [...prev, payload]));
        });

        return () => {
            socket.disconnect();
        };
    }, [stockSymbol]);


    useEffect(() => {
        console.log(roomHistory)
    }, [roomHistory])


    useEffect(() => {
        const stock = stocks.filter(stock => stock.stock_symbol === stockSymbol)[0];

        const intervalId = setInterval(() => {
            const newStockPrice = generateRandomPrice();
            const currentPrice = bars.length ? bars[bars.length - 1].value : stock?.base_price;
            const type = changeType(currentPrice, newStockPrice);

            dispatch(updateGraph({
                change: type,
                value: newStockPrice
            }));

            dispatch(updatePrice({
                stock_symbol: stock.stock_symbol,
                price: newStockPrice
            }))

            setActivePrice({
                value: newStockPrice,
                change: type,
                percentage: parseInt(changeCalculator(currentPrice, newStockPrice).toFixed(2))
            })

        }, 5000);

        return () => clearInterval(intervalId);
    }, [stocks]);

    function buyStock() {
        if (!stockInputRef.current?.value) return;

        const newTransaction: Transaction = {
            status: "Passed",
            stock_name: stockInfo.stock_name,
            stock_symbol: stockInfo.stock_symbol,
            timestamp: new Date().toISOString(),
            transaction_price: stockInputRef.current?.value * activePrice.value,
            quantity: stockInputRef.current?.value,
            type: 'Buy'
        }

        if (!validTransaction(userBalance, newTransaction, userStocks)) {
            newTransaction.status = 'Failed'
            socket.emit('transaction', newTransaction)
            dispatch(addTransaction(newTransaction));
            toast.error("Transaction Unsuccessful")
            return;
        }

        setPersonalHistory([...personalHistory, newTransaction])
        dispatch(addTransaction(newTransaction));
        dispatch(addStock({ quantity: newTransaction.quantity, stock_symbol: newTransaction.stock_symbol }))
        socket.emit('transaction', newTransaction)
        toast.success("Transaction Successful")
    }

    function sellStock() {
        if (!stockInputRef.current?.value) return;

        const newTransaction: Transaction = {
            status: "Passed",
            stock_name: stockInfo.stock_name,
            stock_symbol: stockInfo.stock_symbol,
            timestamp: new Date().toISOString(),
            transaction_price: stockInputRef.current?.value * activePrice.value,
            quantity: stockInputRef.current?.value,
            type: 'Sell'
        }

        if (!validTransaction(userBalance, newTransaction, userStocks)) {
            newTransaction.status = 'Failed'
            socket.emit('transaction', newTransaction)
            dispatch(addTransaction(newTransaction));
            toast.error("Transaction Unsuccessful")
            return;
        }

        setPersonalHistory([...personalHistory, newTransaction])
        dispatch(removeStock({ quantity: newTransaction.quantity, stock_symbol: newTransaction.stock_symbol }))
        dispatch(addTransaction(newTransaction));
        socket.emit('transaction', newTransaction)
        toast.success("Transaction Successful")
    }

    return (
        <StockInfoContainer>
            <Toaster />
            <StockInfoWrapper>
                <StockInfoHeader>
                    <ScrollableDropdown stocks={stocks}>
                        <StockName>
                            <StockLogo>
                                {stockSymbol}
                            </StockLogo>

                            {stockInfo?.stock_name}
                            <KeyboardArrowDownIcon />
                        </StockName>
                    </ScrollableDropdown>

                    <StockPrice $change={activePrice.change}>
                        Price

                        <h1>₹{bars.length === 0 ? stockInfo?.base_price : activePrice.value}  {activePrice.change === 'increase' ? <ArrowUpward /> : <ArrowDownward />}</h1>
                        <p>{activePrice.percentage} %</p>
                    </StockPrice>

                    <StockInput>
                        <input ref={stockInputRef} type="text" placeholder="Enter QTY" />
                    </StockInput>

                    <StockActions>
                        <StockAction onClick={buyStock} change='Buy'>Buy</StockAction>
                        <StockAction onClick={sellStock} change="Sell">Sell</StockAction>
                    </StockActions>
                </StockInfoHeader>

                <StockGraphContainer>
                    <GridComponent />
                    <StockGraph>
                        {bars.map((bar, index) => (
                            <GraphBar key={index} $value={`${bar.value}px`} $type={bar.change} />
                        ))}
                    </StockGraph>
                </StockGraphContainer>
            </StockInfoWrapper>

            <StockHistoryWrapper>
                <StockHistory>
                    History
                    {personalHistory?.map(item => (
                        <StockHistoryItem>
                            <HistoryItemInfo>
                                <h1>{item.quantity} stocks</h1>
                                <p>{new Date(item.timestamp).toUTCString()}</p>
                            </HistoryItemInfo>

                            <HistoryItemType type={`${item.type}`}>
                                {item.type?.charAt(0).toUpperCase() + item.type?.slice(1)}
                            </HistoryItemType>
                        </StockHistoryItem>
                    ))}
                </StockHistory>

                <StockTransactions>
                    {
                        roomHistory?.map(item => (
                            <StockTransaction>
                                <h1>{item.name} {item.type === 'Buy' ? "bought" : "sold"} {item.quantity} {item.stock_name}</h1>
                                <p>{moment(item.timestamp).tz('Asia / Kolkata').format("h:mm A")}</p>
                            </StockTransaction>
                        ))
                    }
                </StockTransactions>
            </StockHistoryWrapper>
        </StockInfoContainer >
    )
}