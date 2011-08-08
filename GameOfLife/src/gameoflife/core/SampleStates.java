package gameoflife.core;

import java.util.Random;

public class SampleStates {
	
	public static boolean[][] getFreshState(){
		boolean[][] state = new boolean[Globals.MAX_X][Globals.MAX_Y];
		for(int i=0;i<Globals.MAX_X;i++){
			state[i] = new boolean[Globals.MAX_Y];
			for(int j=0;j<Globals.MAX_Y;j++){
				state[i][j] = false;
			}
		}
		return state;
	}
	
	public static boolean[][] blockPattern(){
		boolean[][] state = getFreshState();
		state[1][1] = true;
		state[1][2] = true;
		state[2][1] = true;
		state[2][2] = true;
		return state;
	}
	
	public static boolean[][] boatPattern(){
		boolean[][] state = getFreshState();
		state[0][1] = true;
		state[1][0] = true;
		state[2][1] = true;
		state[0][2] = true;
		state[1][2] = true;
		return state;
	}
	
	public static boolean[][] blinkerPattern(){
		boolean[][] state = getFreshState();
		state[1][1] = true;
		state[1][0] = true;
		state[1][2] = true;
		return state;
	}
	
	public static boolean[][] toadPattern(){
		boolean[][] state = getFreshState();
		state[1][1] = true;
		state[1][2] = true;
		state[1][3] = true;
		state[2][2] = true;
		state[2][3] = true;
		state[2][4] = true;
		return state;
	}

	public static boolean[][] randomPattern() {
		Random r = new Random();
		boolean[][] state = new boolean[Globals.MAX_X][Globals.MAX_Y];
		for(int i=0;i<Globals.MAX_X;i++){
			state[i] = new boolean[Globals.MAX_Y];
			for(int j=0;j<Globals.MAX_Y;j++){
				state[i][j] = r.nextBoolean();
			}
		}
		return state;
	}
}
