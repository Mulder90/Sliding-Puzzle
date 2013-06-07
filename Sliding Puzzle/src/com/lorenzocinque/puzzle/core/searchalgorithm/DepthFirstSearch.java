package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.LinkedList;
import java.util.Queue;

import com.lorenzocinque.puzzle.core.Node;

/*
 * Not usable for Sliding Puzzle problem!
 */
public class DepthFirstSearch extends GraphSearch {

	@Override
	protected Queue<Node> createFringe() {

		// LIFO
		return new LinkedList<Node>() {

			private static final long serialVersionUID = 8937567369369146609L;

			@Override
			public Node poll() {
				return getLast();
			}
		};
	}
	
	@Override
	public String toString() {
		return "Depth First Search";
	}

}
