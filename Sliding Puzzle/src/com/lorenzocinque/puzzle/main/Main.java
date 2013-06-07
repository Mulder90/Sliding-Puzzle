package com.lorenzocinque.puzzle.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.lorenzocinque.puzzle.gui.PuzzleGui;

public class Main {

	public static void main(String[] args) {
		startGUI();
	}

	private static void startGUI() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}
				PuzzleGui window = new PuzzleGui();
				window.getFrameSlidingPuzzle().setVisible(true);
				window.getFrameSlidingPuzzle().setLocationRelativeTo(null);
				window.createDialog();
			}
		});

	}

}
