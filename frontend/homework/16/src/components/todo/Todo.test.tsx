
import { render, fireEvent } from '@testing-library/react';
import { expect, it, describe } from 'vitest';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { persistor, store } from '../../store/store';
import AppContextProvider from '../../context/AppContextProvider';
import App from '../../App';

describe('Unit Testing', () => {
    it("TodoContainer Input", () => {
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
        expect(todoInput.disabled, "todo input enabled").toBe(false);

        fireEvent.change(todoInput, { target: { value: inputVal } });
        expect(todoInput.value, "todo input typable").toBe(inputVal)
    });

    it("TodoContainer Submit", () => {
        const { getByTestId } = render(
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <AppContextProvider>
                        <App />
                    </AppContextProvider>
                </PersistGate>
            </Provider>
        );

        const todoAddButton = getByTestId("task-submit") as HTMLButtonElement;
        expect(todoAddButton.disabled, "add button enabled").toBe(false);

        expect(fireEvent.click(todoAddButton), "add button clickable").toBe(true);
    });

    it("TodoContainer Clear Completed", () => {
        const { getByTestId } = render(
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <AppContextProvider>
                        <App />
                    </AppContextProvider>
                </PersistGate>
            </Provider>
        );

        const todoClearButton = getByTestId("task-clear-completed") as HTMLButtonElement;
        expect(todoClearButton.disabled, "clear button enabled").toBe(false);

        expect(fireEvent.click(todoClearButton), "clear button clickable").toBe(true);
    });
});
