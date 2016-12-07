package blkGUIone;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import blkGUIone.GUI2.MyChangeListener;

public class mainGUI extends JFrame {

	private JFrame mainframe;
	private JButton bgo, bexit, bcompare,badd;
	
	//ImageIcon icon = new ImageIcon("src/blkGUIone/BLK-Logo-White.jpg");
	public JLabel showInfo = new JLabel();
	//public static String lbcj="none";
	private static TEMPLE_TYPE lbcj=TEMPLE_TYPE.ASIA_MINOR;
	public static JComboBox sizes2 = new JComboBox(lbcj.values());
	public static JComboBox sizes = new JComboBox(lbcj.values());
	
	public JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
	private static Panel Panel;
	
	private static Container a;
	public mainGUI() {
		// create mainframe
		mainframe = new JFrame("BLK project");
		
		//jpanel
		JPanel panel=new JPanel();
		// label hello
		//JLabel label = new JLabel(icon);
		
		//boxlayout
		Box a=Box.createVerticalBox();
		Box b2=Box.createHorizontalBox();
		a.add(Box.createRigidArea(new Dimension(0,5)));		
		Box compare= Box.createVerticalBox();
		Box compareh=Box.createHorizontalBox();
		//tab
		jtp.addTab("Temples", a);
		jtp.addTab("Compare", compare);
		//jtp.addTab("User input", new JPanel());
		jtp.addChangeListener(new MyChangeListener());
		
		
		
		
		// buttons in Panel

		bgo = new JButton("Go");
		bgo.addActionListener(new MyActionListener());

		bexit = new JButton("exit");
		bexit.addActionListener(new MyActionListener());
		
		bcompare = new JButton("compare");
		bcompare.addActionListener(new MyActionListener());
		
		badd = new JButton("add");
		badd.addActionListener(new MyActionListener());
		// connect to listenner
		sizes.addItemListener(new MyItemListener());

		// add button to panel
		a.add(sizes);
		b2.add(bgo);
		b2.add(bexit);
		a.add(b2);
		a.add(showInfo);
		a.add(panel);
	
		//b1.add(Box.createVerticalGlue());

		
		

		//compare pane
		compare.add(sizes2);
		compareh.add(bcompare);
		compareh.add(badd);
		compare.add(compareh);

		//label.setSize(350, 600);
		// add panel to frame
		//mainframe.add(label);

		mainframe.add(jtp);
		//mainframe.add(p2);
		mainframe.setBackground(Color.white);
		mainframe.setBounds(500, 600,800,1000);
		mainframe.setResizable(true);
		mainframe.setLocationRelativeTo(null);
		mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}

	public static void setPanel(Panel panel) {
		mainGUI.Panel = panel;
	}

	


	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new mainGUI();
	}

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String arg = e.getActionCommand();
			if (arg == "exit") {
				System.exit(0);
			}
			else if (arg == "Go") {
				System.out.println(lbcj);
				
					new GUI2();
			}else if(arg=="add"){
				System.out.println("add");
			}else if(arg=="compare"){
				System.out.println("compare");
			}
		}
	}

	class MyItemListener implements ItemListener {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			JComboBox<TEMPLE_TYPE> cb = (JComboBox<TEMPLE_TYPE>) e.getSource();
			TEMPLE_TYPE pe = (TEMPLE_TYPE) cb.getSelectedItem();
			System.out.println(pe);
			if(pe == TEMPLE_TYPE.PLEASE_SELECT_ONE_TEMPLE){
				JOptionPane.showMessageDialog(null,"you have not select any temple" );
			}else if (pe== TEMPLE_TYPE.ASIA_MINOR) {
				showInfo.setText(TEMPLE_TYPE.ASIA_MINOR.getInfo());
				lbcj=TEMPLE_TYPE.ASIA_MINOR;
			} else if (pe == TEMPLE_TYPE.HERCULER) {
				showInfo.setText(TEMPLE_TYPE.HERCULER.getInfo());
				lbcj=TEMPLE_TYPE.HERCULER;
			}
		}
	}
	
	class MyChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			String temp = jtp.getTitleAt(jtp.getSelectedIndex());
		}
	}
	
	public static TEMPLE_TYPE getLbcj() {
		return lbcj;
	}
	
}