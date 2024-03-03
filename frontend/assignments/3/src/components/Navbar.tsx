import { Link, useNavigate } from "react-router-dom"
import styled from "styled-components"
import appLogo from "../assets/app-logo.png"
import { Dropdown } from '@mui/base/Dropdown';
import { Menu } from '@mui/base/Menu';
import { MenuButton as BaseMenuButton } from '@mui/base/MenuButton';
import { MenuItem as BaseMenuItem, menuItemClasses } from '@mui/base/MenuItem';
import { styled as muiStyled } from '@mui/system';
import MenuIcon from '@mui/icons-material/Menu';

const NavbarContainer = styled.nav`
    color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 1.5rem;
    font-weight: 900;
    padding: 8px;
    margin: 5px;
    background-color: ${props => props.theme.colors.primaryColor};
    `

const NavbarLogo = styled(Link)`
    color: white;
    display: flex;
    align-items: center;
    gap: 20px;
    
    img {
        height: 30px;
        width: 30px;
    }
    `

const NavbarActions = styled.div`
    display: flex;
    gap: 20px;

    @media (max-width: 560px) {
        display: none;
    }
    `

const NavbarAction = styled(Link)`
    color: white;
    text-decoration: none;
    `

const MobileNavbar = styled(Dropdown)`
    button {
        display: none;
    }
`

export function Navbar() {
    const navigate = useNavigate();

    return (
        <NavbarContainer>
            <NavbarLogo to={"/"}>
                <img src={appLogo} alt="App Logo" />
                <h1>
                    KDU Stock Market
                </h1>
            </NavbarLogo>

            <NavbarActions>
                <NavbarAction to={"/summarizer"}>Summarizer</NavbarAction>
                <NavbarAction to={"/portfolio"}>My Portfolio</NavbarAction>
            </NavbarActions>

            <MobileNavbar>
                <MenuButton><MenuIcon /></MenuButton>
                <Menu slots={{ listbox: Listbox }}>
                    <MenuItem onClick={() => navigate('/summarizer')}>Summarizer</MenuItem>
                    <MenuItem onClick={() => navigate('/portfolio')}>
                        My Portfolio
                    </MenuItem>
                </Menu>
            </MobileNavbar>
        </NavbarContainer>
    )
}

const blue = {
    50: '#F0F7FF',
    100: '#C2E0FF',
    200: '#99CCF3',
    300: '#66B2FF',
    400: '#3399FF',
    500: '#007FFF',
    600: '#0072E6',
    700: '#0059B3',
    800: '#004C99',
    900: '#003A75',
};

const grey = {
    50: '#F3F6F9',
    100: '#E5EAF2',
    200: '#DAE2ED',
    300: '#C7D0DD',
    400: '#B0B8C4',
    500: '#9DA8B7',
    600: '#6B7A90',
    700: '#434D5B',
    800: '#303740',
    900: '#1C2025',
};

const Listbox = muiStyled('ul')(
    ({ theme }) => `
  font-family: 'IBM Plex Sans', sans-serif;
  font-size: 0.875rem;
  box-sizing: border-box;
  padding: 6px;
  margin: 12px 0;
  min-width: 200px;
  border-radius: 12px;
  overflow: auto;
  outline: 0px;
  background: ${theme.palette.mode === 'dark' ? grey[900] : '#fff'};
  border: 1px solid ${theme.palette.mode === 'dark' ? grey[700] : grey[200]};
  color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};
  box-shadow: 0px 4px 6px ${theme.palette.mode === 'dark' ? 'rgba(0,0,0, 0.50)' : 'rgba(0,0,0, 0.05)'
        };
  z-index: 1;
  `,
);

const MenuItem = muiStyled(BaseMenuItem)(
    ({ theme }) => `
  list-style: none;
  padding: 8px;
  border-radius: 8px;
  cursor: default;
  user-select: none;

  &:last-of-type {
    border-bottom: none;
  }

  &:focus {
    outline: 3px solid ${theme.palette.mode === 'dark' ? blue[600] : blue[200]};
    background-color: ${theme.palette.mode === 'dark' ? grey[800] : grey[100]};
    color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};
  }

  &.${menuItemClasses.disabled} {
    color: ${theme.palette.mode === 'dark' ? grey[700] : grey[400]};
  }
  `,
);

const MenuButton = muiStyled(BaseMenuButton)(
    ({ theme }) => `
  font-family: 'IBM Plex Sans', sans-serif;
  font-weight: 600;
  font-size: 0.875rem;
  line-height: 1.5;
  padding: 8px 16px;
  border-radius: 8px;
  color: white;
  transition: all 150ms ease;
  cursor: pointer;
  display: none;
  background: ${theme.palette.mode === 'dark' ? grey[900] : '#fff'};
  border: 1px solid ${theme.palette.mode === 'dark' ? grey[700] : grey[200]};
  color: ${theme.palette.mode === 'dark' ? grey[200] : grey[900]};
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 0.05);

  &:hover {
    background: ${theme.palette.mode === 'dark' ? grey[800] : grey[50]};
    border-color: ${theme.palette.mode === 'dark' ? grey[600] : grey[300]};
  }

  &:active {
    background: ${theme.palette.mode === 'dark' ? grey[700] : grey[100]};
  }

  &:focus-visible {
    box-shadow: 0 0 0 4px ${theme.palette.mode === 'dark' ? blue[300] : blue[200]};
    outline: none;
  }

  @media (max-width: 560px) {
    display: inline-block;
  }
  `,
);