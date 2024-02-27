import { useContext, useState } from "react";
import { Task } from "../../types"
import '../todo/Todo.scss'
import { AppContext } from "../../context/AppContextProvider";
import { MdDelete, MdEdit, MdOutlineExitToApp } from "react-icons/md";
import { useDispatch } from "react-redux";
import { checkTodo, deleteTodo, editTodo } from "../../reducers/todoSlice";
import { TiTick } from "react-icons/ti";

interface TodoItemProps {
  task: Task
}

export default function TodoItem({ task }: TodoItemProps) {
  const [editState, setEditState] = useState<boolean>(false);
  const [taskEditInput, setTaskEditInput] = useState<string>('');
  const { searchState } = useContext(AppContext);

  const dispatch = useDispatch();

  function deleteTask(id: number) {
    dispatch(deleteTodo(id));
  }

  function taskCheckHandler(id: number) {
    dispatch(checkTodo(id));
  }


  function editTask(id: number, newName: string) {
    if (!newName.length) return;
    dispatch(editTodo({ id, newName }));
    setEditState(false);
  }

  return (
    <div data-testid="todoitem" className="todo-container__item">
      {editState ? (
        <>
          <input
            data-testid="todo-edit-input"
            className='edit-input'
            type="text"
            value={taskEditInput}
            onChange={e => setTaskEditInput(e.target.value)}
            autoFocus
          />
          <button data-testid="todo-edit-submit" className='edit' onClick={() => editTask(task.id, taskEditInput)}><TiTick className='icon' /></button>
          <button data-testid="todo-edit-exit" className='edit' onClick={() => setEditState(false)}><MdOutlineExitToApp className='icon' /></button>
        </>
      ) : (
        <>
          {!searchState.length &&
            <input data-testid="todo-edit-checkbox" type="checkbox" checked={task.done} onChange={() => taskCheckHandler(task.id)} />
          }
          <p data-testid="todo-name" style={{ textDecoration: `${task.done ? "line-through" : ''}` }}>{task.name}</p>

          {!searchState.length &&
            <div className='todo-container__actions'>
              <button data-testid="todo-edit-button" className='edit' onClick={() => {
                setEditState(true)
                setTaskEditInput(task.name)
              }}><MdEdit className='icon' /></button>
              <button data-testid="todo-delete-button" onClick={() => deleteTask(task.id)} className='close'><MdDelete className='icon' /></button>
            </div>
          }
        </>
      )}
    </div>
  )
}
