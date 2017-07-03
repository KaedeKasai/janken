package janken;

import java.util.ArrayList;

public class Judge {
	
	private String name;
	private final int GU    = 0;
	private final int CHOKI = 1;
	private final int PA    = 2;
	private final int AIKO  = -1;
	
	public Judge() {
		this.name = "名無し";
	}
	
	public Judge(String name) {
		this.name = name;
	}
	
	public void judgeJanken(ArrayList<Player> jankenPlayers){
		
		final int playerNum = jankenPlayers.size();
/*
 *      下のjudgePatternは、二つの[]の中にGU,CHOKI,PAを入れると強いほうの手が返却される
 *      例:int x =judgePattern[GU][CHIKI]
 *         この例だとint xにGUが格納される
 */
		int[][] judgePattern =
			{{AIKO, GU, PA},{GU, AIKO, CHOKI},{PA, CHOKI, AIKO}};
		
		if(playerNum <= 1){
			System.out.println("プレイヤーが足りません");
			return;
//		}
//		//はじめに書いた二人専用のじゃんけん審判
//		if(playerNum == 2){
//			final String player1Name = jankenPlayers.get(0).getName();
//			final int player1Hand =jankenPlayers.get(0).showHand();
//			final String player2Name = jankenPlayers.get(1).getName();
//			final int player2Hand =jankenPlayers.get(1).showHand();
//			final int winPattern = judgePattern[player1Hand][player2Hand];
//			
//			showHand(player1Name, player1Hand);
//			showHand(player2Name, player2Hand);
//			
//			if(winPattern == AIKO){
//				System.out.println("引き分け！");
//			}else if(player1Hand == winPattern){
//				System.out.println(jankenPlayers.get(0).getName() + "の勝利！");
//				jankenPlayers.get(0).notifyHand(true);
//				jankenPlayers.get(1).notifyHand(false);
//			}else{
//				System.out.println(jankenPlayers.get(1).getName() + "の勝利！");
//				jankenPlayers.get(0).notifyHand(false);
//				jankenPlayers.get(1).notifyHand(true);
//			}
		}
		
		boolean putOutGu    = false;
		boolean putOutChoki = false;
		boolean putOutPa    = false;
		ArrayList<Integer> handTypes = new ArrayList<Integer>(); //このラウンドで使われた手の種類を格納(最大３つ)
		ArrayList<Integer> playerHands = new ArrayList<Integer>(); //それぞれのプレイヤーの手を格納
		//このラウンドに使われた手の種類と数をチェックしている
		for(Player player : jankenPlayers){
			final int hand = player.showHand();
			if(hand == GU && putOutGu == false){
				putOutGu = true;
				handTypes.add(GU);
			}else if(hand == CHOKI && putOutChoki == false){
				putOutChoki = true;
				handTypes.add(CHOKI);
			}else if(hand == PA && putOutPa == false){
				putOutPa = true;
				handTypes.add(PA);
			}
			showHand(player.getName(),hand);
			playerHands.add(hand);
		}
		//使われた手の種類が1か3ならあいこなので再起させる
		if(handTypes.size() <= 1 || handTypes.size() >= 3){
			System.out.println("引き分け！もう一度！\n");
			judgeJanken(jankenPlayers);
			return;
		}
		//handTypesにある二つの手をjudgePatternの引数にし、返却値とプレイヤーの手と比較して勝敗を判断する
		final int winPattern = judgePattern[handTypes.get(0)][handTypes.get(1)];
		boolean firstTime    = true;
		for(int i = 0 ;playerNum > i;i++){
			
			if(playerHands.get(i) == winPattern){
				if(firstTime){
					System.out.print(jankenPlayers.get(i).getName());
					jankenPlayers.get(i).notifyHand(true);
					firstTime = false;
				}else{
					System.out.print("," + jankenPlayers.get(i).getName());
					jankenPlayers.get(i).notifyHand(true);
				}
			}else{
				jankenPlayers.get(i).notifyHand(false);
			}
		}
		System.out.println("の勝利！");
	}
	//じゃんけんを開始するメソッド
	public void startJudge(ArrayList<Player> jankenPlayers,int roundNum){
		System.out.println("・ゲームを開始します 審判:" + this.name + "さん\n");
		int MaxWinCount = 0; //この試合での最大勝利数が格納される
		//roundNumの数の分だけ、judgeJankenを実行する
		for(int i = 0; roundNum > i; i++){
			judgeJanken(jankenPlayers);
			System.out.println("\n---------------------------\n");
		}
		//それぞれのプレイヤーの勝利数を表示させ、同時に最大勝利数を求める
		System.out.println("最終結果");
		for(Player player : jankenPlayers){
			System.out.printf("%-8s:%d勝\n",player.getName(),player.getWinCount());
			if(player.getWinCount() > MaxWinCount){
				MaxWinCount = player.getWinCount();
			}
		}
		//MaxWinCountと同じ数のプレイヤーを優勝者と表示する
		boolean firstTime = true;
		System.out.print("\n優勝は");
		for(Player player : jankenPlayers){
			if(player.getWinCount() == MaxWinCount){
				if(firstTime){
					System.out.print(player.getName());
					firstTime = false;
				}else{
					System.out.print("," + player.getName());
				}
			}
		}
		System.out.println("です！");
		//ランキングの表示
		jankenRanking(jankenPlayers);

	}
	//プレイヤーの手を表示する
	public void showHand(String name, int hand){
		if(hand == GU){
			System.out.printf("%-8s:グー\n", name);
			return;
		}else if(hand == CHOKI){
			System.out.printf("%-8s:チョキ\n", name);
			return;
		}
		System.out.printf("%-8s:パー\n", name);
	}
	//シェルソートで勝った回数順に並べる
	public void jankenRanking(ArrayList<Player> jankenPlayers){
		
		Player[] winCounts = new Player[jankenPlayers.size()];
		for(int i = 0; jankenPlayers.size() > i; i++){
			winCounts[i] = jankenPlayers.get(i);
		}
		for (int range = winCounts.length / 2; range > 0; range /= 2) {
			for (int i = 0; i < range; i++) {
				for (int j = i + range; j < winCounts.length; j += range) {
					Player currentPlayer = winCounts[j];
					int k = j;
					for (; k >= range && winCounts[k - range].getWinCount() < currentPlayer.getWinCount(); k -= range) {
						winCounts[k] = winCounts[k - range];
					}
					winCounts[k] = currentPlayer;
				}
			}
		}
		System.out.println("\nランキング");
		int rank = 1;
		for(int i = 0; winCounts.length > i; i++){
			if(i > 0 && winCounts[i].getWinCount() != winCounts[i-1].getWinCount()) {
				rank = i + 1;
			}
			System.out.printf("%d位:%-8s %d勝\n",rank,winCounts[i].getName(),winCounts[i].getWinCount());
		}
	}
	
	public String getName() {
		return name;
	}
}
