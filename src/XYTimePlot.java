import java.awt.Point;
import java.util.ArrayList;

public class XYTimePlot {
	private ArrayList<Point> path;
	private int[][] xytime; //row 1 = time. row 2 = x. row 3 = y.
	
	public XYTimePlot(ArrayList<Point> path) {
		this.path = path;
		xytime = new int[3][path.size()];
		for(int time = 0; time < path.size(); time++) {
			xytime[0][time] = time;
			xytime[1][time] = (int) path.get(time).getX();
			xytime[2][time] = (int) path.get(time).getY();

		}	

		
	}
	
	public int[][] getPlot() {
		return xytime;
	}
	
	public ArrayList<Point> getPath() {
		return path;
	}
	
	public void print() {
		for (int t = 0; t < xytime[0].length; t++) {
			System.out.println("Time: " + xytime[0][t] + " The Point is: " + path.get(t));
		}
	}
	
	public int getPlotSize() {
		return xytime[0].length;
	}
	
	public int getMaxTime() {
		return path.size();
	}
	
	public Point getGoalPoint() {
		return path.get(getMaxTime() - 1); 
	}
	
	public Point getLocAtTime(int time) {
		int length = path.size() - 1;
//		System.out.println("Length: " + length);
		Point endPoint = new Point(xytime[1][length], xytime[2][length]);
//		System.out.println("Time: "+ time);
		return time < length ? new Point(xytime[1][time], xytime[2][time]) : new Point(xytime[1][length], xytime[2][length]);
	}
	

	
	public static XYTimePlot[] plotParser(XYTimePlot[] agents) {
		int maxSize = 0;
		for(XYTimePlot agent : agents) {
			if(agent.getPlotSize() > maxSize) {
				maxSize = agent.getPlotSize();
			}
		}
		
		XYTimePlot[] parsedAgents = new XYTimePlot[agents.length];
		
		for(int i = 0; i < agents.length; i++) {
			ArrayList<Point> agentPath = agents[i].path;
			
			while(agentPath.size() < maxSize) {
				Point goalPoint = agents[i].getGoalPoint();
				agentPath.add(goalPoint);
			}
			
			parsedAgents[i] = new XYTimePlot(agentPath);
		}
		
		
		return parsedAgents;
	}

}
