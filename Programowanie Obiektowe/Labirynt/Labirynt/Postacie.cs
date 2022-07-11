#pragma warning disable CA1303
using System;


namespace Labirynt
{
    abstract class Postac
    {
        public Przedmiot Skarb { get; set; }
        static Random rnd = new Random();
        internal int Hp;
        internal int Sila;
        internal int Ochrona;
        public int Doswiadczenie { set; get; }

        public bool CzyZywy()
        {
            return Hp > 0;
        }

        public int SilaCiosu()
        {
            return Sila + rnd.Next(-Sila / 2, Sila);
        }

        public void PrzyjmijCios(int cios)
        {
            int sila = cios - rnd.Next(0, Ochrona / 2);
            if (sila > 0)
                Hp -= sila;
        }

        public abstract void Zwyciestwo(Postac postac);
        
    }
    class Gracz : Postac
    {
        int HpMax;
        public Przedmiot[] Ekwipunek { get; }
        Tuple<int, int> Pozycja;
        short LiczbaPrzedmiotowWEkwipunku;
        
        public Gracz()
        {
            Skarb = null;
            LiczbaPrzedmiotowWEkwipunku = 9;
            Ekwipunek = new Przedmiot[LiczbaPrzedmiotowWEkwipunku];
            Pozycja = new Tuple<int, int>(0, 0);
            HpMax = 150;
            Hp = HpMax;
            Sila = 20;
            Ochrona = 10;
            Doswiadczenie = 0;
        }

        public Tuple <int, int> GdzieJest()
        {
            return Pozycja;
        }

        public void OpisPrzedmiotu (int index)
        {
            if (Ekwipunek[index] == null)
                Console.WriteLine("Pusty slot w ekwipunku");
            else
                Ekwipunek[index].Opis();
        }

        public void Przejdz(Tuple<int, int> krok)
        {
            Pozycja = Tuple.Create(Pozycja.Item1 + krok.Item1, Pozycja.Item2 + krok.Item2);
        }

        public void CoJestWEkwipunku()
        {
            Console.WriteLine("Zawartosc ekwipunku:");
            for (int i = 0; i < LiczbaPrzedmiotowWEkwipunku; i++) 
            {
                Console.WriteLine("{0} -- {1}", i, (Ekwipunek[i] == null ? "(puste)" : Ekwipunek[i].Nazwa));
            }
        }

        public Przedmiot WyjmijZEkwipunku(int slot)
        {
            if (slot >= LiczbaPrzedmiotowWEkwipunku)
                return null;
            Przedmiot przedmiot = Ekwipunek[slot];
            Ekwipunek[slot] = null;
            return przedmiot;
        }

        public Przedmiot KradziezPrzedmiotu()
        {
            int slotWEkwipunku = 10;
            for (int i = 0; i < LiczbaPrzedmiotowWEkwipunku; i++)
            {
                if (slotWEkwipunku == 10 && Ekwipunek[i] != null)
                    slotWEkwipunku = i;
                if(Ekwipunek[i] != null && (Ekwipunek[i] is Klucz || Ekwipunek[i].Nazwa == "Kasa"))
                {
                    slotWEkwipunku = i;
                    break;
                }
            }
            if (slotWEkwipunku == 10)
                return null;
            else
                Console.WriteLine("Zostałeś okradziony.\nUkradziono: {0}", Ekwipunek[slotWEkwipunku].Nazwa);
                return WyjmijZEkwipunku(slotWEkwipunku);
        }

        public void Uzyj(UzywalnyPrzedmiot przedmiot)
        {
            HpMax += przedmiot.DodatkoweMaxHp;
            Hp += przedmiot.DodatkoweHp;
            if (Hp > HpMax)
                Hp = HpMax;
            Ochrona += przedmiot.DodatkowaOchrona;
            Sila += przedmiot.DodatkowaSila;
            Doswiadczenie += przedmiot.Xp;
        }

        public void UmiescWEkwipunku (int index, Przedmiot przedmiot)
        {
            if (Ekwipunek[index] != null)
                throw new Exception("W ekwipunku w miejscu {index} znajduje się inny przedmiot");
            Ekwipunek[index] = przedmiot;
        }

        public void UmiescWEkwipunku(Przedmiot przedmiot)
        {
            if (przedmiot == null)
                return;
            for (int i = 0; i < LiczbaPrzedmiotowWEkwipunku; i++)
            {
                if (Ekwipunek[i] == null)
                {
                    Ekwipunek[i] = przedmiot;
                    Console.WriteLine("W ekwipunku umieszczono przedmiot:");
                    przedmiot.Opis();
                    return;
                }
            }
            throw new Exception("Ekwipunek jest pełny");
        }

        override public void Zwyciestwo(Postac postac)
        {
            if(postac.Skarb != null)
            {
                try
                {
                    UmiescWEkwipunku(postac.Skarb);
                }
                catch (Exception)
                {
                    if (postac.Skarb is Klucz)
                    {
                        foreach (Przedmiot p in Ekwipunek)
                        {
                            if (p is UzywalnyPrzedmiot)
                            {
                                Uzyj((UzywalnyPrzedmiot)p);
                                break;
                            }
                        }
                        UmiescWEkwipunku(postac.Skarb);
                    }
                    else
                    {
                        Uzyj((UzywalnyPrzedmiot)postac.Skarb);
                    }
                }
            }
        }

        public void Stan()
        {
            Console.WriteLine("Stan gracza");
            Console.WriteLine("{0} / {1} hp, {2} ochrona, {3} siła, {4} doświadczenie\n", Hp, HpMax, Ochrona, Sila, Doswiadczenie);
            CoJestWEkwipunku();
        }
    }

    class Przeciwnik : Postac
    {
        internal static Tuple<string, string, int, int, int, int, int> DomyslnySkarb = new Tuple<string, string, int, int, int, int, int> ("Kasa", "Sakiewka", 0, 0, 0, 0, 15);
        
        internal Przeciwnik() { }
        public Przeciwnik(int hp, int sila, int ochrona, int doswiadczenie, Przedmiot skarb)
        {
            Hp = hp;
            Sila = sila;
            Ochrona = ochrona;
            Doswiadczenie = doswiadczenie;
            Skarb = skarb;
            if(skarb == null)
            {
                Skarb = new UzywalnyPrzedmiot(DomyslnySkarb);
            }
        }

        public override void Zwyciestwo(Postac postac)
        {
            if(postac is Gracz)
            {
                KoniecGry koniec = KoniecGry.StanGry();
                koniec.GameOver = true;
                koniec.GraczUmarl = true;
            }
        }
    }

    class Zlodziej : Przeciwnik
    {
        public Zlodziej(int hp, int sila, int ochrona, int doswiadczenie)
        {
            Hp = hp;
            Sila = sila;
           
            Ochrona = ochrona;
            Skarb = null;
            Doswiadczenie = doswiadczenie;
        }
        public void Kradziez(Gracz gracz)
        {
            Skarb = gracz.KradziezPrzedmiotu();
        }
    }
}