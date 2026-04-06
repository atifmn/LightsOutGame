package lightsOutGame;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * LightsOutButton class, subclass of JButton, creates the buttons for the
 * Lights Out game
 * 
 * @author Atif Memon
 * @version April 4, 2025
 */
public class LightsOutButton extends JButton {

	private int row;
	private int column;
	private boolean isLightOn;
	private ImageIcon lightOnIcon = new ImageIcon("src/lightsOutGame/light_on.jpg");
	private ImageIcon lightOffIcon = new ImageIcon("src/lightsOutGame/light_off.jpg");

	/**
	 * Constructor of the LightsOutButton class, initializes the button based on the
	 * given row and column parameters, also sets the image of the button which is a
	 * "lightOffIcon" by default
	 * 
	 * @param row    the row of the button
	 * @param column the column of the button
	 */
	public LightsOutButton(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		this.isLightOn = false;

		Image lightOffImg = this.lightOffIcon.getImage();
		Image scaledlightOff = lightOffImg.getScaledInstance(130, 110, 1000);
		this.lightOffIcon = new ImageIcon(scaledlightOff);

		Image lightOnImg = this.lightOnIcon.getImage();
		Image scaledlightOn = lightOnImg.getScaledInstance(130, 110, 1000);
		this.lightOnIcon = new ImageIcon(scaledlightOn);

		this.setIcon(lightOffIcon);
	}

	/**
	 * Toggles the specified button object, turns it on and changes the button icon
	 * to "lightOnIcon" if the button was previously off, and turns it off and
	 * changes the button icon to "lightOffIcon" if the button was previously on
	 */
	public void toggle() {
		if (this.isLightOn == false) {
			this.isLightOn = true;
			this.setIcon(lightOnIcon);
		} else if (this.isLightOn == true) {
			this.isLightOn = false;
			this.setIcon(lightOffIcon);
		}
	}

	/**
	 * Gets the row of the specified button object
	 * 
	 * @return the row of the specified button
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Gets the column of the specified button object
	 * 
	 * @return the column of the specified button
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * Check whether the specified button object is on or off
	 * 
	 * @return a boolean expressing whether the given button object in on or off
	 */
	public boolean isOn() {
		return this.isLightOn;
	}

	private static final long serialVersionUID = 1L;

}
