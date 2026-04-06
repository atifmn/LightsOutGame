package lightsOutGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * LightsOutFrame class, subclass of JFrame and implements action listener,
 * creates the main part of the "Lights Out" game
 * 
 * @author 	Atif Memon
 * @version April 4, 2025
 */
public class LightsOutFrame extends JFrame implements ActionListener {

	private LightsOutButton[][] buttonArr = new LightsOutButton[5][5];
	private JButton randomize;
	private JButton manualSetup;
	private boolean inManualSetup;

	/**
	 * Constructor of the LightsOutFrame class, creates the buttons, the window, and
	 * starts/randomizes the game
	 */
	public LightsOutFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.inManualSetup = false;

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 5, 2, 2));

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				buttonArr[row][col] = new LightsOutButton(row, col);
				panel.add(buttonArr[row][col]);
				buttonArr[row][col].addActionListener(this);
			}
		}

		this.randomize = new JButton("Randomize");
		this.manualSetup = new JButton("Enter Manual Setup");

		this.randomize.addActionListener(this);
		this.manualSetup.addActionListener(this);

		JPanel otherPanel = new JPanel();

		otherPanel.add(this.randomize);
		otherPanel.add(this.manualSetup);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(otherPanel, BorderLayout.SOUTH);

		this.setTitle("Lights Out");
		this.setContentPane(mainPanel);
		this.setPreferredSize(new Dimension(600, 600));
		this.pack();

		randomize();
	}

	/**
	 * Randomize method, randomly clicks 9 buttons to turn on in order to create a
	 * solvable puzzle for the Lights out game
	 */
	public void randomize() {
		Random row = new Random();
		Random col = new Random();

		for (int count = 0; count < 9; count++) {
			this.toggleLight(row.nextInt(buttonArr.length), col.nextInt(buttonArr[0].length));
		}
	}

	/**
	 * Checks if the light at the specified row and column is turned on
	 * 
	 * @param row    the row of the button
	 * @param column the column of the button
	 * @return returns a boolean expressing whether the light at the given location
	 *         is turned on or off
	 */
	public boolean lightIsOn(int row, int column) {
		if ((row < 0 || row > 4) && (column < 0 || column > 4)) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		return buttonArr[row][column].isOn();
	}

	/**
	 * Toggles the light at the specified row and column, along with its neighbors
	 * 
	 * @param row    the row of the button
	 * @param column the column of the button
	 */
	public void toggleLight(int row, int column) {
		if ((row < 0 || row > 4) || (column < 0 || column > 4)) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		buttonArr[row][column].toggle();

		if (row > 0) {
			buttonArr[row - 1][column].toggle();
		}
		if (row < 4) {
			buttonArr[row + 1][column].toggle();
		}

		if (column > 0) {
			buttonArr[row][column - 1].toggle();
		}
		if (column < 4) {
			buttonArr[row][column + 1].toggle();
		}

	}

	/**
	 * Checks whether a button has been clicked and performs an action based on the
	 * type of the button, whether the game is in manual setup, and if the game has
	 * been completed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof LightsOutButton && this.inManualSetup == true) {
			LightsOutButton button = (LightsOutButton) e.getSource();
			button.toggle();
		}

		if (e.getSource() instanceof LightsOutButton && this.inManualSetup == false) {
			LightsOutButton button = (LightsOutButton) e.getSource();
			this.toggleLight(button.getRow(), button.getColumn());
		}

		if (e.getSource() == this.randomize) {
			randomize();
		}

		if (e.getSource() == this.manualSetup) {
			if (this.inManualSetup) {
				this.inManualSetup = false;
				this.manualSetup.setText("Enter Manual Setup");
			} else {
				this.inManualSetup = true;
				this.manualSetup.setText("Exit Manual Setup");
			}
		}

		if (lightsOff() && this.inManualSetup == false) {
			JOptionPane.showMessageDialog(this, "Congratulations, You turned off all the lights!");
		}
	}

	/**
	 * Checks if all of the lights in game have been turned off
	 * 
	 * @return a boolean that expresses whether all of lights in game have been
	 *         turned off or not
	 */
	public boolean lightsOff() {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (lightIsOn(row, col)) {
					return false;
				}
			}

		}
		return true;
	}

	// METHODS FOR TESTING THE "LightsOutFrame" CLASS

	/**
	 * Testing Method that simulates clicking a specified light button
	 * 
	 * @param row row of the button that will be clicked
	 * @param col column of the button that will be clicked
	 */
	public void testingButtonClick(int row, int col) {
		this.buttonArr[row][col].doClick();
	}

	/**
	 * Testing Method that simulates clicking the Manual setup button
	 */
	public void testingManualButtonClick() {
		this.manualSetup.doClick();
	}

	/**
	 * Testing method that checks whether the game is in manual setup
	 * 
	 * @return a boolean that expresses whether the game is in manual setup or not
	 */
	public boolean manualSetupStatus() {
		return this.inManualSetup;
	}

	/**
	 * Testing method that turns off all the lights in the Lights Out game
	 */
	public void clearLights() {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (this.buttonArr[row][col].isOn()) {
					this.buttonArr[row][col].toggle();
				}
			}

		}
	}

	private static final long serialVersionUID = 1L;

}
