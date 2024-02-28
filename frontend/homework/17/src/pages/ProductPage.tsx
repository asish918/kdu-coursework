import axios from "axios"
import { Product } from "../apptypes";
import { useLoaderData, useNavigate } from "react-router-dom";

import './../sass/pages/ProductPage.scss'

export async function productLoader({ params }: { params: string }): Promise<{ data: Product }> {
    const res = await axios.get(`https://fakestoreapi.com/products/${params.productId}`);
    return { data: res.data };
}


export default function ProductPage() {
    const { data }: { data: Product } = useLoaderData() as { data: Product };
    console.log(data);

    const navigate = useNavigate();

    return (
        <section className="productpage">
            <h1 className="productpage__heading">{data.title}</h1>

            <div className="element">
                <img className="element__img" src={data.image} alt={data.title} />

                <div className="element__details">
                    <h2 className="element__category">Category: {data.category}</h2>
                    <h3 className="element__price">Price: ${data.price}</h3>
                    <p className="element__desc-heading">Product Description: </p>
                    <p className="element__desc">{data.description}</p>
                    <button className="back-button" onClick={() => navigate(-1)}>Back to products</button>
                </div>
            </div>
        </section>
    )
}