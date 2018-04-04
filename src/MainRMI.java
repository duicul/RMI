import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import classes.*;
import classes.Produs;
import classes.Stoc;
import interfaces.*;
import implement.*;

public class MainRMI {
    public static void main(String args[]) {
        List<Farmacie> l;
        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        System.out.println(args.length);
        String host,dba,user,pass;
        if(args.length<2)
        {System.out.println("Usage <host> <dbase> <user> <pass>");
        return;}
        /*for(String s:args)
        	System.out.println(s);*/
        host=args[0];
        dba=args[1];
        user=(args.length<3)?"":args[2];
        pass=(args.length<4)?"":args[3];
        try {
            String name1 = "DB-"+dba;
            DBManageinter db = new DBManageReal(host,dba,user,pass);
            DBManageinter db_stub=(DBManageinter) UnicastRemoteObject.exportObject(db, 0);
            String name2 = "Produs-"+dba;
            Produsinter prod = new ProdusReal(host,dba,user,pass);
            Produsinter prod_stub =(Produsinter) UnicastRemoteObject.exportObject(prod, 0);
            String name3 = "Stoc-"+dba;
            Stocinter st = new StocReal(host,dba,user,pass);
            Stocinter st_stub =(Stocinter) UnicastRemoteObject.exportObject(st, 0);
            String name4 = "Farmacie-"+dba;
            Farmacieinter fa = new FarmacieReal(host,dba,user,pass);
            Farmacieinter fa_stub =(Farmacieinter) UnicastRemoteObject.exportObject(fa, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name1, db_stub);
            System.out.println("DB bound");
            registry.rebind(name2, prod_stub);
            System.out.println("Produs bound");
            registry.rebind(name3, st_stub);
            System.out.println("Stoc bound");
            registry.rebind(name4, fa_stub);
            System.out.println("Farmacie bound");
            l=db.getFarmacii();
    	for(Farmacie f:l)
    	{System.out.println(f.getHost()+" "+f.getID()+"  "+f.getNume()+" "+f.getOras()+" "+f.getAdresa()+" "+f.getProgram()+" "+f.getNrtel());
    	for(Produs p:fa.getProductsFarmacie(f))
    		System.out.println(p.getID()+" "+p.getNume()+" "+p.getClasa());
    	for(Stoc s:fa.getStocFarmacie(f))
    	   System.out.println(s.getCantitate()+" "+s.getPret());
    	for(Stoc s:prod.getStoc("Amoxicilina"))
    	System.out.println(s.getCantitate()+" "+s.getPret());}
      } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
