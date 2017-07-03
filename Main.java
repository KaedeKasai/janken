package janken;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		ArrayList<Player> jankenPlayers = new ArrayList<Player>();
		
		/*
		 * プレイヤーをインスタンス化する際に、名前のほかに整数型の引数を与えることで出す手の確立を変えられます。
		 * （10）
		 */
		Player kaede = new Player("kaede");
		Player kawashi = new Player("kawashi",50,0,50);
		Player sakai = new Player("sakai",0,50);
		Player taishi = new Player("taishi",50,50,50);
//		Player murajo = new Player("murajo");
//		Player motegi = new Player("motegi");
//		Player shoji_san = new Player("shoji_senpai");
		
		jankenPlayers.add(kaede);
		jankenPlayers.add(kawashi);
		jankenPlayers.add(sakai);
		jankenPlayers.add(taishi);
//		jankenPlayers.add(murajo);
//		jankenPlayers.add(motegi);
//		jankenPlayers.add(shoji_san);
		
		Judge isono = new Judge();
		
		isono.startJudge(jankenPlayers,3);
	}

}
