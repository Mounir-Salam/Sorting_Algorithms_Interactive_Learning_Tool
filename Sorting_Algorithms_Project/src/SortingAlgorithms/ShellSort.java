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

public class ShellSort extends JPanel implements ActionListener, MouseListener  {
	
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


	public ShellSort(int array[]){
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
		labelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
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
		
		labelName = new JLabel("Shell Sort");
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
		
		labelInfo.setText("<html> Shell Sort: is a variation of insertion sort, but while insersion sort compares adjacent"
				+ " indices and moves an element one position ahead or behind, shell sort will start on a specific index"
				+ " called a gap (1st loop), the value at this index is stored in a temporary variable (temp; 2nd loop) and will be compared"
				+ " with values to the left of the gap until a value greater than temp is found (3rd loop) thus swaping them. When the"
				+ " third loop ends or breaks due to the given conditions, temp will be changed to the first value right to the gap and"
				+ " starts another loop. When reaching the end of the array, the 2nd loop ends, and the gap is modified.<br>"
				+ "For the sake of this example gap will be n/2 in the first loop. </html>");
		
		labelAlgo.setText("<html>for (int gap = n/2; gap &#62 0; gap /= 2) <br>" + 
				"        { <br>" +  
				"            &nbsp for (int i = gap; i &#60 n; i += 1) <br>" + 
				"            &nbsp { <br>" + 
				"                &nbsp&nbsp int temp = arr[i]; <br>" + 
				"  <br>" + 
				"                &nbsp&nbsp for (int j = i; j &#62= gap <br>" + 
				"                &nbsp&nbsp&nbsp && arr[j - gap] &#62 temp; j -= gap) <br>" + 
				"                &nbsp&nbsp { <br>" +
				"                &nbsp&nbsp&nbsp&nbsp arr[j] = arr[j - gap]; <br>" + 
				"                &nbsp&nbsp } <br>" +
				"  <br>" + 
				"                &nbsp&nbsp arr[j] = temp; <br>" + 
				"            &nbsp } <br>" + 
				"        } </html>");
		
		while(!next && !exit) actionWhenPaused();
		if (exit) return;
		next = false;
		
		labelInfo.setForeground(Color.green);
		NextButton.setText("Next");
		labelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

		
		 int n = array.length; 
		  
	        for (int gap = n/2; gap > 0; gap /= 2) 
	        { 
	        	
				((JLabel)panelArray.getComponent(gap)).setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.red));
	            for (int i = gap; i < n; i += 1) 
	            { 
	                int temp = array[i]; 
	                int j; 
	                for (j = i;; j -= gap) {
	                	
	                	System.out.println(j);
                		((JLabel)panelArray.getComponent(j)).setText("");
                		
                		
                		if (j < gap) {
	                		labelInfo.setText("<html> Gap: index " + gap + "<br>" + temp + " stored in temporary storage."
	    	                				+ "<br><br> Iteration reached j = " 
	                				+ j + " &#60 gap = " + gap +". Reverse iteration stops and moves to the next index i.<br>" + temp + " is palced back.</html>");
	                		
	                		while(!next && !exit) actionWhenPaused();
	    					if (exit) return;
	    					next = false;
	    					
	                		break;
	                	}
                		
	                	if (array[j-gap] > temp) {
	                		
	                		((JLabel)panelArray.getComponent(j-gap)).setForeground(Color.red);
	                		
	                		labelInfo.setText("<html> Gap: index " + gap + "<br>"
	    	                		+ temp + " stored in temporary storage for comparison with array[j - gap] = " + array[j-gap]
	    	                				+ ".<br><br> Since " + array[j-gap] + " is greater than " + temp +
	    	                				", " + array[j-gap] + " is placed in the current empty slot and comparison continues.</html>");
	                		
	                		while(!next && !exit) actionWhenPaused();
	    					if (exit) return;
	    					next = false;
	                		
	                		array[j] = array[j - gap];
	                		((JLabel)panelArray.getComponent(j)).setText("" + array[j-gap]);
	                		((JLabel)panelArray.getComponent(j-gap)).setForeground(Color.white);

	                	}
	                	else {
	                		
	                		((JLabel)panelArray.getComponent(j-gap)).setForeground(Color.green);

	                		
	                		labelInfo.setText("<html> Gap: index " + gap + "<br>"
	                		+ temp + " stored in temporary storage for comparison with array[j - gap] = " + array[j-gap]
	                				+ "<br><br> Since " + array[j-gap] + " is already smaller than " + temp +
	                				", " + temp + " is placed in the current empty slot.</html>");
	                		
	                		while(!next && !exit) actionWhenPaused();
	    					if (exit) return;
	    					next = false;
	    					
	                		((JLabel)panelArray.getComponent(j-gap)).setForeground(Color.white);
	                		
	                		break;
	                	}
	                }
	                
	                array[j] = temp; 
					((JLabel)panelArray.getComponent(j)).setText("" + temp);
	            } 
	            
				((JLabel)panelArray.getComponent(gap)).setBorder(null);

	        } 
	        
	        NextButton.setText("Finish");
			labelInfo.setForeground(Color.white);
			labelInfo.setText("<html> Worst Case Time Complexity: O(n^2)"
					+ "<br> Average Case Time Complexity: &Theta(nlog^2(n))"
					+ "<br> Best Case Time Complexity: &Omega(nlog(n)) <br>"
					+ "<br> Like insertion sort, shell sort may be time consuming for larger arrays. However, still better than some O(n^2) algorithms like bubble or selection sort.</html>");
	        
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
