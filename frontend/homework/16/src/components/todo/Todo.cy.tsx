import { Provider } from 'react-redux'
import { persistor, store } from '../../store/store'
import { PersistGate } from 'redux-persist/integration/react'
import AppContextProvider from '../../context/AppContextProvider'
import App from '../../App'
import '@testing-library/cypress/add-commands'

describe('Todo Container Component Testing', () => {
  it('Todo Container', () => {
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

    cy.get('.todo-container__heading').eq(0).should('exist').and('have.text', 'Add Items');
    cy.get('.todo-container__heading').eq(1).should('exist').and('have.text', 'Items');

    cy.findByTestId("task-input").should("exist")
    cy.findByTestId("task-submit").should("exist").and('have.text', 'Submit')
    cy.findByTestId("task-clear-completed").should("exist").and('have.text', 'Clear Completed')
  })
})