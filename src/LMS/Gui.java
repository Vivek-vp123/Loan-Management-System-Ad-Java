package LMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Gui extends JFrame {
	
	Connection con;
	PreparedStatement ps;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtinterestrate;
	private JTextField txtnumberofyear;
	private JTextField txtamountofloan;
	private JTextField txtmonthlypayment;
	private JTextField txttotalpayment;
	private JTextField txtname;
	JTextArea txtReceipt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
					frame.setSize(700, 500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(0, 0, 700, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Loan Management System");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(203, 10, 258, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Interest Rate");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 125, 190, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Number of Year\r\n");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 163, 190, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter Amount Of Loan");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 204, 190, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Monthly Payment");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(10, 247, 190, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Total Payment\r\n");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(10, 287, 190, 20);
		contentPane.add(lblNewLabel_1_4);
		
		txtinterestrate = new JTextField();
		txtinterestrate.setBounds(210, 123, 200, 30);
		contentPane.add(txtinterestrate);
		txtinterestrate.setColumns(10);
		
		txtnumberofyear = new JTextField();
		txtnumberofyear.setColumns(10);
		txtnumberofyear.setBounds(210, 161, 200, 30);
		contentPane.add(txtnumberofyear);
		
		txtamountofloan = new JTextField();
		txtamountofloan.setColumns(10);
		txtamountofloan.setBounds(210, 202, 200, 30);
		contentPane.add(txtamountofloan);
		
		txtmonthlypayment = new JTextField();
		txtmonthlypayment.setColumns(10);
		txtmonthlypayment.setBounds(210, 245, 200, 30);
		contentPane.add(txtmonthlypayment);
		
		txttotalpayment = new JTextField();
		txttotalpayment.setColumns(10);
		txttotalpayment.setBounds(210, 285, 200, 30);
		contentPane.add(txttotalpayment);
		
		JButton loancal = new JButton("Loan Calculator\r\n");
		loancal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double InterestRate= Double.parseDouble(txtinterestrate.getText());
				double monthlyInteresrRate=InterestRate/1200;
				int numberOfYear=Integer.parseInt(txtnumberofyear.getText());
				double loanAmount= Double.parseDouble(txtamountofloan.getText());
				
				double monthlyPayment = loanAmount * monthlyInteresrRate /(1-1/Math.pow(1 + monthlyInteresrRate, numberOfYear*12));
				String imonthlyPayment;
				imonthlyPayment = Double.toString(monthlyPayment);
				imonthlyPayment = String.format("₹%.2f", monthlyPayment);
				txtmonthlypayment.setText(imonthlyPayment);
				
				double totalPayment=monthlyPayment*numberOfYear*12;
				String itotalPayment;
				itotalPayment=String.format("₹%.2f", totalPayment);
				txttotalpayment.setText(itotalPayment);
			}
		});
		loancal.setBounds(29, 345, 128, 30);
		contentPane.add(loancal);
		
		JButton btnGenerateReceipt = new JButton("Generate Receipt");
		btnGenerateReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String InterestRate = String.format(txtinterestrate.getText());
				String numberOfYear = String.format(txtnumberofyear.getText());
				String loanAmount = String.format(txtamountofloan.getText());
				String monthlyPayment = String.format(txtmonthlypayment.getText());
				String totalPayment = String.format(txttotalpayment.getText());
				String LoanerName=String.format(txtname.getText());
				
				int refs=1325+(int)(Math.random()*4238);
				
				Calendar timer = Calendar.getInstance();
				timer.getTime();
				SimpleDateFormat tTime=new SimpleDateFormat("HH:mm:ss");
				tTime.format(timer.getTime());
				SimpleDateFormat Tdate=new SimpleDateFormat("dd-MM-yyyy");
				Tdate.format(timer.getTime());
				
				txtReceipt.append("\tLoan Management System\n"+
				"Reference:\t\t"+refs+
				"\n==================================/t"+
				"\nName Of Loan Taker:\t"+LoanerName+
				"\nInterest rate:\t\t"+InterestRate+
				"\nRepayment years:\t"+numberOfYear+
				"\nAmount of Loan:\t\t"+"₹"+loanAmount+
				"\nMonthly Payment:\t"+monthlyPayment+
				"\nTotal Payment:\t\t"+totalPayment+
				"\n====================================\t"+
				"\nDate:"+Tdate.format(timer.getTime())+
				"\t\tTime:"+tTime.format(timer.getTime())+
				"\n\n\t\tThank You\n");
				
				try
				{
			 String url = "jdbc:mysql://localhost:3306/lms", username = "root" ,password = "Vivek@123";     
			 con =DriverManager.getConnection(url,username,password);    	
			 ps = con.prepareStatement("Insert into loaninfo values(?,?,?,?,?,?)");
			 ps.setString(1, LoanerName);
			 ps.setString(2, InterestRate);
			 ps.setString(3, numberOfYear);
			 ps.setString(4, loanAmount);
			 ps.setString(5, monthlyPayment);
			 ps.setString(6, totalPayment);
			 
			 int count=ps.executeUpdate();
			 if(count>0)
			 {
				JOptionPane.showMessageDialog(btnGenerateReceipt,"Record Added");
			 }
			 
              con.close();
			}
		
		
		catch(Exception e1)
		{
			System.out.println(e1);
		}
			}
		});
		btnGenerateReceipt.setBounds(191, 345, 140, 30);
		contentPane.add(btnGenerateReceipt);
		
		JButton btnResetCalculator = new JButton("Reset Calculator\r\n");
		btnResetCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtinterestrate.setText("");
				txtnumberofyear.setText("");
				txtamountofloan.setText("");
				txtmonthlypayment.setText("");
				txttotalpayment.setText("");
				txtReceipt.setText("");
			}
		});
		btnResetCalculator.setBounds(364, 345, 128, 30);
		contentPane.add(btnResetCalculator);
		
		JButton btnExitCalculator = new JButton("Exit Calculator\r\n");
		btnExitCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			JFrame f=new JFrame("Exit");
			if(JOptionPane.showConfirmDialog(f,"Confirm if you want to exit","Loan Managemenet System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
			}
		});
		btnExitCalculator.setBounds(527, 345, 128, 30);
		contentPane.add(btnExitCalculator);
		
		txtReceipt = new JTextArea();
		txtReceipt.setBounds(435, 82, 241, 233);
		contentPane.add(txtReceipt);
		
		JLabel lblNewLabel_1_5 = new JLabel("Name of Loan Taker");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(10, 82, 190, 20);
		contentPane.add(lblNewLabel_1_5);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(210, 80, 200, 30);
		contentPane.add(txtname);
	}
}
