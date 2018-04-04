package implementch;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import classes.Farmacie;
import implement.DBManageReal;
import interfaces.DBManageinter;
import interfacesch.Farmacieinterch;

public class Farmaciech implements Farmacieinterch {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
    public Farmaciech(String host,String dbase,String USER,String PASS)
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
	public void changeNume(int fid, String nume) throws RemoteException {
		try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	          stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "UPDATE FARMACIE SET NUME='"+nume+"' WHERE ID="+fid;
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
	public void changeNume(Farmacie f, String nume) throws RemoteException {
		if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
			return ;
		this.changeNume(f.getID(), nume);
		
	}

	@Override
	public void changeAdresa(int fid, String adresa) throws RemoteException {
		try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	          stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "UPDATE FARMACIE SET ADRESA='"+adresa+"' WHERE ID="+fid;
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
	public void changeAdresa(Farmacie f, String adresa) throws RemoteException {
		if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
			return ;
		this.changeAdresa(f.getID(), adresa);
		
	}

	@Override
	public void changeNrtel(int fid, String nrtel) throws RemoteException {
		try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	          stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "UPDATE FARMACIE SET NRTEL='"+nrtel+"' WHERE ID="+fid;
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
	public void changeNrtel(Farmacie f, String nrtel) throws RemoteException {
		if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
			return ;
		this.changeNrtel(f.getID(), nrtel);
		
	}

	@Override
	public void changeProgram(int fid, String program) throws RemoteException {
		try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	          stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "UPDATE FARMACIE SET PROGRAM='"+program+"' WHERE ID="+fid;
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
	public void changeProgram(Farmacie f, String program) throws RemoteException {
		if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
			return ;
		this.changeProgram(f.getID(), program);
		
	}

	@Override
	public void changeAdresa(String fNume, String adresa) throws RemoteException {
		DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
		Farmacie f=mi.getFarmacieNume(fNume);
		this.changeAdresa(f, adresa);		
	}

	@Override
	public void changeNrtel(String fNume, String nrtel) throws RemoteException {
		DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
		Farmacie f=mi.getFarmacieNume(fNume);
		this.changeNrtel(f, nrtel);
		
	}

	@Override
	public void changeProgram(String fNume, String program) throws RemoteException {
		DBManageinter mi=new DBManageReal(this.host,this.dbase,this.USER,this.PASS);
		Farmacie f=mi.getFarmacieNume(fNume);
		this.changeProgram(f, program);
		
	}
}