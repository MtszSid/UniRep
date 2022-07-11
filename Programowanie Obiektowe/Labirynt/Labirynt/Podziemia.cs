#pragma warning disable CA1303 // Do not pass literals as localized parameters
#pragma warning disable CA1814 // Prefer multidimensional arrays over jagged
#pragma warning disable CA1305 // Specify IFormatProvider

using System;
using System.Collections.Generic;
using System.Linq;

namespace Labirynt
{
    class Podziemia
    {
        private Random rnd;                                                                                         // Do generowania elementów w losowych miejscach
        private Gracz Gracz;
        private int bok;                                                                                            // wymiary Labiryntu = bok x bok
        private Komnata[,] Labirynt;
        private Arena ArenaDoWalk;                                                                                  // Służy do walki omiędzy przeciwnikiem a graczem
        private KoniecGry KoniecRozgrywki;                                                                          // Reprezentuje stan rozgrywki
        static Dictionary<string, Tuple<int, int>> KomnataWKierunku = new Dictionary<string, Tuple<int, int>>()     // Służy do wyznaczania współżędnych komnaty w zadanym kierunku
            {
                {"N", new Tuple<int, int>(0, -1) },
                {"E", new Tuple<int, int>(1,  0) },
                {"S", new Tuple<int, int>(0,  1) },
                {"W", new Tuple<int, int>(-1, 0) }
            };
        static private List<string> Kierunki = new List<string>() {"N", "E", "S", "W" };
        static private Dictionary<string, string> kierunkiPrzeciwne = new Dictionary<string, string>()
            {
                {"N", "S" },
                {"E", "W" },
                {"S", "N" },
                {"W", "E" }
            };

