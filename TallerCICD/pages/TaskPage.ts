import { Locator, Page } from "@playwright/test";

export class TasksPage {
    private page:Page;
    private taskInput: Locator;
    private clearButton: Locator;
    private logoutButton: Locator;
  
    constructor(page: Page) {
      this.page = page;
      this.taskInput = this.page.getByPlaceholder('What needs to be done?');
      this.clearButton = this.page.getByText('clear_allClear');
      this.logoutButton = this.page.locator('a').filter({ hasText: 'power_settings_new' });
    }
  
    async addTask(taskName: string) {
      await this.taskInput.click();
      await this.taskInput.fill(taskName);
      await this.taskInput.press('Enter');
    }
  
    getTask(taskName: string) {
      return this.page.getByText(taskName);
    }
  
    async markTaskAsCompleted(taskName: string) {
      await this.page.locator('span').filter({ hasText: `check_box_outline_blank ${taskName}` }).locator('i').click();
    }
  
    getCompletedTask(taskName: string) {
      return this.page.locator('span').filter({ hasText: `check_box ${taskName}` });
    }
  
    async clearTasks() {
      await this.clearButton.click();
    }
  
    async logout() {
      await this.logoutButton.click();
    }
  }