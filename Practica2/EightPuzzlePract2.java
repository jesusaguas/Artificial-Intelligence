package aima.gui.demo.search;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest2;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction2;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction2;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.util.math.Biseccion;
import aima.core.search.uninformed.BreadthFirstSearch;

/*
 * @author Jesus Aguas Acin (736935)
 */

public class EightPuzzlePract2 {

	public static void main(String[] args) {	
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("||    ||      Nodos Generados                  ||                  b*                   ||");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("||   d||    BFS  |    IDS  | A*h(1)  | A*h(2)  ||    BFS  |    IDS  | A*h(1)  | A*h(2)  ||");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------------");
		
		for (int d=2;d<25;d++) {
			
			int AvgNodsBFS = eightPuzzleSearch(new BreadthFirstSearch(new GraphSearch()), d);
			double AvgRamBFS = getRamification(d,AvgNodsBFS);
			
			int AvgNodsAh1 = eightPuzzleSearch(new AStarSearch(new GraphSearch(),new MisplacedTilleHeuristicFunction2()), d);
			double AvgRamAh1 = getRamification(d,AvgNodsAh1);
			
			int AvgNodsAh2 = eightPuzzleSearch(new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction2()), d);
			double AvgRamAh2 = getRamification(d,AvgNodsAh2);
			
			if (d<=15) {
				int AvgNodsIDS = eightPuzzleSearch(new IterativeDeepeningSearch(), d);
				double AvgRamIDS = getRamification(d,AvgNodsIDS);
				System.out.format("||%4d||%7d  |%7d  |%7d  |%7d  ||   %.2f  |   %.2f  |   %.2f  |   %.2f  ||\n", 
						d, AvgNodsBFS, AvgNodsIDS, AvgNodsAh1, AvgNodsAh2, AvgRamBFS, AvgRamIDS, AvgRamAh1, AvgRamAh2);
			}
			else {
				System.out.format("||%4d||%7d  |%7s  |%7d  |%7d  ||   %.2f  |%7s  |   %.2f  |   %.2f  ||\n", 
						d, AvgNodsBFS, "---", AvgNodsAh1, AvgNodsAh2, AvgRamBFS, "---", AvgRamAh1, AvgRamAh2);
			}
		}
		System.out.println("------------------------------------------------------------------------------------------");
	}	
	
	public static int eightPuzzleSearch(Search search, int Depth) {
		int totalNodes=0;
		int nodesGenerated=0;
		EightPuzzleGoalTest2 goal = new EightPuzzleGoalTest2();
		for(int i=0;i<100;i++) {
			Object initialState = GenerateInitialEightPuzzleBoard.randomIni();
			EightPuzzleGoalTest2.setGoalState(GenerateInitialEightPuzzleBoard.random(Depth,(EightPuzzleBoard)initialState));
			try {
				Problem problem = new Problem(initialState, EightPuzzleFunctionFactory
						.getActionsFunction(), EightPuzzleFunctionFactory
						.getResultFunction(), goal);
				SearchAgent agent = new SearchAgent(problem, search);
				if (agent.getInstrumentation().getProperty("nodesGenerated")==null) nodesGenerated = 0; 
				else nodesGenerated = (int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesGenerated"));
				int depth;
				String pathcostM =agent.getInstrumentation().getProperty("pathCost");
				if (pathcostM!=null) depth = (int)Float.parseFloat(pathcostM);
				else depth = 0;
				if (depth!=Depth) {				//depth != expectedDepth --> discard solution
					i--;
				}
				else {
					totalNodes = totalNodes + nodesGenerated;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalNodes/100;
	}
	
	public static double getRamification(int depth, int GeneratedNodes) {
		Biseccion bf = new Biseccion();
		bf.setDepth(depth);
		bf.setGeneratedNodes(GeneratedNodes);
		return bf.metodoDeBiseccion(1.0000001, 4, 0.0000000001);
	}
	
}