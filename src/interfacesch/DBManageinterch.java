package interfacesch;
import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.*;

public interface DBManageinterch extends Remote{
public void addFarmacie(String nume,String adresa,String nrtel,String oras,String program) throws RemoteException;
public void addProdus(String nume,String clasa) throws RemoteException;
public void addStoc(int fid,int pid,int cantitate,int val) throws RemoteException;
public void addStoc(String fNume,String pNume,int cantitate,int val) throws RemoteException;
public void addStoc(Farmacie f,Produs p,int cantitate,int val) throws RemoteException;
public void deleteStoc(Farmacie f,Produs p) throws RemoteException;
public void deleteStoc(int fid,int pid) throws RemoteException;
public void deleteStoc(String f,String p) throws RemoteException;
public void deleteStoc(Stoc s) throws RemoteException;
public void deleteStoc(int sid) throws RemoteException;
public void deleteProdus(Produs p) throws RemoteException;
public void deleteProdus(int pid) throws RemoteException;
public void deleteProdus(String p) throws RemoteException;
public void deleteFarmacie(Farmacie f) throws RemoteException;
public void deleteFarmacie(int fid) throws RemoteException;
public void deleteFarmacie(String f) throws RemoteException;
}
