import { Provider } from 'react-redux'
import { persistor, store } from '../../store/store'
import { PersistGate } from 'redux-persist/integration/react'
import AppContextProvider from '../../context/AppContextProvider'
import App from '../../App'
import '@testing-library/cypress/add-commands'

describe('Navbar Component Testing', () => {
  it('Navbar Input', () => {
    // see: https://on.cypress.io/mounting-react
    cy.viewport(1280, 720)
    cy.mount(
      <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
          <AppContextProvider>
            <App />
          </AppContextProvider>
        </PersistGate>
      </Provider>
    )

    cy.findByTestId("navbar-input").should('exist')
    cy.get(".navbar__heading").should('exist').and('have.text', 'Item Lister')
  })
})