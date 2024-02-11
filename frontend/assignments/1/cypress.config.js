const { defineConfig } = require("cypress");
// const getCompareSnapshotsPlugin = require("cypress-visual-regression/dist/plugin");
const { configureVisualRegression } = require('cypress-visual-regression')

// Export Cypress configuration using defineConfig function
module.exports = defineConfig({
  // Trash assets (screenshots, videos) before each test run
  // screenshotsFolder : "cypress/screenshots",
  trashAssetsBeforeRuns: true,

  // Disable video recording during test runs
  video: false,

  // Configuration for end-to-end (e2e) tests
  e2e: {
    // Base URL for your application
    // baseUrl: "http://localhost:5500/assignments/assignment-1/twitter-client",
    baseUrl: "http://localhost:1234",

    // Setup Node events for visual regression testing using cypress-visual-regression plugin
    setupNodeEvents(on, config) {
      // Integrate the compareSnapshots plugin into Cypress
      configureVisualRegression(on);
    },
  },
  // Environment variables
  env: {
    EMAIL: "test@gmail.com",
    PASSWORD: "test",
    USERNAME: "test-user",
    NAME: "test-name",
    HOME_PAGE_URL: "/index.html",
    LOGIN_PAGE_URL: "login/index.html",
    REGISTER_PAGE_URL: "/register/index.html",
    TEST_THRESHOLD: 0.2,
    visualRegressionType: 'regression'
  },
});
