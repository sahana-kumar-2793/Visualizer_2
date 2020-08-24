import java.awt.Point;
import java.util.ArrayList;

public class Planner {
	private int gridX, gridY;
	private Point startPoint, goalPoint;
	Point[] staticObstacles;
	XYTimePlot[] dynamicObstacles;
	int[][] obstacleLocs;
	boolean timed;
	
	Node[][] map;
	
	int[][] currGrid;
	
	
	public Planner(int gridX, int gridY, Point startPoint, Point goalPoint, Point[] staticObstacles) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.startPoint = startPoint;
		this.goalPoint = goalPoint;
		this.staticObstacles = staticObstacles;
		timed = false;
		map = new Node[gridX][gridY];
		currGrid = generateMap();

		
		obstacleLocs = generateMap();
	}
	
	public Planner(int gridX, int gridY, Point startPoint, Point goalPoint, Point[] staticObstacles, XYTimePlot ... dynamicObstacles) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.startPoint = startPoint;
		this.goalPoint = goalPoint;
		this.staticObstacles = staticObstacles;
		this.dynamicObstacles = dynamicObstacles;
		timed = true;
		map = new Node[gridX][gridY];
		currGrid = generateMap();


		
		obstacleLocs = generateMap(0, new Node(startPoint, Node.NodeState.OPEN, goalPoint, null, true));

	}
	
	public int[][] generateMap() {
		int[][] grid = new int[gridX][gridY];
		
		if(staticObstacles != null) {
			for(Point obstacle : staticObstacles) {
				grid[(int) obstacle.getX()][(int) obstacle.getY()] = 1;
			}
		}
		
		return grid;
	}
	
	public int[][] generateMap(int time, Node currNode) {
		int[][] grid = new int[gridX][gridY];
		
		if(staticObstacles != null) {
			for(Point obstacle : staticObstacles) {
				grid[(int) obstacle.getX()][(int) obstacle.getY()] = 1;
			}
		}
		
		if(dynamicObstacles != null) {
			for(XYTimePlot plot : dynamicObstacles) {
				Point pt = plot.getLocAtTime(time);
				System.out.println("At time t=" + time + " bloced at " + pt.toString());
				grid[(int) pt.getX()][(int) pt.getY()] = 2;
				if(pt.equals(currNode.getLocation())) {
					grid[(int) plot.getLocAtTime(time - 1).getX()][(int) plot.getLocAtTime(time - 1).getY()] = 2;
				}
			}
		}
		
		return grid;
	}
	
	public ArrayList<Point> getUntimedPath() {
		ArrayList<Point> path = new ArrayList<Point>();
		
		MinHeap openList = new MinHeap((int) (Math.pow(gridX, 3) * Math.pow(gridY, 2)));
		map[(int) startPoint.getX()][(int) startPoint.getY()] = new Node(startPoint, Node.NodeState.OPEN, goalPoint, null, false);
		map[(int) startPoint.getX()][(int) startPoint.getY()].setG(0);
		openList.insert(map[(int) startPoint.getX()][(int) startPoint.getY()]);
		Node goalNode = null;
		
		while(openList.size() != 0) {
			Node currNode = openList.remove();
			currNode.setState(Node.NodeState.CLOSED);
						
			if(currNode.getLocation().equals(goalPoint)) {
				goalNode = currNode;
				break;
			}
			System.out.println(currNode.getLocation().toString());
			
			ArrayList<Point> children = getChildren(currNode);
			
			for(Point childPos : children) {
				int childX = (int) childPos.getX();
				int childY = (int) childPos.getY();
				Node child = map[childX][childY];
				System.out.println("Child X: " + childX + " Child Y: " + childY);

				
				if(child == null) {
					map[childX][childY] = new Node(childPos, Node.NodeState.OPEN, goalPoint, currNode, false);
					child = map[childX][childY];
					map[childX][childY].setG(child.getOrigin().getG() + 1);
					openList.insert(map[childX][childY]);
				} else if(child.getState() != Node.NodeState.CLOSED && map[childX][childY].getG() >= currNode.getG() + child.getDist(currNode.getLocation())) {
					map[childX][childY].setG(currNode.getG() + child.getDist(currNode.getLocation()));
					map[childX][childY].setOrigin(currNode);
					openList.insert(map[childX][childY]);
				}

			}
		}
		
		path.add(goalPoint);

		if(goalNode == null) {
			System.out.println("Path not found");
			return path;
		} 
		
		while(goalNode.getOrigin() != null) {
			System.out.println("Path found");
			path.add(0, goalNode.getOrigin().getLocation());
			goalNode = goalNode.getOrigin();
		}
		
		System.out.println("Printing Path: ");
		for(Point pt : path) {
			System.out.println(pt.toString());
		}
		
		return path;
	}
	
	
	public XYTimePlot getTimedPath() {
		Node[][][] grid = new Node[gridX*gridY][gridX][gridY]; //Time, X, Y
		ArrayList<Point> path = new ArrayList<Point>();
		
		grid[0][(int) startPoint.getX()][(int) startPoint.getY()] = new Node(startPoint, Node.NodeState.OPEN, goalPoint, null, true);
		grid[0][(int) startPoint.getX()][(int) startPoint.getY()].setG(0);
		
		MinHeap openList = new MinHeap((int) (Math.pow(gridX, 3) * Math.pow(gridY, 2)));
		openList.insert(grid[0][(int) startPoint.getX()][(int) startPoint.getY()]);
		
		Node goalNode = null;
		
		while(openList.size() != 0) {
			Node currNode = openList.remove();
			int time = currNode.getTime();
			currNode.setState(Node.NodeState.CLOSED);
			currGrid[(int) currNode.getLocation().getX()][(int) currNode.getLocation().getY()] = 3;
			
			if(currNode.getLocation().equals(goalPoint)) {
				goalNode = currNode;
				break;
			}
			System.out.println(currNode.getLocation());
			
			
			ArrayList<Point> children = getChildren(currNode);
			
			for(Point childPos : children) {
				int childX = (int) childPos.getX();
				int childY = (int) childPos.getY();
				Node child;
				if(time + 1 < grid.length) {
					child = grid[time+1][childX][childY];
				} else {
					break;
				}
				System.out.println("Child X: " + childX + " Child Y: " + childY);

				
				if(child == null) {
					grid[time+1][childX][childY] = new Node(childPos, Node.NodeState.OPEN, goalPoint, currNode, true);
					child = grid[time+1][childX][childY];
					grid[time+1][childX][childY].setG(child.getOrigin().getG() + 1);
					openList.insert(grid[time+1][childX][childY]);
				} else if(child.getState() != Node.NodeState.CLOSED && grid[time+1][childX][childY].getG() >= currNode.getG() + child.getDist(currNode.getLocation())) {
					grid[time+1][childX][childY].setG(currNode.getG() + child.getDist(currNode.getLocation()));
					grid[time+1][childX][childY].setOrigin(currNode);
					openList.insert(grid[time+1][childX][childY]);
				}

			}
		}
		

		if(goalNode == null) {
			System.out.println("Path not found");
			path.add(startPoint);
			return new XYTimePlot(path);
		} 
		
		path.add(goalPoint);

		while(goalNode.getOrigin() != null) {
			System.out.println("Path found");
			path.add(0, goalNode.getOrigin().getLocation());
			goalNode = goalNode.getOrigin();
		}
		
		System.out.println("Printing Path: ");
		for(Point pt : path) {
			System.out.println(pt.toString());
		}

		
		return new XYTimePlot(path);
	}
	
	public ArrayList<Point> getChildren(Node currNode) {
		ArrayList<Point> children = new ArrayList<Point>();
		int x = (int) currNode.getLocation().getX();
		int y = (int) currNode.getLocation().getY();

		currGrid = generateMap(currNode.getTime() + 1, currNode);

		
		if(x+1 < gridX && currGrid[x+1][y] == 0) {
			children.add(new Point(x+1, y));
		}
		
		if(y+1 < gridY && currGrid[x][y + 1] == 0) {
			children.add(new Point(x, y+1));
		}
		
		if(x-1 >= 0 && currGrid[x-1][y] == 0) {
			children.add(new Point(x-1, y));
		}
		
		if(y-1 >= 0 && currGrid[x][y-1] == 0) {
			children.add(new Point(x, y-1));
		}
		
		if(timed && children.size() == 0) {
			System.out.println("Size = 0");
			if(currGrid[x][y] == 0) {
				children.add(currNode.getLocation());
			}
		}
		
		return children;
	}
}
