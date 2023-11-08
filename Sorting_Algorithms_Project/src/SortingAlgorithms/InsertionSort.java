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

public class InsertionSort extends JPanel implements ActionListener, MouseListener{

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

	public InsertionSort(int array[]) {
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
		
		labelName = new JLabel("Insertion Sort");
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
		
		labelInfo.setText("<html> Insertion Sort <br> Sorts an array[N] by taking a number in a specific index "
				+ "and inserts it in its correct position in the array.<br>"
				+ "The algorithm iterates through the array by a loop, each time it compares the current index "
				+ "value with the one before it until it finds two values in an unordered position, when this happens"
				+ " it enters a reverse loop that will keep swapping the unordered index value until it reaches its correct position.");
		
		labelAlgo.setText("<html> int N = arr.length;<br>" + 
				"		for (int i = 0; i &#60 N; i++) <br>" + 
				"		{<br>" + 
				"			&nbsp for (int j = i; j &#62 0 && arr[j] &#60 arr[j-1]; j--)<br>" + 
				"			&nbsp {<br>" + 
				"				&nbsp &nbsp int temp = arr[j]; <br>" + 
				"	            &nbsp &nbsp arr[j] = arr[j-1]; <br>" + 
				"	            &nbsp &nbsp arr[j-1] = temp;<br>" + 
				"			&nbsp }<br>" + 
				"		} </html>");
		
		while(!next && !exit) actionWhenPaused();
		if (exit) return;
		next = false;
		
		NextButton.setText("Next");
		labelInfo.setForeground(Color.green);
		
		for(CurrentIndex = 1; CurrentIndex < array.length; CurrentIndex++) { 
			
			int value = array[CurrentIndex];
			((JLabel)panelArray.getComponent(CurrentIndex)).setBorder(BorderFactory.createLineBorder(Color.white));
			
			if (array[CurrentIndex-1] > array[CurrentIndex]) {
				
				for(TraversingIndex = CurrentIndex, SelectedIndex = TraversingIndex - 1; 
							SelectedIndex >= 0 && (array[SelectedIndex] > array[TraversingIndex]);
								TraversingIndex--, SelectedIndex--) {
					
					((JLabel)panelArray.getComponent(SelectedIndex)).setForeground(Color.red);
					((JLabel)panelArray.getComponent(TraversingIndex)).setForeground(Color.green);
					
					labelInfo.setText("<html> Current Index: " + CurrentIndex
						+ "<br> Value Being Examined: " + array[TraversingIndex]
						+ "<br> Status: Since " + array[TraversingIndex] + " is smaller than "
						+ array[SelectedIndex] + ", values are swapped.");
					
					while(!next && !exit) actionWhenPaused();
					if (exit) return;
					next = false;
					
					int temp = array[TraversingIndex];
					
					((JLabel)panelArray.getComponent(TraversingIndex)).setForeground(Color.white);
					
					array[TraversingIndex] = array[SelectedIndex];
					((JLabel)panelArray.getComponent(TraversingIndex)).setText("" + array[SelectedIndex]);
					
					array[SelectedIndex] = temp;
					((JLabel)panelArray.getComponent(SelectedIndex)).setText("" + temp);
					
					
				}
				
				((JLabel)panelArray.getComponent(TraversingIndex)).setForeground(Color.white);
				((JLabel)panelArray.getComponent(CurrentIndex)).setBorder(null);
			}
			else {
				
				labelInfo.setText("<html> Current Index: " + CurrentIndex
						+ "<br> Value Being Examined: " + value
						+ "<br>Status: Since " + value + " is already greater than " + array[CurrentIndex-1]
						+ ", no swap is needed. <br> Iteration Continues. </html>");
				
				while(!next && !exit) actionWhenPaused();
				if (exit) return;
				next = false;
				
				((JLabel)panelArray.getComponent(CurrentIndex)).setBorder(null);
				
			}
		}
		
		labelInfo.setForeground(Color.white);
		labelInfo.setText("<html> Worst Case Time Complexity: O(n^2)"
				+ "<br> Average Case Time Complexity: &Theta(n^2)"
				+ "<br> Best Case Time Complexity: &Omega(n^2)<br>"
				+ "<br> Overall, having a polynomial time complexity makes it inefficient for large arrays</html>");
		
		NextButton.setText("Finish");
		while(!next && !exit) actionWhenPaused();
		
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
	
	// Unimportant animations for the Next and Exit Buttons

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
