import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Visualizer extends JFrame implements KeyListener {
	private Config config = new Config();
	private int xbuffer = 50;
	private int ybuffer = 50;
	private int time = 0;
	private EnvironmentJPanel panel;

	
	public Visualizer() {
//		super.setTitle("Visualizer");
//		super.setSize(config.X_DIMENSION, config.Y_DIMENSION);
//		super.setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		addKeyListener((KeyListener) this);
//		repaint();
		
//      super();
//      panel = new EnvironmentJPanel();
//      this.add(panel);
//      panel.setVisible(true);
//      pack();
//      addKeyListener(this);
//      repaint();
		
		 super();
	     panel = new EnvironmentJPanel();
	     add(panel);
	     pack();
	     addKeyListener(this);
	     repaint();

	}
	
	 @Override
	    public void repaint() {
	        super.repaint();
//	        this.paint(null);
	    }
	 
	
	
	 public void keyPressed(KeyEvent e) {
		 panel.keyPressed(e);
	     repaint();
	    }
	 
	
	 
//	public void paint(Graphics g) {
////		System.out.println("Painting");
////		double xIncrement = (config.X_DIMENSION - 2 * config.XBUFFER)/(config.GRID_X + 1);
////	    double yIncrement = (config.Y_DIMENSION - 2 * config.XBUFFER)/(config.GRID_Y + 1);
////	    
////	    int[][] dotGrid = new int[config.GRID_X][config.GRID_Y];
////		int id = 2;
////		
////		
////		
////		
////		
////		for(Point o : config.OBSTACLES) {
////			dotGrid[(int) o.getX()][(int) o.getY()] = 1;
////
////		}
////		
////		ArrayList<Point> obstacleLocs = new ArrayList<Point>();
////		for (Point obstacle : config.OBSTACLES) {
////			obstacleLocs.add(obstacle);
////		}
////		
//////		for(Point pt : config.AGENT_1_PATH) {
////////			System.out.println(pt.getY());
//////			dotGrid[(int) (pt.getX())][(int) (pt.getY())] = 2;
//////		}
//////		
//////		for(Point pt : config.AGENT_2_PATH) {
//////			dotGrid[(int) (pt.getX())][(int) (pt.getY())] = 3;
//////		}
////		
////		Point agent1Pt = config.AGENT_1_PLOT.getLocAtTime(time);
////		Point agent2Pt = config.AGENT_2_PLOT.getLocAtTime(time);
////		
////		dotGrid[(int) agent1Pt.getX()][(int) agent1Pt.getY()] = 2;
////		dotGrid[(int) agent2Pt.getX()][(int) agent2Pt.getY()] = 3;
////
////
////		
////		for(int x = 0; x < config.GRID_X; x++) {
////			for (int y = 0; y < config.GRID_Y; y ++) {
////				switch (dotGrid[x][y]) {
////	            case 3:
////	                g.setColor(Color.cyan);
//////	                g.setColor(Color.black);
////	                break;
////	            case 2:
////	                g.setColor(Color.ORANGE);
////	                break;
////	            case 1:
////	            	g.setColor(Color.red);
////	            	break;
////	            case 0:
////	            	g.setColor(Color.black);
////	            	break;
////	            
////				}
////				g.fillOval( (int) (config.XBUFFER + ((x + 1) *xIncrement)), (int) (config.YBUFFER + ((y + 1)*yIncrement)), config.radius, config.radius);
////
////			}
////			
//////			g.setColor(Color.orange);
//////			for(int i = 1; i < config.AGENT_1_PATH.size(); i++) {
//////				Graphics2D g2 = (Graphics2D) g;
//////				int startX = (int) config.AGENT_1_PATH.get(i-1).getX();
//////				int startY= (int) config.AGENT_1_PATH.get(i-1).getY();
//////				int goalX = (int) config.AGENT_1_PATH.get(i).getX();
//////				int goalY = (int) config.AGENT_1_PATH.get(i).getY();
////////				System.out.println("Start Y: " + startX);
//////
//////		        Line2D lin1 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) * xIncrement) + config.radius/2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2, (int) (config.YBUFFER + (goalY + 1) *yIncrement + config.radius/2));
//////		        Line2D lin2 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 - 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement)  + config.radius/2 - 1);
//////		        Line2D lin3 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 + 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 1);
//////		        Line2D lin4 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 - 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 - 2);
//////		        Line2D lin5 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 + 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 2);
//////
//////		        
//////		        g2.draw(lin1);
//////		        g2.draw(lin2);
//////		        g2.draw(lin3);
//////		        g2.draw(lin4);
//////		        g2.draw(lin5);
//////		        
//////		        
//////		        
////// 
//////			}
//////			
//////			g.setColor(Color.cyan);
//////			for(int i = 1; i < config.AGENT_2_PATH.size(); i++) {
//////				Graphics2D g2 = (Graphics2D) g;
//////				int startX = (int) config.AGENT_2_PATH.get(i-1).getX();
//////				int startY= (int) config.AGENT_2_PATH.get(i-1).getY();
//////				int goalX = (int) config.AGENT_2_PATH.get(i).getX();
//////				int goalY = (int) config.AGENT_2_PATH.get(i).getY();
//////
//////				Line2D lin1 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) * xIncrement) + config.radius/2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2, (int) (config.YBUFFER + (goalY + 1) *yIncrement + config.radius/2));
//////		        Line2D lin2 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 - 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement)  + config.radius/2 - 1);
//////		        Line2D lin3 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 + 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 1);
//////		        Line2D lin4 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 - 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 - 2);
//////		        Line2D lin5 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 + 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 2);
//////
//////		        
//////		        g2.draw(lin1);
//////		        g2.draw(lin2);
//////		        g2.draw(lin3);
//////		        g2.draw(lin4);
//////		        g2.draw(lin5);
//////			}
////		}
//	}
	
	public static void main(String[] args) {
		
		
		Config config = new Config();
//		config.AGENT_2_PLOT.print();
		
		
		 Visualizer frame = new Visualizer(); 
//		 frame.paint(null);
		 frame.setVisible(true);
	      frame.addWindowListener(
	      new WindowAdapter()
	      {
	         public void windowClosing(WindowEvent we)
	         {
	            System.exit(0);
	         }
	      }
	      );
	           
	      frame.setSize(config.X_DIMENSION, config.Y_DIMENSION);
	      frame.setVisible(true);
		
		JFrame jf = new JFrame("Renderer");
//        jf.getContentPane().add(new EnvironmentJPanel(), BorderLayout.CENTER);
        jf.setBounds(100,100,400,300);
        jf.setVisible(true);
		
		
		
		
		
		
		
		/** COLLISION + EFFECTIVENESS TESTING */
		
	
//		Agent a2 = new Agent(1, 1, 3, 4, "agent 1");
//		Agent a1 = new Agent(1, 2, 1, 3, "agent 2");
//		
//		Obstacle o1 = new Obstacle(2, 4, "obstacle 1");
//		Obstacle o2 = new Obstacle(1, 4, "obstacle 2");
//		
//		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
//		
//		ArrayList<Point> obstacleLocs = new ArrayList<Point>();
//		
//		obstacles.add(o1);
//		obstacles.add(o2);
//		
//		for (Obstacle obstacle : obstacles) {
//			obstacleLocs.add(new Point(obstacle.getX(), obstacle.getY()));
//		}
		
		
//		Environment env = new Environment(20, 20, obstacles);
//		
//		XYTimePlot[] dynamicObstacles = new XYTimePlot[1];
//		
//		
//		
//		
//		PathPlanner testA1 = new PathPlanner(new Point(a1.getStartX(), a1.getStartY()), new Point(a1.getGoalX(), a1.getGoalY()), obstacleLocs, env.getX(), env.getY(), null);
//		
//		ArrayList<Point> a1Path = testA1.returnPath();
//		XYTimePlot a1Plot = new XYTimePlot(a1Path, a1);
//
//		
//		PathPlanner testA2 = new PathPlanner(new Point(a2.getStartX(), a2.getStartY()), new Point(a2.getGoalX(), a2.getGoalY()), obstacleLocs, env.getX(), env.getY(), a1Plot);
//		testA2.dynamicObstacleTester();
//		ArrayList<Point> a2Path = testA2.returnPath();
//		
//		
//		
//		for(int i = 1; i <= a1Path.size(); i++) {
//			System.out.println("Time " + i + ": " + a1Path.get(i-1));
//		}
//		
//		for(int i = 1; i <= a2Path.size(); i++) {
//			System.out.println("Time " + i + ": " + a2Path.get(i-1));
//		}
		

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
