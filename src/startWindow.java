import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class startWindow {
	public static String person = "";
	public static String option = "";
	public static ArrayList people;
	public static void windowStart () {
	JFrame frame = new JFrame();
	JTextField text = new JTextField();
	JButton submit = new JButton();
	JLabel q = new JLabel(); 
	frame.setSize(300,200);
	frame.setLocationRelativeTo(null);
	frame.getContentPane().setBackground(Color.darkGray);	
	frame.setResizable(false);
	q.setText("Who's banking today?");
	q.setForeground(Color.white);
	q.setBounds(80, 40, 150, 20);
	text.setBounds(115, 70, 50, 20);
	submit.setBounds(90, 100, 100, 20);
	submit.setText("submit");
	frame.add(text);
	frame.add(q);
	frame.add(submit);
	frame.repaint();
	frame.setLayout(null);
	frame.setVisible(true);
	submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
	        if (submit.isEnabled()) {
	        	person = text.getText();
	        	if (person.compareToIgnoreCase("dad") == 0) {
	        		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	        		selectWindow();
	        	}
	        }
		}	
	});
	}
	
	public static void selectWindow() {
		JFrame frame = new JFrame ();
		frame.setSize(300,220);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.darkGray);	
		frame.setResizable(false);
		JButton deposit = new JButton();
		deposit.setBounds(100, 50, 90, 20);
		JButton withdraw = new JButton();
		withdraw.setBounds(100, 90, 90, 20);
		JButton overview = new JButton();
		overview.setBounds(100, 130, 90, 20);
		JLabel q = new JLabel();
		frame.add(deposit);
		frame.add(withdraw);
		frame.add(overview);
		frame.repaint();
		frame.setLayout(null);
		frame.setVisible(true);
	}
}