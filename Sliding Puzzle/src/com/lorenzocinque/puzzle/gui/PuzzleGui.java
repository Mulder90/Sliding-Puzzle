package com.lorenzocinque.puzzle.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;

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
import javax.swing.SwingWorker;

import com.lorenzocinque.puzzle.core.Puzzle;
import com.lorenzocinque.puzzle.core.PuzzleGoalTest;
import com.lorenzocinque.puzzle.core.PuzzleState;
import com.lorenzocinque.puzzle.core.Solution;
import com.lorenzocinque.puzzle.core.Solver;
import com.lorenzocinque.puzzle.core.Utils;
import com.lorenzocinque.puzzle.core.heuristic.Heuristic;
import com.lorenzocinque.puzzle.core.heuristic.ManhattanHeuristic;
import com.lorenzocinque.puzzle.core.heuristic.MisplacedHeuristic;
import com.lorenzocinque.puzzle.core.searchalgorithm.AStar;
import com.lorenzocinque.puzzle.core.searchalgorithm.BreadthFirstSearch;
import com.lorenzocinque.puzzle.core.searchalgorithm.IDAStar;
import com.lorenzocinque.puzzle.core.searchalgorithm.IterativeDLS;
import com.lorenzocinque.puzzle.core.searchalgorithm.SearchAlgorithm;
import com.lorenzocinque.puzzle.core.searchalgorithm.WeightedAStar;

public class PuzzleGui {

	private JFrame frameSlidingPuzzle;

	private int N;
	private SearchAlgorithm algorithm;
	private Heuristic heuristic;
	private Puzzle puzzle;

	public PuzzleGui() {
		initializeDefaultValue();
		initialize();
	}

	private void initializeDefaultValue() {
		N = 3;
		heuristic = new ManhattanHeuristic(N);
		algorithm = new AStar(heuristic);
	}

	public JFrame getFrameSlidingPuzzle() {
		return frameSlidingPuzzle;
	}

	public void setFrameSlidingPuzzle(JFrame frameSlidingPuzzle) {
		this.frameSlidingPuzzle = frameSlidingPuzzle;
	}

