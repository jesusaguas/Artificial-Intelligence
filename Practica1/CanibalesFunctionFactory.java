package aima.core.environment.canibales;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 **  Autor: Jesus Aguas Acin (736935)
 **/

public class CanibalesFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			CanibalesBoard board = (CanibalesBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMove(CanibalesBoard.MOV1C)) {
				actions.add(CanibalesBoard.MOV1C);
			}
			if (board.canMove(CanibalesBoard.MOV2C)) {
				actions.add(CanibalesBoard.MOV2C);
			}
			if (board.canMove(CanibalesBoard.M1M1C)) {
				actions.add(CanibalesBoard.M1M1C);
			}
			if (board.canMove(CanibalesBoard.MOV1M)) {
				actions.add(CanibalesBoard.MOV1M);
			}
			if (board.canMove(CanibalesBoard.MOV2M)) {
				actions.add(CanibalesBoard.MOV2M);
			}
			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			CanibalesBoard board = (CanibalesBoard) s;

			if (CanibalesBoard.MOV1C.equals(a)
					&& board.canMove(CanibalesBoard.MOV1C)) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.moveMOV1C();
				return newBoard;
			} else if (CanibalesBoard.MOV2C.equals(a)
					&& board.canMove(CanibalesBoard.MOV2C)) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.moveMOV2C();
				return newBoard;
			} else if (CanibalesBoard.M1M1C.equals(a)
					&& board.canMove(CanibalesBoard.M1M1C)) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.moveM1M1C();
				return newBoard;
			} else if (CanibalesBoard.MOV1M.equals(a)
					&& board.canMove(CanibalesBoard.MOV1M)) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.moveMOV1M();
				return newBoard;
			} else if (CanibalesBoard.MOV2M.equals(a)
					&& board.canMove(CanibalesBoard.MOV2M)) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.moveMOV2M();
				return newBoard;
			}
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}