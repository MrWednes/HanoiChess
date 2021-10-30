package hanoi;

public class Rule {
	
	public static Cross source;
	public static Cross target;

	public static boolean isLegalIndex(int[] coodinates) {
		for (int i : coodinates) {
			if(i>4||i<0) {
				System.out.println("不合法的坐标");
				return false;
			}	
		}
		return true;
	}
	
	public static boolean isEmptyCross() {
		boolean empty=source.stack.isEmpty();
		if(empty==true)
			System.out.println("无效移动，原位置无棋子");
		return empty;
		
	}
	
	public static boolean isYourMove() {
		
			if(source.stack.peek().color==HanoiChess.cuPlayer.color)
				return true;
			else {
				System.out.println("您不能移动对方棋子");
				return false;
			}
	}
	
	public static boolean isCrossesConneted(int[] coodinates) {
		if((Math.abs(coodinates[0]-coodinates[2])<=1&&Math.abs(coodinates[1]-coodinates[3])<=1))
			return true;
		System.out.println("只能将棋子移动一格");
	
		return false;
	}
	
	public static boolean fitRules(int[] coodinates) {
	
		if((coodinates[2]==0&&coodinates[3]==0)||((coodinates[2]==(HanoiChess.board.size-1))&&(coodinates[3]==(HanoiChess.board.size-1))))
		{	
			if(HanoiChess.cuPlayer.color==((CrossEnd)target).color) {
				target.push(source.stack.pop());
				return true;
			}
		}
		
		if(target.stack.isEmpty()) {
			target.push(source.stack.pop());
			return true;
		}
		else {
			
			if(target.stack.peek().color==HanoiChess.cuPlayer.color) {
				if(target.stack.peek().size>source.stack.peek().size) {
					target.push(source.stack.pop());
					return true;
				}else {
					System.out.println("无法放在更小的棋子上");
					return false;
				}
			}else {
				if(target.stack.peek().size>source.stack.peek().size) {
					target.push(source.stack.pop());
					return true;
				}
					
				else if(target.stack.peek().size==source.stack.peek().size){
					if(target.stack.size()>=2&&
							target.stack.get(target.stack.size()-2).color==HanoiChess.cuPlayer.color) {
						Piece piece=source.stack.pop();
						source.push(target.stack.pop());
						target.push(piece);
						return true;
					}else
						System.out.println("无法如此移动");
						return false;
						
				}else {
					System.out.println("无法如此移动");
					return false;
				}
			}
		}	
	}
	
	public static boolean islegalMove(int[] coodinates) {
		if(!isLegalIndex(coodinates))
			return false;
		source=HanoiChess.board.crosses[coodinates[0]][coodinates[1]];
		target=HanoiChess.board.crosses[coodinates[2]][coodinates[3]];
		return !isEmptyCross()&&isYourMove()&&isCrossesConneted(coodinates)&&fitRules(coodinates);
		
	}
	
	public static boolean isFished(){
		int max=(HanoiChess.board.size+1)*HanoiChess.board.size/2;
		if(HanoiChess.p1.count==max
				||HanoiChess.p2.count==max)
			return true;
		for (Cross crosses1[] : HanoiChess.board.crosses) {
			
			for (Cross cross : crosses1) {
				if(cross.stack.size()>0) {
					Piece peek=cross.stack.peek();
					if(peek.color==HanoiChess.cuPlayer.color)
						return false;
				}	
			}
		}
		return true;
		
	}
	
	public static void whoWin() {
		int black=HanoiChess.p1.count;
		int white=HanoiChess.p2.count;
		if(white>black)
			System.out.println("W方胜利，比分为"+white+":"+black);
		else if(black>white)
			System.out.println("B方胜利，比分为"+black+":"+white);
		else {
			System.out.println("双方平局，比分为"+white+":"+black);
		}
	}
}
