package hanoi;

public abstract class IPlayer {
	
	public int count;
	public int color;
	public abstract int[] move();
	public IPlayer(int color) {
		super();
		this.color = color;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
		
}
