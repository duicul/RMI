package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import classes.*;


public interface Produsinter extends Remote {
    public List<Stoc> getStocFarm(int FID) throws RemoteException;
    public List<Stoc> getStocFarm(Farmacie f) throws RemoteException;
    public List<Stoc> getStoc() throws RemoteException;
    public List<Stoc> getStocProd(Produs p) throws RemoteException;
    public List<Stoc> getStocProd(int PID) throws RemoteException;
    public Stoc getStocProd(int PID,int FID) throws RemoteException;
    public Stoc getStocProd(Produs p,Farmacie f) throws RemoteException;
}
