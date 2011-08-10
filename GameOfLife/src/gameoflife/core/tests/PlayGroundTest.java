package gameoflife.core.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gameoflife.core.Globals;
import gameoflife.core.PlayGround;
import gameoflife.core.SampleStates;

import org.junit.Before;
import org.junit.Test;

public class PlayGroundTest {
	
	@Before
	public void setUp() throws Exception {
		Globals.MAX_X = 6;
		Globals.MAX_Y = 6;
	}
	
	@Test
	public void testAdjacentLivesCount() {
		PlayGround pGround = null;
		
		/*   CASE 1
		 *   - - - - A -
		 *   - A - - - -
		 *   A - - - - A
		 *   - A - - A -
		 *   - - - - - -
		 *   A - A - - A
		 *   
		 *   A ==> ALIVE
		 *   - ==> DEAD
		 *   
		 */
		boolean[][] sampleState = customState();
		pGround = new PlayGround(sampleState);
		
		assertEquals("Center life", 1, pGround.adjacentLivesCount(0, 0));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(0, 1));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(0, 2));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(0, 3));
		assertEquals("Center life", 0, pGround.adjacentLivesCount(0, 4));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(0, 5));
		
		assertEquals("Center life", 2, pGround.adjacentLivesCount(1, 0));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(1, 1));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(1, 2));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(1, 3));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(1, 4));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(1, 5));
		
		assertEquals("Center life", 2, pGround.adjacentLivesCount(2, 0));
		assertEquals("Center life", 3, pGround.adjacentLivesCount(2, 1));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(2, 2));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(2, 3));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(2, 4));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(2, 5));
		
		assertEquals("Center life", 2, pGround.adjacentLivesCount(3, 0));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(3, 1));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(3, 2));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(3, 3));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(3, 4));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(3, 5));
		
		assertEquals("Center life", 2, pGround.adjacentLivesCount(4, 0));
		assertEquals("Center life", 3, pGround.adjacentLivesCount(4, 1));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(4, 2));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(4, 3));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(4, 4));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(4, 5));
		
		assertEquals("Center life", 0, pGround.adjacentLivesCount(5, 0));
		assertEquals("Center life", 2, pGround.adjacentLivesCount(5, 1));
		assertEquals("Center life", 0, pGround.adjacentLivesCount(5, 2));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(5, 3));
		assertEquals("Center life", 1, pGround.adjacentLivesCount(5, 4));
		assertEquals("Center life", 0, pGround.adjacentLivesCount(5, 5));
	}

	@Test
	public void testProgress() {
		PlayGround pGround = null;
		
		//TEST BLOCK BATTERN
		pGround = new PlayGround(SampleStates.blockPattern());
		pGround.progress();
		
		for(int i=0;i<Globals.MAX_X;i++){
			for(int j=0;j<Globals.MAX_Y;j++){
				if ( (i==1 && j==1) || (i==1 && j==2) || (i==2 && j==1) || (i==2 && j==2)){
					assertTrue(pGround.coreState[i][j]);
				}else{
					assertFalse(pGround.coreState[i][j]);
				}
			}
		}
		
		//TEST BOAT BATTERN
		pGround = new PlayGround(SampleStates.boatPattern());
		pGround.progress();
		
		for(int i=0;i<Globals.MAX_X;i++){
			for(int j=0;j<Globals.MAX_Y;j++){
				if ( (i==0 && j==1) || (i==1 && j==0) || (i==2 && j==1) || (i==0 && j==2) || (i==1 && j==2)){
					assertTrue(pGround.coreState[i][j]);
				}else{
					assertFalse(pGround.coreState[i][j]);
				}
			}
		}
		
		//TEST BLINKER BATTERN
		pGround = new PlayGround(SampleStates.blinkerPattern());
		pGround.progress();
		
		for(int i=0;i<Globals.MAX_X;i++){
			for(int j=0;j<Globals.MAX_Y;j++){
				if ( (i==1 && j==1) || (i==0 && j==1) || (i==2 && j==1)){
					assertTrue(pGround.coreState[i][j]);
				}else{
					assertFalse(pGround.coreState[i][j]);
				}
			}
		}
		
		//TEST TOAD BATTERN
		pGround = new PlayGround(SampleStates.toadPattern());
		pGround.progress();
		
		for(int i=0;i<Globals.MAX_X;i++){
			for(int j=0;j<Globals.MAX_Y;j++){
				if ( (i==0 && j==2) || (i==1 && j==1) || (i==1 && j==4) || (i==2 && j==1) || (i==2 && j==4) || (i==3 && j==3)){
					assertTrue(pGround.coreState[i][j]);
				}else{
					assertFalse(pGround.coreState[i][j]);
				}
			}
		}
		
		
	}
	
	/**
	 * Gets a simple integer array as an input and returns state matrix in two dimension. Assumption square matrix. Testing this method is out of scope
	 * @return
	 */
	private boolean[][] customState(){
		boolean[][] state = new boolean[Globals.MAX_X][Globals.MAX_Y];
		for(int i=0;i<6;i++){
			for (int j=0;j<6;j++){
				state[i][i] = false;
			}
		}
		state[0][4] = true;
		state[1][1] = true;
		state[2][0] = true;
		state[2][5] = true;
		state[3][1] = true;
		state[3][4] = true;
		state[5][0] = true;
		state[5][2] = true;
		state[5][5] = true;
		
		return state;
	}
	
}
