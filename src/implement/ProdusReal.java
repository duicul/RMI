package implement;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Farmacie;
import classes.Produs;
import classes.Stoc;
import interfaces.Produsinter;

public class ProdusReal implements Produsinter{
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public ProdusReal(String host,String dbase,String USER,String PASS)
    {this.host=host;
     this.dbase=(dbase==null)?"Test":dbase;
     this.USER=(USER==null)?"root":USER;
     this.PASS=(PASS==null)?"":PASS;
     this.DB_URL= "jdbc:mysql://"+host+"/"+this.dbase;
    }

	@Override
	public List<Stoc> getStoc(Produs p) throws RemoteException {
		if(!p.getHost().equals(this.host))
	    	return null;
		return this.getStoc(p.getID());
	}

	@Override
	public List<Stoc> getStoc(int pid) throws RemoteException {
	    List<Stoc> ls=new ArrayList<Stoc>();
	     int id,fid,pret,cantitate;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT S.ID,S.FID,S.PID,S.PRET,S.CANTITATE FROM PRODUS P,STOC S WHERE S.PID="+pid;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id=rs.getInt("ID");
	                      fid=rs.getInt("FID");
	                      pret=rs.getInt("PRET");
	                     cantitate=rs.getInt("CANTITATE");
	                      ls.add(new Stoc(id,fid,pid,pret,cantitate,host));
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
	       return ls;
	}

	public List<Stoc> getStoc(Produs p, Farmacie f) throws RemoteException {
		if(!p.getHost().equals(this.host)||!f.getHost().equals(this.host))
			return null;
		return this.getStoc(p.getID(), f.getID());
	}

	@Override
	public List<Stoc> getStoc(int pid, int fid) throws RemoteException {
	    List<Stoc> ls=new ArrayList<Stoc>();
	     int id,pret,cantitate;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT S.ID,S.FID,S.PID,S.PRET,S.CANTITATE FROM PRODUS P,STOC S WHERE S.PID="+pid+"S.FID="+fid;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id=rs.getInt("ID");
	                      fid=rs.getInt("FID");
	                      pret=rs.getInt("PRET");
	                     cantitate=rs.getInt("CANTITATE");
	                      ls.add(new Stoc(id,fid,pid,pret,cantitate,host));
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
	       return ls;
	}

	@Override
	public List<Farmacie> getFarm(int pid) throws RemoteException 
		{
		    List<Farmacie> ls=new ArrayList<Farmacie>();
		    int id;
		    String nume,adresa,nrtel,oras,program;  
		        try{Class.forName(JDBC_DRIVER);
			  System.out.println("Connecting to database...");
			  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		            stmt = (Statement) conn.createStatement();
				      String sql;
				      sql = "SELECT F.* FROM PRODUS P,STOC S,FARMACIE F WHERE S.FID=F.ID AND S.PID="+pid;
				      ResultSet rs = stmt.executeQuery(sql);
				      
		                      while(rs.next())
		                      {id=rs.getInt("ID");
		                      nume=rs.getString("NUME");
		                      adresa=rs.getString("ADRESA");
		                      nrtel=rs.getString("NRTEL");
		                      oras=rs.getString("ORAS");
		                      program=rs.getString("PROGRAM");
		                      ls.add(new Farmacie(id,nume,adresa,nrtel,oras,program,host));
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
		       return ls;
		}

	@Override
	public List<Stoc> getStoc(String p) throws RemoteException {
	    List<Stoc> ls=new ArrayList<Stoc>();
	     int id,pret,pid,fid,cantitate;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT S.ID,S.FID,S.PID,S.PRET,S.CANTITATE FROM PRODUS P,STOC S WHERE S.PID=P.ID AND P.NUME='"+p+"'";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id=rs.getInt("ID");
	                      fid=rs.getInt("FID");
	                      pid=rs.getInt("PID");
	                      pret=rs.getInt("PRET");
	                     cantitate=rs.getInt("CANTITATE");
	                      ls.add(new Stoc(id,fid,pid,pret,cantitate,host));
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
	       return ls;
	}

	@Override
	public List<Stoc> getStoc(String p, String f) throws RemoteException {
	    List<Stoc> ls=new ArrayList<Stoc>();
	     int id,pret,pid,fid,cantitate;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT S.ID,S.FID,S.PID,S.PRET,S.CANTITATE FROM PRODUS P,STOC S,FARMACIE F WHERE S.FID=F.ID AND S.PID=P.ID AND P.NUME='"+p+"' AND F.NUME='"+f+"'";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id=rs.getInt("ID");
	                      fid=rs.getInt("FID");
	                      pid=rs.getInt("PID");
	                      pret=rs.getInt("PRET");
	                     cantitate=rs.getInt("CANTITATE");
	                      ls.add(new Stoc(id,fid,pid,pret,cantitate,host));
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
	       return ls;
	}

	@Override
	public List<Farmacie> getFarm(String p) throws RemoteException {
	    List<Farmacie> ls=new ArrayList<Farmacie>();
	    int id;
	    String nume,adresa,nrtel,oras,program;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT F.* FROM PRODUS P,STOC S,FARMACIE F WHERE S.FID=F.ID AND F.NUME='"+p+"'";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id=rs.getInt("ID");
	                      nume=rs.getString("NUME");
	                      adresa=rs.getString("ADRESA");
	                      nrtel=rs.getString("NRTEL");
	                      oras=rs.getString("ORAS");
	                      program=rs.getString("PROGRAM");
	                      ls.add(new Farmacie(id,nume,adresa,nrtel,oras,program,host));
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
	       return ls;
	}


	@Override
	public List<Farmacie> getFarm(Produs p) throws RemoteException {
		if(!p.getHost().equals(this.host))
			return null;
		return this.getFarm(p.getID());
	}
}
