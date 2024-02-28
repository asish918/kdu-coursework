import { Link } from "react-router-dom"
import { useContext, useEffect, useRef } from "react"
import { AppContext } from "../context/AppContextProvider"

import '../sass/pages/Homepage.scss'
import { useSelector } from "react-redux";
import { RootState } from "../store/store";

import Spinner from "../components/spinner/Spinner";
import toast, { Toaster } from 'react-hot-toast';

export default function HomePage() {
    const snackbarRef = useRef(false);
    const { items } = useContext(AppContext);
    const { message, status } = useSelector((state: RootState) => state.snackbar)
    const productLoader = useSelector((state: RootState) => state.product.status)

    useEffect(() => {
        if (!snackbarRef.current) {
            if (status === "success") {
                toast.success(message, {
                    position: "top-center"
                })
            }

            if (status === "error") {
                toast.error(message, {
                    position: "top-center"
                })
            }
        }

    }, [status]);

    return (
        <section className="homepage">
            <Toaster />
            <h1 className="homepage__heading"><span>KDU</span> MARKETPLACE</h1>
            <div className="homepage__products">
                {
                    productLoader === "loading" &&
                    <Spinner />
                }
                {
                    items.length > 0 ?
                        items.map(product => (
                            <Link className="product" to={`/products/${product.id}`} key={product.id}>
                                <img className="product__img" src={product.image} alt={product.title} />
                                <h2 className="product__heading">{product.title}</h2>
                                <p className="product__price">$ {product.price}</p>
                                <p className="product__rating">{product.rating.rate}</p>
                            </Link>
                        ))
                        :
                        <div>
                            No Items
                        </div>
                }
            </div>
        </section>
    )
}