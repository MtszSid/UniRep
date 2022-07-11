package client;

public class Client {

    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy","file:./src/server.policy");
        }
        new MainFrame();
    }
}
/*
*  9223372036854775783
*  -9223372036854775808
* */
