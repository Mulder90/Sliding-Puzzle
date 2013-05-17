package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.LinkedList;
import java.util.Queue;

import com.lorenzocinque.puzzle.core.Node;

public class BreadthFirstSearch extends GraphSearch {

	@Override
	protected Queue<Node> createFringe() {
		
		//FIFO
		return new LinkedList<>();		
	}

}
