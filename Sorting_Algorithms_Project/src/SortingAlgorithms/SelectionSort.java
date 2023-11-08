package SortingAlgorithms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;

public class SelectionSort extends JPanel implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	JPanel panelArray;
	JPanel panelNext;
	JPanel panelInfo;
	JLabel labelAlgo;
	JLabel labelInfo;
	JLabel labelName;
	JButton NextButton;
	JButton ExitButton;
	Random random = new Random();
	int array[];
	boolean next = false;
	boolean exit = false;
	
	// Consturctor creates all the components of this panel
	// panelArray: contains a Jlabel version  of the shuffled array of numbers and will be updated to visualize how sorting is made
	// panelInfo contains:
	//	labelInfo: includes the explanation
	//	labelAlgo: Contains the code of the algorithm being explained
	// NextButton: used for animation when the actionWhenPaused is interupted
	// ExitButton: returns the sort method, thus disposing the panel and returning to the main screen.

	public SelectionSort(int array[]) {
		this.array = array;
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		
		panelArray = new JPanel();
		panelArray.setBackground(Color.black);
		panelArray.setPreferredSize(new Dimension(100,50));
		panelArray.setLayout(new GridLayout());
		this.add(panelArray, BorderLayout.NORTH);
		
		panelInfo = new JPanel();
		panelInfo.setBackground(Color.black);
		panelInfo.setPreferredSize(new Dimension(100,50));
		panelInfo.setLayout(new GridLayout());
		this.add(panelInfo, BorderLayout.CENTER);
		
		panelNext = new JPanel();
		panelNext.setBackground(Color.black);
		panelNext.setPreferredSize(new Dimension(100, 50));
		panelNext.setLayout(new BorderLayout());
		this.add(panelNext, BorderLayout.SOUTH);
		
		labelInfo = new JLabel();
		labelInfo.setBackground(Color.black);
		labelInfo.setPreferredSize(new Dimension(400, 200));
		labelInfo.setLayout(new GridLayout());
		labelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		labelInfo.setBorder(BorderFactory.createEmptyBorder(1,5,1,1));
		labelInfo.setHorizontalAlignment(SwingConstants.LEFT);
		labelInfo.setVerticalAlignment(SwingConstants.NORTH);
		labelInfo.setForeground(Color.white);
		panelInfo.add(labelInfo, BorderLayout.WEST);
		
		labelAlgo = new JLabel();
		labelAlgo.setBackground(Color.black);
		labelAlgo.setPreferredSize(new Dimension(400, 200));
		labelAlgo.setLayout(new GridLayout());
		labelAlgo.setFont(new Font("Consolas", Font.PLAIN, 18));
		labelAlgo.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.white));
		labelAlgo.setHorizontalAlignment(SwingConstants.LEFT);
		labelAlgo.setVerticalAlignment(SwingConstants.NORTH);
		labelAlgo.setForeground(Color.white);
		panelInfo.add(labelAlgo, BorderLayout.EAST);
		
		labelName = new JLabel("Selection Sort");
		labelName.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		labelName.setForeground(Color.red);
		labelName.setHorizontalAlignment(SwingConstants.CENTER);
		labelName.setVerticalAlignment(SwingConstants.CENTER);
		panelNext.add(labelName, BorderLayout.CENTER);
		
		NextButton = new JButton("Start");
		NextButton.setBounds(new Rectangle(100, 50));
		NextButton.setFocusable(false);
		NextButton.setForeground(Color.white);
		NextButton.setBackground(Color.black);
		NextButton.addActionListener(this);
		NextButton.addMouseListener(this);
		panelNext.add(NextButton, BorderLayout.EAST);
		
		ExitButton = new JButton("Exit");
		ExitButton.setBounds(new Rectangle(100, 50));
		ExitButton.setFocusable(false);
		ExitButton.setForeground(Color.white);
		ExitButton.setBackground(Color.black);
		ExitButton.addActionListener(this);
		ExitButton.addMouseListener(this);
		panelNext.add(ExitButton, BorderLayout.WEST);
		
		this.validate();
		
		shuffle(array);
		for (int number : array) {
			JLabel label = new JLabel("" + number);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			label.setForeground(Color.white);
			label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			panelArray.add(label);
			this.revalidate();
		}
	}

	// panelArray, labelInfo, and labelAlgo are set and updated while the array is being sorted according to the algorithm being used.

	public void sort() {
		
		int SelectedIndex = -1;
		int TraversingIndex = -1;
		int CurrentIndex = -1;
		
		labelInfo.setText("<html> Selection sort <br> Sorts an array[N] by iterating through it N times, each cycles finds the smallest value "
				+ "and swaps it with the first element, but the starting index of every cycle is incremented by 1 to prevent tampering with the sorted side of the array.</html>");
		
		labelAlgo.setText("<html>int N = arr.length; <br>" + 
				"  <br>" + 
				"        for (int i = 0; i &#60 N-1; i++) <br>" + 
				"        { <br>" + 
				"            &nbsp int min_idx = i; <br>" + 
				"            &nbsp for (int j = i+1; j &#60 N; j++) <br>" + 
				"                &nbsp &nbsp if (arr[j] < arr[min_idx]) <br>" + 
				"                    &nbsp &nbsp &nbsp min_idx = j; <br>" + 
				"  <br>" + 
				"            &nbsp int temp = arr[min_idx]; <br>" + 
				"            &nbsp arr[min_idx] = arr[i]; <br>" + 
				"            &nbsp arr[i] = temp; <br>" + 
				"       }</html>");
		
		while (!next && !exit) actionWhenPaused();
		if (exit) return;
		next = false;

		labelInfo.setForeground(Color.green);
		NextButton.setText("Next");
		
		for (CurrentIndex = 0; CurrentIndex < array.length-1; CurrentIndex++) {
			
			JLabel label = (JLabel)panelArray.getComponent(CurrentIndex);
			SelectedIndex = CurrentIndex;
			((JLabel)panelArray.getComponent(SelectedIndex)).setForeground(Color.red);
			
			for (TraversingIndex = CurrentIndex; TraversingIndex < array.length; TraversingIndex++) {
				
				((JLabel)panelArray.getComponent(TraversingIndex)).setBorder(BorderFactory.createLineBorder(Color.white));
				
				if(array[TraversingIndex] < array[SelectedIndex]) {
					((JLabel)panelArray.getComponent(TraversingIndex)).setForeground(Color.red);
					((JLabel)panelArray.getComponent(SelectedIndex)).setForeground(Color.white);
					SelectedIndex = TraversingIndex;
					
				}
				labelInfo.setText("<html> Minimum Value : " + array[SelectedIndex] + "<br> Index to be Swaped with min: [" + CurrentIndex + "]</html>");
				
				while (!next && !exit) actionWhenPaused();
				if (exit) return;
				
				labelInfo.setText("<html> Minimum Value : " + array[SelectedIndex] + "<br> Index to be Swaped with min: [" + CurrentIndex + "]</html>");
				panelArray.repaint();
				next = false;
				
				((JLabel)panelArray.getComponent(TraversingIndex)).setBorder(null);
				
				
			}
			
			int temp = array[CurrentIndex];
			
			((JLabel)panelArray.getComponent(SelectedIndex)).setForeground(Color.white);
			
			array[CurrentIndex] = array[SelectedIndex];
			((JLabel)panelArray.getComponent(CurrentIndex)).setText("" + array[SelectedIndex]);
			
			array[SelectedIndex] = temp;
			((JLabel)panelArray.getComponent(SelectedIndex)).setText("" + temp);
			
			((JLabel)panelArray.getComponent(CurrentIndex)).setForeground(Color.green);
			
			panelArray.repaint();
		}
		
		((JLabel)panelArray.getComponent(CurrentIndex)).setForeground(Color.green);
		labelInfo.setForeground(Color.white);
		labelInfo.setText("<html> Worst Case Time Complexity: O(n^2)"
				+ "<br> Average Case Time Complexity: &Theta(n^2)"
				+ "<br> Best Case Time Complexity: &Omega(n^2)<br>"
				+ "<br> Overall, having a polynomial time complexity makes it inefficient for large arrays</html>");
		NextButton.setText("Finish");
		while (!next && !exit) actionWhenPaused();
		if (exit) return;

	}
	
	// shuffles the array in the constructor
	
	private void shuffle(int array[]) {
		int index;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        if (index != i)
	        {
	            array[index] ^= array[i];
	            array[i] ^= array[index];
	            array[index] ^= array[i];
	        }
	    }
	}
	
	// ActionWhenPaused and ActionPerformed are used to pause and continue within the sort method respectively for easier navigation
	
	protected final synchronized void actionWhenPaused() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == NextButton) {
			next = true;
		}
		
		else if (e.getSource() == ExitButton) exit = true;

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == NextButton) NextButton.setForeground(Color.green);
		else if (e.getSource() == ExitButton) ExitButton.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == NextButton) NextButton.setForeground(Color.white);
		else if (e.getSource() == ExitButton) ExitButton.setForeground(Color.white);
	}
}
