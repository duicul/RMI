package implement;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Farmacie;
import classes.Produs;
import classes.Stoc;
import interfaces.Stocinter;

public class StocReal implements Stocinter {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public StocReal(String host,String dbase,String USER,String PASS)
    {this.host=host;
     this.dbase=(dbase==null)?"Test":dbase;
     this.USER=(USER==null)?"root":USER;
     this.PASS=(PASS==null)?"":PASS;
     this.DB_URL= "jdbc:mysql://"+host+"/"+this.dbase;
    }
	@Override
	public Farmacie getFarm(int sid) throws RemoteException {
	    Farmacie f=null;
	    int id;
	    String nume,adresa,nrtel,oras,program;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT F.* FROM STOC S,FARMACIE F WHERE S.FID=F.ID AND S.ID="+sid;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id=rs.getInt("ID");
	                      nume=rs.getString("NUME");
	                      adresa=rs.getString("ADRESA");
	                      nrtel=rs.getString("NRTEL");
	                      oras=rs.getString("ORAS");
	                      program=rs.getString("PROGRAM");
	                      f=new Farmacie(id,nume,adresa,nrtel,oras,program,host);
	                      }
			     
			      rs.close();
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
			   }
	       return f;
	}

	@Override
	public Farmacie getFarm(Stoc s) throws RemoteException {
		if(!this.host.equals(s.getHost()))
			return null;
		return this.getFarm(s.getID());
	}

	@Override
	public Produs getProdus(int sid) throws RemoteException {
	    Produs p=null;
	     int id;
	        String nume,clasa;      
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT P.ID,P.NUME,P.CLASA FROM FARMACIE F,PRODUS P,STOC S WHERE S.PID="+sid+" AND S.PID=P.ID";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id  = rs.getInt("ID");
	                       nume  = rs.getString("NUME");
	                       clasa  = rs.getString("CLASA");
	                      p=new Produs(id,nume,clasa,this.host);
	                      }
			     
			      rs.close();
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
			   }
	       return p;}

	@Override
	public Produs getProdus(Stoc s) throws RemoteException {
		if(!this.host.equals(s.getHost()))
			return null;
		return this.getProdus(s.getID());
	}
}
