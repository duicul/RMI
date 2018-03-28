import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stoc extends Remote {
    public int getID() throws RemoteException;
    public int getFID() throws RemoteException;
    public int getPID() throws RemoteException;
    public int getPret() throws RemoteException;
    public int getCantitate() throws RemoteException;
    public Produs getProdus() throws RemoteException;
    public Farmacie getFarmacie() throws RemoteException;
}
