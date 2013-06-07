package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.LinkedList;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.Solution;

public class DepthLimitedSearch implements SearchAlgorithm {

	private int bound;
	private int nodeExpanded;

	public DepthLimitedSearch(int bound) {
		this.bound = bound;
		this.nodeExpanded = 0;
	}

	@Override
	public Solution search(Puzzle puzzle) {
		return depthSearch(puzzle, bound);
	}

	private Solution depthSearch(Puzzle puzzle, int bound) {
		return recursiveDepthLimitedSearch(new Node(puzzle.getInitialState(),
				null, null), puzzle, bound);
	}

	private Solution recursiveDepthLimitedSearch(Node node, Puzzle puzzle,
			int bound) {
		if (puzzle.getGoalTest().isGoalState(node.getState())) {
			return new Solution(node, getNodeExpanded());
		} else if (bound == 0) {
			return new Solution(null, getNodeExpanded());
		} else {
			LinkedList<Node> expansion = node.expandNode();
			nodeExpanded++;
			for (Node child : expansion) {
				Solution result = recursiveDepthLimitedSearch(child, puzzle,
						bound - 1);
				if (result.getN() != null
						&& puzzle.getGoalTest().isGoalState(
								result.getN().getState()))
					return result;
			}
		}
		return new Solution(null, getNodeExpanded());
	}

	public int getNodeExpanded() {
		return nodeExpanded;
	}
	
	@Override
	public String toString() {
		return "Depth Limited Search";
	}

}
