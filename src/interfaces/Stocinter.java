package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import classes.*;

public interface Stocinter extends Remote {
    public int getID() throws RemoteException;
    public int getFID() throws RemoteException;
    public int getPID() throws RemoteException;
    public int getPret() throws RemoteException;
    public int getCantitate() throws RemoteException;
    public Produs getProdus() throws RemoteException;
    public Farmacie getFarmacie() throws RemoteException;
}
