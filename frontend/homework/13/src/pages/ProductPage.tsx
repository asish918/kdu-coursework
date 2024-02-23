import axios from "axios"
import { Product } from "../apptypes";
import { useLoaderData } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export async function productLoader({ params }): Promise<{ data: Product }> {
    const res = await axios.get(`https://fakestoreapi.com/products/${params.productId}`);
    return { data: res.data };
}


export default function ProductPage() {
    const { data }: { data: Product } = useLoaderData();
    console.log(data);

    const productTitle: React.CSSProperties = {
        textAlign: "center",
        fontSize: "3rem",
        marginBottom: "30px"
    }

    const productContainer: React.CSSProperties = {
        display: "flex",
        maxWidth: "900px",
        marginInline: "auto",
        justifyContent: "space-around",
        gap: "20%"
    }

    const productImage: React.CSSProperties = {
        height: "60vh",
        // flexGrow: "1"
        // width: "70vw"
    }

    const productDetails: React.CSSProperties = {
        // flexGrow: "1"
        display: "flex",
        flexDirection: "column",
        gap: "30px"
    }

    const productCategory: React.CSSProperties = {
        color: "gray"
    }

    const productPrice: React.CSSProperties = {

    }

    const productDescriptionHeading: React.CSSProperties = {
        fontWeight: "bold",
        marginBottom: "-20px"
    }

    const productDescriptionContent: React.CSSProperties = {
    }

    const backButton: React.CSSProperties = {
        border: "1px solid #2996e1",
        borderRadius: "5px",
        color: "#2996e1",
        backgroundColor: "transparent",
        width: "190px",
        height: "50px",
        fontSize: "1.3rem",
        fontWeight: "bold",
        cursor: "pointer"
    }

    const navigate = useNavigate();

    return (
        <section>
            <h1 style={productTitle}>{data.title}</h1>

            <div style={productContainer}>
                <img style={productImage} src={data.image} alt={data.title} />

                <div style={productDetails}>
                    <h2 style={productCategory}>Category: {data.category}</h2>
                    <h3 style={productPrice}>Price: {data.price}</h3>
                    <p style={productDescriptionHeading}>Product Description: </p>
                    <p style={productDescriptionContent}>{data.description}</p>
                    <button onClick={() => navigate(-1)} style={backButton}>Back to products</button>
                </div>
            </div>
        </section>
    )
}