package com.logicaldk.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.logicaldk.*;
import com.logicaldk.enums.*;
import com.logicaldk.javamine.GameBoard;
import com.logicaldk.model.LogicModel;
import com.logicaldk.winmine.WinMineBoard;

/**
 * Main driver for the minesweeper project.
 * @author Dhanendra Kumar
 *
 */
public class Driver extends JFrame implements ActionListener {
	/*
	 * Graphical User Interface Components.
	 */
	private JButton start;
	private JRadioButton winMine, walterMine;
	private JRadioButton beginner, intermediate, advance;
	private ButtonGroup mineGroup;
	private JMenuBar menuBar;
	private JMenu optionsMenu;
	private JMenuItem waltersDefaults, loopAndDelayOptions;
	private JLabel games, gamesLabel, wins, winsLabel, loss, lossLabel, 
					winPercentage, winPercentageLabel;
	private JPanel infoPanel, northPanel, optionPanel;
	
	/*
	 * Statistic Variables.
	 */
	private int numWins, numLosses, numGames = 0; 
	private double numWinPercentage = 0;
	
	/*
	 * Settings Variables.
	 */
	private int rows = 8;
	private int columns = 8;
	private int bomb = 10;
	private int loop = 1;
	private LogicModel lm = new LogicModel();
	private MinesweeperBoard mb = new GameBoard(rows,columns,bomb);
	private boolean hasBeenPlayed = false;
	private boolean hasChangedBoardType = true;
	/**
	 * Default constructor. Initializes components and 
	 * displays the frame
	 */
	public Driver(){
		initializeGuiComponents();
		addComponentsToFrameAndDisplay();
		lm.setBoard(mb);
	}
	
	/**
	 * Initializes each GUI component.
	 */
	private void initializeGuiComponents() {
		northPanel = new JPanel(new GridLayout(1,2));

		start = new JButton("Start");
		start.addActionListener(this);

		optionPanel = new JPanel(new GridLayout(3,1));
		mineGroup = new ButtonGroup();

		beginner = new JRadioButton("Beginner");
		beginner.setSelected(true);
		intermediate = new JRadioButton("Intermediate");
		advance = new JRadioButton("Advance");

		mineGroup.add(beginner);
		mineGroup.add(intermediate);
		mineGroup.add(advance);

		beginner.addActionListener(this);
		intermediate.addActionListener(this);
		advance.addActionListener(this);

		optionPanel.add(beginner);
		optionPanel.add(intermediate);
		optionPanel.add(advance);

		northPanel.add(optionPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Organizes the components in the frame, adds components and then displays.
	 */
	private void addComponentsToFrameAndDisplay() {
		// setJMenuBar(menuBar);
		setLayout(new GridLayout(2,1));
		add(northPanel);
		add(start);
		pack();
		setVisible(true);
	}

	/**
	 * Main that runs the whole Minesweeper program. Takes no args.
	 * @param args Ignored.
	 */
	public static void main(String[] args) {
		new Driver();
	}

	/**
	 * Handles all actions on the GUI.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * If it is the start button play Minesweeper.
		 * Which Minesweeper is played depends on the current options
		 * set in the GUI. 
		 */
		System.out.println("Driver: " + e.getSource());
		if (e.getSource().equals(start)) {
	
			for (int i = 0; i < loop; i++) {
				if (hasBeenPlayed) {
					mb.resetBoard();
				}
				if ( mb.getGameState() == GameState.Lost  || mb.getGameState() == GameState.Won) {
					mb = new GameBoard(rows,columns,bomb);
					lm.setBoard(mb);
					hasChangedBoardType = true;
				}
				if(hasChangedBoardType){
					mb.displayBoard();
					hasChangedBoardType = false;
				}
				hasBeenPlayed = true;
			}
		} else if (e.getSource() instanceof JRadioButton) {
			System.out.println("e.getSource(): " + e.getSource());
			/*
			 * If one of the radio buttons are clicked, this is called.
			 * The Radio Buttons change which Minesweeper is run.
			 */
			if(e.getSource().equals(beginner)) {
				rows = 8;
				columns = 8;
				bomb = 10;
			} else if(e.getSource().equals(intermediate)) {
				rows = 16;
				columns = 16;
				bomb = 36;
			} else if(e.getSource().equals(advance)) {
				rows = 24;
				columns = 24;
				bomb = 91;
			}
			mb = new GameBoard(rows,columns,bomb);
			lm.setBoard(mb);
			hasChangedBoardType = true;
			hasBeenPlayed = false;
		}
	}
	
	private void calculatePercentageAndSetLabels() {
		wins.setText(numWins + "");
		loss.setText(numLosses + "");
		games.setText(numGames + "");
		DecimalFormat df = new DecimalFormat("0.##");
		winPercentage.setText(df.format(((double) numWins )/numGames * 100));
	}
}
