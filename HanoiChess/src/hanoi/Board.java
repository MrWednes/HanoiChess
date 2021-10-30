package hanoi;

import java.util.Stack;

public class Board {
	
	public Cross[][] crosses;
	int size;
	public Board(int size) {

		this.size=size;
		crosses=new Cross[size][size];
		
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				crosses[i][j]=new Cross();
			}
		}
		crosses[0][0]=new CrossEnd(1);
		crosses[size-1][size-1]=new CrossEnd(0);
		for(int i=size;i>=1;i--) {
			Piece piece=new Piece(0, i);
			crosses[0][0].stack.push(piece);
		}
		for(int i=size;i>=1;i--) {
			Piece piece=new Piece(1, i);
			crosses[size-1][size-1].stack.push(piece);
		}
	}
	public void display() {
		int size=crosses.length;
		System.out.print("  \t\t");
		for(int i=0;i<size;i++) {
			System.out.print(""+i+" \t\t");
		} 
		char p1;
		char p2;
		if(HanoiChess.p1.color==0) {
			p1='B';
			p2='W';
		}else {
			p1='W';
			p2='B';
		}
			
		
		System.out.println("比分"+p1+":"+HanoiChess.p1.count+" "+p2+":"+HanoiChess.p2.count);
		System.out.println("\n\n\n");
		for(int i=0;i<size;i++) {
			System.out.print(""+i+" \t\t");
			for(int j=0;j<size;j++) {
				Stack<Piece> stack =crosses[i][j].stack;
				for(int k=stack.size();k<size;k++){
					System.out.print("**");
				}
				for (Piece piece : stack) {
					char color=piece.color==1 ? 'w': 'b';
					System.out.print(""+color+piece.size);
				}
				
				System.out.print("\t");
			}
			System.out.println("\n\n\n");
		}
	}
	
}
