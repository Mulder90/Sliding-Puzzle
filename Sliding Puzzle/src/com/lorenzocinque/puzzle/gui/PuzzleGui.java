package com.lorenzocinque.puzzle.gui;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lorenzocinque.puzzle.core.Puzzle;

public class PuzzleGui {

	private JFrame frameSlidingPuzzle;
	private JTextField seedTextField;
	private JTextField scramblesTextField;
	private int N;
	private Puzzle puzzle; 

	/**
	 * Create the application.
	 */
	public PuzzleGui() {
		initialize();
	}

	public JFrame getFrameSlidingPuzzle() {
		return frameSlidingPuzzle;
	}

	public void setFrameSlidingPuzzle(JFrame frameSlidingPuzzle) {
		this.frameSlidingPuzzle = frameSlidingPuzzle;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrameSlidingPuzzle(new JFrame());
		getFrameSlidingPuzzle().setTitle("Sliding Puzzle");
		getFrameSlidingPuzzle().setBounds(100, 100, 600, 600);
		getFrameSlidingPuzzle().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrameSlidingPuzzle().getContentPane().setLayout(
				new BoxLayout(getFrameSlidingPuzzle().getContentPane(),
						BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getFrameSlidingPuzzle().getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JComboBox dimensionComboBox = new JComboBox();
		dimensionComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				N = Integer.parseInt(e.getItem().toString());
				System.out.println(N);
			}
		});
		
		dimensionComboBox.setModel(new DefaultComboBoxModel(new String[] { "3",
				"4" }));
		panel.add(dimensionComboBox);

		JComboBox algorithmComboBox = new JComboBox();
		algorithmComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"A*", "IDA*", "Breadth First Search" }));
		panel.add(algorithmComboBox);

		JComboBox heuristicComboBox = new JComboBox();
		heuristicComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Manhattan", "Misplaced" }));
		panel.add(heuristicComboBox);

		seedTextField = new JTextField();
		seedTextField.setText("8596");
		panel.add(seedTextField);
		seedTextField.setColumns(10);

		scramblesTextField = new JTextField();
		scramblesTextField.setText("60");
		panel.add(scramblesTextField);
		scramblesTextField.setColumns(10);

		JButton buttonSolve = new JButton("Solve");
		buttonSolve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(buttonSolve);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		getFrameSlidingPuzzle().getContentPane().add(textArea);
	}

}
