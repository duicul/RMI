import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComputeVal {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
            int t = comp.execute(20);
            Compute aux=comp.getCompute();
            System.out.println(t);
            System.out.println(aux.execute(100));

         } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
}
