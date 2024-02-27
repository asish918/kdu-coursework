import '@testing-library/cypress/add-commands'

describe('My Todo App', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5173');
  })

  it('Add Item', () => {
    const inputVal = 'Hello World'

    cy.findByTestId("task-input").should("exist")
    cy.findByTestId("task-input").type(inputVal);
    cy.findByTestId('task-submit').click();

    cy.findByTestId('todo-name').should("exist").and('have.text', inputVal)
  })

  it('Delete Item', () => {
    const inputVal = 'Hello World'

    cy.findByTestId("task-input").should("exist")
    cy.findByTestId("task-input").type(inputVal);
    cy.findByTestId('task-submit').click();

    cy.findByTestId('todo-delete-button').click();

    cy.findByTestId('todoitem').should('not.exist')
  })

  it('Edit Item', () => {
    const inputVal = 'Hello World'
    const newInputVal = 'Hola World'

    cy.findByTestId('task-input').should("exist")
    cy.findByTestId('task-input').type(inputVal);
    cy.findByTestId('task-submit').click();

    cy.findByTestId('todo-edit-button').click();

    cy.findByTestId('todo-edit-input').clear();
    cy.findByTestId('todo-edit-input').type('Hola World');
    cy.findByTestId('todo-edit-submit').click();

    cy.findByTestId('todo-name').should("exist").and('have.text', newInputVal)
  })

  it('Check Item', () => {
    const inputVal1 = 'Hello'
    const inputVal2 = 'Hola'
    const inputVal3 = 'World'

    cy.findByTestId("task-input").should("exist")
    cy.findByTestId("task-input").type(inputVal1);
    cy.findByTestId('task-submit').click();

    cy.findByTestId("task-input").type(inputVal2);
    cy.findByTestId('task-submit').click();

    cy.findByTestId("task-input").type(inputVal3);
    cy.findByTestId('task-submit').click();

    cy.get('.todo-container__list').children().eq(1).findByTestId('todo-edit-checkbox').click();
    cy.get('.todo-container__list').children().eq(2).findByTestId('todo-edit-checkbox').click();

    cy.get('.todo-container__list').children().eq(1).findByTestId('todo-name').should('have.css', 'text-decoration', 'line-through solid rgb(0, 0, 0)');;
    cy.get('.todo-container__list').children().eq(2).findByTestId('todo-name').should('have.css', 'text-decoration', 'line-through solid rgb(0, 0, 0)');;

    cy.findByTestId('task-clear-completed').click();

    cy.get('.todo-container__list').children().should('have.length', 2);
  })

  it('Search Items', () => {
    const inputVal1 = 'Hello'
    const inputVal2 = 'Hola'
    const inputVal3 = 'World'

    cy.findByTestId("task-input").should("exist")
    cy.findByTestId("task-input").type(inputVal1);
    cy.findByTestId('task-submit').click();

    cy.findByTestId("task-input").type(inputVal2);
    cy.findByTestId('task-submit').click();

    cy.findByTestId("task-input").type(inputVal3);
    cy.findByTestId('task-submit').click();

    cy.findByTestId("navbar-input").type('hello')
    cy.get('.todo-container__list').children().eq(1).findByTestId('todo-name').should('have.text', inputVal1);

    cy.findByTestId("navbar-input").clear()
    cy.findByTestId("navbar-input").type('world')
    cy.get('.todo-container__list').children().eq(1).findByTestId('todo-name').should('have.text', inputVal3);

    cy.findByTestId("navbar-input").clear()
    cy.findByTestId("navbar-input").type('kuchbhi')
    cy.findByText("No Results")
  })
})