package com.lorenzocinque.puzzle.core.heuristic;

import java.util.ArrayList;

import com.lorenzocinque.puzzle.core.Node;

public class MisplacedHeuristic implements Heuristic {

	private int N;

	public MisplacedHeuristic(int N) {
		this.N = N;
	}

	@Override
	public int h(Node n) {
		int misplacedCell = 0;
		ArrayList<Integer> board = n.getState().getBoard();
		for (int i = 0; i < N * N; i++) {
			int k = board.get(i);
			if (k != (i + 1) && k != 0)
				misplacedCell++;
		}
		return misplacedCell;
	}
}
