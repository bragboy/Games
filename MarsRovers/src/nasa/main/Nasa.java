package nasa.main;

import nasa.exceptions.InvalidInputException;
import nasa.exceptions.OutOfRangeException;

public class Nasa {
	
	private ISignal signal;
	
	private ControlPanel controlPanel;
	
	public Nasa(ISignal signal){
		this.signal = signal;
	}
	
	public void execute() throws InvalidInputException, OutOfRangeException {
		
		controlPanel = new ControlPanel(signal.getBounds());
		
		controlPanel.setRoverPos(new Heading(signal.getInitialPos()));
		
		controlPanel.setData(signal.getData());
		
	}
	
	public String getFinalPosition(){
		
		Heading finalHeading = controlPanel.getRoverPos();
		
		return finalHeading.toString();
	}
	
}
