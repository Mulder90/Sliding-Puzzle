package com.lorenzocinque.puzzle.core.searchalgorithm;

import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.Solution;
import com.lorenzocinque.puzzle.core.heuristic.Heuristic;

public class IDAStar implements SearchAlgorithm, InformedSearch {
	
	public IDAStar(Heuristic heuristic) {
		setHeuristic(heuristic);
	}

	@Override
	public Solution search(Puzzle puzzle) {
		return null;
	}

	@Override
	public void setHeuristic(Heuristic heuristic) {
		// TODO Auto-generated method stub

	}

	@Override
	public Heuristic getHeuristic() {
		// TODO Auto-generated method stub
		return null;
	}

}
