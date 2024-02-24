import { useEffect, useRef, useState } from 'react'
import './App.scss'

function App() {
  const [timer, setTimer] = useState(0)
  const interValRef = useRef<number>()
  const inputRef = useRef<HTMLInputElement>(null)
  const timerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    interValRef.current = setInterval(() => {
      setTimer(timer => timer + 1)
    }, 1000)

    inputRef.current?.focus();
    return () => {
      clearInterval(interValRef.current)
    }
  }, [])



  function scrollToTop() {
    timerRef.current?.scrollIntoView({ behavior: "smooth" })
  }

  function startTimer() {
    interValRef.current = setInterval(() => {
      setTimer(timer => timer + 1)
    }, 1000)
  }

  function focusInput() {
    inputRef.current?.focus();
  }

  return (
    <main className='main'>
      <div ref={timerRef} className='main__timer'>
        <p>Timer - {timer}</p>
        <button onClick={startTimer}>
          Start Timer
        </button>
        <button onClick={focusInput}>
          Focus Input
        </button>
        <button onClick={() => {
          clearInterval(interValRef.current)
          setTimer(0);
        }}>
          Clear Timer
        </button>
      </div>

      <div className='main__input'>
        <input ref={inputRef} type="text" placeholder='Random Input field' />
      </div>

      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div className='main__card'>
        Random item to have scroll
      </div>
      <div onClick={scrollToTop} className='main__card'>
        Random item to have scroll
      </div>
    </main>
  )
}

export default App
