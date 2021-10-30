package hanoi;

import java.util.Stack;

public class CrossEnd extends Cross {
	int color;
	public CrossEnd(int color) {
		this.color=color;
	}
	public CrossEnd(Stack<Piece> stack, int color) {
		super(stack);
		this.color=color;
	}
	@Override
	public void push(Piece piece) {
		if(piece.color==color) {
			HanoiChess.cuPlayer.count+=piece.size;
		}else
			super.push(piece);
	}
}
