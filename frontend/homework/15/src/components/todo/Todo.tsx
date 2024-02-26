import { useContext, useState } from 'react';
import { Task } from '../../types'
import './Todo.scss'
import { AppContext } from '../../context/AppContextProvider';
import { useDispatch, useSelector } from 'react-redux';
import { RootDispatch, RootState } from '../../store/store';
import { addTodo, checkTodo, clearCompleted, deleteTodo } from '../../reducers/todoSlice';

export default function Todo() {
    const [taskInput, setTaskInput] = useState('');

    const tasks = useSelector((state: RootState) => state.todo);
    const dispatch: RootDispatch = useDispatch();

    const searchResults = useSelector((state: RootState) => state.search);

    const { searchState } = useContext(AppContext);

    function addTask() {
        if (taskInput.trim().length === 0 || taskInput.length === 0) return;

        const newTask: Task = {
            id: tasks.length === 0 ? 1 : tasks[tasks.length - 1].id + 1,
            name: taskInput,
            done: false
        }

        dispatch(addTodo(newTask));
        setTaskInput('');
    }

    function deleteTask(id: number) {
        dispatch(deleteTodo(id));
    }

    function taskInputHandler(event: React.ChangeEvent<HTMLInputElement>) {
        setTaskInput(event.target.value);
    }

    function taskCheckHandler(id: number) {
        dispatch(checkTodo(id));
    }

    function clearChecked() {
        dispatch(clearCompleted())
    }

    return (
        <main className='todo-container'>
            <h1 className='todo-container__heading'>Add Items</h1>

            <div className='todo-container__input'>
                <input
                    type="text"
                    onChange={taskInputHandler}
                    value={taskInput}
                />
                <button onClick={addTask} className='todo-container__submit-button'>Submit</button>
                <button onClick={clearChecked} className='todo-container__submit-button'>Clear Completed</button>
            </div>

            <section className='todo-container__list'>
                <h1 className='todo-container__heading'>Items</h1>
                {
                    searchState.length > 0 && searchResults.length > 0 &&
                    searchResults.map(result => (
                        <div className="todo-container__item" key={result.id}>
                            {result.name}
                        </div>
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
                        <div key={task.id} className='todo-container__item'>
                            <input type="checkbox" checked={task.done} onChange={() => taskCheckHandler(task.id)} />
                            <p style={{ textDecoration: `${task.done ? "line-through" : ''}` }}>{task.name}</p>
                            <button onClick={() => deleteTask(task.id)} className='close'>X</button>
                        </div>
                    ))
                }
            </section>
        </main>
    )
}