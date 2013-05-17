package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.LinkedList;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.Puzzle;

public class DepthLimitedSearch implements SearchAlgorithm {

	private int bound;
	private int nodeExpanded;

	public DepthLimitedSearch(int bound) {
		this.bound = bound;
		this.nodeExpanded = 0;
	}

	@Override
	public Node search(Puzzle puzzle) {
		return depthSearch(puzzle, bound);
	}

	private Node depthSearch(Puzzle puzzle, int bound) {
		return recursiveDepthLimitedSearch(new Node(puzzle.getInitialState(),
				null, null), puzzle, bound);
	}

	private Node recursiveDepthLimitedSearch(Node node, Puzzle puzzle, int bound) {
		if (bound >= 0) {
			if (puzzle.getGoalTest().isGoalState(node.getState()))
				return node;
			else {
				LinkedList<Node> expansion = node.expandNode();
				nodeExpanded++;
				for (Node child : expansion) {
					recursiveDepthLimitedSearch(child, puzzle, bound - 1);
				}
			}
		}
		return null;
	}

	@Override
	public int getNodeExpanded() {
		return nodeExpanded;
	}

}
