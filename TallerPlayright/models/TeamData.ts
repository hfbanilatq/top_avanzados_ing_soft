// TeamData.ts
import { Pokemon } from './Pokemon';

export class TeamData {
  generation: number;
  tier: string;
  pokemon: Pokemon[];

  constructor(generation: number, tier: string, pokemon: Pokemon[]) {
    this.generation = generation;
    this.tier = tier;
    this.pokemon = pokemon;
  }
}
