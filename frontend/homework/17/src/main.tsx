import React from 'react'
import ReactDOM from 'react-dom/client'
import App, { appLoader } from './App.tsx'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ErrorPage from './pages/ErrorPage.tsx';
import NotFoundPage from './pages/NotFoundPage.tsx';
import ProductPage, { productLoader } from './pages/ProductPage.tsx';
import HomePage from './pages/HomePage.tsx';

import AppContextProvider from './context/AppContextProvider.tsx';
import { Provider } from 'react-redux';
import { store } from './store/store.ts';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    loader: appLoader,
    children: [
      {
        path: "/",
        element: <HomePage />,
      },
      {
        path: "products/:productId",
        element: <ProductPage />,
        loader: productLoader,
      },
    ]
  },
  {
    path: "*",
    element: <NotFoundPage />,
    errorElement: <ErrorPage />
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <AppContextProvider>
        <RouterProvider router={router} />
      </AppContextProvider>
    </Provider>
  </React.StrictMode>,
)
