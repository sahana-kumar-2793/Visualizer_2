import java.awt.Point;
import java.util.ArrayList;

public class Config {
	//ENV CONFIG
	public final int GRID_X = 5, GRID_Y = 2;
	public final int X_DIMENSION = 2000, Y_DIMENSION = 2000;
	public final int radius = 40;
	public final int XBUFFER = 50;
	public final int YBUFFER = 50;
	
	//AGENT CONFIG
	public final Point AGENT_1_START_POINT = new Point(1, 1), AGENT_1_GOAL_POINT = new Point(4, 1);
	public final Point AGENT_2_START_POINT = new Point(3, 1), AGENT_2_GOAL_POINT = new Point(0, 1);
	
//	public final Point AGENT_1_START_POINT = new Point(1, 0), AGENT_1_GOAL_POINT = new Point(3, 3);
//	public final Point AGENT_2_START_POINT = new Point(0, 0), AGENT_2_GOAL_POINT = new Point(4, 4);
	
	//INTERNAL CONFIG
	public Planner AGENT_1_PLANNER, AGENT_2_PLANNER;
	public XYTimePlot AGENT_1_PLOT, AGENT_2_PLOT;
	public ArrayList<Point> AGENT_1_PATH, AGENT_2_PATH;
	
	//OBSTACLE CONFIG
	public final Point[] OBSTACLES = {new Point(2,1)}; //{new Point(0, 1), new Point(1, 1), new Point(3, 1), new Point(4, 1)};
//	public final Point[] OBSTACLES = {new Point(3, 4), new Point(4,3)};
	
	//Cul de sac init
//	public final Point[] OBSTACLES = new Point[280];
		
	
	public Config() {
		//Cul de sac construction
//		int startX = 16;
//		int counter = 0;
//		for(int i = 0; i < 10; i++) {
//			int startY = 35;
//			for(int j = 0; j < 10; j++) {
//				OBSTACLES[counter] = new Point(startX, startY);
//				startY ++;
//				counter++;
//			}
//			startX ++;
//		}
//		startX = 13;
//		
//		for(int i = 0; i < 3; i++) {
//			int startY = 15;
//			for(int j = 0; j < 30; j++) {
//				OBSTACLES[counter] = new Point(startX, startY);
//				startY ++;
//				counter++;
//				
//			}
//			startX++;
//		}
//		
//		startX = 26;
//		
//		for(int i = 0; i < 3; i++) {
//			int startY = 15;
//			for(int j = 0; j < 30; j++) {
//				OBSTACLES[counter] = new Point(startX, startY);
//				startY ++;
//				counter++;
//				
//			}
//			startX++;
//		}
		
		System.out.println("Configuring");
		AGENT_1_PLANNER = new Planner(GRID_X, GRID_Y, AGENT_1_START_POINT, AGENT_1_GOAL_POINT, OBSTACLES, null);
//		AGENT_1_PATH = AGENT_1_PLANNER.getUntimedPath();
		AGENT_1_PLOT = AGENT_1_PLANNER.getTimedPath();
		AGENT_1_PATH = AGENT_1_PLOT.getPath();

		
		AGENT_2_PLANNER = new Planner(GRID_X, GRID_Y, AGENT_2_START_POINT, AGENT_2_GOAL_POINT, OBSTACLES, AGENT_1_PLOT);
//		AGENT_1_PATH = AGENT_1_PLANNER.getUntimedPath();
		AGENT_2_PLOT = AGENT_2_PLANNER.getTimedPath();
		AGENT_2_PATH = AGENT_2_PLOT.getPath();
		
//		AGENT_2_PLANNER = new Planner(GRID_X, GRID_Y, AGENT_2_START_POINT, AGENT_2_GOAL_POINT, OBSTACLES, AGENT_1_PLOT);
//		AGENT_2_PLOT = AGENT_2_PLANNER.getPlot();
//		AGENT_2_PATH = AGENT_2_PLANNER.getPath();
//		AGENT_2_PLOT.print();
		
//		for(Point pt : AGENT_2_PATH) {
//			System.out.println("Agent 2: " + pt.toString());
//		}
	}

	
}
