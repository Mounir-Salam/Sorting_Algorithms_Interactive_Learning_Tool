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

public class QuickSort extends JPanel implements ActionListener, MouseListener {
	
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

	public QuickSort(int array[]) {
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
		labelAlgo.setFont(new Font("Consolas", Font.PLAIN, 12));
		labelAlgo.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.white));
		labelAlgo.setHorizontalAlignment(SwingConstants.LEFT);
		labelAlgo.setVerticalAlignment(SwingConstants.NORTH);
		labelAlgo.setForeground(Color.white);
		panelInfo.add(labelAlgo, BorderLayout.EAST);
		
		labelName = new JLabel("Quick Sort");
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
		
		labelInfo.setText("<html>Quick Sort: Uses the method of divide and conquer, the algorithm starts at an index"
				+ " specified by the user or programmer called the \"pivot\". The program splits the array at the pivot"
				+ " into left and right halves, then recursively quick sorting both sides with the same technique.<br><br>"
				+ " for the sake of this example, the pivot will be assigned to the last index in the array/subarray.</html>");
		
		labelAlgo.setText("<html>public void quickSort(int first, int last)<br>" + 
				"{<br>" + 
				"    &nbsp if (first &#60 last)<br>" + 
				"    &nbsp {<br>" + 
				"         <br>" + 
				"    &nbsp&nbsp int pivot = arr[last];<br>" + 
				"     <br>" + 
				"    &nbsp&nbsp int i = (first - 1);<br>" + 
				" <br>" + 
				"    &nbsp&nbsp for(int j = first; j &#60= last - 1; j++)<br>" + 
				"    &nbsp&nbsp {<br>" + 
				"         <br>" + 
				"    &nbsp&nbsp&nbsp if (arr[j] &#60 pivot)<br>" + 
				"    &nbsp&nbsp&nbsp {<br>" + 
				"             <br>" + 
				"    &nbsp&nbsp&nbsp&nbsp i++;<br>" + 
				"    &nbsp&nbsp&nbsp&nbsp swap(i, j);<br>" + 
				"    &nbsp&nbsp&nbsp }<br>" + 
				"    &nbsp&nbsp }<br>" + 
				"    &nbsp&nbsp swap(i + 1, last);" + 
				" <br>" + 
				"    &nbsp&nbsp quickSort(first, (i+1) - 1);<br>" + 
				"    &nbsp&nbsp quickSort((i+1) + 1, last);<br>" + 
				"    &nbsp }<br>" + 
				"}</html>");
		
		while(!next && !exit) actionWhenPaused();
		if (exit) return;
		next = false;

		NextButton.setText("Next");
		labelInfo.setForeground(Color.green);
		
		sortUtil(0,array.length-1);
		
		NextButton.setText("Finish");
		labelInfo.setForeground(Color.white);
		labelInfo.setText("<html> Worst Case Time Complexity: O(n^2)"
				+ "<br> Average Case Time Complexity: &Theta(nlog(n))"
				+ "<br> Best Case Time Complexity: &Omega(nlog(n)) <br>"
				+ "<br> Worst case is when the pivot is always the largest or smallest number in the array/sub-array.<br>"
				+ "One of the best developed algorithms. Unlike merge sort, quick sort is an \"in-place\" algorithm which is lower on memory. </html>");
		
		while(!next && !exit) actionWhenPaused();
		if (exit) return;
		
	}
	
	private void sortUtil(int first, int last) {
		if (first < last)
	    {
	         
	        // pi is partitioning index, arr[p]
	        // is now at right place
			
			for (int index = first; index <= last; index++)
				((JLabel)panelArray.getComponent(index)).setForeground(Color.blue);
			
			((JLabel)panelArray.getComponent(last)).setForeground(Color.yellow);
			int pivot = array[last];
			
			int i = (first - 1);
			int j = first;
			 
		    for(j = first; j <= last - 1; j++)
		    {
		        
		        if (array[j] < pivot)
		        {
		             
		            i++;
		            
		            ((JLabel)panelArray.getComponent(i)).setBorder(BorderFactory.createLineBorder(Color.red));
					((JLabel)panelArray.getComponent(j)).setBorder(BorderFactory.createLineBorder(Color.red));
					//((JLabel)panelArray.getComponent(i)).setForeground(Color.red);
					//((JLabel)panelArray.getComponent(j)).setForeground(Color.red);

		            
		            labelInfo.setText("<html> Pivot = " + pivot + "<br>" +
		            "i = " + i + "; j = " + j + "<br>" +
		            "array[j] = " + array[j] + " &#60 " + "pivot = " + pivot + "<br>" +
		            "i becomes " + i + "<br>" + 
		            "array[i] = " + array[i] + " swapped with array[j] = " + array[j] + "</html>");
		            
		            while(!next && !exit) actionWhenPaused();
					if (exit) return;
					next = false;
					
					((JLabel)panelArray.getComponent(i)).setBorder(null);
					((JLabel)panelArray.getComponent(j)).setBorder(null);
					//((JLabel)panelArray.getComponent(i)).setForeground(Color.blue);
					//((JLabel)panelArray.getComponent(j)).setForeground(Color.blue);
		            
		            int temp = array[i];
					
					array[i] = array[j];
					((JLabel)panelArray.getComponent(i)).setText("" + array[j]);
					
					array[j] = temp;
					((JLabel)panelArray.getComponent(j)).setText("" + temp);
					
		        }
		        
		        else {
		        	
					((JLabel)panelArray.getComponent(j)).setBorder(BorderFactory.createLineBorder(Color.green));
		        	
		        	labelInfo.setText("<html> Pivot = " + pivot + "<br>" +
				            "i = " + i + "; j = " + j + "<br>" +
				            "array[j] = " + array[j] + " &#60 " + "pivot = " + pivot + "<br>" + 
				            "No action is made </html>");
		        	
		        	 while(!next && !exit) actionWhenPaused();
		        	 if (exit) return;
		        	 next = false;
		        	 
		        	 ((JLabel)panelArray.getComponent(j)).setBorder(null);
						
		        }
		    }
		    
			((JLabel)panelArray.getComponent(i+1)).setBorder(BorderFactory.createLineBorder(Color.red));
			((JLabel)panelArray.getComponent(last)).setBorder(BorderFactory.createLineBorder(Color.red));
		    //((JLabel)panelArray.getComponent(i+1)).setForeground(Color.red);
			//((JLabel)panelArray.getComponent(last)).setForeground(Color.red);
		    
		    labelInfo.setText("<html> Pivot = " + pivot + "<br>" +
		            "i = " + i + "; j = " + j + "<br>" +
		    		"j reached index " + j + "; iteration stops <br>" +
		            "array[i+1] = " + array[i+1] + " is swapped with the pivot.");
		    
		    while(!next && !exit) actionWhenPaused();
			if (exit) return;
			next = false;
			
			((JLabel)panelArray.getComponent(i+1)).setBorder(null);
			((JLabel)panelArray.getComponent(last)).setBorder(null);
			((JLabel)panelArray.getComponent(last)).setForeground(Color.white);
			((JLabel)panelArray.getComponent(i+1)).setForeground(Color.yellow);
			//((JLabel)panelArray.getComponent(i+1)).setForeground(Color.blue);
			//((JLabel)panelArray.getComponent(last)).setForeground(Color.blue);
		    
		    int temp = array[i+1];
			
			array[i+1] = array[last];
			((JLabel)panelArray.getComponent(i+1)).setText("" + array[last]);
			
			array[last] = temp;
			((JLabel)panelArray.getComponent(last)).setText("" + temp);
			
			
			labelInfo.setText("<html> Since pivot = " + pivot + " is in its correct position, the sub-array to the"
					+ " left of the pivot will recursively be quick sorted the same way.</html>");
			
			while(!next && !exit) actionWhenPaused();
			if (exit) return;
			next = false;
			
			((JLabel)panelArray.getComponent(last)).setBorder(null);
			
			for (int index = first; index <= last; index++)
				((JLabel)panelArray.getComponent(index)).setForeground(Color.white);
			
			if (i >= 0) {
			
				sortUtil(first, (i + 1) - 1);
	        
				labelInfo.setText("<html> Since the left side of pivot = " + pivot + " is sorted, the sub-array to the"
						+ " right of the pivot will recursively be quick sorted the same way, with the new pivot =" + array[last] + "</html>");
			}
			
			else {
				labelInfo.setText("<html> There is no left side, so the sub array to the right of the pivot will recursively be "
						+ "be quick sorted the same way, with the new pivot =" + array[last] + "</html>");
			}
	        
	        while(!next && !exit) actionWhenPaused();
			if (exit) return;
			next = false;
	        
	        sortUtil((i + 1) + 1, last);
	        
	        for (int index = first; index <= last; index++)
				((JLabel)panelArray.getComponent(index)).setForeground(Color.white);
	        
	    }
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
