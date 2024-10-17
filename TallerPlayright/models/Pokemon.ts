// Pokemon.ts
import { EVStats } from './EVStats';

export class Pokemon {
  name: string;
  item: string;
  moves: string[];
  evs: EVStats;

  constructor(name: string, item: string, moves: string[], evs: EVStats) {
    this.name = name;
    this.item = item;
    this.moves = moves;
    this.evs = new EVStats(
        evs.hp ?? 0,
        evs.atk ?? 0,
        evs.def ?? 0,
        evs.spa ?? 0,
        evs.spd ?? 0,
        evs.spe ?? 0
      );
  }
}
