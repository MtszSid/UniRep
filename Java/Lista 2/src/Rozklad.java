
public class Rozklad {
    public static void main(String[] args) {

        if(args.length < 1){
            System.err.println("Użycie: java Rozklad [argumenty]");
        }

        for (String s: args) {
            long[] rozklad = LiczbyPierwsze.naCzynnikiPierwsze(Long.parseLong(s));
            System.out.print(s + " = ");
            for (int i = 0; i < rozklad.length; i++) {
                System.out.print(rozklad[i]);
                if(i < rozklad.length - 1 && rozklad[i + 1] != 0)
                    System.out.print( " * ");
                else
                    break;
            }
            System.out.println();
        }
    }
}