        public Podziemia()
        {
            KoniecRozgrywki = KoniecGry.StanGry();
            rnd = new Random();
            bok = 16; 
            Labirynt = new Komnata[bok, bok];
            ArenaDoWalk = new Arena();
            LinkedList<Tuple<int, int>> komnatyDoPolaczenia = new LinkedList<Tuple<int, int>>();                    // Komnaty, z których mają wychodzić nowe przejścia
            List<Tuple<int, int>> nieOsiagalne = new List<Tuple<int, int>>();                                       // Komnaty, które nie są jeszcze osiągalne
            nieOsiagalne.Capacity = bok * bok;
            Gracz = new Gracz();
            for(int i = 0; i < bok; i++)
            {
                for (int j = 0; j < bok; j++)
                {
                    Labirynt[i, j] = new Komnata();
                    nieOsiagalne.Add(new Tuple<int, int>(i, j));
                }
            }

            List<Tuple<int, int>> polaPoczatkowe = new List<Tuple<int, int>>()
                {
                    Tuple.Create(0, 0),
                    Tuple.Create(0, bok/2),
                    Tuple.Create(bok/2, bok/2),
                    Tuple.Create(bok/2, 0)
                };

            foreach (Tuple<int, int> T in polaPoczatkowe)
            {
                Labirynt[T.Item1, T.Item2].JestOsiagalna();
                komnatyDoPolaczenia.AddLast(T);
                nieOsiagalne.Remove(T);
            }

            Tuple<int, int> wybranaKomnata;
            List<string> nowePrzejsciaDoKomnaty;
            Tuple<int, int> noweWspolrzedne;
            int index;
            string kierunekDolaczania;

            while (nieOsiagalne.Count != 0)                                                                         // Generowanie przejść
            {
                if (komnatyDoPolaczenia.Count == 0)
                {
                    index = 0;
                    kierunekDolaczania = "O";
                    while(kierunekDolaczania == "O")
                    {
                        kierunekDolaczania = KierunekDolaczeniaKomnaty(nieOsiagalne[index].Item1, nieOsiagalne[index].Item2);
                        index++;
                    }
                    index--;
                    wybranaKomnata = nieOsiagalne[index];
                    Labirynt[wybranaKomnata.Item1, wybranaKomnata.Item2].NowePrzejscie(kierunekDolaczania);
                    noweWspolrzedne = new Tuple<int, int>(wybranaKomnata.Item1 + KomnataWKierunku[kierunekDolaczania].Item1, wybranaKomnata.Item2 + KomnataWKierunku[kierunekDolaczania].Item2);
                    Labirynt[noweWspolrzedne.Item1, noweWspolrzedne.Item2].NowePrzejscie(kierunkiPrzeciwne[kierunekDolaczania]);
                    Labirynt[wybranaKomnata.Item1, wybranaKomnata.Item2].JestOsiagalna();
                    nieOsiagalne.Remove(wybranaKomnata);
                    komnatyDoPolaczenia.AddLast(wybranaKomnata);
                }
                else
                {
                    wybranaKomnata = komnatyDoPolaczenia.First.Value;
                    nowePrzejsciaDoKomnaty = nowe_przejscia(wybranaKomnata.Item1, wybranaKomnata.Item2);
                    foreach (string kierunek in nowePrzejsciaDoKomnaty)
                    {
                        Labirynt[wybranaKomnata.Item1, wybranaKomnata.Item2].NowePrzejscie(kierunek);
                        noweWspolrzedne = new Tuple<int, int>(wybranaKomnata.Item1 + KomnataWKierunku[kierunek].Item1, wybranaKomnata.Item2 + KomnataWKierunku[kierunek].Item2);
                        Labirynt[noweWspolrzedne.Item1, noweWspolrzedne.Item2].NowePrzejscie(kierunkiPrzeciwne[kierunek]);
                        Labirynt[noweWspolrzedne.Item1, noweWspolrzedne.Item2].JestOsiagalna();
                        komnatyDoPolaczenia.AddLast(noweWspolrzedne);
                        nieOsiagalne.Remove(noweWspolrzedne);
                    }
                    komnatyDoPolaczenia.Remove(wybranaKomnata);
                }
            }
            RozmiescKluczeIWrota();

            string przedmioty = System.IO.File.ReadAllText("przedmioty.csv");                                       // Grnerowanie przedmiotow
            string[] wiersze = przedmioty.Split("\r",
                StringSplitOptions.RemoveEmptyEntries);
            string[] przedmiot;
            foreach(string T in wiersze)
            {
                if (T == "\n")
                    continue;
                przedmiot = T.Split(";");
                for(int i = 0; i < int.Parse(przedmiot[0]); i++)
                {
                    Labirynt[rnd.Next(0, bok), rnd.Next(0, bok)].UmiescPrzedmiot(
                        new UzywalnyPrzedmiot(przedmiot[1], przedmiot[2],
                        int.Parse(przedmiot[3]), int.Parse(przedmiot[4]),
                        int.Parse(przedmiot[5]), int.Parse(przedmiot[6]),
                        int.Parse(przedmiot[7])));
                }
            }

            int x, y;                                                                                               //Generowanie przeciwników
            for(int i = 0; i <= 7; i++)
            {
                x = rnd.Next(0, bok);
                y = rnd.Next(0, bok);
                if (!Labirynt[x, y].CzyWKomnacieJestPrzestępca())
                    Labirynt[x, y].NowyBandyta(new Przeciwnik(25, 20, 20, 40, null));

            }
            for (int i = 0; i <= 7; i++)
            {
                x = rnd.Next(0, bok);
                y = rnd.Next(0, bok);
                if (!Labirynt[x, y].CzyWKomnacieJestPrzestępca())
                    Labirynt[x, y].NowyBandyta(new Zlodziej(20, 20, 20, 40));

            }
        }

