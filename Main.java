import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.*;

public class Main extends UnicastRemoteObject implements StudentData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Main() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private String DB_URL = "jdbc:mysql://localhost/Test";
	private String USER = "root";
	private String PASS = "";
	private Connection conn = null;
	private Statement stmt = null;
	public List<Student> getStudents() throws RemoteException{
		List<Student> l=new ArrayList<Student>();
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM Student";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("ID");
		         String nume = rs.getString("NUME");
		         String pren = rs.getString("PRENUME");
		         int var = rs.getInt("VARSTA");
                 l.add(new Student(id,nume,pren,var));
		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(" Nume: " + nume);
		         System.out.print(" Prenume: " + pren);
		         System.out.println(" Varsta " + var);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return l;
		}//end main
	public static void main(String argc[])
	{try { 
        // Instantiating the implementation class 
        Main obj = new Main(); 
   
        // Exporting the object of implementation class  
        // (here we are exporting the remote object to the stub) 
        StudentData stub = (StudentData) UnicastRemoteObject.exportObject(obj, 0);  
        
        // Binding the remote object (stub) in the registry 
        Registry registry = LocateRegistry.getRegistry(); 
        
        registry.bind("Main", stub);  
        System.err.println("Server ready"); 
     } catch (Exception e) { 
        System.err.println("Server exception: " + e.toString()); 
        e.printStackTrace(); 
     } }
	

}
