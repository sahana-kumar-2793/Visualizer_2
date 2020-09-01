import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class EnvironmentJPanel extends JPanel {
	private int time = 0;
	private Config config = new Config();
	
	 public EnvironmentJPanel() {
	        setPreferredSize(new Dimension(config.X_DIMENSION, config.Y_DIMENSION));
	    }

	    @Override
	    public void update(Graphics g) {
	        paint(g);
	    }

	    @Override
	    public void paint(Graphics g) {
	    	System.out.println("Painting");
			double xIncrement = (config.X_DIMENSION - 2 * config.XBUFFER)/(config.GRID_X + 1);
		    double yIncrement = (config.Y_DIMENSION - 2 * config.XBUFFER)/(config.GRID_Y + 1);
		    
		    int[][] dotGrid = new int[config.GRID_X][config.GRID_Y];
			int id = 2;
			dotGrid[(int) config.AGENT_1_GOAL_POINT.getX()][(int) config.AGENT_1_GOAL_POINT.getY()] = 4;
			dotGrid[(int) config.AGENT_2_GOAL_POINT.getX()][(int) config.AGENT_2_GOAL_POINT.getY()] = 5;
			
			
			
			
			
			for(Point o : config.OBSTACLES) {
				dotGrid[(int) o.getX()][(int) o.getY()] = 1;

			}
			
			ArrayList<Point> obstacleLocs = new ArrayList<Point>();
			for (Point obstacle : config.OBSTACLES) {
				obstacleLocs.add(obstacle);
			}
			
//			for(Point pt : config.AGENT_1_PATH) {
////				System.out.println(pt.getY());
//				dotGrid[(int) (pt.getX())][(int) (pt.getY())] = 2;
//			}
//			
//			for(Point pt : config.AGENT_2_PATH) {
//				dotGrid[(int) (pt.getX())][(int) (pt.getY())] = 3;
//			}
			
			Point agent1Pt = config.AGENT_1_PLOT.getLocAtTime(time);
			Point agent2Pt = config.AGENT_2_PLOT.getLocAtTime(time);
//			Point agent3Pt = config.AGENT_3_PLOT.getLocAtTime(time);

			
			System.out.println("At time t = " + time + " agent 1 is at " + agent1Pt.toString());
			System.out.println("At time t = " + time + " agent 2 is at " + agent2Pt.toString());
			
			dotGrid[(int) agent1Pt.getX()][(int) agent1Pt.getY()] = 2;
			dotGrid[(int) agent2Pt.getX()][(int) agent2Pt.getY()] = 3;
//			dotGrid[(int) agent3Pt.getX()][(int) agent3Pt.getY()] = 4;

			
//			dotGrid[(int) config.AGENT_1_PATH.get(0).getX()][(int) config.AGENT_1_PATH.get(0).getY()] = 2;
//			dotGrid[(int) config.AGENT_2_PATH.get(0).getX()][(int) config.AGENT_2_PATH.get(0).getY()] = 3;
//			dotGrid[(int) config.AGENT_3_PATH.get(0).getX()][(int) config.AGENT_3_PATH.get(0).getY()] = 4;

			
			
//			dotGrid[(int) config.AGENT_3_GOAL_POINT.getX()][(int) config.AGENT_3_GOAL_POINT.getY()] = 4;

			
		
			
			for(int x = 0; x < config.GRID_X; x++) {
				for (int y = 0; y < config.GRID_Y; y ++) {
					switch (dotGrid[x][y]) {
					case 3:
		                g.setColor(Color.cyan);
//		                g.setColor(Color.black);
		                break;
		            case 2:
		                g.setColor(Color.ORANGE);
		                break;
		            case 1:
		            	g.setColor(Color.red);
		            	break;
		            case 0:
		            	g.setColor(Color.black);
		            	break;
		            case 5:
						g.setColor(Color.blue);
						break;
					case 4:
						g.setColor(Color.MAGENTA);
						break;
		            
					}
					g.fillOval( (int) (config.XBUFFER + ((x + 1) *xIncrement)), (int) (config.YBUFFER + ((y + 1)*yIncrement)), config.radius, config.radius);

				}
				
				g.drawString("t = " + time, config.XBUFFER, config.YBUFFER);
				
				
//				g.setColor(Color.orange);
//				for(int i = 1; i < config.AGENT_1_PATH.size(); i++) {
//					Graphics2D g2 = (Graphics2D) g;
//					int startX = (int) config.AGENT_1_PATH.get(i-1).getX();
//					int startY= (int) config.AGENT_1_PATH.get(i-1).getY();
//					int goalX = (int) config.AGENT_1_PATH.get(i).getX();
//					int goalY = (int) config.AGENT_1_PATH.get(i).getY();
////					System.out.println("Start Y: " + startX);
	//
//			        Line2D lin1 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) * xIncrement) + config.radius/2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2, (int) (config.YBUFFER + (goalY + 1) *yIncrement + config.radius/2));
//			        Line2D lin2 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 - 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement)  + config.radius/2 - 1);
//			        Line2D lin3 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 + 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 1);
//			        Line2D lin4 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 - 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 - 2);
//			        Line2D lin5 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 + 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 2);
	//
//			        
//			        g2.draw(lin1);
//			        g2.draw(lin2);
//			        g2.draw(lin3);
//			        g2.draw(lin4);
//			        g2.draw(lin5);
//			        
//			        
//			        
	// 
//				}
//				
//				g.setColor(Color.cyan);
//				for(int i = 1; i < config.AGENT_2_PATH.size(); i++) {
//					Graphics2D g2 = (Graphics2D) g;
//					int startX = (int) config.AGENT_2_PATH.get(i-1).getX();
//					int startY= (int) config.AGENT_2_PATH.get(i-1).getY();
//					int goalX = (int) config.AGENT_2_PATH.get(i).getX();
//					int goalY = (int) config.AGENT_2_PATH.get(i).getY();
	//
//					Line2D lin1 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) * xIncrement) + config.radius/2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2, (int) (config.YBUFFER + (goalY + 1) *yIncrement + config.radius/2));
//			        Line2D lin2 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 - 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement)  + config.radius/2 - 1);
//			        Line2D lin3 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 + 1, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 1, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 1);
//			        Line2D lin4 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (startY + 1) *yIncrement)  + config.radius/2 - 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 - 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 - 2);
//			        Line2D lin5 = new Line2D.Float((int) (config.XBUFFER + (startX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (startY + 1) *yIncrement) + config.radius/2 + 2, (int) (config.XBUFFER + (goalX + 1) *xIncrement) + config.radius/2 + 2, (int) (config.YBUFFER + (goalY + 1) *yIncrement) + config.radius/2 + 2);
	//
//			        
//			        g2.draw(lin1);
//			        g2.draw(lin2);
//			        g2.draw(lin3);
//			        g2.draw(lin4);
//			        g2.draw(lin5);
//				}
			}
	    }

	    public void keyPressed(KeyEvent e) {
	        int k = e.getKeyCode();

	        switch (k) {
		        case KeyEvent.VK_LEFT:
	            	System.out.println("left");

	            	if(time > 0) {
	            		time--;
	            	}
	    	        repaint();

	                break;
	            case KeyEvent.VK_RIGHT:
	            	System.out.println("right");
	                if(time < config.MAX_TIME) {
	                	time++;
	                }
	    	        repaint();

	                break;
	        }
	        
	    }
}
