import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		State initialState = new State (3, 3, Position.left, 0, 0);
		
			executeBFS(initialState);
			executeDLS(initialState);
	
	}

	private static void executeBFS(State initialState) {
		BreadthFirstSearch search = new BreadthFirstSearch();
		State solution1 = search.exec(initialState);
		printSolution(solution1);
	}

	private static void executeDLS(State initialState) {
		DepthFirstSearch search = new DepthFirstSearch();
		State solution2 = search.exec(initialState);
		printSolution2(solution2);
	}

	private static void printSolution(State solution1) {
		if (null == solution1) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("\nSolution for BFS (cannibal,missanry,boat,cannibal,missanry): ");
			List<State> path = new ArrayList<State>();
			State state = solution1;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.print(state.toString());
				} else {
					System.out.print(state.toString() + " -> ");
				}
			}
		}
	}
	
	private static void printSolution2(State solution2) {
		if (null == solution2) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("\nSolution for DFS (cannibal,missanry,boat,cannibal,missanry): ");
			List<State> path = new ArrayList<State>();
			State state = solution2;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.print(state.toString());
				} else {
					System.out.print(state.toString() + " -> ");
				}
			}
		}
	}
}