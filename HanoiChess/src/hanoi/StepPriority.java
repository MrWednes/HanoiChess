package hanoi;

public class StepPriority implements Comparable<StepPriority> {
	int[] step;
	int priority;
	
	public StepPriority(int[] step,int priority) {
		this.step=step;
		this.priority=priority;
	}

	@Override
	public int compareTo(StepPriority other) {
		// TODO Auto-generated method stub
		return this.priority-other.priority;
	}
}
