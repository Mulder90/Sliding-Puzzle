package com.lorenzocinque.puzzle.main;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;

import com.lorenzocinque.puzzle.core.Node;
import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.PuzzleGoalTest;
import com.lorenzocinque.puzzle.core.PuzzleState;
import com.lorenzocinque.puzzle.core.Solver;
import com.lorenzocinque.puzzle.core.Utils;
import com.lorenzocinque.puzzle.core.heuristic.ManhattanHeuristic;
import com.lorenzocinque.puzzle.core.searchalgorithm.AStar;
import com.lorenzocinque.puzzle.gui.PuzzleGui;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Console or GUI?:\n1)Console\n2)GUI");
		int type = sc.nextInt();
		if (type == 1)
			startConsole();
		else
			startGUI();
		sc.close();
	}

	private static void startConsole() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1)8-Puzzle\n2)15-Puzzle");
		int type = sc.nextInt();
		int N = (type == 1) ? 3 : 4;
		Puzzle puzzle = new Puzzle(N, new PuzzleState(N), new PuzzleGoalTest(N));
		Utils.makeRandomInitialState(puzzle, 1279, 60);
		System.out.println("Initial state:\n" + puzzle.getInitialState());
		Solver solver = new Solver(puzzle, new AStar(new ManhattanHeuristic(N)));
		long startTime = System.nanoTime();
		Node n = solver.solve();
		long searchTime = System.nanoTime() - startTime;
		Utils.printSolution(n);
		System.out.println("Number of nodes expanded: "
				+ solver.getAlgorithm().getNodeExpanded());
		System.out.println("Solution found with " + solver.toString()
				+ " in approximately: " + (searchTime / 1000000000.0) + "s");
		sc.close();
	}

	private static void startGUI() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				start();
			}
		});

	}

	protected static void start() {
		JFrame mainGui = new PuzzleGui("Sliding Puzzle");
		mainGui.setVisible(true);
	}

}
