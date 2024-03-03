import 'styled-components';

declare module 'styled-components' {
    export interface DefaultTheme {
        colors: {
            primaryColor: string;
            greenPrimary: string;
            greenSecondary: string;
            redPrimary: string;
            redSecondary: string;
            yellowPrimary: string;
            yellowSecondary: string;
            greyPrimary: string;
            greySecondary: string;
        };
    }
}

declare module '@mui/material/styles' {
    interface Theme {
        colors: {
            primaryColor: string;
            greenPrimary: string;
            greenSecondary: string;
            redPrimary: string;
            redSecondary: string;
            yellowPrimary: string;
            yellowSecondary: string;
            greyPrimary: string;
            greySecondary: string;
        };
    }
    interface ThemeOptions {
        colors?: {
            primaryColor?: string;
            greenPrimary?: string;
            greenSecondary?: string;
            redPrimary?: string;
            redSecondary?: string;
            yellowPrimary?: string;
            yellowSecondary?: string;
            greyPrimary: string;
            greySecondary: string;
        };
    }
}
