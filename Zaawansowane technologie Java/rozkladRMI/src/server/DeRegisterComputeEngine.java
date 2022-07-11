package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DeRegisterComputeEngine {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy","file:./src/server.policy");
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry();
            registry.unbind(name);
            System.out.println("ComputeEngine unbound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
