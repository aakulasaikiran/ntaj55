package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AllStmtsTest extends JFrame implements ActionListener {
	private static final String ALL_STUDENT_NOS_QUERY="SELECT SNO FROM ALL_STUDENT";
	private static final String GET_STUDENT_DETAILS_BY_NO="SELECT SNAME,M1,M2,M3 FROM ALL_STUDENT WHERE SNO=?";
	private static final String  CALL_PROCEDURE="{ call FIND_PASS_FAIL(?,?,?,?)}"; 
	private JLabel lno,lname,lm1,lm2,lm3,lres;
	private JTextField tname,tm1,tm2,tm3,tres;
	private JComboBox tno;
	private JButton  bdetails,bresult;
	private Connection con;
	private Statement st; 
	private ResultSet rs;
	CallableStatement cs;
	private PreparedStatement ps=null;
	//constructor
	public AllStmtsTest(){
		System.out.println("constructor---");
		setTitle("Swing GUI App");
		setSize(400,400);
		setLayout(new FlowLayout());
		//add comps
		lno=new JLabel("sno");
		add(lno);
		
		tno=new JComboBox();
		add(tno);
		
		bdetails=new JButton("Details");
		bdetails.addActionListener(this);
		add(bdetails);
		
		lname=new JLabel("sname");
		add(lname);
		
		tname=new JTextField(10);
		tname.setEditable(false);
		add(tname);
		
		
		lm1=new JLabel("Marks1");
		add(lm1);
		
		tm1=new JTextField(10);
		add(tm1);
		tm1.setEditable(false);
		
		lm2=new JLabel("Marks2");
		add(lm2);
		
		tm2=new JTextField(10);
		add(tm2);
		tm2.setEditable(false);
		
		lm3=new JLabel("Marks3");
		add(lm3);
		
		tm3=new JTextField(10);
		tm3.setEditable(false);
		add(tm3);
		
		bresult=new JButton("Result");
		add(bresult);
		bresult.addActionListener(this);
		
		lres=new JLabel("result:");
		add(lres);
		tres=new JTextField(10);
		tres.setEditable(false);
		add(tres);
		setVisible(true);
		//get all student nos during the app startup
		initialize();
		//Close the process when close button is clicked
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//contructor
	
	private  void initialize(){
		System.out.println("initialize()...");
		try{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Staement obj
			st=con.createStatement();
			//execute Query to all student nos
			rs=st.executeQuery(ALL_STUDENT_NOS_QUERY);
			//add nos to ComboBox
			while(rs.next()){
				tno.addItem(rs.getInt(1));
			}//while
			//create PreparedStatement obj
			ps=con.prepareStatement(GET_STUDENT_DETAILS_BY_NO);
			//create CallableStatement obj
			cs=con.prepareCall(CALL_PROCEDURE);
			//register OUT params with jdbc types
			cs.registerOutParameter(4,Types.VARCHAR);
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}//initialize()
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		int m1=0,m2=0,m3=0;
		String result=null;
		System.out.println("actionPerformed(...)");
		if(ae.getSource()==bdetails){
			System.out.println("Details button is clicked");
			try{
				//read selected Item from choice box/comboBox
				no=(Integer)tno.getSelectedItem();
				//set value to the Query parameter
				ps.setInt(1,no);
				//execute the Query
				rs=ps.executeQuery();
				//set record values to text boxes
				if(rs.next()){
					tname.setText(rs.getString(1));
					tm1.setText(rs.getString(2));
					tm2.setText(rs.getString(3));
					tm3.setText(rs.getString(4));
				}//fi
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			System.out.println("result button is clicked");
			try{
				//read marks from text boxes
				m1=Integer.parseInt(tm1.getText());
				m2=Integer.parseInt(tm2.getText());
				m3=Integer.parseInt(tm3.getText());
				//set values to IN params of PL/SQL PROCEDURE
				cs.setInt(1,m1);
				cs.setInt(2,m2);
				cs.setInt(3,m3);
				//call pl/sql procedure
				cs.execute();
				//gather results from OUT params
				result=cs.getString(4);
				//set results to TextBoxes
				tres.setText(result);
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	  }//else
	}//actionPerfomed(-)

	public static void main(String[] args) {
		System.out.println("main(---)");
	         new AllStmtsTest(); 
	}//main
}//class
