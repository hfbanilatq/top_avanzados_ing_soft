// src/pages/PokemonDetailsPage.ts
import { Locator, Page } from "@playwright/test";
import { Pokemon } from "../models/Pokemon";

export class PokemonDetailsPage {
  readonly page: Page;
  private itemInput: Locator;
  private moveInputs: Locator[] = [];
  private evInputs: Locator[] = [];
  private backToTeamButton: Locator;
  private pokemonNameInput: Locator;

  constructor(page: Page) {
    this.page = page;

    this.pokemonNameInput = page.locator(
      '//div[@class="teamchartbox individual"]//div[@class="setcell setcell-pokemon"]//input[@name="pokemon"]'
    );

    this.itemInput = this.page.locator(
      '//div[@class="setrow"]//div[@class="setcell setcell-item"]//input[@name="item"]'
    );

    this.moveInputs = [
      this.page.locator(
        '//div[@class="setcol setcol-moves"]//input[@name="move1"]'
      ),
      this.page.locator(
        '//div[@class="setcol setcol-moves"]//input[@name="move2"]'
      ),
      this.page.locator(
        '//div[@class="setcol setcol-moves"]//input[@name="move3"]'
      ),
      this.page.locator(
        '//div[@class="setcol setcol-moves"]//input[@name="move4"]'
      ),
    ];

    this.backToTeamButton = this.page.locator(
      '//div[@class="teamwrapper"]//div[@class="pad"]//button[@name="back"]'
    );
  }

  async addPokemon(pokemon: Pokemon) {
    await this.pokemonNameInput.pressSequentially(pokemon.name);
    await this.pokemonNameInput.press("Enter");

    await this.itemInput.click();
    await this.itemInput.pressSequentially(pokemon.item);

    for (let i = 0; i < pokemon.moves.length; i++) {
      await this.moveInputs[i].click();
      await this.moveInputs[i].pressSequentially(pokemon.moves[i]);
      await this.moveInputs[i].press("Enter");
    }

    for (const [stat, value] of Object.entries(pokemon.evs) as [
      keyof typeof pokemon.evs,
      number
    ][]) {
      const statInput = this.page.locator(
        `//div[@class="col evcol"]//input[@name="stat-${stat}"]`
      );
      await statInput.click();
      await statInput.pressSequentially(value.toString());
    }

    await this.page.screenshot({ path: `../screenshots/${pokemon.name}.png` });
  }

  async returnToTeam() {
    await this.backToTeamButton.click();
  }
}
