package interfacesch;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.*;

public interface Produsinterch extends Remote{
	public void changeNume(int pid,String nume) throws RemoteException;
	public void changeNume(Produs p,String nume) throws RemoteException;
	public void changeClasa(int pid,String clasa) throws RemoteException;
	public void changeClasa(Produs p,String clasa) throws RemoteException;

}
