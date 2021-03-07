import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
	
public class item implements Comparable <item>{
	private String name = "";
	private double amount = 0;
	private String date = "00000000";
	private String amountS = "";
	
	public item (String n, double a, String d) {
		name = n;
		amount = a;
		date = d;
		amountS = Double.toString(a);
	}
	private static String changeDate (String d) {
		String Date = "";
		String Month = "";
		String Year = "";
		String FullDate = "";
		Month = d.substring(0,2);
		Date = d.substring(2,4);
		if (Month.compareTo("01") == 0)
			Month = "January";
		if (Month.compareTo("02") == 0)
			Month = "Febuary";
		if (Month.compareTo("03") == 0)
			Month = "March";
		if (Month.compareTo("04") == 0)
			Month = "April";
		if (Month.compareTo("05") == 0)
			Month = "May";
		if (Month.compareTo("06") == 0)
			Month = "June";
		if (Month.compareTo("07") == 0)
			Month = "July";
		if (Month.compareTo("08") == 0)
			Month = "August";
		if (Month.compareTo("09") == 0)
			Month = "September";
		if (Month.compareTo("10") == 0)
			Month = "October";
		if (Month.compareTo("11") == 0)
			Month = "November";
		if (Month.compareTo("12") == 0)
			Month = "December";
		Year = d.substring(4,8);
		FullDate = Month + " " + Date + ", " + Year;
		return FullDate;
	}
	public double getAmount () {
		return amount;
	}
	public String getAmountS () {
		if (amountS.charAt(0) == '-')
			return amountS.substring(1,amountS.length());
		else
			return amountS;
	}
	public String getName () {
		return name;
	}
	public String getDate () {
		return changeDate(date);
	}
	public String getDateOrg () {
		return date;
	}
	public int compare (item i1, item i2) {
		return 0; 
	}
	public int compareTo (item i1) {
		return this.date.compareToIgnoreCase(i1.date);
	}
}
class SortByDate implements Comparator <item> { 
	public int compare (item s1, item s2) {
		return s1.getDateOrg().compareToIgnoreCase(s2.getDateOrg());
	}
}
class SortByAmount implements Comparator <item> { 
	public int compare (item s1, item s2) {
		return s1.getAmountS().compareToIgnoreCase(s2.getAmountS());
	}
}