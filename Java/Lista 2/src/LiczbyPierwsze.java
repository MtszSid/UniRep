import java.lang.Math;

public final class LiczbyPierwsze {
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

    // potrzebny jest statyczny blok inicjalizacyjny dla sita
    // [0, 1, 2, 3, 2, 5, 2, 7, 2, 3, 2, 11, … ]

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

    public static long[] naCzynnikiPierwsze(long x) {
        // treść metody

        long[] czynniki = new long[65];
        int index = 0;
        for (int i = 0; i < 65 ; i++){
            czynniki[i] = 0;
        }

        if(x == Long.MIN_VALUE){
            czynniki[0] = -1L;
            czynniki[1] = 2L;
            x /= 2;
            x *= -1;
            index = 2;
        }
        else if(x == -1){
            czynniki[0] = -1L;
            return czynniki;
        }

        if(x < 0){
            x *= -1;
            czynniki[0] = -1L;
            index = 1;
        }

        if(czyPierwsza(x)){
            czynniki[index] = x;
            return czynniki;
        }

        while(x >= (1 << POTEGA2) && x % 2 == 0){
            czynniki[index] = 2;
            index++ ;
            x /= 2;
        }

        double limit = Math.floor(Math.sqrt(x));
        long dzielnik = 3;

        while(x >= (1 << POTEGA2) && dzielnik <= limit){
            if(x % dzielnik == 0){
                czynniki[index] = dzielnik;
                index += 1;
                x /= dzielnik;
            }
            else{
                dzielnik += 1;
            }
        }

        if(x >= (1 << POTEGA2)){
            czynniki[index] = x;
            return czynniki;
        }

        while(x > 1){
            czynniki[index] = SITO[(int)x];
            index += 1;
            x /= SITO[(int)x];
        }

        return czynniki;
    }
}
