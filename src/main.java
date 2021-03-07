import java.util.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.EventHandler;
import java.io.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import javafx.scene.*;

public class main {
	public static String person = "";
	private static BufferedReader fileIn;
	private static FileWriter out;
	private static GridLayout layout = new GridLayout(0, 3, 20, 20);
	private static ArrayList<item> storage = new ArrayList<item>();
	private static ArrayList<item> sorted = new ArrayList<item>();
	
	private static double amount = 0;
	private static String name = "";
	private static String date = "";
	private static double total = 0;
	private static int pageno = 1;
	private static int totalpage;
	private static String[] columnNames = {"Date","Transaction","Amount"};
	private static String [][] displayData = new String [0][3];
	
	public static void reset () {
		amount = 0;
		name = "";
		date = "";
		total = 0;
		pageno = 1;
		storage.clear();
		sorted.clear();
		displayData = new String [0][3];
	}

	public static void readFile ()  {
		String in = "";
		String [] inArr;
		try {
			while ((in =main.fileIn.readLine())!= null) { 
				inArr = in.split(" ");
				for (int i = 0; i < inArr.length; i++) {
				}
				storage.add(new item (inArr[0], Double.parseDouble(inArr[1]), inArr[2]));
			}
		} catch (IOException e) {
			System.out.println("Error Reading File");
		}
		totalpage = storage.size()/10;
	}
	public static void Deposit () {
		JFrame frame = new JFrame ("Finance Manager");
		frame.setSize(300,220);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.darkGray);	
		frame.setResizable(false);
		frame.repaint();
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public static void Withdraw () {
		JFrame frame = new JFrame ("Finance Manager");
		frame.setSize(300,220);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.darkGray);	
		frame.setResizable(false);
		frame.repaint();
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public static void Overview ()  {
		if (total == 0)
			for (int i = 0; i < storage.size(); i++) {
				double m = storage.get(i).getAmount();
				main.total += m;
			}
		
		JFrame frame = new JFrame ("Finance Manager");
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.darkGray);	
		frame.setResizable(false);
		if (storage.size() != 0) { // remove after
			displayData = new String [storage.size()][3];
			for (int i = 0; i < storage.size(); i++ ) {
				System.out.println(storage.get(i).getName());
				System.out.println(storage.get(i).getAmount());
				System.out.println(storage.get(i).getDate());
				System.out.println(" ");
				displayData[i][0] = storage.get(i).getDate();
				displayData[i][1] = storage.get(i).getName();
				displayData[i][2] = Double.toString(storage.get(i).getAmount());
		}	
		}
		JTable table = new JTable(displayData, columnNames) {
			public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(300);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.setBackground(Color.gray);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setBounds(250, 100, 500, 580);
		for (int i = 0; i < 20; i ++)
			table.setRowHeight(i, 29);
		JScrollPane pane = new JScrollPane (table);
		pane.setBounds(250, 50, 500, 630);
		pane.setBorder(BorderFactory.createEmptyBorder());
		table.getTableHeader().setPreferredSize(new Dimension(pane.getWidth(), 50));
		frame.add(pane); 
		JButton sortByAmount = new JButton("Search");
		sortByAmount.setBounds(85, 500, 70, 20);
		frame.add(sortByAmount);
		JButton sortByDate = new JButton("Search");
		sortByDate.setBounds(85, 600, 70, 20);
		frame.add(sortByDate);
		JPanel filter = new JPanel();
		filter.setBounds(30, 365, 185, 315);
		filter.setBackground(Color.gray);
		frame.add(filter);
		JButton back = new JButton("Back");
		back.setBounds(85, 25, 70, 20);
		frame.add(back);
		JLabel text = new JLabel("Banking for: "+ person, SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setBounds(30, 55, 185, 20);
		frame.add(text);
		JLabel totalCAD = new JLabel ("Total: CAD$" + new DecimalFormat("#.##").format(main.total), SwingConstants.CENTER);
		totalCAD.setForeground(Color.white);
		totalCAD.setBounds(30, 95, 185, 20);
		frame.add(totalCAD);
		JLabel totalUSD = new JLabel ("Total: USD$" + new DecimalFormat("#.##").format(main.total/1.3), SwingConstants.CENTER);
		totalUSD.setForeground(Color.white);
		totalUSD.setBounds(30, 135, 185, 20);
		frame.add(totalUSD);
		frame.repaint();
		frame.setLayout(null);
		frame.setVisible(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (back.isEnabled()) {
		        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	selectWindow();
		        }
			}	
		});
		sortByAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (sortByAmount.isEnabled()) {
		        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	Collections.sort(storage, new SortByAmount());
		        	Overview();
		        }
			}	
		});
		sortByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (sortByDate.isEnabled()) {
		        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	Collections.sort(storage, new SortByAmount());
		        	Collections.sort(storage, new SortByDate());
		        	Overview();
		        }
			}	
		});
	}
	
	public static void selectWindow() {
		JFrame frame = new JFrame ("Finance Manager");
		frame.setSize(300,220);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.darkGray);	
		frame.setResizable(false);
		JButton deposit = new JButton("Deposit");
		deposit.setBounds(160, 40, 90, 20);
		JButton withdraw = new JButton("Withdraw");
		withdraw.setBounds(160, 80, 90, 20);
		JButton overview = new JButton("Overview");
		overview.setBounds(160, 120, 90, 20);
		JLabel text = new JLabel("Banking for: ", SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setBounds(35, 50, 80, 20);
		JLabel who = new JLabel(person, SwingConstants.CENTER);
		who.setForeground(Color.white);
		who.setBounds(35, 70, 80, 20);
		JButton back = new JButton("Change");
		back.setBounds(30, 100, 90, 20);
		frame.add(deposit);
		frame.add(withdraw);
		frame.add(overview);
		frame.add(back);
		frame.add(text);
		frame.add(who);
		frame.repaint();
		frame.setLayout(null);
		frame.setVisible(true);
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (deposit.isEnabled()) {
		        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	Deposit();
		        }
			}	
		});
		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (withdraw.isEnabled()) {
		        	
		        }
			}
		});
		overview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (overview.isEnabled()) {
		        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	readFile();
		        	Overview();
		        }
			}	
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (back.isEnabled()) {
		        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        	windowStart();
		    		try {
		    			fileIn.close();
		    		} catch (IOException e3) {
		    		}
		        }
			}	
		});
	}
	
	public static void windowStart () {
		reset();
		JFrame frame = new JFrame("Finance Manager");
		JTextField text = new JTextField();
		JButton submit = new JButton("Log In");
		JButton new1 = new JButton("Create New");
		JLabel q = new JLabel(); 
		frame.setSize(300,200);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.darkGray);	
		frame.setResizable(false);
		q.setText("Who's banking today?");
		q.setForeground(Color.white);
		q.setBounds(80, 40, 150, 20);
		text.setBounds(115, 70, 50, 20);
		new1.setBounds(145, 100, 100, 20);
		submit.setBounds(35, 100, 100, 20);
		frame.add(text);
		frame.add(q);
		frame.add(submit);
		frame.add(new1);
		frame.repaint();
		frame.setLayout(null);
		frame.setVisible(true);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (submit.isEnabled()) {
					person = text.getText();
					try {
						fileIn = new BufferedReader (new FileReader("C:\\Users\\Battl\\eclipse-workspace\\Company Transaction Manager\\" + person +".txt"));
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						selectWindow();
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(frame, "This person does not exist. Please enter a valid person.");
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			        	windowStart();
					}
				}
			}	
		});
		new1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new1.isEnabled()) {
					person = text.getText();
					String[] options = {"yes", "no"};
					int option;
					try {
						fileIn = new BufferedReader (new FileReader("C:\\Users\\Battl\\eclipse-workspace\\Company Transaction Manager\\" + person +".txt"));
						option = JOptionPane.showOptionDialog(null, "The name already exists. Do you want to log into " + person + "?" , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						if (option == 1) {
							frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
							windowStart();
						}
						else {
							frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
							selectWindow();
						}
					} catch (FileNotFoundException e1) {
					File newFile = new File(person + ".txt");
					try {
						newFile.createNewFile();
						fileIn = new BufferedReader (new FileReader("C:\\Users\\Battl\\eclipse-workspace\\Company Transaction Manager\\" + person +".txt"));
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						selectWindow();
					} catch (IOException e2) {
						System.out.println("Errorororororororororor");
					}
					}
				}
			}	
		});
	}
	
	public static void main(String[] args) throws IOException {
		windowStart();
	}
}
