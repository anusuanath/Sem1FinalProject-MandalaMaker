import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

/* http://math.hws.edu/eck/cs124/javanotes6/source/SimplePaint.java  */

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
	    
	    g.drawLine(width/2, 0, width/2, height); //THIS IS Y-AXIS
	    g.drawLine(0, height/2, width, height/2); //THIS IS X-AXIS

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

	    else if (x > 0 && x < width && y > 0 && y < height) {
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
         
	   /*
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   graphicsForDrawing.drawLine(pX + 2, pY + 2, x + 2, y + 2);
	   graphicsForDrawing.drawLine(pX + 4, pY + 4, x + 4, y + 4);
	   graphicsForDrawing.drawLine(pX - 2, pY - 2, x - 2, y - 2);
	   graphicsForDrawing.drawLine(pX - 4, pY - 4, x - 4, y - 4);
	   */

	   /*
	   
	   if (x < width/2) {
	       graphicsForDrawing.drawLine(pX, pY, x, y);
	       graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	   }

	   if (x > width/2) {
	       graphicsForDrawing.drawLine(pX, pY, x, y);
	       graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	   }
	   
	   */

	   // 2 AXES

	   /*
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   graphicsForDrawing.drawLine(pX, height - pY, x, height - y);
	   graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	   graphicsForDrawing.drawLine(width - pX, height - pY, width - x, height - y);
	   */
	   
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   
	   graphicsForDrawing.drawLine(((int)(pX * Math.cos(theta) - pY * Math.sin(theta)) + (width)),
				       ((int)(pX * Math.sin(theta) + pY * Math.cos(theta)) + (height)),
				       ((int)(x * Math.cos(theta) - y * Math.sin(theta)) + (width)),
				       ((int)(x * Math.sin(theta) + y * Math.cos(theta))) + (height));
	   pX = x;
	   pY = y;

	}
       
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseMoved(MouseEvent evt) { }
    }
}
