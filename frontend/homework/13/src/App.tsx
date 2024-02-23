import { Outlet, useLoaderData } from "react-router-dom";
import Navbar from "./components/Navbar";
import axios from "axios";
import { Product } from "./apptypes";
import { useContext, useEffect } from "react";
import { AppContext } from "./context/AppContextProvider";

export async function appLoader(): Promise<{ data: Product[] }> {
  const res = await axios.get("https://fakestoreapi.com/products");
  return { data: res.data };
}

export default function App() {
  const { data }: { data: Product[] } = useLoaderData();
  console.log(data);

  const { items, setItems } = useContext(AppContext);

  useEffect(() => {
    setItems(data);
  }, [])

  return (
    <>
      <Navbar />
      <Outlet context={items} />
    </>
  )
}