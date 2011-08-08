package nasa.implementation;

import nasa.main.Nasa;

public class TestMain {

	public static void main(String args[]) {
		try {
			TestSignal test = new TestSignal();

			Nasa n = new Nasa(test);

			n.execute();

			String finalPos = n.getFinalPosition();

			System.out.println("Final position is : " + finalPos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}