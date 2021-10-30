package hanoi;

import java.util.Stack;

public class ComputerGreedy extends IPlayer{
	public static int myMark;
	public static int oppositeMark;
	public ComputerGreedy(int color) {
		super(color);
	}

	
	
	@Override
	public int[] move() {
		int x;
		int y;
		StepPriority maxP=new StepPriority(null, -100);
		if(super.color==((CrossEnd)(HanoiChess.board.crosses[0][0])).color) {
			x=0;
			y=0;
		}else {
			x=HanoiChess.board.size;
			y=x;
		}
		Cross[][] crosses=cloneArray();
		myMark=HanoiChess.cuPlayer.count;
		oppositeMark =(HanoiChess.p1==HanoiChess.cuPlayer)? HanoiChess.p2.count:HanoiChess.p1.count;
		for (int i=0;i<crosses.length;i++) {
			for (int j=0;j<crosses[i].length;j++) {
				Stack<Piece> stack=crosses[i][j].stack;
				if(stack.size()<1)
					continue;
				if(crosses[i][j].stack.peek().color==super.color) {
					int priority=0;
					int size=stack.peek().size;
					priority+=size;
					
					if(stack.size()>1&&stack.elementAt(stack.size()-2).color!=super.color)
							priority--;
					else
						priority++;
					
			
					for(int k=i-1;k<=i+1;k++) {
						for(int l=j-1;l<=j+1;l++) {
							int[] step= {i,j,k,l};
							int subPriority=priority;
							Integer code=0;
							if(RuleForDroid.islegalMove(step,crosses,code)) {
										
								int value=Math.abs(x-i)+Math.abs(y-j)
								-Math.abs(x-k)-Math.abs(y-l);
								subPriority+=value;
								
								if(RuleForDroid.isFished(crosses)&&RuleForDroid.win()) {
									for (int m : step) {
										System.out.print(m+" ");
									}
									System.out.println("");
									return step;
								}
									
								
								if(code.equals(1)) {////如果能进洞
									subPriority+=size;
								}else if(code.equals(2)) {//目标无棋子
									
								}else if(code.equals(3)) {//放在自己的上面
									subPriority+=1;
								}else if(code.equals(4)) {//放在对面的上面
									subPriority+=size/2;
								}else if(code.equals(5)) {//调换敌我棋子
									subPriority+=size/2;
								}
								
								if(subPriority>maxP.priority) {
									maxP.priority=subPriority;
									maxP.step=step;
								}
								
								crosses=cloneArray();
							}
							 
						}
					}
				}
			}
		}
		for (int m : maxP.step) {
			System.out.print(m+" ");
		}
		System.out.println("");
		return maxP.step;
	}
	
	Cross[][] cloneArray(){
		int size=HanoiChess.board.size;
		Cross[][] clone= new Cross[size][size];
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				@SuppressWarnings("unchecked")
				Stack<Piece> stack =(Stack<Piece>) HanoiChess.board.crosses[i][j].stack.clone();
				clone[i][j]=new Cross(stack);
			}
		}
		@SuppressWarnings("unchecked")
		Stack<Piece> stack =(Stack<Piece>) HanoiChess.board.crosses[0][0].stack.clone();
		clone[0][0]=new CrossEnd(stack,1 );
		@SuppressWarnings("unchecked")
		Stack<Piece> stack1 =(Stack<Piece>) HanoiChess.board.crosses[size-1][size-1].stack.clone();
		clone[size-1][size-1]=new CrossEnd(stack1,0 );
		return clone;
	}
	
}
