package com.lorenzocinque.puzzle.core.searchalgorithm;

import com.lorenzocinque.puzzle.core.heuristic.Heuristic;

/*
 * An Informed Search algorithm must have an Heuristic
 */
public interface InformedSearch {

	public void setHeuristic(Heuristic heuristic);
	
	public Heuristic getHeuristic();

}
