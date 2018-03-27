import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DBManage extends Remote {
    public Farmacie getFarmacieId(int FID) throws RemoteException;
    public Farmacie getFarmacieNume(String nume) throws RemoteException;
    public Stoc getStocID(int SID) throws RemoteException;
    public Produs getProductID(int PID) throws RemoteException;
    public Produs getProductName(String name) throws RemoteException;
    public Produs getProductClass(String clasa) throws RemoteException;
}
