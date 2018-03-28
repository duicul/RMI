import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

public class Data implements Serializable {
    private int val;
    public Data(int val)
   {this.val=val;}
    
    public int getVal()
    {return this.val;}

    public Dataaux getDataaux()
    {return new Dataaux(this.val);}
}
