import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class mainDraw extends JComponent {

    public int x = 100;
    public int y = 100;
    public newXY xy = new newXY(1,1,180);
    
    public void paint(Graphics g) {
	super.paint(g);
	g.fillRect(x, y, 10, 10);
	g.fillRect(x+20, y+20, 10, 10);
	g.fillRect(x+20, y, 10, 10);
	g.fillRect(x+20, y-20, 10, 10);
	g.fillRect(x-20, y+20, 10, 10);
	g.fillRect(x-20, y, 10, 10);
	g.fillRect(x-20, y-20, 10, 10);
	g.fillRect(x, y+20, 10, 10);
	g.fillRect(x, y-20, 10, 10);
        g.setColor(Color.BLACK);
    }

    /*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(x, y, 10, 10);
	g.fillRect(x+20, y+20, 10, 10);
	g.fillRect(x+20, y, 10, 10);
	g.fillRect(x+20, y-20, 10, 10);
	g.fillRect(x-20, y+20, 10, 10);
	g.fillRect(x-20, y, 10, 10);
	g.fillRect(x-20, y-20, 10, 10);
	g.fillRect(x, y+20, 10, 10);
	g.fillRect(x, y-20, 10, 10);
        g.setColor(Color.BLACK);
    }
    */

    public void moveRight() {
        if (x + 50 < 600) {
	    x = x + 20;
	    repaint();
	}
    }

    public void moveLeft() {
	if (x > 0) {
	    x = x - 20;
	    repaint();
	}
    }

    public void moveDown() {
	if (y + 50 < 600) {
	    y = y + 20;
	    repaint();
	}
    }

    public void moveUp() {
	if (y > 0) {
	    y = y - 20;
	    repaint();
	}
    }

}
