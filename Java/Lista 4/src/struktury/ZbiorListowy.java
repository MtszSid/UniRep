package struktury;

import java.util.Objects;

public class ZbiorListowy implements Zbior, Cloneable {

    private class Wezel {
        private Para para;
        private Wezel nast;

        public Wezel(){
            para = null;
            nast = null;
        }

        public Wezel(Para p){
            para = p;
            nast = null;
        }
    }


    private Wezel lista;
    private int length;


    public ZbiorListowy(){
        lista = null;
        length = 0;
    }

    @Override
    public Para szukaj(String k) {
        Para para = null;
        Wezel wezel = lista;

        for(int i = 0; i < length; i++){
            if(Objects.equals(wezel.para.getKlucz(), k)){
                para = wezel.para;
                break;
            }
            wezel = wezel.nast;
        }

        return para;
    }

    @Override
    public void wstaw(Para p) {
        Wezel w = lista;

        for(int i = 0; i < length; i++){
            if(Objects.equals(w.para.getKlucz(), p.getKlucz())){
                w.para.setWartosc(p.getWartosc());
                return;
            }
            w = w.nast;
        }

        w = new Wezel(p);
        w.nast = lista;
        lista = w;
        length++;
    }

    @Override
    public void usun(String k) {
        if(Objects.equals(lista.para.getKlucz(), k)){
            lista = lista.nast;
            length--;
            return;
        }

        Wezel wezel = lista;

        for(int i = 0; i < length - 1; i++){
            if(Objects.equals(wezel.nast.para.getKlucz(), k)){
                wezel.nast = wezel.nast.nast;
                length--;
                return;
            }
            wezel = wezel.nast;
        }
    }

    @Override
    public void czysc() {
        lista = null;
        length = 0;
    }

    @Override
    public int ile() {
        return length;
    }

    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
