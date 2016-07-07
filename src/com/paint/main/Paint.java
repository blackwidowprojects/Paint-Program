/* Brandon Widow
 * 2016
 * 
 * Simple Paint Program
 * 
 * 
 */

package com.paint.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

public class Paint extends Canvas implements MouseMotionListener, MouseListener
{
	private static final long serialVersionUID = 3383988355157275519L;

	private int lastX, lastY; // Mouse coordinates
	private Color currentColor = Color.black; // Painting with this color
	private Color selectColor = new Color(0, 191, 255); // Indicator color for selected brush size
	private Color backColor = new Color(230,230,230);  // Toolbar Border color

	Shape brushSel = new Ellipse2D.Float(495, 55, 30, 30); // brush indicator 1
	Shape brushSel2 = new Ellipse2D.Float(525, 45, 50, 50); // brush indicator 2
	Shape brushSel3 = new Ellipse2D.Float(575, 35, 70, 70); // brush indicator 3
	
	private int brushX = 20, brushY = 20; // brush size

	public Paint()
	{
		addMouseListener(this); // MousePressed listener
		addMouseMotionListener(this); // MouseDragged listener

	}

	public void paint( Graphics g )
	{
		setBackground(Color.white); 		// Set Background Color to White

		g.setColor(backColor);				// Toolbar Border 
		g.fill3DRect(0,0, getWidth(), (getHeight()/5), true ); // Boundaries

		g.setColor(Color.red); // Pick Red Color
		g.fillOval(10, 5, 40, 40);

		g.setColor(Color.green); // Pick Green Color 
		g.fillOval(10, 50, 40, 40);

		g.setColor(Color.blue); // Pick Blue Color
		g.fillOval(10, 95, 40, 40);

		g.setColor(Color.yellow);  // Pick Yellow Color
		g.fillOval(55, 5, 40, 40);

		g.setColor(Color.orange);  // Pick Orange Color
		g.fillOval(55, 50, 40, 40);

		g.setColor(Color.cyan);  // Pick Cyan Color
		g.fillOval(55, 95, 40, 40);

		g.setColor(Color.pink);  // Pick Pink Color
		g.fillOval(100, 5, 40, 40);

		g.setColor(Color.magenta);  // Pick Magenta Color
		g.fillOval(100, 50, 40, 40);

		g.setColor(Color.lightGray);  // Pick light Gray Color
		g.fillOval(100, 95, 40, 40);

		g.setColor(Color.gray); // Pick Gray Color
		g.fillOval(145, 5, 40, 40);

		g.setColor(Color.darkGray); // Pick dark Gray Color
		g.fillOval(145, 50, 40, 40);

		g.setColor(Color.black);  // Pick Black Color
		g.fillOval(145, 95, 40, 40);

		g.setColor(Color.white);  // Pick White Color
		g.fillOval(190, 5, 40, 40);


		g.setColor(Color.black); 			// Selected color indicator
		g.fillOval(250, 35, 100, 100);

		
		g.fillOval(500, 60, 20, 20); 		// Brush sizes, small

		g.fillOval(530, 50, 40, 40); 		// medium

		g.fillOval(580, 40, 60, 60); 		// large


		g.setColor(selectColor);	
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(brushSel); 					// drawing brush indicator
		

		g.setColor(Color.black);
		g.drawString("Current Color:", 255, 25);  // text....takes a while to load?
		g.drawString("Brush Size:", 530, 25); 
		
	}

	public void update( Graphics g ) // update instead of painting again (keeps old art)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		if (lastY > (getHeight()/5)) { // stay within the white boundaries
			g.setColor(currentColor);
			
			int drawX = brushX / 2; // centers the circle drawn
			int drawY = brushY / 2;

			if ( (lastY - drawY) < (getHeight()/5)) drawY = 0;
			g.fillOval(lastX - drawX, lastY - drawY, brushX, brushY); // draw circle
			
		}

		else {
			g.setColor(currentColor); // change color
			g.fillOval(250, 35, 100, 100); // show new color on indicator
		}

		
		if(mouseOver(lastX, lastY, 500, 60, 20, 20)) { // if mouse is on small brush
			brushX = 20; // change brush size to 20 x 20
			brushY = 20;

			g.setColor(backColor); // hide other brush indicators
			g2.draw(brushSel2);
			g2.draw(brushSel3);

			g.setColor(selectColor); // show selected brush indicator
			g2.draw(brushSel);
		}

		if(mouseOver(lastX, lastY, 530, 50, 40, 40)) { // medium brush select
			brushX = 40; // 40 x 40 brush
			brushY = 40;
			
			g.setColor(backColor);
			g2.draw(brushSel);
			g2.draw(brushSel3);

			g.setColor(selectColor);
			g2.draw(brushSel2);
		}

		if(mouseOver(lastX, lastY, 580, 40, 60, 60)) { // large brush select
			brushX = 60; // 60 x 60 brush
			brushY = 60;

			g.setColor(backColor);
			g2.draw(brushSel);
			g2.draw(brushSel2);

			g.setColor(selectColor);
			g2.draw(brushSel3);
		}
	}
	
	public void mouseClicked( MouseEvent evt )
	{
	}

	public void mouseDragged( MouseEvent evt )
	{
		lastX = evt.getX(); // get coordinates of mouse
		lastY = evt.getY();
		
		repaint(); // draw art
	}

	public void mouseMoved( MouseEvent evt )
	{
		
	}

	public void mousePressed( MouseEvent evt )
	{
		lastX = evt.getX();
		lastY = evt.getY();
		
		// Change-your-color "buttons"
		
		if(mouseOver(lastX, lastY, 10, 5, 40, 40)) currentColor = Color.red;
		if(mouseOver(lastX, lastY, 10, 50, 40, 40)) currentColor = Color.green;
		if(mouseOver(lastX, lastY, 10, 95, 40, 40)) currentColor = Color.blue;
		if(mouseOver(lastX, lastY, 55, 5, 40, 40)) currentColor = Color.yellow;
		if(mouseOver(lastX, lastY, 55, 50, 40, 40)) currentColor = Color.orange;
		if(mouseOver(lastX, lastY, 55, 95, 40, 40)) currentColor = Color.cyan;
		if(mouseOver(lastX, lastY, 100, 5, 40, 40)) currentColor = Color.pink;
		if(mouseOver(lastX, lastY, 100, 50, 40, 40)) currentColor = Color.magenta;
		if(mouseOver(lastX, lastY, 100, 95, 40, 40)) currentColor = Color.lightGray;
		if(mouseOver(lastX, lastY, 145, 5, 40, 40)) currentColor = Color.gray;
		if(mouseOver(lastX, lastY, 145, 50, 40, 40)) currentColor = Color.darkGray;
		if(mouseOver(lastX, lastY, 145, 95, 40, 40)) currentColor = Color.black;
		if(mouseOver(lastX, lastY, 190, 5, 40, 40)) currentColor = Color.white;

		repaint();
	}

	public void mouseReleased( MouseEvent evt )
	{
	}

	public void mouseEntered( MouseEvent evt )
	{
	}

	public void mouseExited( MouseEvent evt )
	{
	}

	public static void main(String[] args)
	{
		// Window stuff...
		JFrame win = new JFrame("Paint");
		win.setSize(1024,768);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setResizable(false);
		win.setLocationRelativeTo(null);
		win.add( new Paint() );
		win.setVisible(true);

	}

	// Mouse over function to use for "buttons"
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) { 
		if(mx > x && mx < (x + width)) {
			if(my > y && my < (y + height)) {
				return true;
			} else return false;
		} else return false;
	}

}
