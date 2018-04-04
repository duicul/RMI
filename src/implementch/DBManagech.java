package implementch;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Farmacie;
import classes.Produs;
import classes.Stoc;
import interfacesch.*;
import implement.*;
import interfaces.DBManageinter;

public class DBManagech implements DBManageinterch{
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
    public DBManagech(String host,String dbase,String USER,String PASS)
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
public void addFarmacie(String nume, String adresa, String nrtel, String oras, String program) throws RemoteException{
	 try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
          stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "INSERT INTO FARMACIE (NUME,ADRESA,NRTEL,ORAS,PROGRAM) VALUES ('"+nume+"','"+adresa+"','"+nrtel+"','"+oras+"','"+program+"')";
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
public void addProdus(String nume, String clasa) throws RemoteException{
	try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "INSERT INTO PRODUS (NUME,CLASA) VALUES ('"+nume+"','"+clasa+"')";
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
public void addStoc(int fid, int pid, int cantitate, int val) throws RemoteException{
	try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "INSERT INTO STOC (FID,PID,CANTITATE,PRET) VALUES ("+fid+","+pid+","+cantitate+","+val+")";
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
public void addStoc(String fNume, String pNume, int cantitate, int val) throws RemoteException{
	DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
		Farmacie f=mi.getFarmacieNume(fNume);
		Produs p=mi.getProductName(pNume);
		if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase()))||!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
			return ;
		this.addStoc(f.getID(), p.getID(), cantitate, val);}

@Override
public void addStoc(Farmacie f, Produs p, int cantitate, int val) throws RemoteException {
	if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase()))||!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
		return ;
	this.addStoc(f.getID(), p.getID(), cantitate, val);}



@Override
public void deleteStoc(Farmacie f, Produs p) throws RemoteException {
	if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase()))||!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
		return ;
	this.deleteStoc(f.getID(), p.getID());
	
}

@Override
public void deleteStoc(int fid, int pid) throws RemoteException {
	try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "DELETE * FROM STOC WHERE FID="+fid+" AND PID="+pid;
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
		   }
	
}

@Override
public void deleteStoc(String fNume, String pNume) throws RemoteException {
	DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
	Farmacie f=mi.getFarmacieNume(fNume);
	Produs p=mi.getProductName(pNume);
	if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase()))||!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
		return ;
	this.deleteStoc(f.getID(), p.getID());
}

@Override
public void deleteStoc(Stoc s) throws RemoteException {
	if(!(this.host.equals(s.getHost())&&this.dbase.equals(s.getDBase())))
		return ;
	this.deleteStoc(s.getID());
	
}

@Override
public void deleteStoc(int sid) throws RemoteException {
	try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "DELETE * FROM STOC WHERE ID="+sid;
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
		   }
	
}

@Override
public void deleteProdus(Produs p) throws RemoteException {
	if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase())))
		return ;
	this.deleteProdus(p.getID());
	
}

@Override
public void deleteProdus(int pid) throws RemoteException {
	try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "DELETE S.*,P.* FROM STOC S,PRODUS P WHERE P.ID=S.PID AND P.ID="+pid;
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
public void deleteProdus(String pNume) throws RemoteException {
	DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
	Produs p=mi.getProductName(pNume);
	if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase())))
		return ;
	this.deleteProdus(p.getID());
	
}

@Override
public void deleteFarmacie(Farmacie f) throws RemoteException {
	if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
		return ;
	this.deleteFarmacie(f.getID());
	
}

@Override
public void deleteFarmacie(int fid) throws RemoteException {
	try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
    stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "DELETE S.*,F.* FROM STOC S,FARMACIE F WHERE F.ID=S.FID AND F.ID="+fid;
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
		   }
	
}

@Override
public void deleteFarmacie(String fNume) throws RemoteException {
	DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
	Farmacie f=mi.getFarmacieNume(fNume);
	if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
		return ;
	this.deleteFarmacie(f.getID());
	
}
}
