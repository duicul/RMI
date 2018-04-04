package interfacesch;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.*;

public interface Stocinterch extends Remote{
 public void changeQuantity(int sid,int quantity) throws RemoteException;
 public void changeQuantity(Stoc s,int quantity) throws RemoteException;
 public void changeValue(int sid,int value) throws RemoteException;
 public void changeValue(Stoc s,int value) throws RemoteException;
}
