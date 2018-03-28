package classes;
import java.rmi.*;


public class Stoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id,fid,pid,pret,cantitate;
    private String host;
    public Stoc(int id,int fid,int pid,int pret,int cantitate,String host)
    {this.id=id;
     this.fid=fid;
     this.pid=pid;
     this.pret=pret;
     this.cantitate=cantitate;
     this.host=host;}
    public int getID() throws RemoteException
    {return this.id;}
    public int getFID() throws RemoteException
    {return this.fid;}
    public int getPID() throws RemoteException
    {return this.pid;}    
    public int getPret() throws RemoteException
    {return this.pret;}    
    public int getCantitate() throws RemoteException
    {return this.cantitate;}
    public String getHost() throws RemoteException
    {return this.host;}
  }
