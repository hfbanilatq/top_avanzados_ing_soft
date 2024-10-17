// src/tests/teamBuilder.spec.ts
import { test } from "@playwright/test";
import { HomePage } from "../pages/HomePage";
import { TeamCreationPage } from "../pages/TeamCreationPage";
import { PokemonDetailsPage } from "../pages/PokemonDetailsPage";
import { TeamListPage } from "../pages/TeamListPage";
import { TeamData } from "../models/TeamData";
import { Pokemon } from "../models/Pokemon";
import { EVStats } from "../models/EVStats";
import * as teamDataJson from "../data/team.json";

test("Create and validate new team", async ({ page }) => {
  test.setTimeout(60000);
  const teamData = new TeamData(
    teamDataJson.generation,
    teamDataJson.tier,
    teamDataJson.pokemon.map(
      (p: any) =>
        new Pokemon(
          p.name,
          p.item,
          p.moves,
          new EVStats(
            p.evs.hp,
            p.evs.atk,
            p.evs.def,
            p.evs.spa,
            p.evs.spd,
            p.evs.spe
          )
        )
    )
  );

  const homePage = new HomePage(page);
  const teamCreationPage = new TeamCreationPage(
    page,
    teamData.generation,
    teamData.tier
  );
  const pokemonDetailsPage = new PokemonDetailsPage(page);
  const teamListPage = new TeamListPage(page);

  await homePage.navigate();
  await homePage.goToTeambuilder();
  await teamCreationPage.selectFormat(teamData.tier);
  await teamCreationPage.createNewTeam();

  for (const pokemon of teamData.pokemon) {
    await teamListPage.addPokemon();
    await pokemonDetailsPage.addPokemon(pokemon);
    await pokemonDetailsPage.returnToTeam();
  }

  await teamListPage.validateTeam(teamData.generation, teamData.tier);
});
