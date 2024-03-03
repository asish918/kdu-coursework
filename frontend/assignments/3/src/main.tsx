import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './pages/App.tsx'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ErrorPage from './pages/ErrorPage.tsx';
import { StockInfo } from './pages/StockInfo.tsx';
import { Dashboard } from './pages/Dashboard.tsx';
import { Portfolio } from './pages/Portfolio.tsx';
import { Summarizer } from './pages/Summarizer.tsx';
import { Provider } from 'react-redux';
import { store } from './store/store.ts';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/",
        element: <Dashboard />
      },
      {
        path: "/stock-info/:stockSymbol",
        element: <StockInfo />
      },
      {
        path: "/portfolio",
        element: <Portfolio />
      },
      {
        path: "/summarizer",
        element: <Summarizer />
      },
    ]
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>,
)
