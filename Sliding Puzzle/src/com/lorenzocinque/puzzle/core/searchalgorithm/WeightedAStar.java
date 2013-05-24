package com.lorenzocinque.puzzle.core.searchalgorithm;

import com.lorenzocinque.puzzle.core.heuristic.Heuristic;

public class WeightedAStar extends AStar {

	public WeightedAStar(Heuristic heuristic, int w) {
		super(heuristic);
		setW(w);
	}
	
	public int getW() {
		return w;
	}
	
	public void setW(int w) {
		this.w = w;
	}

}
