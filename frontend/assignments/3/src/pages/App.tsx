import { ThemeProvider as CustomThemeProvider, createGlobalStyle } from 'styled-components'
import { appTheme } from '../styles/theme'
import { Navbar } from '../components/Navbar'
import { Outlet } from 'react-router-dom'
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { theme as CustomTheme } from '../styles/colors';
import { useEffect } from 'react';
import { store } from '../store/store';
import { fetchStocks } from '../reducers/thunks/fetchStocks';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { fetchPortfolio } from '../reducers/thunks/fetchPortfolio';

const GlobalStyle = createGlobalStyle`
  * {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    text-decoration: none;
    font-weight: normal;
    font-family: "Poppins", sans-serif;
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

function App() {
  const theme = createTheme(
    {
      components: {
        MuiTabs: {
          styleOverrides: {
            indicator: {
              borderBottom: `2px solid ${CustomTheme.primaryColor}`, // Change the border bottom color as desired
            },
          },
        }
      },
      palette: {
        primary: {
          main: CustomTheme.primaryColor
        },
        secondary: {
          main: CustomTheme.greyPrimary
        }
      }
    }
  );

  useEffect(() => {
    store.dispatch(fetchStocks())
    store.dispatch(fetchPortfolio());
  }, [])

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <ThemeProvider theme={theme}>
        <CustomThemeProvider theme={appTheme}>
          <GlobalStyle />
          <Navbar />
          <Outlet />
        </CustomThemeProvider>
      </ThemeProvider>
    </LocalizationProvider>
  )
}

export default App