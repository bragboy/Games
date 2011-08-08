package gameoflife.core;

public class PlayGround{
	
	public boolean[][] coreState;
	
	public PlayGround(boolean[][] seedState){
		this.coreState = seedState;
	}
	
	public int adjacentLivesCount(int xIndex, int yIndex) {
		int total = 0;
		if(areInBounds(xIndex-1,yIndex-1)) total+=1;
		if(areInBounds(xIndex-1,yIndex)) total+=1;
		if(areInBounds(xIndex-1,yIndex+1)) total+=1;
		if(areInBounds(xIndex,yIndex-1)) total+=1;
		if(areInBounds(xIndex,yIndex+1)) total+=1;
		if(areInBounds(xIndex+1,yIndex-1)) total+=1;
		if(areInBounds(xIndex+1,yIndex)) total+=1;
		if(areInBounds(xIndex+1,yIndex+1)) total+=1;
		return total;
	}
	
	private boolean areInBounds(int x, int y){
		if(x>=0 && y>=0 && x<Globals.MAX_X && y<Globals.MAX_Y)
			return coreState[x][y];
		return false;
	}

	public void printState() {
		for(int i=0;i<coreState.length;i++)
			for(int j=0;j<coreState[i].length;j++)
				if(coreState[i][j])
					System.out.println(i+", "+j);
	}

	public void progress() {
		boolean[][] newState = SampleStates.getFreshState(); 
		for(int i=0;i<coreState.length;i++){
			for(int j=0;j<coreState[i].length;j++){
				int nearLives = adjacentLivesCount(i,j);
				newState[i][j] = coreState[i][j]; //RULE 3 implicitly applied
				if(coreState[i][j]){
					if(nearLives < 2 || nearLives > 3)//RULE 1 and 2
						newState[i][j] = false;
				}else{
					if(nearLives == 3){//RULE 4
						newState[i][j] = true;
					}
				}
			}
		}
		coreState = newState;
	}
}
