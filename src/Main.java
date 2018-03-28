import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import classes.*;
import classes.Produs;
import classes.Stoc;
import interfaces.*;
import implement.*;
public class Main {
    public static void main(String args[]) {
        List<Farmacie> l;
        try {
    	DBManageinter ai=new DBManageReal("localhost","Test","root","");
    	Stocinter si=new StocReal("localhost","Test","root","");
    	Produsinter pi=new ProdusReal("localhost","Test","root","");
    	Farmacieinter fi=new FarmacieReal("localhost","Test","root","");
    	l=ai.getFarmacii();
    	for(Farmacie f:l)
    	{System.out.println(f.getHost()+" "+f.getID()+"  "+f.getNume()+" "+f.getOras()+" "+f.getAdresa()+" "+f.getProgram()+" "+f.getNrtel());
    	for(Produs p:fi.getProductsFarmacie(f))
    		System.out.println(p.getID()+" "+p.getNume()+" "+p.getClasa());
    	for(Stoc s:fi.getStocFarmacie(f))
    	   System.out.println(s.getCantitate()+" "+s.getPret());
    	for(Stoc s:pi.getStoc("Amoxicilina"))
    	System.out.println(s.getCantitate()+" "+s.getPret());	
    	
    	}
        }catch(Exception e)
        {e.printStackTrace();}
    	/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            DBManageinter engine = new DBManageReal();
            Compute stub =(Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }*/
    }
}