        private List<string> nowe_przejscia(int x, int y)                                                           // Lista kierunków dołączanych nowych komnat
        {
            List<string> przejscia =new List<string> {"N", "E", "S", "W"};
            int rozmiar_sektora = bok / 2;
            int max_przejsc = 4;
            if (x % rozmiar_sektora == 0 || Labirynt[x - 1, y].CzyOsiagalna())
            {
                max_przejsc--;
                przejscia.Remove("W");
            } 

            if (x % rozmiar_sektora == rozmiar_sektora - 1 || Labirynt[x + 1, y].CzyOsiagalna())
            {
                max_przejsc--;
                przejscia.Remove("E");
            }

            if (y % rozmiar_sektora == 0 || Labirynt[x, y - 1].CzyOsiagalna())
            {
                max_przejsc--;
                przejscia.Remove("N");
            }
            
            if (y % rozmiar_sektora == rozmiar_sektora - 1 || Labirynt[x, y + 1].CzyOsiagalna())
            {
                max_przejsc--;
                przejscia.Remove("S");
            }

            int liczba_przejsc = rnd.Next(0, max_przejsc);
            for (int i = liczba_przejsc; i < max_przejsc; i++)
                przejscia.RemoveAt(rnd.Next(przejscia.Count));
            return przejscia;
        }

        private string KierunekDolaczeniaKomnaty(int x, int y)                                                      // Gdy trzeba dołączyć komnate nieosiągalną
        {
            List<string> przejscia = new List<string> ();
            int rozmiar_sektora = bok / 2;
            int max_przejsc = 0;

            if (x % rozmiar_sektora != 0 && Labirynt[x - 1, y].CzyOsiagalna())
            {
                max_przejsc++;
                przejscia.Add("W");
            }

            if (x % rozmiar_sektora != rozmiar_sektora - 1 && Labirynt[x + 1, y].CzyOsiagalna())
            {
                max_przejsc++;
                przejscia.Add("E");
            }

            if (y % rozmiar_sektora != 0 && Labirynt[x, y - 1].CzyOsiagalna())
            {
                max_przejsc++;
                przejscia.Add("N");
            }

            if (y % rozmiar_sektora != rozmiar_sektora - 1 && Labirynt[x, y + 1].CzyOsiagalna())
            {
                max_przejsc++;
                przejscia.Add("S");
            }

            if (max_przejsc == 0)
                return "O";
            return przejscia[rnd.Next(przejscia.Count)];
        }

        private void RozmiescKluczeIWrota()                                                                         // Rozmieszcza klucze, wrota i wyjście 
        {
            int wspRand = rnd.Next(0, bok / 2);
            Labirynt[bok / 2 - 1, wspRand].NoweWrota("E", 1);
            Labirynt[rnd.Next(0, bok/2), rnd.Next(0, bok/2)].UmiescPrzedmiot(new Klucz(1));
            Labirynt[bok / 2, wspRand].NowePrzejscie("W");

            wspRand = rnd.Next(bok / 2, bok);
            Labirynt[wspRand, bok / 2 - 1].NoweWrota("S", 2);
            Labirynt[rnd.Next(bok / 2, bok), rnd.Next(0, bok / 2)].UmiescPrzedmiot(new Klucz(2));
            Labirynt[wspRand, bok / 2].NowePrzejscie("N");

            wspRand = rnd.Next(bok / 2, bok);
            Labirynt[bok / 2, wspRand].NoweWrota("W", 3);
            Labirynt[rnd.Next(bok / 2, bok), rnd.Next(bok / 2, bok)].UmiescPrzedmiot(new Klucz(3));
            Labirynt[bok / 2 - 1, wspRand].NowePrzejscie("E");

            wspRand = rnd.Next(bok / 2, bok);
            Labirynt[0, wspRand].UstawWyjscie("W", 4);
            Labirynt[rnd.Next(0, bok), rnd.Next(0, bok)].NowyBandyta(
                                             new Przeciwnik(80, 14, 13, 41, new Klucz(5)));
        }

