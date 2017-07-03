package janken;

import java.util.Random;

public class Player {
	
	private String name;
	private int winCount;
	private boolean rateFlag;
	private int guRate;
	private int chokiRate;
	private int paRate;
	
	public Player(String name) {
		this.name = name;
		this.rateFlag = false;
		System.out.println("プレイヤー「" + name +"」を生成しました");
	}
	
	public Player(String name,int guRate,int chokiRate) {
		this.name = name;
		if(guRate + chokiRate > 100){
			System.out.println("注意:出す手の割合の数値が正しくありません\n通常のプレイヤー「" + name +"」を生成しました");
			this.rateFlag = false;
			return;
		}
		this.rateFlag = true;
		this.guRate = guRate;
		this.chokiRate = chokiRate;
		this.paRate = 100 - (guRate + chokiRate);
		System.out.println("オプションプレイヤー「" + name +"」を生成しました");
		System.out.println("「" + name +"」の出す手の割合:グー "+ guRate +"%,チョキ "+ chokiRate +"%,パー "+ paRate +"%");
	}
	
	public Player(String name,int guRate,int chokiRate,int paRate) {
		this.name = name;
		if(guRate + chokiRate + paRate != 100){
			System.out.println("注意:出す手の割合の数値が正しくありません\n通常のプレイヤー「" + name +"」を生成しました");
			this.rateFlag = false;
			return;
		}
		this.rateFlag = true;
		this.guRate = guRate;
		this.chokiRate = chokiRate;
		this.paRate = paRate;
		System.out.println("オプションプレイヤー「" + name +"」を生成しました");
		System.out.println("「" + name +"」の出す手の割合:グー "+ guRate +"%,チョキ "+ chokiRate +"%,パー "+ paRate +"%");
	}

	public int showHand(){
		if(rateFlag){
			Random rnd   = new Random();
			final int rateSplit1 = guRate;
			final int rateSplit2 = guRate + chokiRate;
			final int rndNum = rnd.nextInt(100);
			if(rndNum >= -1 && rndNum < rateSplit1){
				return 0;	
			}else if(rndNum > rateSplit1 -1 && rndNum < rateSplit2){
				return 1;
			}
			return 2;
		}
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
