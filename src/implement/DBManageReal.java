package implement;
import java.rmi.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import classes.*;
import interfaces.*;

public class DBManageReal implements DBManageinter {
        private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
    public DBManageReal(String host,String dbase,String USER,String PASS)
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

   public List<Farmacie> getFarmacii() throws RemoteException
     {List<Farmacie> ls=new ArrayList<Farmacie>();
      int id;      
      String nume,adresa,nrtel,oras,program;   
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
           stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM FARMACIE";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      while(rs.next())
                      {id  = rs.getInt("ID");
		         nume = rs.getString("NUME");
                         adresa = rs.getString("ADRESA");
                         nrtel = rs.getString("NRTEL");
                         oras = rs.getString("ORAS");
                         program = rs.getString("PROGRAM");
                         ls.add(new Farmacie(id,nume,adresa,nrtel,oras,program,host,dbase));}
		     
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

    public Farmacie getFarmacieId(int FID) throws RemoteException
     {int id;    
     Farmacie f=null;
      String nume,adresa,nrtel,oras,program;   
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
           stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM FARMACIE WHERE ID="+FID;
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("ID");
		         nume = rs.getString("NUME");
                         adresa = rs.getString("ADRESA");
                         nrtel = rs.getString("NRTEL");
                         oras = rs.getString("ORAS");
                         program = rs.getString("PROGRAM");
		     f=new Farmacie(id,nume,adresa,nrtel,oras,program,host,dbase);}
                      
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
       return f;}

    public Farmacie getFarmacieNume(String nume) throws RemoteException
         {int id; 
         Farmacie f=null;
      String adresa,nrtel,oras,program;   
            try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
                   stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM FARMACIE WHERE NUME='"+nume+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("ID");
                         adresa = rs.getString("ADRESA");
                         nrtel = rs.getString("NRTEL");
                         oras = rs.getString("ORAS");
                         program = rs.getString("PROGRAM");
		      f=new Farmacie(id,nume,adresa,nrtel,oras,program,host,dbase);}
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
       return f;}

    public classes.Stoc getStocID(int SID) throws RemoteException
         {int id,fid,pid,pret,cantitate;      
         classes.Stoc s=null;
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
           stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM STOC WHERE ID="+SID;
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("ID");
                       fid  = rs.getInt("FID");
                       pid  = rs.getInt("PID");
                       pret  = rs.getInt("PRET");
                       cantitate  = rs.getInt("CANTITATE");
                       s=new classes.Stoc(id,fid,pid,pret,cantitate,this.host,dbase);}
		     
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
       return s;}

    public classes.Produs getProductID(int PID) throws RemoteException
       {int id;
       classes.Produs p=null;
        String nume,clasa;      
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM STOC WHERE ID="+PID;
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("ID");
                       nume  = rs.getString("NUME");
                       clasa  = rs.getString("CLASA");
                       p=new classes.Produs(id,nume,clasa,host,dbase);}
		     
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
    
    public classes.Produs getProductName(String name) throws RemoteException
 {int id;
 classes.Produs p=null;
        String nume,clasa;      
        try{Class.forName("com.mysql.jdbc.Driver");
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM STOC WHERE NUME='"+name+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("ID");
                       nume  = rs.getString("NUME");
                       clasa  = rs.getString("CLASA");
                       p=new classes.Produs(id,nume,clasa,host,dbase);}
		     
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
    public classes.Produs getProductClass(String clasa) throws RemoteException
{int id;
  classes.Produs p=null;
        String nume;      
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM STOC WHERE CLASA='"+clasa+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("ID");
                       nume  = rs.getString("NUME");
                       p=new classes.Produs(id,nume,clasa,host,dbase);}
		     
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
}
