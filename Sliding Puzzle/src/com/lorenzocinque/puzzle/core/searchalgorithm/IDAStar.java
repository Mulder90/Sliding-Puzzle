package com.lorenzocinque.puzzle.core.searchalgorithm;

import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.Solution;
import com.lorenzocinque.puzzle.core.heuristic.Heuristic;

public class IDAStar implements SearchAlgorithm, InformedSearch {
	
	private Heuristic heuristic;
	
	public IDAStar(Heuristic heuristic) {
		setHeuristic(heuristic);
	}

	@Override
	public Solution search(Puzzle puzzle) {
		return new Solution(null, 0);
	}

	@Override
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	@Override
	public Heuristic getHeuristic() {
		return heuristic;
	}

}
