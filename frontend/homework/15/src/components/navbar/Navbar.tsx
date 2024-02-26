import { useContext } from 'react';
import './Navbar.scss'
import { AppContext } from '../../context/AppContextProvider';
import { RootDispatch, RootState } from '../../store/store';
import { useDispatch, useSelector } from 'react-redux';
import { filterTodo } from '../../reducers/searchSlice';


export default function Navbar() {
    const { searchState, setSearchState } = useContext(AppContext);

    const tasks = useSelector((state: RootState) => state.todo);
    const dispatch: RootDispatch = useDispatch();

    function searchChangeHandler(event: React.ChangeEvent<HTMLInputElement>): void {
        if (event.target.value.length === 0) {
            setSearchState('');
            return;
        }

        setSearchState(event.target.value)
        dispatch(filterTodo({
            tasks,
            searchValue: event.target.value
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