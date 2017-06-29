package janken;

import java.util.ArrayList;

public class Judge {
	
	private String name;
	private final int GU    = 0;
	private final int CHOKI = 1;
	private final int PA    = 2;
	private final int AIKO  = -1;
	
	public Judge(String name) {
		super();
		this.name = name;
	}
	
	public void judgeJanken(ArrayList<Player> jankenPlayers){
		
		final int playerNum = jankenPlayers.size();
		
		int[][] judgePattern = 
			{{AIKO, GU, PA},{GU, AIKO, CHOKI},{PA, CHOKI, AIKO}};
		
		if(playerNum <= 1){
			System.out.println("プレイヤーが足りません");
			return;
		}
		
		if(playerNum == 2){
			final String player1Name = jankenPlayers.get(0).getName();
			final int player1Hand =jankenPlayers.get(0).showHand();
			final String player2Name = jankenPlayers.get(1).getName();
			final int player2Hand =jankenPlayers.get(1).showHand();
			final int winPattern = judgePattern[player1Hand][player2Hand];
			
			printHand(player1Name, player1Hand);
			printHand(player2Name, player2Hand);
			
			if(winPattern == AIKO){
				System.out.println("引き分け！");
			}else if(player1Hand == winPattern){
				System.out.println(jankenPlayers.get(0).getName() + "の勝利！");
				jankenPlayers.get(0).notifyHand(true);
				jankenPlayers.get(1).notifyHand(false);
			}else{
				System.out.println(jankenPlayers.get(1).getName() + "の勝利！");
				jankenPlayers.get(0).notifyHand(false);
				jankenPlayers.get(1).notifyHand(true);
			}
			
		}else{
			boolean guFlag = false;
			boolean chokiFlag = false;
			boolean paFlag = false;
			ArrayList<Integer> handType = new ArrayList<Integer>();
			ArrayList<Integer> playerHands = new ArrayList<Integer>();
			
			for(Player player : jankenPlayers){
				final int hand = player.showHand();
				if(hand == GU && !guFlag){
					handType.add(GU);
				}else if(hand == CHOKI && !chokiFlag){
					handType.add(CHOKI);
				}else if(hand == PA && !paFlag){
					paFlag = true;
					handType.add(PA);
				}
				printHand(player.getName(),hand);
				playerHands.add(hand);
			}
			
			if(handType.size() <= 1 || handType.size() >= 3){
				System.out.println("引き分け！もう一度！\n");
				judgeJanken(jankenPlayers);
				return;
			}
			
			final int winPattern = judgePattern[handType.get(0)][handType.get(1)];
			
			for(int i = 0 ;playerNum > i;i++){
				if(playerHands.get(i) == winPattern){
					System.out.print(jankenPlayers.get(i).getName() + ",");
					jankenPlayers.get(i).notifyHand(true);
				}else{
					jankenPlayers.get(i).notifyHand(false);
				}
			}
			System.out.println("の勝利！");
		}	
	}

	public void startJudge(ArrayList<Player> jankenPlayers,int roundNum){
		int MaxWinCount = 0;
		String jankenchampion = "";
		for(int i = 0; roundNum > i; i++){
			judgeJanken(jankenPlayers);
			System.out.println("\n---------------------------\n");
		}
		System.out.println("最終結果");
		for(Player player : jankenPlayers){
			System.out.printf("%-8s:%d勝\n",player.getName(),player.getWinCount());
			if(player.getWinCount() > MaxWinCount){
				MaxWinCount = player.getWinCount();
			}
		}
		System.out.print("\n優勝は");
		for(Player player : jankenPlayers){
			if(player.getWinCount() == MaxWinCount){
				System.out.print(player.getName() + ",");
			}
		}
		System.out.println("です！");
		
		jankenRanking(jankenPlayers);

	}
	
	public void printHand(String name, int hand){
		if(hand == GU){
			System.out.printf("%-8s:ぐー\n", name);
			return;
		}else if(hand == CHOKI){
			System.out.printf("%-8s:ちょき\n", name);
			return;
		}
		System.out.printf("%-8s:ぱー\n", name);
	}
	
	public void jankenRanking(ArrayList<Player> jankenPlayers){
		
		Player[] WinCounts = new Player[jankenPlayers.size()];
		for(int i = 0; jankenPlayers.size() > i; i++){
			WinCounts[i] = jankenPlayers.get(i);
		}
		for (int range = WinCounts.length / 2; range > 0; range /= 2) {
			for (int h = 0; h < range; h++) {
				for (int i = h + range; i < WinCounts.length; i += range) {
					Player insertionData = WinCounts[i];
					int j = i;
					for (; j >= range && WinCounts[j - range].getWinCount() < insertionData.getWinCount(); j -= range) {
						WinCounts[j] = WinCounts[j - range];
					}
					WinCounts[j] = insertionData;
				}
			}
		}
		System.out.println("\nランキング");
		for(int i = 0; WinCounts.length > i; i++){
			System.out.println(i + "位:" + WinCounts[i].getName());
		}
	}
	
	public String getName() {
		return name;
	}
}
