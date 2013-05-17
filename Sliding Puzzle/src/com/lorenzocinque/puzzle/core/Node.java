package com.lorenzocinque.puzzle.core;

import java.util.LinkedList;

public class Node {

	private PuzzleState state;
	private Node parent;
	private Action action;
	private int g;
	private int f;
	private int h;

	public Node(PuzzleState state, Node parent, Action action) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.g = 0;
		this.h = 0;
		this.f = 0;
	}

	public PuzzleState getState() {
		return state;
	}

	public Node getParent() {
		return parent;
	}

	public Action getAction() {
		return action;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public LinkedList<Node> expandNode() {
		LinkedList<Node> nodes = new LinkedList<Node>();
		for (Action action : state.getAdmissibleActions()) {
			PuzzleState copyState = new PuzzleState(getState());
			copyState.move(action);
			Node n = new Node(copyState, this, action);
			n.setG(this.g + 1);
			nodes.add(n);
		}
		return nodes;
	}

	public LinkedList<Node> getPathFromRoot() {
		LinkedList<Node> path = new LinkedList<Node>();
		Node n = this;
		while (n != null) {
			path.addFirst(n);
			n = n.getParent();
		}
		return path;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Node))
			return false;
		Node node = (Node) obj;
		return node.getState().equals(this.state);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + state.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "[<Action: " + action + ">]\n" + state.toString();
	}

}
