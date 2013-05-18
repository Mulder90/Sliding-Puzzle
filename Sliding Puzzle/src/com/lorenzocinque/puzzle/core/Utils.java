package com.lorenzocinque.puzzle.core;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

	public static void makeRandomInitialState(Puzzle puzzle, long seed,
			int numberOfMoves) {
		makeMoves(puzzle.getInitialState(), seed, numberOfMoves);
	}

	private static void makeMoves(PuzzleState state, long seed,
			int numberOfMoves) {
		Random rand = new Random();
		rand.setSeed(seed);
		for (int i = 0; i < numberOfMoves; i++) {
			ArrayList<Action> act = state.getAdmissibleActions();
			Action randomAction = act.get(rand.nextInt(act.size()));
			state.move(randomAction);
		}
	}

}
