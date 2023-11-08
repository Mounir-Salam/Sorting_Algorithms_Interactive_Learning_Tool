package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import SortingAlgorithms.*;

// Includes the main panel which navigates to any selected sorting algorithm panel

public class MainScreen extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	JButton Selection;
	JButton Insertion;
	JButton Bubble;
	JButton Shell;
	JButton Quick;
	JButton Useless;
	
	JPanel panel;
	
	// Constructor creates buttons of each algorithm

	public MainScreen(Dimension d) {
		this.setBounds(0, 0, d.width, d.height);
		this.setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setBounds(0, 0, d.width, d.height);
		panel.setLayout(new GridLayout(3,2));
		
		Selection = new JButton("Selection Sort");
		Insertion = new JButton("Insertion Sort");
		Bubble = new JButton("Bubble Sort");
		Shell = new JButton("Shell Sort");
		Quick = new JButton("Quick Sort");
		Useless = new JButton("Useless Button");
		
		
		Selection.addActionListener(this);
		Insertion.addActionListener(this);
		Bubble.addActionListener(this);
		Shell.addActionListener(this);
		Quick.addActionListener(this);
		Useless.addActionListener(this);
		
		Selection.addMouseListener(this);
		Insertion.addMouseListener(this);
		Bubble.addMouseListener(this);
		Shell.addMouseListener(this);
		Quick.addMouseListener(this);
		Useless.addMouseListener(this);
		
		Selection.setBackground(Color.black);
		Selection.setForeground(Color.white);
		Selection.setBorder(BorderFactory.createLineBorder(Color.white));
		Selection.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		Insertion.setBackground(Color.black);
		Insertion.setForeground(Color.white);
		Insertion.setBorder(BorderFactory.createLineBorder(Color.white));
		Insertion.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		Bubble.setBackground(Color.black);
		Bubble.setForeground(Color.white);
		Bubble.setBorder(BorderFactory.createLineBorder(Color.white));
		Bubble.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		Shell.setBackground(Color.black);
		Shell.setForeground(Color.white);
		Shell.setBorder(BorderFactory.createLineBorder(Color.white));
		Shell.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		Quick.setBackground(Color.black);
		Quick.setForeground(Color.white);
		Quick.setBorder(BorderFactory.createLineBorder(Color.white));
		Quick.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		Useless.setBackground(Color.black);
		Useless.setForeground(Color.white);
		Useless.setBorder(BorderFactory.createLineBorder(Color.white));
		Useless.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		Selection.setFocusable(false);
		Insertion.setFocusable(false);
		Bubble.setFocusable(false);
		Shell.setFocusable(false);
		Quick.setFocusable(false);
		Useless.setFocusable(false);
		
		panel.add(Selection);
		panel.add(Insertion);
		panel.add(Bubble);
		panel.add(Shell);
		panel.add(Quick);
		panel.add(Useless);
		
		this.add(panel);
		this.validate();
		
	}
	
	// For each button pressed will repaint the panel and start any algorithm class in a new thread to prevent
	// overlapping processes

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == Selection) {
			
			SelectionSort sort = new SelectionSort(new int[]{1,2,3,4,5,6});
			
			remove(panel);
			add(sort);
			
			sort.setVisible(true);

			repaint();
			revalidate();
			
			SwingWorker<Boolean,Void> worker = new SwingWorker<>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					sort.sort();
					return true;
				}

				@Override
				protected void done() {
					add(panel);
					remove(sort);
					Selection.setForeground(Color.white);
					
					repaint();
					revalidate();
				}
				
			};
			worker.execute();
			
		}
		
		else if (e.getSource() == Insertion) {
			
			InsertionSort sort = new InsertionSort(new int[]{1,2,3,4,5,6});

			remove(panel);
			add(sort);
			
			sort.setVisible(true);

			repaint();
			revalidate();
			
			SwingWorker<Boolean,Void> worker = new SwingWorker<>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					sort.sort();
					return true;
				}

				@Override
				protected void done() {
					add(panel);
					remove(sort);
					Insertion.setForeground(Color.white);
					
					repaint();
					revalidate();
				}
				
			};
			worker.execute();
		}
		
		else if (e.getSource() == Bubble) {
			
			BubbleSort sort = new BubbleSort(new int[]{1,2,3,4,5,6});
			
			remove(panel);
			add(sort);
			
			sort.setVisible(true);

			repaint();
			revalidate();
			
			SwingWorker<Boolean,Void> worker = new SwingWorker<>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					sort.sort();
					return true;
				}

				@Override
				protected void done() {
					add(panel);
					remove(sort);
					Bubble.setForeground(Color.white);
					
					repaint();
					revalidate();
				}
				
			};
			worker.execute();
			
		}
		
		else if (e.getSource() == Shell) {
			
			ShellSort sort = new ShellSort(new int[]{1,2,3,4,5,6});
			
			remove(panel);
			add(sort);
			
			sort.setVisible(true);

			repaint();
			revalidate();
			
			SwingWorker<Boolean,Void> worker = new SwingWorker<>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					sort.sort();
					return true;
				}

				@Override
				protected void done() {
					add(panel);
					remove(sort);
					Shell.setForeground(Color.white);
					
					repaint();
					revalidate();
				}
				
			};
			worker.execute();
			
		}
		
		else if (e.getSource() == Quick) {
			
			QuickSort sort = new QuickSort(new int[]{1,2,3,4,5,6,7,8,9});
			
			remove(panel);
			add(sort);
			
			sort.setVisible(true);

			repaint();
			revalidate();
			
			SwingWorker<Boolean,Void> worker = new SwingWorker<>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					sort.sort();
					return true;
				}

				@Override
				protected void done() {
					add(panel);
					remove(sort);
					Quick.setForeground(Color.white);
					
					repaint();
					revalidate();
				}
				
			};
			worker.execute();
			
		}
		
		else if (e.getSource() == Useless) {
			Useless.setText("Still Useless");
		}

	}
	
	// this actionWhenPaused is not used, since each sorting algorithm class contains its own actionWhenPaused
	
	protected final synchronized void actionWhenPaused() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// interrupts the sleep without crashing the program
			System.out.println("Thread Closed");
		}
	}
	
	// Unimportant animations when cursor hovers over a button

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
		
		if (e.getSource() == Selection) Selection.setForeground(Color.red);
		
		else if (e.getSource() == Insertion) Insertion.setForeground(Color.red);
		
		else if (e.getSource() == Bubble) Bubble.setForeground(Color.red);
		
		else if (e.getSource() == Shell) Shell.setForeground(Color.red);
		
		else if (e.getSource() == Quick) Quick.setForeground(Color.red);
		
		else if (e.getSource() == Useless) Useless.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == Selection) Selection.setForeground(Color.white);
		
		else if (e.getSource() == Insertion) Insertion.setForeground(Color.white);
		
		else if (e.getSource() == Bubble) Bubble.setForeground(Color.white);
		
		else if (e.getSource() == Shell) Shell.setForeground(Color.white);
		
		else if (e.getSource() == Quick) Quick.setForeground(Color.white);
		
		else if (e.getSource() == Useless) Useless.setForeground(Color.white);
	}
	
}
