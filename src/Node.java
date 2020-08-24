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
	
	public Node(Point location, NodeState nodeState, Point goalPoint, Node originNode, boolean isTimed) {
		this.location = location;
		this.nodeState = nodeState;
		this.goalPoint = goalPoint;
		this.originNode = originNode;
		this.isTimed = isTimed;
		
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
		h = getDist(goalPoint);
	}
	
	public int getDist(Point pt) {
		int xDist = (int) Math.abs(this.getLocation().getX() - pt.getX());
		int yDist = (int) Math.abs(this.getLocation().getY() - pt.getY());
		
		return (int) Math.sqrt(xDist*xDist + yDist*yDist);

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
		return g;
	}
	
	
	
	public int getH() {
		return h;
	}
	
	public int getF() {
		return f;
	}
	
	public void setG(int g) {
		this.g = g;
		f = g + h;
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