	private void initialize() {
		setFrameSlidingPuzzle(new JFrame());
		frameSlidingPuzzle.setTitle("Sliding Puzzle");
		frameSlidingPuzzle.setResizable(false);
		frameSlidingPuzzle.setBounds(100, 100, 600, 600);
		frameSlidingPuzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSlidingPuzzle.getContentPane().setLayout(
				new BoxLayout(frameSlidingPuzzle.getContentPane(),
						BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 50));
		getFrameSlidingPuzzle().getContentPane().add(panel);

		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		Font font = new Font("Verdana", Font.BOLD, 11);
		textArea.setFont(font);
		JScrollPane scrollPane = new JScrollPane(textArea);
		frameSlidingPuzzle.getContentPane().add(scrollPane);
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

		final JComboBox<String> algorithmComboBox = new JComboBox<String>();
		algorithmPanel.add(algorithmComboBox);
		final DefaultComboBoxModel<String> eightAlgorithm = new DefaultComboBoxModel<String>(
				new String[] { "A*", "Weighted A*", "IDA*",
						"Breadth First Search", "Iterative DLS" });
		final DefaultComboBoxModel<String> fifteenAlgorithm = new DefaultComboBoxModel<String>(new String[] {
				"A*", "Weighted A*", "IDA*" });
		algorithmComboBox.setModel(eightAlgorithm);

		final JComboBox<String> heuristicComboBox = new JComboBox<String>();
		algorithmPanel.add(heuristicComboBox);
		heuristicComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Manhattan", "Misplaced" }));

		JLabel seedLabel = new JLabel("Seed:");
		creationPanel.add(seedLabel);
		final JTextField seedTextField = new JTextField();
		seedTextField.setText("1278");
		seedTextField.setColumns(6);
		creationPanel.add(seedTextField);

		JLabel scramblesLabel = new JLabel("Scrambles:");
		creationPanel.add(scramblesLabel);
		final JTextField scramblesTextField = new JTextField();
		scramblesTextField.setText("60");
		scramblesTextField.setColumns(4);
		creationPanel.add(scramblesTextField);

		JButton createButton = new JButton("Create");
		JButton aboutButton = new JButton("About");
		creationPanel.add(createButton);
		creationPanel.add(aboutButton);

		JLabel weightLabel = new JLabel("Weight:");
		algorithmPanel.add(weightLabel);
		final JTextField weightTextField = new JTextField();
		weightTextField.setText("2");
		weightTextField.setColumns(3);
		algorithmPanel.add(weightTextField);
		weightTextField.setEnabled(false);

		final JButton solveButton = new JButton("Solve");
		solveButton.setEnabled(false);
		algorithmPanel.add(solveButton);
		

		dimensionComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = dimensionComboBox.getSelectedIndex();
				N = (i == 0) ? 3 : 4;
				if (N == 3) {
					algorithmComboBox.setModel(eightAlgorithm);
					if (algorithmComboBox.getSelectedItem().toString()
							.equals("Breadth First Search")
							|| algorithmComboBox.getSelectedItem().toString()
									.equals("Iterative DLS"))
						heuristicComboBox.setEnabled(false);
				} else {
					heuristicComboBox.setEnabled(true);
					algorithmComboBox.setModel(fifteenAlgorithm);
				}
				if (algorithmComboBox.getSelectedItem().toString()
						.equals("Weighted A*")) {
					weightTextField.setEnabled(true);
				} else {
					weightTextField.setEnabled(false);
				}
			}
		});

		algorithmComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String alg = algorithmComboBox.getSelectedItem().toString();
				if (alg.equals("Breadth First Search")
						|| alg.equals("Iterative DLS")) {
					heuristicComboBox.setEnabled(false);
					weightTextField.setEnabled(false);
				} else if (alg.equals("Weighted A*")) {
					weightTextField.setEnabled(true);
				} else {
					weightTextField.setEnabled(false);
					heuristicComboBox.setEnabled(true);
				}
			}
		});

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
				solveButton.setEnabled(true);
			}
		});

		aboutButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				AboutDialog dialog = new AboutDialog(frameSlidingPuzzle);
				SwingUtils.fadeIn(dialog);
			};
		});

		solveButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				SwingWorker<Solution, Void> worker = new SwingWorker<Solution, Void>() {

					Solver solver;
					long searchTime;

					@Override
					protected Solution doInBackground() throws Exception {
						solveButton.setText("Solving...");
						solveButton.setEnabled(false);
						setAlgorithm();
						solver = new Solver(puzzle, algorithm);
						long startTime = System.nanoTime();
						Solution solution = solver.solve();
						searchTime = System.nanoTime() - startTime;
						return solution;
					}

					private void setAlgorithm() {
						String heu = heuristicComboBox.getSelectedItem()
								.toString();
						if (heu.equals("Manhattan")) {
							heuristic = new ManhattanHeuristic(N);
						} else if (heu.equals("Misplaced")) {
							heuristic = new MisplacedHeuristic(N);
						}
						String alg = algorithmComboBox.getSelectedItem()
								.toString();
						if (alg.equals("Breadth First Search")) {
							heuristicComboBox.setEnabled(false);
							algorithm = new BreadthFirstSearch();
						} else if (alg.equals("Iterative DLS")) {
							heuristicComboBox.setEnabled(false);
							algorithm = new IterativeDLS();
						} else {
							heuristicComboBox.setEnabled(true);
							if (alg.equals("A*")) {
								algorithm = new AStar(heuristic);
							} else if (alg.equals("IDA*")) {
								algorithm = new IDAStar(heuristic);
							} else if (alg.equals("Weighted A*")) {
								algorithm = new WeightedAStar(heuristic,
										Integer.parseInt(weightTextField
												.getText()));
							}
						}
					}

					@Override
					protected void done() {
						try {
							solveButton.setText("Solve");
							textArea.append(get().toString() + "\n");
						} catch (InterruptedException | ExecutionException ex) {
							ex.printStackTrace();
						}
						textArea.append("Search finished with "
								+ solver.toString() + " in approximately: "
								+ (searchTime / 1000000000.0) + "s");
					}
				};
				
				if(solveButton.isEnabled())
					worker.execute();
			}
		});
	}

	public void createDialog() {
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				new AboutDialog(frameSlidingPuzzle);
				return null;
			}
		};
		
		worker.execute();
	}
}
