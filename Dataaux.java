import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

public class Dataaux implements Serializable {
    private int val;
    public Dataaux(int val)
   {this.val=val*2;}
    
    public int getVal()
    {return this.val;}
}
