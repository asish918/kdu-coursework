import { useContext } from 'react';
import './Navbar.scss'
import { AppContext } from '../../context/AppContextProvider';


export default function Navbar() {
    const { searchState, setSearchState, setSearchResults, tasks } = useContext(AppContext);

    function searchChangeHandler(event: React.ChangeEvent<HTMLInputElement>): void {
        if (event.target.value.length === 0) {
            setSearchResults([]);
            setSearchState('');
            return;
        }

        setSearchState(event.target.value)
        progressiveSearch();
    }

    function progressiveSearch(): void {
        setSearchResults(tasks.filter(item => {
            const lowerCaseItem = item.name.toLowerCase();
            return lowerCaseItem.includes(searchState.toLowerCase());
        }));
    }

    return (
        <nav className="navbar">
            <h1 className="navbar__heading">Item Lister</h1>

            <input
                type="text"
                placeholder="Search Items"
                className='navbar__search'
                value={searchState}
                onChange={searchChangeHandler}
            />
        </nav>
    )
}