package janken;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		ArrayList<Player> jankenPlayers = new ArrayList<Player>();
		
		Player kaede = new Player("kaede");
		Player kawashi = new Player("kawashi");
		Player sakai = new Player("sakai");
		Player taishi = new Player("taishi");
		
		
		jankenPlayers.add(kaede);
		jankenPlayers.add(kawashi);
		jankenPlayers.add(sakai);
		jankenPlayers.add(taishi);
		
		
		Judge isono = new Judge("磯野");
		
		isono.startJudge(jankenPlayers,5);
	}

}
