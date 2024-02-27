import { render, fireEvent } from '@testing-library/react';
import { expect, it, describe } from 'vitest';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { persistor, store } from '../../store/store';
import AppContextProvider from '../../context/AppContextProvider';
import App from '../../App';

describe('Unit Testing', () => {
    it("Navbar", () => {
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

        const navbarInput = getByTestId("navbar-input") as HTMLInputElement;
        expect(navbarInput.disabled, "nav input enabled").toBe(false);

        fireEvent.change(navbarInput, { target: { value: inputVal } });
        expect(navbarInput.value, "nav input typable").toBe(inputVal);
    });
});
