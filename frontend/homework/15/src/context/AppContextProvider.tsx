import { createContext, useState } from "react"
import { IAppContext } from "../types";

export const AppContext = createContext<IAppContext>({
    searchState: "",
    setSearchState: () => { },
});

export default function AppContextProvider({ children }: Readonly<React.PropsWithChildren>) {

    const [searchState, setSearchState] = useState('');

    return (
        <AppContext.Provider value={{ searchState, setSearchState }}>
            {children}
        </AppContext.Provider>
    )
}
