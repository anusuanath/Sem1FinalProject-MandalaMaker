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
<<<<<<< HEAD

	    //THIS IS Y-AXIS
	    g.drawLine((width - 56)/2, 0, (width - 56)/2, height);

	    //THIS IS X-AXIS
	    g.drawLine(0, height/2, width-56, height/2);

	    //DIAGONAL AXES
	    g.drawLine(0, 0, width-56, height);
	    g.drawLine(0, height, width-56, 0);

	}

	private void changeColor(int y) {   
	    int width = getWidth();
	    int height = getHeight();
	    int colorSpacing = (height - 56) / 7;
	    int newColor = y / colorSpacing;
=======
>>>>>>> racheltriestodostuff
	    
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
         
<<<<<<< HEAD
	    if (x < 3) {
		x = 3;
	    }
	
	   if (x > getWidth() - 57) {
	       x = getWidth() - 57;
	   }
	
	   if (y < 4) {
	       y = 4;
	   }

	   if (y > getHeight() - 5) {
	       y = getHeight() - 5;
	   }


	   /*reflects across y-axis
	  
=======
	   /*
>>>>>>> racheltriestodostuff
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	  

	   //reflects across x-axis

	   graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	   graphicsForDrawing.drawLine(width - pX, height - pY, width - x, height - y);
       	   */
	   //x-, y-, and diagonal axes refelctions

<<<<<<< HEAD
=======
	   // 2 AXES

	   /*
>>>>>>> racheltriestodostuff
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   graphicsForDrawing.drawLine(pX, height - pY, x, height - y);
	   graphicsForDrawing.drawLine(width - pX, pY, width - x, y);
	   graphicsForDrawing.drawLine(width - pX, height - pY, width - x, height - y);
<<<<<<< HEAD
	   graphicsForDrawing.drawLine(pY, pX, y, x);
	   graphicsForDrawing.drawLine(height - pY, pX, height - y, x);
	   graphicsForDrawing.drawLine(pY, width - pX, y, width - x);
	   graphicsForDrawing.drawLine(height - pY, width - pX, height - y, width - x);
	   

	   /*//attempting to use formula

	   //for one axis
	   double theta = Math.toRadians(180);
	   
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   graphicsForDrawing.drawLine(((int)((pX-width/2) * Math.cos(theta) - (pY-height/2) * Math.sin(theta)) + width/2),
				       (-1*(int)((pX-width/2) * Math.sin(theta) + (pY-height/2) * Math.cos(theta)) +  height/2),
				       ((int)((x-width/2) * Math.cos(theta) - (y-height/2) * Math.sin(theta)) + width/2),
				       (-1*(int)((x-width/2) * Math.sin(theta) + (y-height/2) * Math.cos(theta)) + height/2));
	   
	   */
	   /*for two axis
	     double theta = Math.toRadians(90);
	   
	     graphicsForDrawing.drawLine(pX, pY, x, y);
	     graphicsForDrawing.drawLine(((int)((pX-width/2) * Math.sin(theta) + (pY-height/2) * Math.cos(theta)) + width/2),
					 ((int)((pX-width/2) * Math.cos(theta) - (pY-height/2) * Math.sin(theta)) +  height/2),
					 ((int)((x-width/2) * Math.sin(theta) + (y-height/2) * Math.cos(theta)) + width/2),
					 ((int)((x-width/2) * Math.cos(theta) - (y-height/2) * Math.sin(theta)) + height/2));
	     graphicsForDrawing.drawLine(((int)((pX-width/2) * Math.cos(theta) - (pY-height/2) * Math.sin(theta)) + width/2),
					 ((int)((pX-width/2) * Math.sin(theta) + (pY-height/2) * Math.cos(theta)) +  height/2),
					 ((int)((x-width/2) * Math.cos(theta) - (y-height/2) * Math.sin(theta)) + width/2),
					 ((int)((x-width/2) * Math.sin(theta) + (y-height/2) * Math.cos(theta)) + height/2));
	   */
=======
	   */
	   
	   graphicsForDrawing.drawLine(pX, pY, x, y);
	   
	   graphicsForDrawing.drawLine(((int)(pX * Math.cos(theta) - pY * Math.sin(theta)) + (width)),
				       ((int)(pX * Math.sin(theta) + pY * Math.cos(theta)) + (height)),
				       ((int)(x * Math.cos(theta) - y * Math.sin(theta)) + (width)),
				       ((int)(x * Math.sin(theta) + y * Math.cos(theta))) + (height));
>>>>>>> racheltriestodostuff
	   pX = x;
	   pY = y;

	}
       
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseMoved(MouseEvent evt) { }
    }
}
