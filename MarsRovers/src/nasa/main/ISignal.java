package nasa.main;

public interface ISignal {
	
	/**
	 * This returns the upper right corner of the Rectangle in string format
	 */
	public String getBounds();

	/**
	 * This returns the data which includes movement and rotation commands
	 */
	public String getData();

	/**
	 * This returns the initial position of the rover
	 */
	public String getInitialPos();
}
