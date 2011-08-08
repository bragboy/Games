package nasa.main;

import nasa.exceptions.InvalidInputException;
import nasa.exceptions.OutOfRangeException;

public class Rover {
	ControlPanel parentControl;
	
	Heading currentHeading;
	
	public Rover(ControlPanel control){
		this.parentControl = control;
	}

	public Heading getCurrentHeading() {
		return currentHeading;
	}

	public void setCurrentHeading(Heading currentHeading) {
		this.currentHeading = currentHeading;
	}

	public void setData(String data) throws InvalidInputException, OutOfRangeException{
		parseData(data);
	}

	private void parseData(String data) throws InvalidInputException, OutOfRangeException{
		
		byte[] bytes = data.trim().getBytes();
		
		for(int i=0;i<bytes.length;i++){
			currentHeading.parseCommand(bytes[i]);
		}
	}
}
