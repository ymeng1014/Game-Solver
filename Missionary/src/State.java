
import java.util.ArrayList;
import java.util.List;

enum Position {right, left}

public class State {

	private int cannibalLeft;
	private int missanryLeft;
	private int cannibalRight;
	private int missanryRight;
	private Position boat;

	private State parentState;

	public State(int cannibalLeft, int missanryLeft, Position boat,
			int cannibalRight, int missanryRight) {
		this.cannibalLeft = cannibalLeft;
		this.missanryLeft = missanryLeft;
		this.boat = boat;
		this.cannibalRight = cannibalRight;
		this.missanryRight = missanryRight;
	}

	public boolean isGoal() {
		return cannibalLeft == 0 && missanryLeft == 0;
	}

	public boolean isValid() {
		if (missanryLeft >= 0 && missanryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
	               && (missanryLeft == 0 || missanryLeft >= cannibalLeft)
	               && (missanryRight == 0 || missanryRight >= cannibalRight)) {
			return true;
		}
		return false;
	}

	public List<State> generateSuccessors() {
		List<State> successors = new ArrayList<State>();
		if (boat == Position.left) {
			testAndAdd(successors, new State(cannibalLeft, missanryLeft - 2, Position.right,
					cannibalRight, missanryRight + 2)); 
			testAndAdd(successors, new State(cannibalLeft - 2, missanryLeft, Position.right,
					cannibalRight + 2, missanryRight)); 
			testAndAdd(successors, new State(cannibalLeft - 1, missanryLeft - 1, Position.right,
					cannibalRight + 1, missanryRight + 1));
			testAndAdd(successors, new State(cannibalLeft, missanryLeft - 1, Position.right,
					cannibalRight, missanryRight + 1)); 
			testAndAdd(successors, new State(cannibalLeft - 1, missanryLeft, Position.right,
					cannibalRight + 1, missanryRight)); 
		} 
		else {
			testAndAdd(successors, new State(cannibalLeft, missanryLeft + 2, Position.left,
					cannibalRight, missanryRight - 2)); 
			testAndAdd(successors, new State(cannibalLeft + 2, missanryLeft, Position.left,
					cannibalRight - 2, missanryRight)); 
			testAndAdd(successors, new State(cannibalLeft + 1, missanryLeft + 1, Position.left,
					cannibalRight - 1, missanryRight - 1)); 
			testAndAdd(successors, new State(cannibalLeft, missanryLeft + 1, Position.left,
					cannibalRight, missanryRight - 1)); 
			testAndAdd(successors, new State(cannibalLeft + 1, missanryLeft, Position.left,
					cannibalRight - 1, missanryRight)); 
		}
		return successors;
	}

	private void testAndAdd(List<State> successors, State newState) {
		if (newState.isValid()) {
			newState.setParentState(this);
			successors.add(newState);
		}
	}

	public int getCannibalLeft() {
		return cannibalLeft;
	}

	public void setCannibalLeft(int cannibalLeft) {
		this.cannibalLeft = cannibalLeft;
	}

	public int getMissanryLeft() {
		return missanryLeft;
	}

	public void setMissanryLeft(int missanryLeft) {
		this.missanryLeft = missanryLeft;
	}

	public int getCannibalRight() {
		return cannibalRight;
	}

	public void setCannibalRight(int cannibalRight) {
		this.cannibalRight = cannibalRight;
	}

	public int getMissanryRight() {
		return missanryRight;
	}

	public void setMissanryRight(int missanryRight) {
		this.missanryRight = missanryRight;
	}

	public void goToLeft() {
		boat = Position.left;
	}

	public void goToRight() {
		boat = Position.right;
	}

	public boolean isOnLeft() {
		return boat == Position.left;
	}

	public boolean isOnRigth() {
		return boat == Position.right;
	}

	public State getParentState() {
		return parentState;
	}

	public void setParentState(State parentState) {
		this.parentState = parentState;
	}

	@Override
	public String toString() {
		if (boat == Position.left) {
			return "(" + cannibalLeft + "," + missanryLeft + ",L,"
        			+ cannibalRight + "," + missanryRight + ")";
		} else {
			return "(" + cannibalLeft + "," + missanryLeft + ",R,"
        			+ cannibalRight + "," + missanryRight + ")";
		}
     }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		State s = (State) obj;
        return (s.cannibalLeft == cannibalLeft && s.missanryLeft == missanryLeft
        		&& s.boat == boat && s.cannibalRight == cannibalRight
        		&& s.missanryRight == missanryRight);
	}
}
