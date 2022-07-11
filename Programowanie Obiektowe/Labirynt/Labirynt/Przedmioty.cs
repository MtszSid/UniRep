using System;
#pragma warning disable CA1303    

namespace Labirynt
{
    abstract class Przedmiot
    {
        internal string Nazwa { get; set; }

        abstract public void Opis();
    }

    class Klucz : Przedmiot
    {
        short NrDrzwi;
        override public void Opis()
        {
            Console.WriteLine("Klucz otwierajacy drzwi nr {0}", NrDrzwi);
        }

        public Klucz(short nr)
        {
            Nazwa = "Klucz";
            NrDrzwi = nr;
        }
        public bool OtwieraDrzwiNr(int nr)
        {
            return NrDrzwi == nr;
        }
    }

    class UzywalnyPrzedmiot : Przedmiot
    {
        public int DodatkoweHp { get; }
        public int DodatkoweMaxHp { get; }
        public int DodatkowaOchrona { get; }
        public int DodatkowaSila { get; }
        public int Xp { get; }
        string DodatkoweInfo;

        public UzywalnyPrzedmiot(string nazwa, string info, int hp, int maxhp, int ochrona, int sila, int xp)
        {
            Nazwa = nazwa;
            DodatkoweHp = hp;
            DodatkoweMaxHp = maxhp;
            DodatkowaOchrona = ochrona;
            DodatkowaSila = sila;
            Xp = xp;
            DodatkoweInfo = info;
        }

        public UzywalnyPrzedmiot(Tuple<string, string, int, int, int, int, int> przedmiot)
        {
            Nazwa = przedmiot.Item1;
            DodatkoweHp = przedmiot.Item3;
            DodatkoweMaxHp = przedmiot.Item4;
            DodatkowaOchrona = przedmiot.Item5;
            DodatkowaSila = przedmiot.Item6;
            Xp = przedmiot.Item7;
            DodatkoweInfo = przedmiot.Item2;
        }
        public override void Opis()
        {
            Console.WriteLine("{0}", Nazwa);
            Console.WriteLine("{0}",DodatkoweInfo);
            Console.WriteLine("Efekty: {0} hp, {1} max hp, {2} ochrona, {3} siła, {4} doświadczenie", 
                DodatkoweHp, DodatkoweMaxHp, DodatkowaOchrona, DodatkowaSila, Xp);
        }
       
    }
}

