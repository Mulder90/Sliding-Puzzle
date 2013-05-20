package com.lorenzocinque.puzzle.core;

import com.lorenzocinque.puzzle.core.searchalgorithm.SearchAlgorithm;

public class Solver {

	private Puzzle puzzle;
	private SearchAlgorithm algorithm;
	
	public Solver(Puzzle puzzle, SearchAlgorithm algorithm) {
		this.puzzle = puzzle;
		this.algorithm = algorithm;
	}

	public SearchAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(SearchAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public Solution solve() {
		return algorithm.search(puzzle);
	}
	
	@Override
	public String toString() {
		return algorithm.getClass().getSimpleName();
	}

}
