export type Product = {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: Rating;
}

export type Rating = {
    rate: number;
    count: number;
}

export type IAppContext = {
    items: Product[],
    setItems: React.Dispatch<React.SetStateAction<Product[]>>
}

export type APIState = "success" | "error" | "loading" | null;