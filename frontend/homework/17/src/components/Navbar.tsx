import React, { useContext, useEffect, useRef, useState } from "react";
import { FaSearch } from "react-icons/fa";
import { AppContext } from "../context/AppContextProvider";
import { Product } from "../apptypes";
import { useParams, useSearchParams } from "react-router-dom";

import './Navbar.scss'
import { RootState } from "../store/store";
import { useSelector } from "react-redux";

export default function Navbar() {
    const [filterState, setFilterState] = useState("");
    const [sortState, setSortState] = useState("none");
    const inputRef = useRef<HTMLInputElement>(null);
    const { productId } = useParams();
    const [searchParams] = useSearchParams();

    const { items, setItems } = useContext(AppContext);
    const products = useSelector((state: RootState) => state.product.products)

    useEffect(() => {
        if (searchParams.get("filter") === "electronics" || searchParams.get("filter") === "jewelery") {
            console.log(searchParams.get("filter"))
            setFilterState(searchParams.get("filter")!);
            return;
        }

        console.log(searchParams.get("filter"))
        return;
    }, [])

    useEffect(() => {
        if (filterState === "" || filterState === "none") {
            setItems(products);
            return;
        }

        setItems(products.filter(item => item.category === filterState))
    }, [filterState])

    useEffect(() => {
        if (sortState === "none") {
            return;
        }

        if (filterState === "none" && inputRef.current?.value.length === 0) {
            if (sortState === "asc") {
                const updatedItems = [...products].sort(function (a, b) { return a.price - b.price });
                console.log(updatedItems)
                setItems(updatedItems);
            } else if (sortState === "desc") {
                const updatedItems = [...products].sort(function (a, b) { return b.price - a.price });
                console.log(updatedItems)
                setItems(updatedItems);
            }
        } else {
            if (sortState === "asc") {
                const updatedItems = [...items].sort(function (a, b) { return a.price - b.price });
                console.log(updatedItems)
                setItems(updatedItems);
            } else if (sortState === "desc") {
                const updatedItems = [...items].sort(function (a, b) { return b.price - a.price });
                console.log(updatedItems)
                setItems(updatedItems);
            }

        }
    }, [sortState])

    const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setFilterState(event.target.value);
    }

    const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSortState(event.target.value);
    }

    const handleSearch = () => {
        if (inputRef.current?.value.length === 0) return;

        setItems(products.filter(item => {
            const lowerCaseItem = item.title.toLowerCase();
            return lowerCaseItem.includes(inputRef.current!.value!.toLowerCase());
        }));
    }

    return (
        <nav className="navbar">
            <div className="navbar__input">
                <input disabled={!products.length} ref={inputRef} type="text" placeholder="Search" />
                <button onClick={handleSearch}><FaSearch /></button>
            </div>

            {
                !productId &&
                <div className="navbar__action">
                    <p>Filter: </p>

                    <select disabled={!products.length} className="navbar__select" name="filter" id="filter" defaultValue={"none"} onChange={handleFilterChange}>
                        <option value="men's clothing">Men's Clothing</option>
                        <option value="women's clothing">Women's Clothing</option>
                        <option value="jewelery">Jewellery</option>
                        <option value="electronics">Electronics</option>
                        <option value="none">None</option>
                    </select>
                </div>
            }
            {
                !productId &&
                <div className="navbar__action">
                    <p>Sort: </p>

                    <select disabled={!products.length} className="navbar__select" name="sort" id="sort" defaultValue={"none"} onChange={handleSortChange}>
                        <option value="asc">Price(ASC)</option>
                        <option value="desc">Price(DESC)</option>
                        <option value="none">None</option>
                    </select>
                </div>
            }
        </nav>
    )
}