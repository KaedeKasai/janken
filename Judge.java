package janken;

import java.util.ArrayList;

public class Judge {
	
	private String name;
	
	public Judge(String name) {
		super();
		this.name = name;
	}
	
	public void judgeJanken(ArrayList<Player> jankenPlayers){
		
		final int GU    = 0;
		final int CHOKI = 1;
		final int PA    = 2;
		final int AIKO  = -1;
		final int playerNum = jankenPlayers.size();
		
		int[][] judgePattern = 
			{{AIKO, GU, PA},{GU, AIKO, CHOKI},{PA, CHOKI, AIKO}};
		
		if(playerNum <= 1){
			System.out.println("プレイヤーが足りません");
			return;
		}
		
		if(playerNum == 2){
			final int player1Hand =jankenPlayers.get(0).showHand();
			final int player2Hand =jankenPlayers.get(1).showHand();
			final int winPattern = judgePattern[player1Hand][player2Hand];
			
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
				if(hand == GU){
					if(!guFlag){
						guFlag = true;
						handType.add(GU);
					}
					System.out.printf("%-8s:ぐー\n",player.getName());
				}else if(hand == CHOKI){
					if(!chokiFlag){
						chokiFlag = true;
						handType.add(CHOKI);
					}
					System.out.printf("%-8s:ちょき\n",player.getName());
				}else{
					if(!paFlag){
						paFlag = true;
						handType.add(PA);
					}
					System.out.printf("%-8s:ぱー\n",player.getName());
				}
				playerHands.add(hand);
			}
			
			if(handType.size() <= 1 || handType.size() >= 3){
				System.out.println("引き分け！");
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
			System.out.println();
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

	}
	

	public String getName() {
		return name;
	}
}
