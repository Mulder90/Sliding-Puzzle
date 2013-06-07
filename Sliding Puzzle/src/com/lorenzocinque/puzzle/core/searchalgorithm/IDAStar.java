package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.LinkedList;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.Solution;
import com.lorenzocinque.puzzle.core.heuristic.Heuristic;

public class IDAStar implements SearchAlgorithm, InformedSearch {

	private Heuristic heuristic;
	private int nodeExpanded;

	public IDAStar(Heuristic heuristic) {
		setHeuristic(heuristic);
		nodeExpanded = 0;
	}

	@Override
	public Solution search(Puzzle puzzle) {
		Node startNode = new Node(puzzle.getInitialState(), null, null);
		int nextCostBound = heuristic.h(startNode);
		Solution result = new Solution(null, 0);
		while (result.getN() == null) {
			result = depthFirstSearch(startNode, puzzle, nextCostBound);
			if(result.getN() != null && puzzle.getGoalTest().isGoalState((result.getN().getState())))
				return result;
			nextCostBound += 2;
		}
		return new Solution(null, 0);
	}

	private Solution depthFirstSearch(Node selectedNode, Puzzle puzzle,
			int currentCostBound) {
		if (puzzle.getGoalTest().isGoalState(selectedNode.getState()))
			return new Solution(selectedNode, nodeExpanded);
		nodeExpanded++;
		LinkedList<Node> expansion = selectedNode.expandNode();
		for (Node n : expansion) {
			n.setH(heuristic.h(n));
			n.setF(n.getG() + n.getH());
			int value = n.getF();
			if (value <= currentCostBound) {
				Solution result = depthFirstSearch(n, puzzle, currentCostBound);
				if (result.getN() != null)
					return result;
			}
		}
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

	public int getNodeExpanded() {
		return nodeExpanded;
	}

	@Override
	public String toString() {
		return "IDA*";
	}

}
