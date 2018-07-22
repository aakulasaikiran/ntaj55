package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrame extends JFrame implements ActionListener {
	private static final String QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";
	private JLabel lno,lname,ladd;
	private JTextField tno,tname,tadd;
	private JButton bfirst,bnext,blast,bprevious;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	
	//constructor
	public ScrollFrame(){
		System.out.println("constructor");
		setLayout(new FlowLayout());
		setSize(400,400);
		setTitle("Scrollable Frame App");
		//add comps
		lno=new JLabel("Student no");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("Student name");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		ladd=new JLabel("Student Address");
		add(ladd);
		tadd=new JTextField(10);
		add(tadd);
		
		bfirst=new JButton("first");
		bfirst.addActionListener(this);
		add(bfirst);
		
		bnext=new JButton("next");
		bnext.addActionListener(this);
		add(bnext);
		
		bprevious=new JButton("previous");
		bprevious.addActionListener(this);
		add(bprevious);
		
		blast=new JButton("Last");
		blast.addActionListener(this);
		add(blast);
		
		//close window 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//keep visible on
		setVisible(true);
		initialize();
	}//constructor
	
	public void initialize(){
		System.out.println("initialize()");
		try{
			//registr jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement obj 
			if(con!=null)
				ps=con.prepareStatement(QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,
						                                                           ResultSet.CONCUR_UPDATABLE);
			//create  Scrollable Result
			if(ps!=null)
			  rs=ps.executeQuery();	
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//initialize()
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("actionPerformed(-)");
		try{
		boolean flag=false;
		if(ae.getSource()==bfirst){
			rs.first();
			flag=true;
		 }
		else if(ae.getSource()==blast){
			rs.last();
			flag=true;
		}
		else if(ae.getSource()==bnext){
			if(!rs.isLast()){
			   rs.next();
			   flag=true;
			}
		}
		else{
			if(!rs.isFirst()){
				rs.previous();
			  flag=true;
			}
		}
		//set record values text boxes
		if(flag==true){
			tno.setText(rs.getString(1));
			tname.setText(rs.getString(2));
			tadd.setText(rs.getString(3));
		}//if
	  }//try
	catch(SQLException se){
		se.printStackTrace();
	}
   catch(Exception e){
	   e.printStackTrace();
   }
}//actionPerformed(-)

public static void main(String[] args) {
	System.out.println("main(-)");
		new ScrollFrame();
	}//main
}//class
