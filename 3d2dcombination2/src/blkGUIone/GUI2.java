package blkGUIone;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.energon2;

public class GUI2 extends JFrame {
	public static JFrame birdyf;
	public JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
	private JTextField showFeild = new JTextField();
	public static BorderLayout comb = new BorderLayout();

	public GUI2() {

		// Jframe
		String a = null;
		
		System.out.println("out");
		setVisible(true);

		// JPanel

		if (mainGUI.getLbcj()==TEMPLE_TYPE.ASIA_MINOR) {
			JPanel panel = new temple1();
			jtp.add("2d", panel);
			a=TEMPLE_TYPE.ASIA_MINOR.name;
		} else if (mainGUI.getLbcj()==TEMPLE_TYPE.HERCULER) {
			JPanel panel = new templeofherculer();
			jtp.add("2d", panel);
			a=TEMPLE_TYPE.HERCULER.name;
		}
		
		birdyf = new JFrame(a);
		// JTabbed Pane
		
		jtp.addTab("3d", new JPanel());
		jtp.addChangeListener(new MyChangeListener());
		jtp.setSize(350, 100);

		birdyf.add(jtp);

		// add
		birdyf.setSize(350, 600);
		birdyf.setVisible(true);
		
		
		birdyf.setLayout(comb);

	}

	class MyChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			String temp = jtp.getTitleAt(jtp.getSelectedIndex());
			if (jtp.getSelectedIndex() == 1) {
				energon2.main(null);
			}
		}
	}

}
