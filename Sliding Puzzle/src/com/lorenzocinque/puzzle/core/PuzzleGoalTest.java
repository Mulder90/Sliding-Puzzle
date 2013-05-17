package com.lorenzocinque.puzzle.core;

import java.util.ArrayList;

public class PuzzleGoalTest {

	private final PuzzleState goalState;

	public PuzzleGoalTest(int N) {
		goalState = makeGoalState(N);
	}

	public PuzzleState getGoalState() {
		return goalState;
	}

	public boolean isGoalState(PuzzleState state) {
		return state.equals(goalState);
	}

	private PuzzleState makeGoalState(int N) {
		ArrayList<Integer> s = new ArrayList<>();
		for (int i = 1; i < N * N; i++) {
			s.add(i);
		}
		s.add(0);
		return new PuzzleState(N, s, N - 1, N - 1);
	}

}
