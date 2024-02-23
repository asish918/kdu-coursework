import { Link, useOutletContext } from "react-router-dom"
import { Product } from "../apptypes"

export default function HomePage() {
    const mainSection: React.CSSProperties = {
        maxWidth: "900px",
        marginInline: "auto",
    }

    const mainTitle: React.CSSProperties = {
        textAlign: "center",
        fontSize: "2.5rem",
        color: "#2a2a72",
        letterSpacing: "2px",
        marginBottom: "20px"
    }

    const productContainer: React.CSSProperties = {
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "flex-start",
        gap: "30px"
    }

    const cardContainer: React.CSSProperties = {
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-between",
        padding: "20px",
        // border: "1px solid black",
        backgroundColor: "white",
        borderRadius: "5px",
        position: "relative",
        width: "30%",
        textDecoration: "none",
        color: "black"
    }

    const cardImage: React.CSSProperties = {
        height: "200px",
        objectFit: "cover",
        width: "100%",
        marginBottom: "10px"
    }

    const cardTitle: React.CSSProperties = {
        maxWidth: "12ch",
        // overflow: "hidden",
        // whiteSpace: "nowrap",
        textOverflow: "ellipsis"
    }

    const cardPrice: React.CSSProperties = {

    }

    const cardRating: React.CSSProperties = {
        position: "absolute",
        top: "-10px",
        right: "-10px",
        backgroundColor: "black",
        borderRadius: "50%",
        width: "25px",
        height: "25px",
        color: "white",
        display: "flex",
        justifyContent: "center",
        alignItems: "center"
    }

    const data: Product[] = useOutletContext();

    return (
        <section style={mainSection}>
            <h1 style={mainTitle}>KDU MARKETPLACE</h1>
            <div style={productContainer}>
                {
                    data.map(product => (
                        <Link to={`/products/${product.id}`} key={product.id} style={cardContainer}>
                            <img style={cardImage} src={product.image} alt={product.title} />
                            <h2 style={cardTitle}>{product.title}</h2>
                            <p style={cardPrice}>$ {product.price}</p>
                            <p style={cardRating}>{product.rating.rate}</p>
                        </Link>
                    ))
                }
            </div>
        </section>
    )
}