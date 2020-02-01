package aima.core.environment.canibales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 **  Autor: Jesus Aguas Acin (736935)
 **/

public class CanibalesBoard {

	public static Action MOV1C = new DynamicAction("MOV1C");

	public static Action MOV2C = new DynamicAction("MOV2C");

	public static Action M1M1C = new DynamicAction("M1M1C");

	public static Action MOV1M = new DynamicAction("MOV1M");
	
	public static Action MOV2M = new DynamicAction("MOV2M");

	private int[] state;

	//
	// PUBLIC METHODS
	//

	public CanibalesBoard() {
		state = new int[] { 3, 3, 0, 0, 0 };
	}
	
	public CanibalesBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}
	
	public CanibalesBoard(CanibalesBoard copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}

	public void moveMOV1C() {
		int bote = getBote();
		if (bote==0) {
			state[0] = state[0] - 1;
			state[3] = state[3] + 1;
			state[2] = 1;
		}
		else if (bote==1) {
			state[3] = state[3] - 1;
			state[0] = state[0] + 1;
			state[2] = 0;
		}
	}

	public void moveMOV2C() {
		int bote = getBote();
		if (bote==0) {
			state[0] = state[0] - 2;
			state[3] = state[3] + 2;
			state[2] = 1;
		}
		else if (bote==1) {
			state[3] = state[3] - 2;
			state[0] = state[0] + 2;
			state[2] = 0;
		}
	}

	public void moveM1M1C() {
		int bote = getBote();
		if (bote==0) {
			state[0] = state[0] - 1;
			state[3] = state[3] + 1;
			state[1] = state[1] - 1;
			state[4] = state[4] + 1;
			state[2] = 1;
		}
		else if (bote==1) {
			state[3] = state[3] - 1;
			state[0] = state[0] + 1;
			state[4] = state[4] - 1;
			state[1] = state[1] + 1;
			state[2] = 0;
		}
	}

	public void moveMOV1M() {
		int bote = getBote();
		if (bote==0) {
			state[1] = state[1] - 1;
			state[4] = state[4] + 1;
			state[2] = 1;
		}
		else if (bote==1) {
			state[4] = state[4] - 1;
			state[1] = state[1] + 1;
			state[2] = 0;
		}
	}
	
	public void moveMOV2M() {
		int bote = getBote();
		if (bote==0) {
			state[1] = state[1] - 2;
			state[4] = state[4] + 2;
			state[2] = 1;
		}
		else if (bote==1) {
			state[4] = state[4] - 2;
			state[1] = state[1] + 2;
			state[2] = 0;
		}
	}

	public boolean canMove(Action where) {
		boolean retVal = true;
		int bote = getBote();
		if (where.equals(MOV1C)) {
			if (bote==0)
				retVal = (getCLeft() >= 1 && (getMRight() >= getCRight() + 1 || getMRight() == 0));
			else if (bote==1)
				retVal = (getCRight() >= 1 && (getMLeft() >= getCLeft() + 1 || getMLeft() == 0));
		}	
		else if (where.equals(MOV2C)) {
			if (bote==0)
				retVal = (getCLeft() >= 2 && (getMRight() >= getCRight() + 2 || getMRight() == 0));
			else if (bote==1)
				retVal = (getCRight() >= 2 && (getMLeft() >= getCLeft() + 2 || getMLeft() == 0));
		}
		else if (where.equals(M1M1C)) {
			if (bote==0)
				retVal = (getCLeft() >= 1 && getMLeft() >= 1 && getMRight() >= getCRight());
			else if (bote==1)
				retVal = (getCRight() >= 1 && getMRight() >= 1 && getMLeft() >= getCLeft());
		}
		else if (where.equals(MOV1M)) {
			if (bote==0)
				retVal = (getMLeft() >= 1 && (getMLeft() >= getCLeft() + 1 || getMLeft() == 1) && getMRight() >= getCRight() - 1);
			else if (bote==1)
				retVal = (getMRight() >= 1 && (getMRight() >= getCRight() + 1 || getMRight() == 1) && getMLeft() >= getCLeft() - 1);
		}
		else if (where.equals(MOV2M)) {
			if (bote==0)
				retVal = (getMLeft() >= 2 && (getMLeft() >= getCLeft() + 2 || getMLeft() == 2) && getMRight() >= getCRight() - 2);
			else if (bote==1)
				retVal = (getMRight() >= 2 && (getMRight() >= getCRight() + 2 || getMRight() == 2) && getMLeft() >= getCLeft() - 2);
		}
		return retVal;
	}
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CanibalesBoard other = (CanibalesBoard) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public String toString() {
		String retVal = "RIBERA-IZQ " + Misioneros(state[1]) + " " + Canibales(state[0])
						+ "  " + BoteLeft() + " --RIO-- " + BoteRight() + " " +
						Misioneros(state[4]) + " " + Canibales(state[3]) + "  RIBERA-DCH";
		return retVal;
	}

	//
	// PRIVATE METHODS
	//

	private int getCLeft() {
		return state[0];
	}
	
	private int getMLeft() {
		return state[1];
	}
	
	
	private int getBote() {
		return state[2];
	}
	
	private int getCRight() {
		return state[3];
	}
	
	private int getMRight() {
		return state[4];
	}
	
	private String Misioneros(int numMisioneros) {
		if (numMisioneros==3)
			return "M M M";
		else if (numMisioneros==2)
			return "  M M";
		else if (numMisioneros==1)
			return "    M";
		else
			return "     ";
	}
	
	private String Canibales(int numCanibales) {
		if (numCanibales==3)
			return "C C C";
		else if (numCanibales==2)
			return "  C C";
		else if (numCanibales==1)
			return "    C";
		else
			return "     ";
	}
	
	private String BoteLeft() {
		if (state[2]==1)
			return "    ";
		else
			return "BOTE";
	}
	private String BoteRight() {
		if (state[2]==1)
			return "BOTE";
		else
			return "    ";
	}
}