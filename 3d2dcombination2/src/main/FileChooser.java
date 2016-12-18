package main;
 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
 
public class FileChooser extends JPanel { 
    public static Float getZ() {
    	Component frame = null;
		Object x = ( JOptionPane.showInputDialog(frame,
                "Please input a Height: ",
                "Iteration 3 Prototype", JOptionPane.INFORMATION_MESSAGE,
                null,
                null,""
                ));
    	String k = (String) x;
    	Float y = Float.parseFloat(k);
    	System.out.println(y);
    	return y;
    }
}

