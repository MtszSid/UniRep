#pragma warning disable CA1303 // Do not pass literals as localized parameters
using System;
using System.Collections.Generic;

namespace Labirynt
{
    class Komnata
    {
        bool Osiagalna;                             
        List<Przejscie> Przejscia;                
        Przedmiot[] Skarby;
        Przeciwnik Bandyta;
        public Komnata()
        {
            Osiagalna = false;
            Przejscia = new List<Przejscie>();
            Skarby = new Przedmiot[5];
            Bandyta = null;
        }

        public bool CzyOsiagalna()
        {
            return Osiagalna;
        }

        public void JestOsiagalna()
        {
            Osiagalna = true;
        }

        public void NowePrzejscie(string kierunekPrzejscia)
        {
            Przejscia.Add(new Przejscie(kierunekPrzejscia));
        }

        public void NoweWrota(string kierunekPrzejscia, short nrKlucza)
        {
            Przejscia.Add(new Wrota(kierunekPrzejscia, nrKlucza));
        }

        public Przeciwnik GetBandyta()
        {
            Przeciwnik P = Bandyta;
            Bandyta = null;
            return P;
        }

        public void UstawWyjscie(string kierunekPrzejscia, short nrKlucza)
        {
            Przejscia.Add(new Wyjscie(kierunekPrzejscia, nrKlucza));
        }

        public bool CzyMoznaPrzejsc(string kierunek)
        {
            return Przejscia.Exists(T => T.KierunekPrzejscia() == kierunek);
        }
        public void RozejrzyjSie(Gracz gracz)
        {
            Console.WriteLine("W komnacjie znajdują sie następujące przejscia:");
            foreach (Przejscie T in Przejscia)
            {
                T.opis();
            }

            Console.WriteLine("\nPrzedmioty w komnaie:");
            for (int i = 0; i < 5; i++)
            {
                if (Skarby[i] != null)
                {
                    Console.WriteLine("{0} -- {1}", i, Skarby[i].Nazwa);
                }
            }

            if(Bandyta != null)
            {
                if(Bandyta is Zlodziej)
                {
                    Console.WriteLine("W komnacie znajduje się złodziej.");
                    ((Zlodziej)Bandyta).Kradziez(gracz);
                }
                else
                {
                    Console.WriteLine("W komnacie znajduje się przestępca.");
                }
            }
        }

        public List<Przejscie> PrzejsciaWKomnacie()
        {
            return Przejscia;
        }

        public void UmiescPrzedmiot(Przedmiot skarb)
        {
            for (int i = 0; i < 5; i++)
            {
                if (Skarby[i] == null)
                {
                    Skarby[i] = skarb;
                    break;
                }
            }
        }
        public Przedmiot PodniesPrzedmiot(int index)
        {
            if (Skarby[index] == null)
                throw new Exception("Brak przedmiotu w slocie {index}");
            Przedmiot skarb = Skarby[index];
            Skarby[index] = null;
            return skarb;
        }
        
        public bool CzyWKomnacieJestPrzestępca()
        {
            return (Bandyta != null);
        }

        public void NowyBandyta(Przeciwnik przeciwnik)
        {
            Bandyta = przeciwnik;
        }

        public bool OtworzWrota(Klucz klucz)
        {
            foreach (Przejscie P in Przejscia)
            {
                if (P is Wrota)
                    return ((Wrota)P).otworz(klucz);
            }
            return false;
        }

    }

    public class Przejscie
    {
        internal string Kierunek;
        internal bool OtwartePrzejscie;
        protected Przejscie() { }

        public Przejscie(string kierunekPrzejscia)
        {
            Kierunek = kierunekPrzejscia;
            OtwartePrzejscie = true;
        }
        public string KierunekPrzejscia()
        {
            return Kierunek;
        }
        virtual public void opis()
        {
            Console.WriteLine("Przejscie w kierunku {0}", Kierunek);
        }
    }

    class Wrota : Przejscie
    {
        internal short NrKluczaOtwierajacegoDrzwi;
        public Wrota(string kierunekPrzejscia, short nrKlucza)
        {
            Kierunek = kierunekPrzejscia;
            NrKluczaOtwierajacegoDrzwi = nrKlucza;
            OtwartePrzejscie = false;
        }
        public virtual bool otworz(Klucz klucz)
        {
            if (klucz.OtwieraDrzwiNr(NrKluczaOtwierajacegoDrzwi))
            {
                OtwartePrzejscie = true;
                return true;
            }
            return false;
        }
        public Wrota() { }

        override public void opis()
        {
            if (OtwartePrzejscie)
                Console.WriteLine("Przejscie w kierunku {0}", Kierunek);
            else
                Console.WriteLine("Zamkniete przejscie w kierunku {0}, mozna je otworzyć kluczem nr {1}", Kierunek, NrKluczaOtwierajacegoDrzwi);
        }
    }

    class Wyjscie : Wrota
    {
        
        public Wyjscie(string kierunekPrzejscia, short nrKlucza)
        {
            Kierunek = kierunekPrzejscia;
            NrKluczaOtwierajacegoDrzwi = nrKlucza;
            OtwartePrzejscie = false;
        }
        override public bool otworz(Klucz klucz)
        {
            if (klucz.OtwieraDrzwiNr(NrKluczaOtwierajacegoDrzwi))
            {
                KoniecGry Koniec = KoniecGry.StanGry();
                OtwartePrzejscie = true;
                Koniec.GameOver = true;
                Koniec.OsiagnietoWyjscie = true;
                return true;
            }
            return false;
        }
    }
}