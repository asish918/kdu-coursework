export type Task = {
    id: number;
    name: string;
    done: boolean;
}

export type IAppContext = {
    searchState: string;
    setSearchState: React.Dispatch<React.SetStateAction<string>>;
}