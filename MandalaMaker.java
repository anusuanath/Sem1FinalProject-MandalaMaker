import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class MandalaMaker extends JApplet {
   
    public static void main(String[] args) {
	JFrame window = new JFrame("Mandala Maker");
	panel content = new panel();
	window.setContentPane(content);
	window.setSize(800,800);
	window.setLocation(0,0);
	window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	window.setVisible(true);
    }
   
    public void init() {
	setContentPane(new panel());
    }

    public static class panel extends JPanel implements MouseListener, MouseMotionListener {
	private int pX, pY;      
	private boolean dragging;
	private Graphics graphicsForDrawing;
	
	panel() {
	    setBackground(Color.WHITE);
	    addMouseListener(this);
	    addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int width = getWidth();
	    int height = getHeight();

	    g.setColor(Color.BLACK);

	    //THIS IS Y-AXIS
	    g.drawLine(width/2, 0, width/2, height);
	    
	    //THIS IS X-AXIS
	    g.drawLine(0, height/2, width, height/2);
	    
	    //DIAGONAL AXES
	    g.drawLine(0, 0, width, height);
	    g.drawLine(0, height, width, 0);

	    /*MORE AXES --> to make 8 total
	    g.drawLine(0, height/4, width, height/4*3);
	    g.drawLine(0, height/4*3, width, height/4);
	    g.drawLine(width/4, 0, width/4*3, height);
	    g.drawLine(width/4*3, 0, width/4, height);*/
	}

	private void setUpDrawingGraphics() {
	    graphicsForDrawing = getGraphics();
	    graphicsForDrawing.setColor(Color.BLACK);
	}
	
	public void mousePressed(MouseEvent e) {    
	    int x = e.getX();
	    int y = e.getY();

	    int width = getWidth();
	    int height = getHeight();
	    
	    if (dragging == true) {
		return;
	    }	 

	    if (x > width) {
		if (y > height) {
		    repaint();
		}
	    }

	    else if (x > 0 &&
		     x < width &&
		     y > 0 &&
		     y < height) {
		pX = x;
		pY = y;
		dragging = true;
		setUpDrawingGraphics();
	    }     
	}

	public void mouseReleased(MouseEvent evt) {
	   if (dragging == false) {
	       return;
	   }
	   dragging = false;
	   graphicsForDrawing.dispose();
	   graphicsForDrawing = null;
	}
      
	public void mouseDragged(MouseEvent e) {
	    int width = getWidth();
	    int height = getHeight();
	    int angle = 360/2;
	    double theta = Math.toRadians(angle);
	    if (dragging == false) {
		return;
	    }

	    int x = e.getX();
	    int y = e.getY();

	    //4 AXES

	    graphicsForDrawing.drawLine(pX, pY, x, y);
	    graphicsForDrawing.drawLine(pX, height - pY, x, height - y);
	    graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	    graphicsForDrawing.drawLine(width - pX, height - pY, width - x, height - y);

	    graphicsForDrawing.drawLine(pY, pX, y, x);
	    graphicsForDrawing.drawLine(height - pY, pX, height - y, x);
	    graphicsForDrawing.drawLine(pY, width - pX, y, width - x);
	    graphicsForDrawing.drawLine(height - pY, width - pX, height - y, width - x);  

	   
	    /*

	    //attempting to use formula

	    //storing pX, pY, x, y into other variables whose values can be changed
	    int new_pX = pX;
	    int new_pY = pY;
	    int new_x = x;
	    int new_y = y;
	    
	    //the x- and y-coordinates if they were on a Cartesian plane
	    int cart_pX = new_pX - width/2;
	    int cart_pY = height/2 - new_pY;
	    int cart_x = new_x - width/2;
	    int cart_y = height/2 - new_y;

	    //for one axis
	    graphicsForDrawing.drawLine(pX, pY, x, y);
	    graphicsForDrawing.drawLine(((int)(cart_pX*Math.cos(theta) - cart_pY*Math.sin(theta)) + width/2),
					((int)(cart_pX*Math.sin(theta) + cart_pY*Math.cos(theta)) +  height/2),
					((int)(cart_x*Math.cos(theta) - cart_y*Math.sin(theta)) + width/2),
					((int)(cart_x*Math.sin(theta) + cart_y*Math.cos(theta)) + height/2));
	    
	    //for two axes
	    theta = Math.toRadians(90);
	    graphicsForDrawing.drawLine(((int)(cart_pX*Math.sin(theta) - cart_pY*Math.cos(theta)) + width/2),
					((int)(cart_pX*Math.cos(theta) + cart_pY*Math.sin(theta)) +  height/2),
					((int)(cart_x*Math.sin(theta) - cart_y*Math.cos(theta)) + width/2),
					((int)(cart_x*Math.cos(theta) + cart_y*Math.sin(theta)) + height/2));

	    theta = Math.toRadians(180);
	    
	    new_pX = (int)(cart_pX*Math.cos(theta) - cart_pY*Math.sin(theta)) + width/2;
	    new_pY = (int)(cart_pX*Math.sin(theta) + cart_pY*Math.cos(theta)) +  height/2;
	    new_x = (int)(cart_x*Math.cos(theta) - cart_y*Math.sin(theta)) + width/2;
	    new_y = (int)(cart_x*Math.sin(theta) + cart_y*Math.cos(theta)) + height/2;

	    cart_pX = new_pX - width/2;
	    cart_pY = height/2 - new_pY;
	    cart_x = new_x - width/2;
	    cart_y = height/2 - new_y;
	    
	    theta = Math.toRadians(90);
	    graphicsForDrawing.drawLine(((int)(cart_pX*Math.sin(theta) - cart_pY*Math.cos(theta)) + width/2),
					((int)(cart_pX*Math.cos(theta) + cart_pY*Math.sin(theta)) +  height/2),
					((int)(cart_x*Math.sin(theta) - cart_y*Math.cos(theta)) + width/2),
					((int)(cart_x*Math.cos(theta) + cart_y*Math.sin(theta)) + height/2));
	    
	    */
	    
	   
	    /*attempting a for loop and failing miserably
	    for (int i = 0; i < 2; i++) {
		graphicsForDrawing.drawLine(pX, pY, x, y);
		new_pX = ((int)(cart_pX*Math.cos(theta) - cart_pY*Math.sin(theta)) + width/2);
		new_pY = ((int)(cart_pX*Math.sin(theta) + cart_pY*Math.cos(theta)) +  height/2);
		new_x = ((int)(cart_x*Math.cos(theta) - cart_y*Math.sin(theta)) + width/2);
		new_y = ((int)(cart_x*Math.sin(theta) + cart_y*Math.cos(theta)) + height/2);
		pX = new_pX;
		pY = new_pY;
		x = new_x;
		y = new_y;
	    }
	    */
	 
	    pX = x;
	    pY = y;

	}
       
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseMoved(MouseEvent evt) { }
    }
}
