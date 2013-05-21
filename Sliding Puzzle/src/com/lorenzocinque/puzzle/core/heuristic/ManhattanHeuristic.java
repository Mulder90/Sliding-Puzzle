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
		ArrayList<Integer> board = n.getState().getBoard();
		int[][] multiBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				multiBoard[i][j] = board.get(i * N + j);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int value = multiBoard[i][j];
				if (multiBoard[i][j] != 0) {
					int targetX = (value - 1) / N;
					int targetY = (value - 1) % N;
					distance += Math.abs(i - targetX) + Math.abs(j - targetY);
				}
			}
		}
		return distance;
	}

}
