package com.lorenzocinque.puzzle.core.searchalgorithm;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.Puzzle;

public interface SearchAlgorithm {
	
	public Node search(Puzzle puzzle);
	
	public int getNodeExpanded();

}
