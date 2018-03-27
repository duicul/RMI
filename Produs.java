import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Produs extends Remote {
    public int getID() throws RemoteException;
    public String getNume() throws RemoteException;
    public String getClasa() throws RemoteException;
    public Stoc getStocFID(int FID) throws RemoteException;
    public Stoc[] getStoc() throws RemoteException;
}
