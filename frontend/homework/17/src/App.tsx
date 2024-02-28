import { Outlet, useLoaderData } from "react-router-dom";
import Navbar from "./components/Navbar";
import { Product } from "./apptypes";
import { useContext, useEffect } from "react";
import { AppContext } from "./context/AppContextProvider";

import './App.scss'
import { RootState, store } from "./store/store";
import { getProducts } from "./reducers/thunks/getProducts";
import { useSelector } from "react-redux";

import './App.scss'

export async function appLoader() {
  store.dispatch(getProducts());
  return null;
}

export default function App() {
  const products = useSelector((state: RootState) => state.product.products)

  const { setItems } = useContext(AppContext);

  useEffect(() => {
    setItems(products);
  }, [products])

  return (
    <>
      <Navbar />
      <Outlet />
    </>
  )
}