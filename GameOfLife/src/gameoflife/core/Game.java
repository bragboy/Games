package gameoflife.core;

public class Game {
	
	private PlayGround playGround;
	
	public Game(boolean[][] state){
		playGround = new PlayGround(state);
	}

	public void tick() {
		playGround.progress();
	}

	public void printCurrentState() {
		playGround.printState();
	}

	public void play() {
		System.out.println("\nBEFORE");
		printCurrentState();
		tick();
		System.out.println("\nAFTER");
		printCurrentState();
	}
}
