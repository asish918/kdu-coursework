import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { Stock } from '../app';
import { Pagination, Stack } from '@mui/material';
import { useState } from 'react';
import { paginate } from '../reducers/stockSlice';
import { useNavigate } from 'react-router-dom';
import { resetGraph } from '../reducers/graphSlice';
import { ChangingButton } from './ChangingButton';


const CustomTableContainer = styled(TableContainer)`
    border: 2px solid black;
    border-radius: 10px;
    max-width: 700px;
    margin: 40px auto;
`

const CustomTableHead = styled(TableHead)`
    border-bottom: 2px solid black;    
`

export default function StocksTable() {
    const paginatedStocks: Stock[] = useSelector((state: RootState) => state.stocks.page);
    const [page, setPage] = useState<number>(null);
    const dispatch = useDispatch();
    const navigate = useNavigate();


    function handleChange(event: React.ChangeEvent<unknown>, page: number) {
        dispatch(paginate(page - 1));
        setPage(page);
    }

    return (
        <CustomTableContainer>
            <Table sx={{ maxWidth: 700 }} aria-label="simple table">
                <CustomTableHead>
                    <TableRow>
                        <TableCell>Company</TableCell>
                        <TableCell align="center">Base Price</TableCell>
                        <TableCell align="center">Watchlist</TableCell>
                    </TableRow>
                </CustomTableHead>
                <TableBody>
                    {paginatedStocks?.map((row, index) => (
                        <TableRow
                            key={row.stock_name}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 }, cursor: "pointer" }}
                        >
                            <TableCell onClick={() => {
                                navigate(`/stock-info/${row.stock_symbol}`)
                                dispatch(resetGraph());
                            }} component="th" scope="row">
                                {row.stock_name}
                            </TableCell>
                            <TableCell align="center">₹{row.base_price}</TableCell>
                            <TableCell align="center">
                                <ChangingButton index={index} wishlist={row?.wishlist} page={page} />
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>

            <Stack sx={{ marginBlock: 2 }} alignItems={'center'} spacing={10}>
                <Pagination onChange={handleChange} sx={{ fontWeight: 900 }} count={10} color="primary" />
            </Stack>
        </CustomTableContainer>
    );
}
