package aima.core.environment.eightpuzzle;

import aima.core.search.framework.GoalTest;

/*
 * @author Jesus Aguas Acin (736935)
 */

public class EightPuzzleGoalTest2 implements GoalTest {
	
	static EightPuzzleBoard goal = new EightPuzzleBoard(new int[] { 0, 1, 2, 3, 4, 5,
			6, 7, 8 });

	public boolean isGoalState(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return board.equals(goal);
	}
	
	public static EightPuzzleBoard getGoalState() {
		return goal;
	}
	
	public static void setGoalState(EightPuzzleBoard board) {
		goal=board;
	}
	
}
