import dayjs from "dayjs";

export type RoomType = {
    id: number;
    name: string;
    costPerNight: string;
    currency: string;
    addOns: AddOn[];
}

export type AddOn = {
    name: string;
    cost: string;
    currency: string;
}

export type APIState = "success" | "error" | "loading" | null;

export type UserData = {
    activeRoom: RoomType | null,
    startDate: dayjs.Dayjs | null,
    endDate: dayjs.Dayjs | null,
    addOns: AddOn[],
    bookingPrice: number
}

export type Field = "room-type" | "date-range" | "add-ons";