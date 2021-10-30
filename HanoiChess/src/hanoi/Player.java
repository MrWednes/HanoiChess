package hanoi;

import java.util.Scanner;

public class Player extends IPlayer {
	
	
	public Player(int color) {
		super(color);
	}

	@Override
	public int[] move() {
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		int[] steps=new int[4];
		for(int i=0;i<4;i++) {
			steps[i]=scanner.nextInt();
		}
		return steps;
	}
}
