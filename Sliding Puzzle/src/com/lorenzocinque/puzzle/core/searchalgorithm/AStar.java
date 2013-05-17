package com.lorenzocinque.puzzle.core.searchalgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.heuristic.Heuristic;

public class AStar extends GraphSearch implements InformedSearch {

	private Heuristic heuristic;

	public AStar(Heuristic heuristic) {
		setHeuristic(heuristic);
	}

	public Heuristic getHeuristic() {
		return heuristic;
	}

	@Override
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	@Override
	protected Queue<Node> createFringe() {
		return new PriorityQueue<Node>(1000, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				o1.setH(heuristic.h(o1));
				o2.setH(heuristic.h(o2));
				o1.setF(o1.getG() + o1.getH());
				o2.setF(o2.getG() + o2.getH());
				if (o1.getF() < o2.getF())
					return -1;
				if (o1.getF() > o2.getF())
					return 1;
				return 0;
			}
		});
	}

}
