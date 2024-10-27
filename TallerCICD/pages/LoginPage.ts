import { Locator, Page } from "@playwright/test";

export class LoginPage {
  page: Page;
  loginButton: Locator;
  getTestAccountButton: Locator;
  usernameField: Locator;

  constructor(page: Page) {
    this.page = page;
    this.getTestAccountButton = this.page.getByText("Get a test account");
    this.loginButton = this.page.locator("#login-btn");
    this.usernameField = this.page.locator('#username-input');
  }

  async navigateToLoginPage() {
    await this.page
      .getByRole("navigation")
      .getByRole("link", { name: "Login" })
      .click();
  }

  async generateTestAccount() {
    await this.getTestAccountButton.click();
  }

  async login() {
    await this.loginButton.click();
  }
}
