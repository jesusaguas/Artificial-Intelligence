package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.canibales.CanibalesBoard;
import aima.core.environment.canibales.CanibalesFunctionFactory;
import aima.core.environment.canibales.CanibalesGoalTest;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.TreeSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;
import aima.core.search.uninformed.BreadthFirstSearch;

/**
 **  Autor: Jesus Aguas Acin (736935)
 **/

public class CanibalesPract1 {
	public static void main(String[] args) {
		CanibalesSearch(new BreadthFirstSearch(new GraphSearch()),"BFS");
		CanibalesSearch(new DepthLimitedSearch(11),"DLS (11)");
		CanibalesSearch(new IterativeDeepeningSearch(),"IDLS");
	}
	
	public static void CanibalesSearch(Search search, String mensaje) {
		System.out.println("\nMisioneros y canibales " + mensaje + " -->");
		try {
			Problem problem = new Problem(new CanibalesBoard(), CanibalesFunctionFactory
					.getActionsFunction(), CanibalesFunctionFactory
					.getResultFunction(), new CanibalesGoalTest());
			long t1= System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long t= System.currentTimeMillis()-t1;
			printInstrumentation(agent.getInstrumentation());
			System.out.println("Tiempo:" + t + "mls\n");
			executeActions(agent.getActions(),problem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}
	}
	
	public static void executeActions(List<Action> actions, Problem problem) { 
		Object initialState = problem.getInitialState();
		ResultFunction resultFunction = problem.getResultFunction();
		System.out.println("SOLUCIÓN:");
		System.out.println("GOAL STATE            RIBERA-IZQ                   --RIO-- BOTE M M M C C C  RIBERA-DCH");
		System.out.println("CAMINO ENCONTRADO");
		System.out.println("ESTADO INICIAL        " + initialState);
		Object state = initialState;
		for (Action action : actions) { 
			state = resultFunction.result(state, action);
			System.out.println(action.toString() + "   " + state);
		} 
	}
}