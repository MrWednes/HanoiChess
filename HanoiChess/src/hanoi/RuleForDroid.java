package hanoi;

public class RuleForDroid {
	
	public static Cross[][] crosses;
	public static Cross source;
	public static Cross target;
	public static Integer code;

	public static boolean isLegalIndex(int[] coodinates) {
		for (int i : coodinates) {
			if(i>4||i<0) {
				return false;
			}	
		}
		return true;
	}
	
	public static boolean isEmptyCross() {
		boolean empty=source.stack.isEmpty();
	
		return empty;
		
	}
	
	public static boolean isYourMove() {
		
			if(source.stack.peek().color==HanoiChess.cuPlayer.color)
				return true;
			else {

				return false;
			}
	}
	
	public static boolean isCrossesConneted(int[] coodinates) {
		if((Math.abs(coodinates[0]-coodinates[2])<=1&&Math.abs(coodinates[1]-coodinates[3])<=1))
			return true;
		
	
		return false;
	}
	
	public static boolean fitRules(int[] coodinates) {
	
		if((coodinates[2]==0&&coodinates[3]==0)||((coodinates[2]==(HanoiChess.board.size-1))
				&&(coodinates[3]==(HanoiChess.board.size-1))))//进洞1
		{	
			if(HanoiChess.cuPlayer.color==((CrossEnd)target).color) {
				Piece piece =source.stack.pop();
				ComputerGreedy.myMark+=piece.size;
				code=1;
				return true;
			}
		}
		
		if(target.stack.isEmpty()) {//目标上无棋子2
			target.push(source.stack.pop());
			code=2;
			return true;
		}
		else {
			
			if(target.stack.peek().color==HanoiChess.cuPlayer.color) {//放在自己上的3
				if(target.stack.peek().size>source.stack.peek().size) {
					target.push(source.stack.pop());
					code=3;
					return true;
				}else {
					return false;
				}
			}else {
				if(target.stack.peek().size>source.stack.peek().size) {//放在对面上的4
					target.push(source.stack.pop());
					code=4;
					return true;
				}
					
				else if(target.stack.peek().size==source.stack.peek().size){//调换敌我棋子5
					if(target.stack.size()>=2&&
							target.stack.get(target.stack.size()-2).color==HanoiChess.cuPlayer.color) {
						Piece piece=source.stack.pop();
						source.push(target.stack.pop());
						target.push(piece);
						code=5;
						return true;
					}else
						return false;
						
				}else {
					return false;
				}
			}
		}	
	}
	//验证是否合法入口
	public static boolean islegalMove(int[] coodinates,Cross[][] crosses,Integer code) {
		if(!isLegalIndex(coodinates))
			return false;
		RuleForDroid.code=code;
		RuleForDroid.crosses=crosses;
		source=crosses[coodinates[0]][coodinates[1]];
		target=crosses[coodinates[2]][coodinates[3]];
		return !isEmptyCross()&&isYourMove()&&isCrossesConneted(coodinates)&&fitRules(coodinates);
		
	}
	
	public static boolean isFished(Cross[][] crosses){
		int max=(HanoiChess.board.size+1)*HanoiChess.board.size/2;
		if(ComputerGreedy.myMark==max
				||ComputerGreedy.oppositeMark==max)
			return true;
		for (Cross crosses1[] : crosses) {
			
			for (Cross cross : crosses1) {
				if(cross.stack.size()>0) {
					Piece peek=cross.stack.peek();
					if(peek.color==((HanoiChess.p1.color==HanoiChess.cuPlayer.color) ? HanoiChess.p2.color: HanoiChess.p1.color))
						return false;
				}	
			}
		}
		return true;
		
	}
	
	public static boolean win() {
		if(ComputerGreedy.myMark>ComputerGreedy.oppositeMark)
			return true;
		return false;
			
	}
}
