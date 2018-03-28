import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    public int execute(int x) throws RemoteException;
    public Data getData() throws RemoteException;
}
