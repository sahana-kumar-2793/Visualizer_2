import java.awt.Point;


public class Node {
	public enum NodeState {
		  OPEN,
		  CLOSED,
		  UNSEEN,
		  UNAVAILABLE
	}
	
	private Node originNode;
	private Point location;
	private int f, g, h;
	private int time;
	private NodeState nodeState, prevNodeState;
	private final int MIN_DIST = 5;
	private boolean isCurr = false;
	private boolean isTimed;
	private Point goalPoint;
	private XYTimePlot[] dynamicObstacles;
	private int distWeight = 100;
	private int gWeight = (Config.GRID_X + Config.GRID_Y) * 2 * distWeight != 0 ? distWeight : 1;
	private int gRaw;
	
	public Node(Point location, NodeState nodeState, Point goalPoint, Node originNode, boolean isTimed, XYTimePlot ... dynamicObstacles) {
		this.location = location;
		this.nodeState = nodeState;
		this.goalPoint = goalPoint;
		this.originNode = originNode;
		this.isTimed = isTimed;
		this.dynamicObstacles = dynamicObstacles;
		
		if(isTimed) {
			if(originNode == null) {
				time = 0;
			} else {
				time = originNode.time + 1;
			}
		} else {
			time = -1;
		}
		
		f = Integer.MAX_VALUE;
		g = Integer.MAX_VALUE;
		gRaw = Integer.MAX_VALUE;
		h = getDist(goalPoint);
	}
	
	public int getDist(Point pt) {
		int xDist = (int) Math.abs(this.getLocation().getX() - pt.getX());
		int yDist = (int) Math.abs(this.getLocation().getY() - pt.getY());
		
		return (int) Math.sqrt(xDist*xDist + yDist*yDist);

	}
	
	public int getDistCost() {
		if(dynamicObstacles == null || !isTimed) {
			return 0;
		} else {
			int distFromOtherAgents = 0;
			for(XYTimePlot obstacle : dynamicObstacles) {
				distFromOtherAgents += getDist(obstacle.getLocAtTime(time));
			}
			System.out.println(distFromOtherAgents);

			return (-distFromOtherAgents*distFromOtherAgents);
		}
		
		
	}
	
	public Node getOrigin() {
		return originNode;
	} 
	
	public void setOrigin(Node origin) {
		this.originNode = origin;
		if(isTimed) {
			time = originNode.getTime() + 1;
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getCost() {
		return f;
	}
	
	public int getG() {
		System.out.println("G: " + g);
		return g;
	}
	
	public int getGRaw() {
		return gRaw;
	}
	
	public int getH() {
		return h;
	}
	
	public int getF() {
		return f;
	}
	
	public void setG(int g) {
		gRaw = g;
		System.out.println("G v raw: " + g);
		System.out.println("G Weight: " + gWeight);
		System.out.println("G (Raw): " + g * gWeight);

		this.g = g*gWeight + getDistCost() * distWeight;
		System.out.println("Dist cost: " + getDistCost() * distWeight);
		f = g + h; //+ (getDistCost()*weight);
//		System.out.println("F: " + f);
//		System.out.println("Old f:" + (g+h));
	}
	
	public NodeState getState() {
		return nodeState;
	}
	
	public void setState(NodeState newState) {
		prevNodeState = nodeState;
		nodeState = newState;
	}
	
	public void revertState() {
		nodeState = prevNodeState;
	}
	
}
