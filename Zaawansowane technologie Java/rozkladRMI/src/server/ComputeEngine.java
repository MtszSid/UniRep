package server;

import remoteInterface.ComputeInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;



public class ComputeEngine implements ComputeInterface {

    public ComputeEngine(){
        super();
    }

    private final static int POTEGA2 = 21;
    private final static int[] SITO = new int[1 << POTEGA2];

    static{

        SITO[0] = 0;
        SITO[1] = 1;

        for(int i = 2 ; i < (1 << POTEGA2); i++){
            SITO[i] = 0;
        }
        for(int i = 2 ; i < (1 << POTEGA2); i++){
            if(SITO[i] == 0){
                for(int j  = i; j < (1 << POTEGA2); j += i){
                    if(SITO[j] == 0)
                        SITO[j] = i;
                }
            }
        }
    }

    public static boolean czyPierwsza(long x) {
        if(x < 1 << POTEGA2){
            return (SITO[(int)x] == x);
        }
        if(x % 2 == 0) return false;
        double limit = Math.floor(Math.sqrt(x));
        for(long i =3 ; i <= limit; i += 2){
            if(x % i == 0)
                return false;
        }
        return true;
    }

    @Override
    public LinkedList<Long> primeFactorization(long x) throws RemoteException{

        LinkedList<Long> czynniki = new LinkedList<Long>();

        if(x == Long.MIN_VALUE){
            czynniki.add(-1L);
            czynniki.add(2L);
            x /= 2;
            x *= -1;

        }
        else if(x == -1){
            czynniki.add(-1L);
            return czynniki;
        }

        if(x < 0){
            x *= -1;
            czynniki.add(-1L);
        }

        if(czyPierwsza(x)){
            czynniki.add(x);
            return czynniki;
        }

        while(x >= (1 << POTEGA2) && x % 2 == 0){
            czynniki.add(2L);
            x /= 2;
        }

        double limit = Math.floor(Math.sqrt(x));
        long dzielnik = 3;

        while(x >= (1 << POTEGA2) && dzielnik <= limit){
            if(x % dzielnik == 0){
                czynniki.add(dzielnik);
                x /= dzielnik;
            }
            else{
                dzielnik += 1;
            }
        }

        if(x >= (1 << POTEGA2)){
            czynniki.add(x);
            return czynniki;
        }

        while(x > 1){
            czynniki.add((long)SITO[(int)x]);
            x /= SITO[(int)x];
        }

        return czynniki;
    }

    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy","file:./src/server.policy");
        }
        try {
            String name = "Compute";
            ComputeInterface engine = new ComputeEngine();
            ComputeInterface stub = (ComputeInterface) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(name, stub);
            System.err.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
