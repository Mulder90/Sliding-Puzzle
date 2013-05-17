package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.Puzzle;

public abstract class GraphSearch implements SearchAlgorithm {

	protected Queue<Node> fringe;
	protected HashSet<Node> closedList;

	public GraphSearch() {
		fringe = createFringe();
		closedList = new HashSet<Node>(100);
	}

	protected abstract Queue<Node> createFringe();

	public int getNodeExpanded() {
		return closedList.size();
	}

	@Override
	public Node search(Puzzle puzzle) {
		fringe.add(new Node(puzzle.getInitialState(), null, null));
		while (!fringe.isEmpty()) {
			Node selectedNode = fringe.poll();
			if (puzzle.getGoalTest().isGoalState(selectedNode.getState())) {
				return selectedNode;
			}
			closedList.add(selectedNode);
			LinkedList<Node> expansion = selectedNode.expandNode();
			for (Node n : expansion) {
				if (!closedList.contains(n) && !fringe.contains(n))
					fringe.add(n);
			}
		}
		return null;
	}

}
