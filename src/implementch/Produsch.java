package implementch;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Produs;
import interfacesch.Produsinterch;

public class Produsch implements Produsinterch {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
    public Produsch(String host,String dbase,String USER,String PASS)
    {this.host=host;
     this.dbase=(dbase==null)?"Test":dbase;
     this.USER=(USER==null)?"root":USER;
     this.PASS=(PASS==null)?"":PASS;
     this.DB_URL= "jdbc:mysql://"+host+"/"+this.dbase;
    }
   
   public String getHost() throws RemoteException
   {return this.host;}
   
   public String getDBase() throws RemoteException
   {return this.dbase;}
   
	@Override
	public void changeNume(int pid, String nume) throws RemoteException {
		try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	          stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "UPDATE PRODUS SET NUME='"+nume+"' WHERE ID="+pid;
			      stmt.executeUpdate(sql);
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }finally{
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){}
			      try{  if(conn!=null)
			            conn.close();
			      }catch(SQLException se){se.printStackTrace();}
			   }}

	@Override
	public void changeNume(Produs p, String nume) throws RemoteException {
		if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase())))
			return ;
		this.changeNume(p.getID(), nume);}

	@Override
	public void changeClasa(int pid, String clasa) throws RemoteException {
		try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	          stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "UPDATE PRODUS SET CLASA='"+clasa+"' WHERE ID="+pid;
			      stmt.executeUpdate(sql);
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }finally{
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){}
			      try{  if(conn!=null)
			            conn.close();
			      }catch(SQLException se){se.printStackTrace();}
			   }}

	@Override
	public void changeClasa(Produs p, String clasa) throws RemoteException {
		if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase())))
			return ;
		this.changeClasa(p.getID(), clasa);}

}
