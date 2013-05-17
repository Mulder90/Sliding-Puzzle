package com.lorenzocinque.puzzle.core;

import java.util.ArrayList;

public class PuzzleState {

	private final int N;
	private int blankRow;
	private int blankColumn;
	private ArrayList<Integer> board;

	
	public PuzzleState(int N) {
		this.N = N;
		board = new ArrayList<>();
		for (int i = 1; i < N * N; i++) {
			board.add(i);
		}
		board.add(0);
		this.blankColumn = N - 1;
		this.blankRow = N - 1;
	}

	public PuzzleState(int N, ArrayList<Integer> board, int blankRow,
			int blankColumn) {
		this.N = N;
		this.blankRow = blankRow;
		this.blankColumn = blankColumn;
		this.board = board;
	}

	public PuzzleState(PuzzleState state) {
		this.N = state.N;
		this.blankColumn = state.blankColumn;
		this.blankRow = state.blankRow;
		this.board = new ArrayList<>(state.board);
	}

	public ArrayList<Integer> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<Integer> board) {
		this.board = board;
	}

	public void setItem(int row, int column, int value) {
		board.set(row * N + column, value);
	}

	public int getItem(int row, int column) {
		return board.get(row * N + column);
	}

	public int getZeroRow() {
		return blankRow;
	}

	public int getZeroColum() {
		return blankColumn;
	}

	public void move(Action action) {
		if (action == Action.DOWN && blankRow != N - 1) {
			board.set(blankRow * N + blankColumn, board.get((blankRow + 1) * N + blankColumn));
			board.set((blankRow + 1) * N + blankColumn, 0);
			blankRow = blankRow + 1;
		}
		if (action == Action.RIGHT && blankColumn != N - 1) {
			board.set(blankRow * N + blankColumn, board.get(blankRow * N + (blankColumn + 1)));
			board.set(blankRow * N + (blankColumn + 1), 0);
			blankColumn = blankColumn + 1;
		}
		if (action == Action.UP && blankRow != 0) {
			board.set(blankRow * N + blankColumn, board.get((blankRow - 1) * N + blankColumn));
			board.set((blankRow - 1) * N + blankColumn, 0);
			blankRow = blankRow - 1;
		}
		if (action == Action.LEFT && blankColumn != 0) {
			board.set(blankRow * N + blankColumn, board.get(blankRow * N + (blankColumn - 1)));
			board.set(blankRow * N + (blankColumn - 1), 0);
			blankColumn = blankColumn - 1;
		}
	}
	
	public ArrayList<Action> getAdmissibleActions() {
		ArrayList<Action> admissibleActions = new ArrayList<>();
		admissibleActions.add(Action.DOWN);
		admissibleActions.add(Action.UP);
		admissibleActions.add(Action.LEFT);
		admissibleActions.add(Action.RIGHT);
		if (blankColumn == 0)
			admissibleActions.remove(Action.LEFT);
		if (blankColumn == N - 1)
			admissibleActions.remove(Action.RIGHT);
		if (blankRow == 0)
			admissibleActions.remove(Action.UP);
		if (blankRow == N - 1)
			admissibleActions.remove(Action.DOWN);
		return admissibleActions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(!(obj instanceof PuzzleState))
			return false;
		PuzzleState state = (PuzzleState)obj;
		ArrayList<Integer> stateBoard = state.getBoard();
		for(int i = 0; i < N * N; i++) {
			if(stateBoard.get(i) != board.get(i))
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + board.hashCode();
		return result;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < board.size(); i += N) {
			for (int j = 0; j < N; j++) {
				b.append(board.get(i + j) + "\t");
			}
			b.append("\n");
		}
		return b.toString();
	}

}
