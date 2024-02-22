export type Task = {
    id: number;
    name: string;
}

export type IAppContext = {
    tasks: Task[];
    searchResults: Task[];
    searchState: string;
    setSearchState: React.Dispatch<React.SetStateAction<string>>;
    setSearchResults: React.Dispatch<React.SetStateAction<Task[]>>;
    setTasks: React.Dispatch<React.SetStateAction<Task[]>>;
}