package nasa.main;

import java.util.StringTokenizer;

import nasa.exceptions.InvalidInputException;
import nasa.exceptions.OutOfRangeException;

public class ControlPanel {

	private Rover rover;

	/**
	 * Default bounding value for X
	 */
	public static int BOUNDS_X = 5;

	/**
	 * Default bounding value for Y
	 */
	public static int BOUNDS_Y = 5;

	public ControlPanel(String bounds) throws InvalidInputException {
		parse(bounds);
		rover = new Rover(this);
	}

	private void parse(String bounds) throws InvalidInputException {

		StringTokenizer tokens = new StringTokenizer(bounds);

		if (tokens.hasMoreTokens()) {

			try {
				BOUNDS_X = Integer.parseInt(tokens.nextToken());

				BOUNDS_Y = Integer.parseInt(tokens.nextToken());
			} catch (NumberFormatException e) {
				throw new InvalidInputException("Invalid bounding values");
			}
		}

		if (BOUNDS_X <= 0 || BOUNDS_Y <= 0) {
			throw new InvalidInputException(
					"Bounding values should be greater than or equal to 1");
		}

	}

	public void setRoverPos(Heading heading) {
		rover.setCurrentHeading(heading);
	}

	public Heading getRoverPos() {
		return rover.getCurrentHeading();
	}

	public void setData(String data) throws InvalidInputException,
			OutOfRangeException {
		rover.setData(data);
	}

}
