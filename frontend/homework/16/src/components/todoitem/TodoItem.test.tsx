
import { render, fireEvent, screen } from '@testing-library/react';
import { expect, it, describe } from 'vitest';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { persistor, store } from '../../store/store';
import AppContextProvider from '../../context/AppContextProvider';
import App from '../../App';

describe('Unit Testing', () => {
    it("TodoItem Add", () => {
        const { getByTestId } = render(
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <AppContextProvider>
                        <App />
                    </AppContextProvider>
                </PersistGate>
            </Provider>
        );

        const inputVal = "Hello World";

        const todoInput = getByTestId("task-input") as HTMLInputElement;

        fireEvent.change(todoInput, { target: { value: inputVal } });

        const todoAddButton = getByTestId("task-submit") as HTMLButtonElement;
        fireEvent.click(todoAddButton)

        expect(screen.getByText(inputVal), "Correct todoitem appearing").not.toBeNull();

        const todoClearButton = getByTestId("task-clear-completed") as HTMLButtonElement;
        expect(todoClearButton.disabled, "clear button enabled").toBe(false);

        expect(fireEvent.click(todoClearButton), "clear button clickable").toBe(true);
    });

    it("TodoItem Check", () => {
        const { getByTestId } = render(
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <AppContextProvider>
                        <App />
                    </AppContextProvider>
                </PersistGate>
            </Provider>
        );

        const todoCheckbox = getByTestId("todo-edit-checkbox") as HTMLInputElement;
        const todoName = getByTestId("todo-name") as HTMLInputElement;
        expect(fireEvent.click(todoCheckbox), "checkbox clickable").toBe(true);

        expect(todoName.style.textDecoration, "text strikethrough working").toBe("line-through");
    });

    it("TodoItem Delete", () => {
        const { getByTestId } = render(
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <AppContextProvider>
                        <App />
                    </AppContextProvider>
                </PersistGate>
            </Provider>
        );

        const todoItemDeleteButton = getByTestId("todo-delete-button") as HTMLButtonElement;
        expect(todoItemDeleteButton.disabled, "todo delete button enabled").toBe(false);

        expect(fireEvent.click(todoItemDeleteButton), "todo delete button clickable").toBe(true);

        expect(screen.queryByText("Hello World"), "todoitem deleted").toBeNull();
    });

    it("TodoItem Edit", () => {
        const { getByTestId } = render(
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <AppContextProvider>
                        <App />
                    </AppContextProvider>
                </PersistGate>
            </Provider>
        );

        const inputVal = "Hello World";
        const newInputVal = "Hola World";

        const todoInput = getByTestId("task-input") as HTMLInputElement;

        fireEvent.change(todoInput, { target: { value: inputVal } });

        const todoAddButton = getByTestId("task-submit") as HTMLButtonElement;
        fireEvent.click(todoAddButton)

        const todoEditButton = getByTestId("todo-edit-button") as HTMLButtonElement;
        expect(todoEditButton.disabled, "edit button enabled").toBe(false);

        expect(fireEvent.click(todoEditButton), "edit button clickable").toBe(true);

        const todoEditInput = getByTestId("todo-edit-input") as HTMLInputElement;
        fireEvent.change(todoEditInput, { target: { value: newInputVal } });

        const todoEditSubmitButton = getByTestId("todo-edit-submit") as HTMLButtonElement;
        expect(todoEditSubmitButton.disabled, "edit submit button enabled").toBe(false);

        expect(fireEvent.click(todoEditSubmitButton), "edit submit button clickable").toBe(true);

        expect(screen.getByText(newInputVal), "Todo Item edited").not.toBeNull();
    });
});
