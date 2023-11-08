package SortingAlgorithms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BubbleSort extends JPanel implements ActionListener, MouseListener {

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

	public BubbleSort(int array[]) {
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
		labelAlgo.setFont(new Font("Consolas", Font.PLAIN, 16));
		labelAlgo.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.white));
		labelAlgo.setHorizontalAlignment(SwingConstants.LEFT);
		labelAlgo.setVerticalAlignment(SwingConstants.NORTH);
		labelAlgo.setForeground(Color.white);
		panelInfo.add(labelAlgo, BorderLayout.EAST);
		
		labelName = new JLabel("Bubble Sort");
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
		
		labelInfo.setText("<html> Bubble Sort <br> Sorts and array[N] by inerating through it N times"
				+ " comparing two adjacent indices, each time it comes across an unsorted pair, it swaps them.<br>"
				+ "You can notice that this algorithm sorts the array in reverse (i.e each iteration takes the "
				+ "largest value and places it in the end of the array).<br> which is why this modified bubble sort will iterate through "
				+ "the array one less index every cycle, and will break the iteration if no swaps have been made in one cycle.");
		
		labelAlgo.setText("<html> for (int i = N-1; i &#62= 0; i--)  <br>" + 
				"{ <br>" + 
				"            &nbsp boolean swapped = false; <br>" + 
				"            &nbsp for (int j = 0; j &#60 N-i-1; j++)  <br>" + 
				"            &nbsp { <br>" + 
				"                &nbsp&nbsp if (arr[j] &#62 arr[j+1])  <br>" + 
				"                &nbsp&nbsp { <br>" + 
				"                    &nbsp&nbsp&nbsp int temp = arr[j]; <br>" + 
				"                    &nbsp&nbsp&nbsp arr[j] = arr[j+1]; <br>" + 
				"                    &nbsp&nbsp&nbsp arr[j+1] = temp; <br>" + 
				"                    &nbsp&nbsp&nbsp swapped = true; <br>" + 
				"                &nbsp&nbsp } <br>" + 
				"            &nbsp } <br>" + 
				"  <br>" + 
				"            &nbsp if (swapped == false) <br>" + 
				"                &nbsp break; <br>" + 
				"} </html>");
		
		while(!next && !exit) actionWhenPaused();
		if (exit) return;
		next = false;
		
		labelInfo.setForeground(Color.green);
		NextButton.setText("Next");

		boolean swapped;
		for(CurrentIndex = array.length - 1; CurrentIndex >= 0; CurrentIndex--) {
			swapped = false;
			for(TraversingIndex = 0, SelectedIndex = TraversingIndex + 1;
					SelectedIndex <= CurrentIndex;
						TraversingIndex++, SelectedIndex++) {
				
				((JLabel)panelArray.getComponent(TraversingIndex)).setBorder(BorderFactory.createLineBorder(Color.white));
				((JLabel)panelArray.getComponent(SelectedIndex)).setBorder(BorderFactory.createLineBorder(Color.white));

				if(array[TraversingIndex] > array[SelectedIndex]) {
					swapped = true;
					
					labelInfo.setText("<html>" + array[SelectedIndex] + " is less than " + array[TraversingIndex]
							+ "<br> Swap is needed. </html>");
					
					((JLabel)panelArray.getComponent(TraversingIndex)).setForeground(Color.red);
					((JLabel)panelArray.getComponent(SelectedIndex)).setForeground(Color.red);
					
					while(!next && !exit) actionWhenPaused();
					if (exit) return;
					next = false;
					
					((JLabel)panelArray.getComponent(TraversingIndex)).setBorder(null);
					((JLabel)panelArray.getComponent(SelectedIndex)).setBorder(null);
					
					int temp = array[TraversingIndex];
										
					array[TraversingIndex] = array[SelectedIndex];
					((JLabel)panelArray.getComponent(TraversingIndex)).setText("" + array[SelectedIndex]);
					
					array[SelectedIndex] = temp;
					((JLabel)panelArray.getComponent(SelectedIndex)).setText("" + temp);
				}
				
				else {
					labelInfo.setText("<html>" + array[SelectedIndex] + " is already greater than " + array[TraversingIndex]
							+ "<br> Swap is not needed. </html>");
					
					while(!next && !exit) actionWhenPaused();
					if (exit) return;
					next = false;
					
					((JLabel)panelArray.getComponent(TraversingIndex)).setBorder(null);
					((JLabel)panelArray.getComponent(SelectedIndex)).setBorder(null);
				}
				
				((JLabel)panelArray.getComponent(TraversingIndex)).setForeground(Color.white);
				((JLabel)panelArray.getComponent(SelectedIndex)).setForeground(Color.white);

			}
			
			((JLabel)panelArray.getComponent(CurrentIndex)).setForeground(Color.green);

			if(swapped == false) {
				
				for (int i = CurrentIndex; i >= 0; i--) {
					((JLabel)panelArray.getComponent(i)).setForeground(Color.green);
				}
				
				labelInfo.setText("<html>The modified bubble sort algorithm will stop the iteration if no"
						+ " swapping occured, thus the array is sorted");
				
				while(!next && !exit) actionWhenPaused();
				if (exit) return;
				next = false;
				
				break;
				
				
			}
		}
		
		//((JLabel)panelArray.getComponent(0)).setForeground(Color.green);
		NextButton.setText("Finish");
		labelInfo.setForeground(Color.white);
		labelInfo.setText("<html> Worst Case Time Complexity: O(n^2)"
				+ "<br> Average Case Time Complexity: &Theta(n^2)"
				+ "<br> Best Case Time Complexity: &Omega(n) <br>"
				+ "<br> Although the best case time complexity decreased from n^2 to n, bubble sort stays in efficient in case of large arrays.</html>");
		
		while(!next && !exit) actionWhenPaused();
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
