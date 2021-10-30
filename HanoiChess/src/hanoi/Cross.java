package hanoi;

import java.util.Stack;

public class Cross {
	
	Stack<Piece> stack;
	public Cross() {
		stack=new Stack<Piece>();
	}
	public Cross(Stack<Piece> stack) {
		this.stack=stack;
	}
	public void push(Piece piece) {
		stack.push(piece);
	}
}
