import { Locator, Page } from "@playwright/test";

export class TeamCreationPage {
  readonly page: Page;
  private addFormatFolder: Locator;
  private searchFormats: Locator;
  private gen9UbersButton: Locator;
  tier: string;
  gen: number;

  constructor(page: Page, gen: number, tier: string) {
    this.page = page;
    this.gen = gen;
    this.tier = tier;
    const tiertLowerCase = this.tier.toLowerCase();
    this.addFormatFolder = this.page.locator(
      '//div[@class="folder"]//div[@class="selectFolder"]//em[text()="(add format folder)"]'
    );
    this.searchFormats = this.page.locator(
      '//ul[@class="popupmenu"]//li//input[@placeholder="Search formats"]'
    );
    this.gen9UbersButton = this.page.locator(
      `//button[@value="gen${this.gen}${tiertLowerCase}"]`
    );
  }

  private getCreateNewTeamButton() {
    return this.page.locator(
      `//div[@class="teampane"]//p//button[@value="team" and contains(text(), "New [Gen ${this.gen}] ${this.tier} Team")]`
    );
  }

  async selectFormat(format: string) {
    await this.addFormatFolder.click();
    await this.searchFormats.fill(format);
    await this.gen9UbersButton.click();
  }

  async createNewTeam() {
    await this.getCreateNewTeamButton().click();
  }
}
