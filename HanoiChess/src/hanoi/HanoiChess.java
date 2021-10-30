package hanoi;

import java.util.Scanner;

public class HanoiChess {


	public static IPlayer p1;
	public static IPlayer p2;
	public static IPlayer cuPlayer;
	public static Board board;


	public static int chooseGame() {
		@SuppressWarnings("resource")
		Scanner scanner= new Scanner(System.in);
		System.out.println("Please enter the coresponding number to choose different mode to play");
		System.out.println("1.PVP");
		System.out.println("2.PVC");
		System.out.println("3.CVC");
		int cn=scanner.nextInt();
		while(cn!=1&&cn!=2&&cn!=3)
			cn=scanner.nextInt();
		return cn;
	}
	

	public static void initGame(int cn){
		if(cn==1) {
			p1=new Player(0);
			p2=new Player(1);
			
		}
		else if(cn==2) {
			p1=new Player(0);
			p2=new ComputerGreedy(1);
			
		}else {
			p1=new ComputerGreedy(0);
			p2=new ComputerGreedy(1);
		}
			
		
		
		
		cuPlayer=p1;
		board=new Board(5);
		
	}
	
	
	public static void begins() {
		 
		board.display();
		 outWhosMove();
		while(!Rule.isFished()) {
			 if(next())
				 swapPlayers();
			board.display();
			 outWhosMove();
		}
		
	}
	
	public static void outWhosMove(){
		char cc ;
	 	switch (cuPlayer.color) {
		case 0:
			cc='B';
			break;
			
		default:
			cc='W';
			break;
		}
	 	System.out.print(cc+"走棋");

	}
	
	public static void swapPlayers() {
		if(cuPlayer==p1)
			cuPlayer=p2;
		else 
			cuPlayer=p1;
	}
	
	public static boolean next() {
		
		return Rule.islegalMove(cuPlayer.move());
		
	}
	
	public static void main(String[] args) {
		while(true) {
			initGame(chooseGame());
			begins();
			Rule.whoWin();
		}
	}

}
