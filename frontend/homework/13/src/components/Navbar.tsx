import React, { useContext, useEffect, useRef, useState } from "react";
import { FaSearch } from "react-icons/fa";
import { AppContext } from "../context/AppContextProvider";
import { Product } from "../apptypes";
import { useParams, useSearchParams } from "react-router-dom";

interface NavbarProps {
    data: Product[]
}

export default function Navbar({ data }: Readonly<NavbarProps>) {
    const navbarContainer: React.CSSProperties = {
        backgroundColor: "#2a2a72",
        display: "flex",
        alignItems: "center",
        paddingInline: "90px",
        paddingBlock: "10px",
        gap: "15px",
        marginBottom: "50px",
    }

    const navbarSearchContainer: React.CSSProperties = {
        flexGrow: "1"
    }

    const navbarActionItems: React.CSSProperties = {
        display: "flex",
        color: "white"
    }

    const navbarSearchInput: React.CSSProperties = {
        height: "30px"
    }

    const navbarSearchButton: React.CSSProperties = {
        height: "30px",
        width: "30px"
    }

    const [filterState, setFilterState] = useState("");
    const [sortState, setSortState] = useState("none");
    const inputRef = useRef<HTMLInputElement>(null);
    const { productId } = useParams();
    const [searchParams] = useSearchParams();

    const { items, setItems } = useContext(AppContext);

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
            setItems(data);
            return;
        }

        setItems(data.filter(item => item.category === filterState))
    }, [filterState])

    useEffect(() => {
        if (sortState === "none") {
            return;
        }

        if (filterState === "none" && inputRef.current?.value.length === 0) {
            if (sortState === "asc") {
                const updatedItems = [...data].sort(function (a, b) { return a.price - b.price });
                console.log(updatedItems)
                setItems(updatedItems);
            } else if (sortState === "desc") {
                const updatedItems = [...data].sort(function (a, b) { return b.price - a.price });
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

        setItems(data.filter(item => {
            const lowerCaseItem = item.title.toLowerCase();
            return lowerCaseItem.includes(inputRef.current!.value!.toLowerCase());
        }));
    }

    return (
        <nav style={navbarContainer}>
            <div style={navbarSearchContainer}>
                <input style={navbarSearchInput} ref={inputRef} type="text" placeholder="Search" />
                <button style={navbarSearchButton} onClick={handleSearch}><FaSearch /></button>
            </div>

            {
                !productId &&
                <div style={navbarActionItems}>
                    <p>Filter: </p>

                    <select name="filter" id="filter" defaultValue={"none"} onChange={handleFilterChange}>
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
                <div style={navbarActionItems}>
                    <p>Sort: </p>

                    <select name="sort" id="sort" defaultValue={"none"} onChange={handleSortChange}>
                        <option value="asc">Price(ASC)</option>
                        <option value="desc">Price(DESC)</option>
                        <option value="none">None</option>
                    </select>
                </div>
            }
        </nav>
    )
}