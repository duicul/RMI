package implement;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import interfaces.Farmacieinter;
import classes.Produs;
import classes.Farmacie;
import classes.Stoc;

public class FarmacieReal implements Farmacieinter {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public FarmacieReal(String host,String dbase,String USER,String PASS)
    {this.host=host;
     this.dbase=(dbase==null)?"Test":dbase;
     this.USER=(USER==null)?"root":USER;
     this.PASS=(PASS==null)?"":PASS;
     this.DB_URL= "jdbc:mysql://"+host+"/"+this.dbase;
    }
    
    
    public List<Produs> getProductsFarmacie(Farmacie f) throws RemoteException
    {if(this.host!=f.getHost())
    	return null;
    return this.getProductsFarmacie(f.getID());
    }
     
	@Override
	public List<Produs> getProductsFarmacie(int fid) throws RemoteException { 
    List<Produs> ls=new ArrayList<Produs>();
     int id;
        String nume,clasa;      
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT P.ID,P.NUME,P.CLASA FROM FARMACIE F,PRODUS P,STOC S WHERE S.FID="+fid+" AND S.PID=P.ID";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      while(rs.next())
                      {id  = rs.getInt("ID");
                       nume  = rs.getString("NUME");
                       clasa  = rs.getString("CLASA");
                      ls.add(new Produs(id,nume,clasa,this.host));
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
       return ls;}


	@Override
	public List<Produs> getProductsFarmacie(String f) throws RemoteException { 
	    List<Produs> ls=new ArrayList<Produs>();
	     int id;
	        String nume,clasa;      
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT P.ID,P.NUME,P.CLASA FROM FARMACIE F,PRODUS P,STOC S WHERE S.FID='"+f+"' AND S.PID=P.ID";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id  = rs.getInt("ID");
	                       nume  = rs.getString("NUME");
	                       clasa  = rs.getString("CLASA");
	                      ls.add(new Produs(id,nume,clasa,this.host));
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
	       return ls;}

	public List<Stoc> getStocFarmacie(Farmacie f) throws RemoteException
	{if(this.host!=f.getHost())
    	return null;
    return this.getStocFarmacie(f.getID());
    }
    
	
	@Override
	public List<Stoc> getStocFarmacie(int fid) throws RemoteException
	{ List<Stoc> ls=new ArrayList<Stoc>();
	   int id,pid,pret,cantitate;      
	   try{Class.forName(JDBC_DRIVER);
	 System.out.println("Connecting to database...");
	 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT S.ID,S.FID,S.PID,S.PRET,S.CANTITATE FROM STOC S WHERE S.FID="+fid;
		      ResultSet rs = stmt.executeQuery(sql);
		      
	                 while(rs.next())
	                 {id  = rs.getInt("ID");
	                  fid  = rs.getInt("FID");
	                  pid  = rs.getInt("PID");
	                  pret  = rs.getInt("PRET");
	                  cantitate  = rs.getInt("CANTITATE");
	                  ls.add(new Stoc(id,fid,pid,pret,cantitate,this.host));}
		     
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
	  return ls;}


	@Override
	public List<Stoc> getStocFarmacie(String f) throws RemoteException { List<Stoc> ls=new ArrayList<Stoc>();
	   int id,fid,pid,pret,cantitate;      
	   try{Class.forName(JDBC_DRIVER);
	 System.out.println("Connecting to database...");
	 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT S.ID,S.FID,S.PID,S.PRET,S.CANTITATE FROM STOC S,FARMACIE F WHERE S.FID=F.ID AND F.NUME='"+f+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
	                 while(rs.next())
	                 {id  = rs.getInt("ID");
	                  fid  = rs.getInt("FID");
	                  pid  = rs.getInt("PID");
	                  pret  = rs.getInt("PRET");
	                  cantitate  = rs.getInt("CANTITATE");
	                  ls.add(new Stoc(id,fid,pid,pret,cantitate,this.host));}
		     
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
	  return ls;}
}
