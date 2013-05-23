package com.lorenzocinque.puzzle.gui;

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
import javax.swing.JOptionPane;
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

public class PuzzleGui {

	private JFrame frameSlidingPuzzle;
	private JPanel panel;
	private JPanel creationPanel;
	private JPanel algorithmPanel;
	private JTextArea textArea;
	private JTextField seedTextField;
	private JTextField scramblesTextField;
	private JButton createButton;
	private JButton solveButton;
	private JComboBox<String> dimensionComboBox;
	private JComboBox<String> algorithmComboBox;
	private JComboBox<String> heuristicComboBox;
	private DefaultComboBoxModel<String> eightAlgorithm;
	private DefaultComboBoxModel<String> fifteenAlgorithm;

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
		frameSlidingPuzzle.setResizable(false);
	}

	private void initialize() {
		setFrameSlidingPuzzle(new JFrame());
		getFrameSlidingPuzzle().setTitle("Sliding Puzzle");
		getFrameSlidingPuzzle().setBounds(100, 100, 600, 600);
		getFrameSlidingPuzzle().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrameSlidingPuzzle().getContentPane().setLayout(
				new BoxLayout(getFrameSlidingPuzzle().getContentPane(),
						BoxLayout.Y_AXIS));

		panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 50));
		getFrameSlidingPuzzle().getContentPane().add(panel);

		textArea = new JTextArea();
		textArea.setEditable(false);
		Font font = new Font("Verdana", Font.BOLD, 11);
		textArea.setFont(font);
		JScrollPane scrollPane = new JScrollPane(textArea);
		getFrameSlidingPuzzle().getContentPane().add(scrollPane);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		creationPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) creationPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(creationPanel);

		algorithmPanel = new JPanel();
		flowLayout = (FlowLayout) algorithmPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(algorithmPanel);

		dimensionComboBox = new JComboBox<String>();
		creationPanel.add(dimensionComboBox);
		dimensionComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "8 Puzzle", "15 Puzzle" }));

		algorithmComboBox = new JComboBox<String>();
		algorithmPanel.add(algorithmComboBox);
		eightAlgorithm = new DefaultComboBoxModel<String>(new String[] { "A*",
				"IDA*", "Breadth First Search", "Iterative DLS" });
		fifteenAlgorithm = new DefaultComboBoxModel<String>(new String[] {
				"A*", "IDA*" });
		algorithmComboBox.setModel(eightAlgorithm);

		heuristicComboBox = new JComboBox<String>();
		algorithmPanel.add(heuristicComboBox);
		heuristicComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Manhattan", "Misplaced" }));

		JLabel seedLabel = new JLabel("Seed:");
		creationPanel.add(seedLabel);
		seedTextField = new JTextField();
		seedTextField.setText("1278");
		seedTextField.setColumns(6);
		creationPanel.add(seedTextField);

		JLabel scramblesLabel = new JLabel("Scrambles:");
		creationPanel.add(scramblesLabel);
		scramblesTextField = new JTextField();
		scramblesTextField.setText("60");
		scramblesTextField.setColumns(4);
		creationPanel.add(scramblesTextField);

		createButton = new JButton("Create");
		final JLabel contact = new JLabel();
		contact.setText("<html>mail: <a href=\"\">lore.cinque@gmail.com</a></html>");
		JButton aboutButton = new JButton("About");
		creationPanel.add(createButton);
		creationPanel.add(aboutButton);

		solveButton = new JButton("Solve");
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
			}
		});

		algorithmComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String alg = algorithmComboBox.getSelectedItem().toString();
				if (alg.equals("Breadth First Search")
						|| alg.equals("Iterative DLS")) {
					heuristicComboBox.setEnabled(false);
				} else {
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
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(getFrameSlidingPuzzle(),
						"Coded by Lorenzo Cinque\n" + contact.getText());
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
							}
						}
					}

					@Override
					protected void done() {
						try {
							solveButton.setText("Solve");
							textArea.append(get().toString() + "\n");
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						textArea.append("Search finished with "
								+ solver.toString() + " in approximately: "
								+ (searchTime / 1000000000.0) + "s");
					}
				};

				worker.execute();
			}
		});
	}
}
