package com.lorenzocinque.puzzle.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PuzzleGui extends JFrame {

	private static final long serialVersionUID = 8087916699886146393L;

	public PuzzleGui(String title) {
		super(title);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(800, 600);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				int answer = JOptionPane.showConfirmDialog(e.getWindow(),
						"Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
					e.getWindow().dispose();
			}
		});
	}

}
