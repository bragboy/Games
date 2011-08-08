package gameoflife.core;


public class TestMain {
	public static void main(String[] args) {
		System.out.print("BLOCK PATTERN");
		Game g = new Game(SampleStates.blockPattern());
		g.play();
		
		System.out.print("BOAT PATTERN");
		g = new Game(SampleStates.boatPattern());
		g.play();
		
		System.out.print("BLINKER PATTERN");
		g = new Game(SampleStates.blinkerPattern());
		g.play();
		
		System.out.print("TOAD PATTERN");
		g = new Game(SampleStates.toadPattern());
		g.play();
	}
}
