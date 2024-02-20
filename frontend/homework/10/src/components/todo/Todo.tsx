import { useState } from 'react';
import { Task } from '../../types'
import './Todo.scss'

interface TodoProps {
    tasks: Task[];
    setTasks: React.Dispatch<React.SetStateAction<Task[]>>;
    searchState: string;
    searchResults: Task[];
}


export default function Todo({ tasks, setTasks, searchState, searchResults }: TodoProps) {
    const [taskInput, setTaskInput] = useState('');

    function addTask() {
        if (taskInput.trim().length === 0 || taskInput.length === 0) return;

        const newTask = {
            id: tasks.length === 0 ? 1 : tasks[tasks.length - 1].id + 1,
            name: taskInput
        }

        setTasks([...tasks, newTask])
        setTaskInput('');
    }

    function deleteTask(id: number) {
        setTasks(tasks.filter(task => task.id !== id));
    }

    function taskInputHandler(event: React.ChangeEvent<HTMLInputElement>) {
        setTaskInput(event.target.value);
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
                            <p>{task.name}</p>
                            <button onClick={() => deleteTask(task.id)} className='close'>X</button>
                        </div>
                    ))
                }
            </section>
        </main>
    )
}