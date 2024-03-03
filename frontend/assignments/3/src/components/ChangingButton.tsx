import { useDispatch } from "react-redux";
import styled from "styled-components"
import { addWishList, removeWishList } from "../reducers/stockSlice";
import { useState } from "react";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';

const WishlistItem = styled.div`
    cursor: pointer;
`

interface ChangingButtonProps {
    index: number,
    wishlist: boolean,
    page: number
}

export function ChangingButton({ index, wishlist, page }: Readonly<ChangingButtonProps>) {
    const dispatch = useDispatch();
    const [wishlistChild, setWishlistChild] = useState<React.ReactNode>(<CheckCircleIcon color='primary' />)


    function removeWishlistState(index: number) {
        if (!page) {
            dispatch(removeWishList({ page, index }));
            return;
        }
        dispatch(removeWishList({ page: page - 1, index }));
    }

    function addWishlistState(index: number) {
        if (!page) {
            dispatch(addWishList({ page, index }));
            return;
        }

        dispatch(addWishList({ page: page - 1, index }));
    }

    function handleMouseEnter() {
        setWishlistChild(<CancelIcon color='error' />)
    }

    function handleMouseLeave() {
        setWishlistChild(<CheckCircleIcon color='primary' />)
    }

    return (
        <>
            {
                wishlist ?
                    <WishlistItem onClick={() => removeWishlistState(index)} onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
                        {wishlistChild}
                    </WishlistItem> :
                    <WishlistItem onClick={() => addWishlistState(index)}>
                        <AddCircleOutlineIcon color='primary' />
                    </WishlistItem>
            }
        </>
    )
} 