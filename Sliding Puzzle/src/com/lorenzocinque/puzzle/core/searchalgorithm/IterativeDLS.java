package com.lorenzocinque.puzzle.core.searchalgorithm;

import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.Solution;

public class IterativeDLS implements SearchAlgorithm {

	@Override
	public Solution search(Puzzle puzzle) {

		int depth = 1;
		while (true) {
			DepthLimitedSearch dls = new DepthLimitedSearch(depth);
			Solution result = dls.search(puzzle);
			if (result.getN() != null && puzzle.getGoalTest().isGoalState(result.getN().getState()))
				return result;
			depth++;
		}
	}
	
	@Override
	public String toString() {
		return "Iterative DLS";
	}

}
