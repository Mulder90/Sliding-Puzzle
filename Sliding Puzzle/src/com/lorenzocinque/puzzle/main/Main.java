package com.lorenzocinque.puzzle.main;

import java.awt.EventQueue;

import com.lorenzocinque.puzzle.gui.PuzzleGui;

public class Main {

	public static void main(String[] args) {
		startGUI();
	}

	private static void startGUI() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PuzzleGui window = new PuzzleGui();
					window.getFrameSlidingPuzzle().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
