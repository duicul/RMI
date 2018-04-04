import java.util.List;

import classes.*;
import classes.Produs;
import classes.Stoc;
import interfaces.*;
import interfacesch.DBManageinterch;
import implement.*;
import implementch.DBManagech;
public class Main {
    public static void main(String args[]) {
        List<Farmacie> l;
        try {
    	DBManageinter ai=new DBManageReal("localhost","Test","root","");
    	Stocinter si=new StocReal("localhost","Test","root","");
    	Produsinter pi=new ProdusReal("localhost","Test","root","");
    	Farmacieinter fi=new FarmacieReal("localhost","Test","root","");
    	DBManageinterch mic=new DBManagech("localhost","Test","root",""); 
    	mic.addFarmacie("Ceva","Panselute 7","0778909787", "Temesvarin ", "18:00-22:00");
    	mic.addProdus("SET", "CLASS");
    	mic.addStoc(ai.getFarmacieNume("Ceva"), ai.getProductName("SET"), 112, 90);
    	l=ai.getFarmacii();
    	for(Farmacie f:l)
    	{System.out.println(f.getHost()+" "+f.getID()+"  "+f.getNume()+" "+f.getOras()+" "+f.getAdresa()+" "+f.getProgram()+" "+f.getNrtel());
    	for(Produs p:fi.getProductsFarmacie(f))
    		System.out.println(p.getID()+" "+p.getNume()+" "+p.getClasa());
    	for(Stoc s:fi.getStocFarmacie(f))
    	   System.out.println(s.getCantitate()+" "+s.getPret());    	
    	}
        }catch(Exception e)
        {e.printStackTrace();}
    	}
}
