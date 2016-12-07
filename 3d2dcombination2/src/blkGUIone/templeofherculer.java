package blkGUIone;


import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class templeofherculer extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		// JPanel

		// temple of hercules at cori

		// outline around
		//left side line
		g.drawLine(15, 50, 15, 255);
		//right side line
		g.drawLine(115,50,115,255);
		//top side line
		g.drawLine(15,50,115,50 );
		g.drawLine(15, 255, 115, 255);
		// bottom four
		g.drawArc(25, 230, 10, 10, 0, 360);
		g.drawArc(50, 230, 10, 10, 0, 360);
		g.drawArc(75, 230, 10, 10, 0, 360);
		g.drawArc(100, 230, 10, 10, 0, 360);
		g.fillArc(25, 230, 10, 10, 0, 360);
		// filling
		g.fillArc(50, 230, 10, 10, 0, 360);
		g.fillArc(75, 230, 10, 10, 0, 360);
		g.fillArc(100, 230, 10, 10, 0, 360);
		// side two right
		g.drawArc(100, 180, 10, 10, 0, 360);
		g.fillArc(100, 180, 10, 10, 0, 360);
		g.fillArc(100, 205, 10, 10, 0, 360);
		g.drawArc(100, 205, 10, 10, 0, 360);
		// side two left
		g.drawArc(25, 180, 10, 10, 0, 360);
		g.fillArc(25, 180, 10, 10, 0, 360); 
		// filling
		g.fillArc(25, 205, 10, 10, 0, 360);
		g.drawArc(25, 205, 10, 10, 0, 360);
		// across bar
		g.fillRect(20, 60, 90, 8);
		// left side bar
		g.fillRect(20, 60, 10, 100);
		// right side bar
		g.fillRect(100, 60, 10, 100);
		// across bar bottom right
		g.fillRect(80, 155, 30, 8);
		// across bar bottom left
		g.fillRect(20, 155, 30, 8);
			}
}
