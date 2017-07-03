package janken;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Player> jankenPlayers = new ArrayList<Player>();
/*
 *  プレイヤーをインスタンス化する際に、名前のほかに整数型の引数を与えることで出す手の確率を変えられます。
 *  例:Player kawashi = new Player("kawashi",50,0,50);
 *     名前の後の整数は順番にグー、チョキ、パーの手が出る確率になります。
 *     この場合のkawashiはグーが50％、パーが50％で出し、チョキは出しません。
 *  パーの確率は省略可能です。(下記のsakaiが例です)
 *  正しくない値が渡されると、通常のプレイヤーを生成します。(下記のtaishiが例です)
 */
		Player kaede = new Player("kaede");
		Player kawashi = new Player("kawashi",25,25,50);
		Player sakai = new Player("sakai",50,25);
		Player taishi = new Player("taishi",70,40);
		Player murajo = new Player("murajo");
		Player motegi = new Player("motegi");
		Player shoji_san = new Player("shoji_senpai");
		
		jankenPlayers.add(kaede);
		jankenPlayers.add(kawashi);
		jankenPlayers.add(sakai);
		jankenPlayers.add(taishi);
		jankenPlayers.add(murajo);
		jankenPlayers.add(motegi);
		jankenPlayers.add(shoji_san);
		
		//審判は名前の設定も可能です。
		Judge isono = new Judge("磯野");
		
		isono.startJudge(jankenPlayers,5);
	}

}
