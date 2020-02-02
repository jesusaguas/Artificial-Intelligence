package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * @author Jesus Aguas Acin (736935)
 */
public class ManhattanHeuristicFunction2 implements HeuristicFunction {

	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		EightPuzzleBoard goal = EightPuzzleGoalTest2.getGoalState();
		int retVal = 0;
		for (int i = 1; i < 9; i++) {
			XYLocation loc = board.getLocationOf(i);
			XYLocation fin = goal.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc, fin);
		}
		return retVal;

	}

	public int evaluateManhattanDistanceOf(int i, XYLocation loc, XYLocation fin) {
		int retVal = -1;
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		int xposf = fin.getXCoOrdinate();
		int yposf = fin.getYCoOrdinate();
		switch (i) {

		case 1:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;
		case 2:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - xposf);
			break;
		case 3:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;
		case 4:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;
		case 5:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;
		case 6:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;
		case 7:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;
		case 8:
			retVal = Math.abs(xpos - xposf) + Math.abs(ypos - yposf);
			break;

		}
		return retVal;
	}
}