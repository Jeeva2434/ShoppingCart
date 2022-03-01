package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import model.ProductModel;

public class ProductDao {

	public void storeEmpRecordInDb(ArrayList<ProductModel> pdList) throws ClassNotFoundException {
	        Class.forName("com.mysql.jdbc.Driver");
		    String sql = "insert into product(pid,name,price,stock) values (?,?,?,?)";
		    try {
				  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
				  if(con!=null) {
				
					  PreparedStatement ps = con.prepareStatement(sql);
				      for(ProductModel pt: pdList) {
				    	ps.setInt(1, pt.getPid());
				    	ps.setString(2, pt.getProductName());
					    ps.setDouble(3, pt.getPrice());
					    ps.setInt(4, pt.getStock());
					    int result = ps.executeUpdate();
				     	if(result == 1) {
					     	System.out.println("Product added successfully");
					     }
				      }
				  }
				}catch(SQLException e) {
				      e.printStackTrace();
		 	      }
     }

	public void viewProducts() throws ClassNotFoundException{
		 int count = 0;
	     Class.forName("com.mysql.jdbc.Driver");
	     String sql = "Select * from product";
	     try {
	     	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
	     	 Statement stmt = con.createStatement();
	     	 ResultSet rs = stmt.executeQuery(sql);
	     	 while(rs.next()) {
	              if(count==0) {
	             	 System.out.println(String.format("%-17s%-25s%-16s%-10s", "Id","Name","Price","Stock"));
	             	 System.out.println();
	             	 count++;
	              }
	              System.out.println(String.format("%-10s%-30s%-20s%-10s",rs.getInt(1) ,rs.getString(2),rs.getDouble(3),rs.getInt(4)));	 
	              System.out.println();
	     	 }
	     }catch(SQLException e) {
	     	e.printStackTrace();
	     }
	    }

 
	public void update(int stock_rem,int id,double price) throws ClassNotFoundException {
		 Scanner sc = new Scanner(System.in);
	    Class.forName("com.mysql.jdbc.Driver");
	    String sql = "update product set stock = ? , price = ? where pid = ?";
	    
	    try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,stock_rem );
			ps.setDouble(2,price);
			ps.setInt(3,id);
			ps.executeUpdate();
			System.out.println("Product updated successfully");
			System.out.println("********************************************************************");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   }
	
	public void delete(int pid) throws ClassNotFoundException {
		 Scanner sc = new Scanner(System.in);
	
	    String sql = "delete from product where pid = ?";
	   
	    Class.forName("com.mysql.jdbc.Driver");
	    try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,pid);
			ps.executeUpdate();
			System.out.println("product record updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void adcart(int sid) throws ClassNotFoundException{
		
	     Class.forName("com.mysql.jdbc.Driver");
	     String sql = "Select * from product";
	     boolean check =true;
	     try {
	     	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
	     	 Statement stmt = con.createStatement();
	     	 ResultSet rs = stmt.executeQuery(sql);
	     	 while(rs.next()) {
	     		 check = checkcartforexists(sid,check);
	     		 if(check) {
	     		    if(rs.getInt(1)==sid) {
	                  if(rs.getInt(4)!=0) {
	             	     String sq = "insert into cart(pid,name,price,stock) values (?,?,?,?)";
	             	     PreparedStatement ps = con.prepareStatement(sq);
				      
				    	 ps.setInt(1,rs.getInt(1));
				      	 ps.setString(2,rs.getString(2));
				    	 ps.setDouble(3,rs.getDouble(3));
				    	 ps.setInt(4,(rs.getInt(4)-1));
					     int result = ps.executeUpdate();
				     	 if(result == 1) {
					     	System.out.println("Product added to cart successfully");
					     }
	                   }
	                   else {
	            	      System.out.println("ordered item has no stock !! notify you when available");
	            	      break;
	                   }
	     		    }
	     		 }
	     		 else {
	     			 System.out.println("Item already added to cart ");
	     			 break;
	     		 }
	     	 }
	     }catch(SQLException e) {
	     	e.printStackTrace();
	     }
	    }
	
	public boolean checkcartforexists(int sid,boolean check) throws ClassNotFoundException{

	     Class.forName("com.mysql.jdbc.Driver");
	     String sql = "Select * from cart";
	     try {
	     	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
	     	 Statement stmt = con.createStatement();
	     	 ResultSet rs = stmt.executeQuery(sql);
	     	 while(rs.next()) {
	             if(rs.getInt(1)==sid) {
	                	 check = false;
	             }
	     	 }
	     }catch(SQLException e) {
	     	e.printStackTrace();
	     }
		return check;
	    }
	
	
	
	
	
	public void viewcart() throws ClassNotFoundException{
		 int count = 0;
	     Class.forName("com.mysql.jdbc.Driver");
	     String sql = "Select * from cart";
	     try {
	     	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
	     	 Statement stmt = con.createStatement();
	     	 ResultSet rs = stmt.executeQuery(sql);
	     	 while(rs.next()) {
	              if(count==0) {
	             	 System.out.println(String.format("%-17s%-25s%-16s%-10s", "Id","Name","Price","Stock"));
	             	 System.out.println();
	             	 count++;
	              }
	              System.out.println(String.format("%-10s%-30s%-20s%-10s",rs.getInt(1) ,rs.getString(2),rs.getDouble(3),rs.getInt(4)));	 
	              System.out.println();
	     	 }
	     }catch(SQLException e) {
	     	e.printStackTrace();
	     }
	    }
	
	
	
	public void delete_cart(int delid) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
	    String sql = "delete from cart where pid = ?";	   
	    Class.forName("com.mysql.jdbc.Driver");

	    try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,delid);
			ps.executeUpdate();
			System.out.println("product record updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	
	public void totalAmount() throws ClassNotFoundException{
		 int total = 0;
		 Class.forName("com.mysql.jdbc.Driver");
	     String sql = "Select * from cart";
	     try {
	     	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
	     	 Statement stmt = con.createStatement();
	     	 ResultSet rs = stmt.executeQuery(sql);
	     	 while(rs.next()) {
	              total += rs.getInt(3);
	     	 }
	     	 System.out.println("Total Amount : "+total);
	     	 System.out.println();
	     }catch(SQLException e) {
	     	e.printStackTrace();
	     }
	    }	    
	
	public void stockupdate(int delid) throws ClassNotFoundException{
		 Class.forName("com.mysql.jdbc.Driver");
	     String sql = "Select * from product";
	     try {
	     	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopie","root","jeeva2434");
	     	 Statement stmt = con.createStatement();
	     	 ResultSet rs = stmt.executeQuery(sql);
	     	 while(rs.next()) {
	     		     if(rs.getInt(1)==delid) {
	             	    String sq = "update cart set stock = ? where pid = ?";
	             	    PreparedStatement ps = con.prepareStatement(sq);
				    	ps.setInt(1,(rs.getInt(4)+1));
				    	ps.setInt(2,(rs.getInt(1)));
					    int result = ps.executeUpdate();
				     	if(result == 1) {
					     	System.out.println("Product added to cart successfully");
					     }
	     		     }
	     	     } 
	       }catch(SQLException e) {
	     	e.printStackTrace();
	      }	    
	}}
