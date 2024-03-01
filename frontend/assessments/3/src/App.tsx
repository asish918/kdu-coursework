import { ThemeProvider as CustomThemeProvider, createGlobalStyle } from 'styled-components'
import { appTheme } from './styles/theme'
import Header from './components/Header'
import SelectField from './components/SelectField'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { theme as CustomTheme } from './styles/colors';
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { Provider } from 'react-redux'
import { store } from './store/store'
import { useEffect } from 'react'
import { fetchRooms } from './reducers/thunk/fetchRooms'

const GlobalStyle = createGlobalStyle`
  * {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    text-decoration: none;
    font-weight: normal;
    font-family: "Poppins", sans-serif;
  }

  body {
    padding-inline: 10px;
  }

  input {
    border: none;
    outline: none;
    background-color: transparent;
  }

  button {
    border: none;
    outline: none;
    background-color: transparent;
  }
`

function App({ children }: React.PropsWithChildren) {
  useEffect(() => {
    store.dispatch(fetchRooms());
  })

  const theme = createTheme({
    palette: {
      primary: {
        main: CustomTheme.primaryColor
      }
    }
  });

  return (
    <>
      <Provider store={store}>
        <ThemeProvider theme={theme}>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <GlobalStyle />
            <CustomThemeProvider theme={appTheme}>
              <Header />
              <SelectField />
            </CustomThemeProvider>
            {children}
          </LocalizationProvider>
        </ThemeProvider>
      </Provider>
    </>
  )
}

export default App
