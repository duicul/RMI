import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Farmacie extends Remote {
    public int getID() throws RemoteException;
    public String getNume() throws RemoteException;
    public String getAdresa() throws RemoteException;
    public String getNrtel() throws RemoteException;
    public String getOras() throws RemoteException;
    public String getProgram() throws RemoteException;
    public Product[] getProductsFarmacie() throws RemoteException;
    public Stoc[] getStocFarmacie() throws RemoteException;
    
}
