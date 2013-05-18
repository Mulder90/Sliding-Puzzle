package com.lorenzocinque.puzzle.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.PuzzleGoalTest;
import com.lorenzocinque.puzzle.core.PuzzleState;
import com.lorenzocinque.puzzle.core.Solution;
import com.lorenzocinque.puzzle.core.Solver;
import com.lorenzocinque.puzzle.core.Utils;
import com.lorenzocinque.puzzle.core.heuristic.ManhattanHeuristic;
import com.lorenzocinque.puzzle.core.searchalgorithm.AStar;

public class PuzzleGui {

	private JFrame frameSlidingPuzzle;
	private JTextArea textArea;
	private JTextField seedTextField;
	private JTextField scramblesTextField;
	private int N = 3;
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
		frameSlidingPuzzle.setResizable(false);
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
		panel.setMaximumSize(new Dimension(32767, 50));
		getFrameSlidingPuzzle().getContentPane().add(panel);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		getFrameSlidingPuzzle().getContentPane().add(scrollPane);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel creationPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) creationPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(creationPanel);

		JPanel algorithmPanel = new JPanel();
		flowLayout = (FlowLayout) algorithmPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(algorithmPanel);

		final JComboBox<String> dimensionComboBox = new JComboBox<String>();
		creationPanel.add(dimensionComboBox);
		dimensionComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "8 Puzzle", "15 Puzzle" }));

		dimensionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = dimensionComboBox.getSelectedIndex();
				N = (i == 0) ? 3 : 4;
			}
		});

		JLabel seedLabel = new JLabel("Seed:");
		creationPanel.add(seedLabel);
		seedTextField = new JTextField();
		creationPanel.add(seedTextField);
		seedTextField.setText("1200");
		seedTextField.setColumns(10);

		JLabel scramblesLabel = new JLabel("Scrambles:");
		creationPanel.add(scramblesLabel);
		scramblesTextField = new JTextField();
		creationPanel.add(scramblesTextField);
		scramblesTextField.setText("60");
		scramblesTextField.setColumns(3);

		final JComboBox<String> algorithmComboBox = new JComboBox<String>();
		algorithmPanel.add(algorithmComboBox);
		algorithmComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "A*", "IDA*", "Breadth First Search" }));

		final JComboBox<String> heuristicComboBox = new JComboBox<String>();
		algorithmPanel.add(heuristicComboBox);
		heuristicComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Manhattan", "Misplaced" }));

		JButton createButton = new JButton("Create");
		creationPanel.add(createButton);

		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				puzzle = new Puzzle(N, new PuzzleState(N),
						new PuzzleGoalTest(N));
				int seed = Integer.parseInt(seedTextField.getText());
				int scrambles = Integer.parseInt(scramblesTextField.getText());
				Utils.makeRandomInitialState(puzzle, seed, scrambles);
				textArea.append("\nInitial state:\n" + puzzle.getInitialState()
						+ "\n");
			}
		});

		JButton solveButton = new JButton("Solve");
		algorithmPanel.add(solveButton);

		solveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Solver solver = new Solver(puzzle, new AStar(
						new ManhattanHeuristic(N)));
				long startTime = System.nanoTime();
				Solution solution = solver.solve();
				long searchTime = System.nanoTime() - startTime;
				textArea.append(solution.toString() + "\n");
				textArea.append("Search finished with " + solver.toString()
						+ " in approximately: " + (searchTime / 1000000000.0)
						+ "s");
			}
		});
	}

}
