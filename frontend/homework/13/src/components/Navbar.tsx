// #2a2a72

import React from "react";
import { FaSearch } from "react-icons/fa";

export default function Navbar() {
    const navbarContainer: React.CSSProperties = {
        backgroundColor: "#2a2a72",
        display: "flex",
        alignItems: "center",
        paddingInline: "90px",
        paddingBlock: "10px",
        gap: "15px",
        marginBottom: "50px"
    }

    const navbarSearchContainer: React.CSSProperties = {
        flexGrow: "1"
    }

    const navbarActionItems: React.CSSProperties = {
        display: "flex",
        color: "white"
    }

    return (
        <nav style={navbarContainer}>
            <div style={navbarSearchContainer}>
                <input type="text" placeholder="Search" />
                <button><FaSearch /></button>
            </div>

            <div style={navbarActionItems}>
                <p>Filter: </p>

                <select name="filter" id="filter">
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="mercedes">Mercedes</option>
                    <option value="audi">Audi</option>
                </select>
            </div>

            <div style={navbarActionItems}>
                <p>Sort: </p>

                <select name="cars" id="cars">
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="mercedes">Mercedes</option>
                    <option value="audi">Audi</option>
                </select>
            </div>
        </nav>
    )
}