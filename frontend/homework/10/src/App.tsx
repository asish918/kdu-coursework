import { useState } from "react"
import Navbar from "./components/navbar/Navbar"
import Todo from "./components/todo/Todo"
import { Task } from "./types";

function App() {
  const [tasks, setTasks] = useState<Task[]>([
    // {
    //   id: 1,
    //   name: "Item1"
    // },
    // {
    //   id: 2,
    //   name: "Item2"
    // },
    // {
    //   id: 3,
    //   name: "Item3"
    // },
    // {
    //   id: 4,
    //   name: "Item4"
    // }
  ]);

  const [searchState, setSearchState] = useState('');
  const [searchResults, setSearchResults] = useState<Task[]>([]);

  return (
    <>
      <Navbar searchState={searchState} setSearchState={setSearchState} setSearchResults={setSearchResults} tasks={tasks} />
      <Todo tasks={tasks} setTasks={setTasks} searchState={searchState} searchResults={searchResults} />
    </>
  )
}

export default App
