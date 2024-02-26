import { useContext, useState } from 'react';
import { Task } from '../../types'
import './Todo.scss'
import { AppContext } from '../../context/AppContextProvider';
import { useDispatch, useSelector } from 'react-redux';
import { RootDispatch, RootState } from '../../store/store';
import { addTodo, checkTodo, clearCompleted, deleteTodo, editTodo } from '../../reducers/todoSlice';
import { MdDelete, MdEdit } from 'react-icons/md';
import { TiTick } from 'react-icons/ti';

export default function Todo() {
    const [taskInput, setTaskInput] = useState('');

    const [taskEditInput, setTaskEditInput] = useState('');
    const [editTaskId, setEditTaskId] = useState<number | null>(null);

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

    function editTask(id: number, newName: string) {
        if (!newName.length) return;
        dispatch(editTodo({ id, newName }));
        setEditTaskId(null);
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
                            {editTaskId === task.id ? (
                                <>
                                    <input
                                        className='edit-input'
                                        type="text"
                                        value={taskEditInput}
                                        onChange={e => setTaskEditInput(e.target.value)}
                                        autoFocus
                                    />
                                    <button className='edit' onClick={() => editTask(task.id, taskEditInput)}><TiTick className='icon' /></button>
                                </>
                            ) : (
                                <>
                                    <input type="checkbox" checked={task.done} onChange={() => taskCheckHandler(task.id)} />
                                    <p style={{ textDecoration: `${task.done ? "line-through" : ''}` }}>{task.name}</p>
                                    <div className='todo-container__actions'>
                                        <button className='edit' onClick={() => {
                                            setEditTaskId(task.id)
                                            setTaskEditInput(task.name)
                                        }}><MdEdit className='icon' /></button>
                                        <button onClick={() => deleteTask(task.id)} className='close'><MdDelete className='icon' /></button>
                                    </div>
                                </>
                            )}
                        </div>
                    ))
                }
            </section>
        </main>
    )
}