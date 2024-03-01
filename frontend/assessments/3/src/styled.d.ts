import 'styled-components';

declare module 'styled-components' {
    export interface DefaultTheme {
        colors: {
            primaryColor: string;
            primaryFaded: string;
        };
    }
}

declare module '@mui/material/styles' {
    interface Theme {
        colors: {
            primaryColor: string;
        };
    }
    interface ThemeOptions {
        colors?: {
            primaryColor?: string;
        };
    }
}
