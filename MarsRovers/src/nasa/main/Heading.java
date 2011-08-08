package nasa.main;

import java.util.StringTokenizer;

import nasa.exceptions.InvalidInputException;
import nasa.exceptions.OutOfRangeException;

public class Heading {
	
	private int x;
	
	private int y;
	
	private byte direction;
	
	public Heading(){
		
	}
	
	public Heading(String position) throws InvalidInputException, OutOfRangeException{
		parse(position);
	}
	
	private void parse(String position) throws InvalidInputException, OutOfRangeException {
		
		StringTokenizer tokens = new StringTokenizer(position);
		if(tokens.hasMoreTokens()){
			
			try{
				x = Integer.parseInt(tokens.nextToken());
			
				y = Integer.parseInt(tokens.nextToken());
			}catch(NumberFormatException ne){
				throw new InvalidInputException("Invalid number!!");
			}
			
			direction = tokens.nextToken().getBytes()[0];
		}
		
		if(!verifyBounds()){
			throw new OutOfRangeException("Invalid inital position!!!");
		}
	}
	
	public void parseCommand(byte command) throws InvalidInputException, OutOfRangeException{
		
		switch(command){
		case Constants.ROTATE_LEFT:
			rotateLeft();
			break;
		case Constants.ROTATE_RIGHT:
			rotateRight();
			break;
		case Constants.MOVE:
			move();
			break;
		default:
			throw new InvalidInputException("Invalid signal");
		}
	}

	private void move() throws OutOfRangeException{
		switch (direction) {
		case Constants.DIRECTION_NORTH:
			y += 1;
			break;
		case Constants.DIRECTION_EAST:
			x += 1;
			break;
		case Constants.DIRECTION_SOUTH:
			y -= 1;
			break;
		case Constants.DIRECTION_WEST:
			x -= 1;
			break;
		}
		if(!verifyBounds()){
			throw new OutOfRangeException("Rover exceeding range!!!");
		}
	}

	private boolean verifyBounds() {
		if((x>ControlPanel.BOUNDS_X || y>ControlPanel.BOUNDS_Y)
				|| x<0 || y<0){
			return false;
		}
		return true;
	}

	private void rotateRight() {
		switch(direction){
		case Constants.DIRECTION_NORTH:
			direction = Constants.DIRECTION_EAST;
			break;
		case Constants.DIRECTION_EAST:
			direction = Constants.DIRECTION_SOUTH;
			break;
		case Constants.DIRECTION_SOUTH:
			direction = Constants.DIRECTION_WEST;
			break;
		case Constants.DIRECTION_WEST:
			direction = Constants.DIRECTION_NORTH;
			break;
		}
	}

	private void rotateLeft() {
		switch(direction){
		case Constants.DIRECTION_NORTH:
			direction = Constants.DIRECTION_WEST;
			break;
		case Constants.DIRECTION_WEST:
			direction = Constants.DIRECTION_SOUTH;
			break;
		case Constants.DIRECTION_SOUTH:
			direction = Constants.DIRECTION_EAST;
			break;
		case Constants.DIRECTION_EAST:
			direction = Constants.DIRECTION_NORTH;
			break;
		}
	}

	public String toString(){
		String toString = null;
		
		toString = x+" "+y+" "+toChar(direction);
		
		return toString;
	}

	private char toChar(byte direction) {
		String s = String.valueOf(direction);
		
		char c = (char)Integer.parseInt(s);
		
		return c;
	}

	
}
