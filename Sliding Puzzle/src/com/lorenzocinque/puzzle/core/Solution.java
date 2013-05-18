package com.lorenzocinque.puzzle.core;

import java.util.LinkedList;

public class Solution {

	private LinkedList<Node> solutionPath;
	private int nodeExplored;

	public Solution(Node n, int nodeExplored) {
		this.solutionPath = initSolution(n);
		this.nodeExplored = nodeExplored;
	}

	private LinkedList<Node> initSolution(Node n) {
		if (n != null)
			return n.getPathFromRoot();
		return null;
	}

	public LinkedList<Node> getSolutionPath() {
		return solutionPath;
	}

	public int getNodeExplored() {
		return nodeExplored;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (solutionPath != null) {
			builder.append("Solution:\n");
			for (Node node : solutionPath) {
				builder.append(node + "\n");
			}
			builder.append("Resolved in " + (solutionPath.size() - 1)
					+ " moves\n");
			builder.append("Number of nodes expanded: " + nodeExplored);
		} else {
			builder.append("No solution found\n");
			builder.append("Number of nodes expanded: " + nodeExplored);
		}
		return builder.toString();
	}

}
