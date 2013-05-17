package com.lorenzocinque.puzzle.core.heuristic;

import java.util.ArrayList;

import com.lorenzocinque.puzzle.core.Node;

public class ManhattanHeuristic implements Heuristic {

	private int N;

	public ManhattanHeuristic(int N) {
		this.N = N;
	}

	@Override
	public int h(Node n) {
		int distance = 0;
		int size = n.getState().getBoard().size();
		ArrayList<Integer> board = n.getState().getBoard();
		for (int i = 0; i < size; i++) {
			int valueFound = board.get(i);
			if (valueFound != (i + 1) && valueFound != 0) {
				int k = (i + 1) % N;
				if (k == 0)
					k = N;
				int columnFound = k;
				int rowFound = (i + 1) / N;
				k = valueFound % N;
				if (k == 0)
					k = N;
				int columnRight = k;
				int rowRight = valueFound / N;
				distance += Math.abs(columnRight - columnFound)
						+ Math.abs(rowRight - rowFound);
			}
		}
		return distance;
	}

}
