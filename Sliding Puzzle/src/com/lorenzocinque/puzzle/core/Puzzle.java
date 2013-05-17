package com.lorenzocinque.puzzle.core;


public class Puzzle {

	private final int N;
	private PuzzleState initialState;
	private final PuzzleGoalTest goalTest;
	private final Action[] defaultActions;

	public Puzzle(int N, PuzzleState initialState, PuzzleGoalTest goalTest) {
		this.N = N;
		this.initialState = initialState;
		this.goalTest = goalTest;
		this.defaultActions = Action.values();
	}

	public int getN() {
		return N;
	}

	public PuzzleState getInitialState() {
		return initialState;
	}

	public PuzzleGoalTest getGoalTest() {
		return goalTest;
	}

	public Action[] getDefaultActions() {
		return defaultActions;
	}

}
