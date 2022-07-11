package remoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ComputeInterface extends Remote {
    public LinkedList<Long> primeFactorization (long x)
            throws RemoteException;
}
