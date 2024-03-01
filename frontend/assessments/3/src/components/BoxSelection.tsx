import styled from "styled-components"
import { AddOn, Field, RoomType, UserData } from "../types";
import { AppDispatch, RootState } from "../store/store";
import { useDispatch, useSelector } from "react-redux";
import { calculatePrice, resetAddOns, setActiveRoom, setAddOn } from "../reducers/userSlice";

const SelectContainer = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 30px;

    @media (max-width: 504px) {
        flex-direction: column;
    }
`

const Selection = styled.div<{ $selected?: boolean; }> `
    border: 1px solid ${props => props.$selected ? props.theme.colors.primaryColor : "black"};
    color: ${props => props.$selected ? props.theme.colors.primaryColor : "black"};
    padding-inline: 80px;
    padding-block: 20px;

    &:hover {
        cursor: pointer;
    }

    @media (max-width: 504px) {
        text-align: center;
    }
`

interface BoxSelectionProps {
    type: Field
}

export default function BoxSelection({ type }: BoxSelectionProps) {
    const dispatch: AppDispatch = useDispatch();
    const roomData: RoomType[] = useSelector((state: RootState) => state.rooms.rooms)
    const userData: UserData = useSelector((state: RootState) => state.user.data);

    function selectRoom(id: number) {
        dispatch(setActiveRoom(roomData[id - 1]))
        dispatch(resetAddOns());
        dispatch(calculatePrice());
    }

    function selectAddOn(addOn: AddOn) {
        dispatch(setAddOn(addOn));
        dispatch(calculatePrice());
    }

    return (
        <>
            {
                type === "room-type" &&
                <SelectContainer>
                    {roomData.map((room =>
                        <Selection onClick={() => selectRoom(room.id)} $selected={userData.activeRoom?.id === room.id}>
                            {room.name}
                        </Selection>
                    ))}
                </SelectContainer>
            }

            {
                type === "add-ons" && userData.activeRoom ?
                    <SelectContainer>
                        {roomData[userData.activeRoom?.id - 1].addOns.map(addOn => (
                            <Selection onClick={() => selectAddOn(addOn)} $selected={userData.addOns?.includes(addOn)}>
                                {addOn.name}
                            </Selection>
                        ))}
                    </SelectContainer> :
                    type === "add-ons" &&
                    <SelectContainer>
                        No Room Selected
                    </SelectContainer>
            }
        </>
    )
}