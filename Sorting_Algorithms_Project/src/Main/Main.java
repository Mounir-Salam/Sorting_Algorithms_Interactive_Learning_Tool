package Main;

import java.awt.*;
import javax.swing.*;

public class Main {
	
	public static final int Width = 1000;
	public static final int Height = 500;
	
	// Main frame shows MainScreen class
	
	public static void main(String args[]) {
		MainScreen screen = new MainScreen(new Dimension(Width, Height));
		
		Image image = Toolkit.getDefaultToolkit().getImage("Array.png");
		JFrame frame = new JFrame("Sorting Algorithms");
		frame.setIconImage(image);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Width, Height);
		frame.setContentPane(screen);
		frame.validate();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
