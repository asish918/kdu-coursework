import { Provider } from 'react-redux'
import { persistor, store } from '../../store/store'
import { PersistGate } from 'redux-persist/integration/react'
import AppContextProvider from '../../context/AppContextProvider'
import App from '../../App'
import '@testing-library/cypress/add-commands'

describe('TodoItem Component Testing', () => {
  it('TodoItem', () => {
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

    cy.findByTestId("task-input").type('Hello World')
    cy.findByTestId("task-submit").click()

    cy.findByTestId("todo-edit-checkbox").should('exist')
    cy.findByTestId("todo-edit-button").should('exist')
    cy.findByTestId("todo-delete-button").should('exist')

    cy.findByTestId("todo-edit-button").click()

    cy.findByTestId("todo-edit-input").should('exist')
    cy.findByTestId("todo-edit-submit").should('exist')
    cy.findByTestId("todo-edit-exit").should('exist')
  })
})