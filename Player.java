package janken;

import java.util.Random;

public class Player {
	
	private String name;
	private int    winCount;
	
	public Player(String name) {
		this.name = name;
	}

	public int showHand(){
		Random rnd   = new Random();
		int     myHand = rnd.nextInt(3);
		return myHand;	
	}
	
	public void notifyHand(boolean winMatch){
		if(winMatch){
			winCount++;
		}
	}

	public String getName() {
		return name;
	}

	public int getWinCount() {
		return winCount;
	}
}
