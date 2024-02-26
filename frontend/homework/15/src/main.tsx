import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import AppContextProvider from './context/AppContextProvider.tsx'
import { Provider } from 'react-redux'
import { store } from './store/store.ts'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <AppContextProvider>
        <App />
      </AppContextProvider>
    </Provider>
  </React.StrictMode>,
)
