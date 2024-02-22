import { createContext, useState } from "react"
import { IAppContext, Task } from "../types";

export const AppContext = createContext<IAppContext>({
    tasks: [],
    searchState: "",
    searchResults: [],
    setSearchResults: () => { },
    setSearchState: () => { },
    setTasks: () => { }
});

export default function AppContextProvider({ children }: Readonly<React.PropsWithChildren>) {

    const [searchState, setSearchState] = useState('');
    const [searchResults, setSearchResults] = useState<Task[]>([]);
    const [tasks, setTasks] = useState<Task[]>([]);

    return (
        <AppContext.Provider value={{ tasks, searchState, searchResults, setSearchResults, setSearchState, setTasks }}>
            {children}
        </AppContext.Provider>
    )
}
