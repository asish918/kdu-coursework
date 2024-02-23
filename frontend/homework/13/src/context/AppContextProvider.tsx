import { createContext, useState } from "react";
import { IAppContext, Product } from "../apptypes";

export const AppContext = createContext<IAppContext>({
    items: [],
    setItems: () => { }
});

export default function AppContextProvider({ children }: React.PropsWithChildren) {
    const [items, setItems] = useState<Product[]>([]);

    return (
        <AppContext.Provider value={{ items, setItems }}>
            {children}
        </AppContext.Provider>
    )
}