import { useContext, useState } from 'react';
import { Task } from '../../types'
import './Todo.scss'
import { AppContext } from '../../context/AppContextProvider';
import { useDispatch, useSelector } from 'react-redux';
import { RootDispatch, RootState } from '../../store/store';
import { addTodo, clearCompleted } from '../../reducers/todoSlice';
import TodoItem from '../todoitem/TodoItem';

export default function Todo() {
    const [taskInput, setTaskInput] = useState('');


    const tasks = useSelector((state: RootState) => state.todo);
    const dispatch: RootDispatch = useDispatch();

    const searchResults = useSelector((state: RootState) => state.search);

    const { searchState } = useContext(AppContext);

    function addTask() {
        if (!taskInput.trim().length || !taskInput.length) return;

        const newTask: Task = {
            id: !tasks.length ? 1 : tasks[tasks.length - 1].id + 1,
            name: taskInput,
            done: false
        }

        dispatch(addTodo(newTask));
        setTaskInput('');
    }

    function taskInputHandler(event: React.ChangeEvent<HTMLInputElement>) {
        setTaskInput(event.target.value);
    }


    function clearChecked() {
        dispatch(clearCompleted())
    }

    return (
        <main data-testid="todo-container" className='todo-container'>
            <h1 className='todo-container__heading'>Add Items</h1>

            <div className='todo-container__input'>
                <input
                    type="text"
                    onChange={taskInputHandler}
                    value={taskInput}
                    data-testid="task-input"
                />
                <button data-testid="task-submit" onClick={addTask} className='todo-container__submit-button'>Submit</button>
                <button data-testid="task-clear-completed" onClick={clearChecked} className='todo-container__submit-button'>Clear Completed</button>
            </div>

            <section className='todo-container__list'>
                <h1 className='todo-container__heading'>Items</h1>
                {
                    searchState.length > 0 && searchResults.length > 0 &&
                    searchResults.map(result => (
                        <TodoItem key={result.id} task={result} />
                    ))
                }
                {
                    searchResults.length === 0 && searchState.length > 0 &&
                    <div>
                        No Results
                    </div>
                }
                {
                    searchState.length == 0 &&
                    tasks.map(task => (
                        <TodoItem key={task.id} task={task} />
                    ))
                }
            </section>
        </main>
    )
}