        public void interakcja()                                                                                    // Sterowanie
        {
            if (KoniecRozgrywki.GameOver)
            {
                Console.WriteLine("Koniec Gry");
                KoniecRozgrywki.PrzyczynaKoncaGry();
                Console.WriteLine("Zdobytu {0} punktów", Gracz.Doswiadczenie);
                Console.WriteLine("Naciśnij ENTER aby zakończyć.");
                Console.ReadLine();
                Environment.Exit(0);
            }
            else
            {
                Console.WriteLine("\nCo chcesz zrobic:");

                string input = Console.ReadLine();
                List<string> komenda = input.Split(' ').ToList();
                Tuple<int, int> gdzieJestem = Gracz.GdzieJest();
                Console.Clear();
                if(komenda.Count == 2 && komenda[0] == "otwórz")
                {
                    int index;
                    try
                    {
                        index = int.Parse(komenda[1]);
                        if (index < 0 || index >= 9)
                        {
                            Console.WriteLine("{0} -- niewłaściwy argument", index);
                        }
                        else
                        {
                            
                            Przedmiot przedmiot = Gracz.WyjmijZEkwipunku(index);
                            if (przedmiot is Klucz)
                            {
                                if (!Labirynt[gdzieJestem.Item1, gdzieJestem.Item2].OtworzWrota((Klucz)przedmiot))
                                    Gracz.UmiescWEkwipunku(index, przedmiot);
                                
                            }

                            else
                            {
                                Gracz.UmiescWEkwipunku(index, przedmiot);
                                Console.WriteLine("{0} -- niewłaściwy ar gument", komenda[1]);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine("{0} -- niewłaściwy argument", komenda[1]);
                    }

                }
                else if (komenda.Count == 2 && komenda[0] == "przejdź" && Kierunki.Contains(komenda[1]))
                {
                    foreach (Przejscie T in Labirynt[gdzieJestem.Item1, gdzieJestem.Item2].PrzejsciaWKomnacie())
                    {
                        if (T.KierunekPrzejscia() == komenda[1] && T.OtwartePrzejscie)
                        {
                            Gracz.Przejdz(KomnataWKierunku[komenda[1]]);
                            break;
                        }
                    }
                }
                else if (komenda.Count == 2 && komenda[0] == "użyj")
                {
                    int index;
                    try
                    {
                        index = int.Parse(komenda[1]);
                        if(index < 0 || index >= 9)
                        {
                            Console.WriteLine("{0} -- niewłaściwy argument", index);
                        }
                        else
                        {
                            Przedmiot przedmiot = Gracz.WyjmijZEkwipunku(index);
                            if (przedmiot is UzywalnyPrzedmiot)
                                Gracz.Uzyj((UzywalnyPrzedmiot)przedmiot);
                            else
                                Gracz.UmiescWEkwipunku(index, przedmiot);
                        }
                    }
                    catch(Exception e)
                    {
                        Console.WriteLine("{0} -- niewłaściwy argument", komenda[1]);
                    }

                }
                else if (komenda.Count == 2 && komenda[0] == "opis")
                {
                    int index;
                    try
                    {
                        index = int.Parse(komenda[1]);
                        if (index < 0 || index >= 9)
                        {
                            Console.WriteLine("{0} -- niewłaściwy argument", index);
                        }
                        else
                        {
                            Gracz.OpisPrzedmiotu(index);
                        }
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine("{0} -- niewłaściwy argument", komenda[1]);
                    }
                }
                else if (komenda.Count == 2 && komenda[0] == "podnieś")
                {
                    int index;
                    try
                    {
                        index = int.Parse(komenda[1]);
                        if (index < 0 || index >= 5)
                        {
                            Console.WriteLine("{0} -- niewłaściwy argument", index);
                        }
                        else
                        {
                            Gracz.UmiescWEkwipunku(Labirynt[gdzieJestem.Item1, gdzieJestem.Item2].PodniesPrzedmiot(index));
                        }
                    }
                    catch(Exception e)
                    {
                        Console.WriteLine(e.Message);
                    }

                }
                else if(komenda.Count == 1 && komenda[0] == "walcz")
                {
                    Komnata komnata = Labirynt[gdzieJestem.Item1, gdzieJestem.Item2];
                    Przeciwnik przeciwnik = komnata.GetBandyta();
                    if (przeciwnik == null)
                    {
                        Console.WriteLine("W komnacie nie ma przeciwnika");
                    }
                    else
                    {
                        ArenaDoWalk.Walka(Gracz, przeciwnik);
                    }
                }
                else if (komenda.Count == 1 && komenda[0] == "rozejrzyj_się")
                {
                    Labirynt[gdzieJestem.Item1, gdzieJestem.Item2].RozejrzyjSie(Gracz);
                }
                else if (komenda.Count == 1 && komenda[0] == "stan_gracza")
                {
                    Gracz.Stan();
                }
                else if (komenda.Count == 1 && komenda[0] == "ekwipunek")
                {
                    Gracz.CoJestWEkwipunku();
                }
                else if (komenda.Count == 1 && komenda[0] == "exit")
                {
                    Environment.Exit(0);
                }
                else if (komenda.Count == 1 && komenda[0] == "pomoc")
                {
                    Console.WriteLine("ekwipunek -- wyświetla ekwipunek");
                    Console.WriteLine("opis [Nr_Przedmiotu_W_Ekwipunku] -- wyświetla opis przedmiotu");
                    Console.WriteLine("otwórz [Nr_Przedmiotu_W_Ekwipunku] -- otwiera przejśćie przy użyciu klucza");
                    Console.WriteLine("podnieś [Nr_Przedmiotu_W_Komnacie] -- umieszcza rzedmiot z komnaty w ekwipunku");
                    Console.WriteLine("pomoc -- wyświetla pomoc");
                    Console.WriteLine("przejdź [kierunek] -- przejdz do komnaty w kierunku (N, E, S, W)");
                    Console.WriteLine("rozejrzyj_się -- rozejrzyj się po komnacie");
                    Console.WriteLine("stan_gracza -- wyświetla aktualne statystyki gracza oraz zawartość ekwipunku");
                    Console.WriteLine("użyj [Nr_Przedmiotu_W_Ekwipunku] -- użyj przedmiot na graczu");
                    Console.WriteLine("walcz -- rozpoczyna walkę z przeciwnikiem znajdującym się w komnacie");
                    Console.WriteLine("exit -- zakończ działanie aplikacji");
                }
                else
                {
                    Console.WriteLine("Niewłaściwe polecenie. W celu uzyskania pomocy sprubuj użyć: pomoc");
                }
            }
        }
    }

    
    class KoniecGry                                                                                                 // Stan Gry
    {
        public bool GameOver { get; set;}
        public bool GraczUmarl { get; set; }
        public bool OsiagnietoWyjscie { get; set; }
        static KoniecGry Koniec; 

        KoniecGry()
        {
            GameOver = false;
            GraczUmarl = false;
            OsiagnietoWyjscie = false;
        }
        public static KoniecGry StanGry()
        {
            if (Koniec == null)
                Koniec = new KoniecGry();
            return Koniec;
        }
        public void PrzyczynaKoncaGry()
        {
            if (GameOver)
            {
                if (GraczUmarl)
                    Console.WriteLine("Przegrana! Gracz umarł.");
                if (OsiagnietoWyjscie)
                    Console.WriteLine("Wygrana. Osiągnięto wuyjście.");
            }
        }
    }

    class Arena
    {
        public Arena() { }

        public void Walka(Postac postac1, Postac postac2)
        {
            while (postac1.CzyZywy() && postac2.CzyZywy())
            {
                postac2.PrzyjmijCios(postac1.SilaCiosu());
                if (postac2.CzyZywy())
                    postac1.PrzyjmijCios(postac2.SilaCiosu());
            }
            if (postac1.CzyZywy())
                postac1.Zwyciestwo(postac2);
            else
                postac2.Zwyciestwo(postac1);

        }
    }
}
