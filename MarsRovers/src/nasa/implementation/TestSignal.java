package nasa.implementation;

import nasa.main.ISignal;

public class TestSignal implements ISignal {
	
	private String bounds = "5 5";
	
	private String data = "LMLMLMLMM";
	
	private String initalPos = "2 2 N";

	public String getBounds() {
		return bounds;
	}

	public String getData() {
		return data;
	}

	public String getInitialPos() {
		return initalPos;
	}
	
}
