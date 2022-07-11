//Mateusz Sid³o


#include "Wyrazenia.hpp"

int main()
{
    wyrazenie* T = new odejmij(new pi(), new dodaj(new liczba(2), new mnoz(new zmienna("x"), new liczba(7))));

    wyrazenie* W =
        new odwrot(
            new modulo(new liczba(3.5), new liczba(2)));

    wyrazenie* X = 
        new potega(
            new ln(new mnoz(new fi(), new class sin(new liczba(15)))),
            new bezwzgl(new odwrot(new zmienna("g"))));
    
    wyrazenie* Y =
        new class exp(
            new przeciw(
                new logarytm(
                    new liczba(3.5), new e())));

    wyrazenie* Z =
        new dodaj(
            new potega(
                new class sin(new zmienna("t")), new liczba(2)),
            new potega(
                new class cos(new zmienna("t")), new liczba(2)));

    zmienna::nadaj_wart_zmiennej("g", 5);
    zmienna::nadaj_wart_zmiennej("t", 2.1);

    std::cout << T->opis() << " = " << T->oblicz() << std::endl;
    std::cout << W->opis() << " = " << W->oblicz() << std::endl;
    std::cout << X->opis() << " = " << X->oblicz() << std::endl;
    std::cout << Y->opis() << " = " << Y->oblicz() << std::endl;
    std::cout << Z->opis() << " = " << Z->oblicz() << std::endl;

    wyrazenie* A =
        new dziel(
            new mnoz(
                new odejmij(new zmienna("x"), new liczba(1)),
                new zmienna("x")),
            new liczba(2));

    wyrazenie* B =
        new dziel(
            new dodaj(new liczba(3), new liczba(5)),
            new dodaj(
                new liczba(2),
                new mnoz(new zmienna("x"), new liczba(7))));

    wyrazenie* C =
        new odejmij(
            new dodaj(
                new liczba(2),
                new mnoz(new zmienna("x"), new liczba(7))),
            new dodaj(
                new mnoz(new zmienna("y"), new liczba(3)),
                new liczba(5)));

    wyrazenie* D =
        new dziel(
            new class cos(
                new mnoz(
                    new dodaj(new liczba(2), new zmienna("x")),
                    new zmienna("x"))),
            new potega(
                new e(),
                new potega(
                    new zmienna("x"), new liczba(2))));

    std::cout << "A = " << A->opis() << std::endl;
    std::cout << "B = " << B->opis() << std::endl;
    std::cout << "C = " << C->opis() << std::endl;
    std::cout << "D = " << D->opis() << std::endl;
    for (double i = 0; i < 1; i += 0.01) {
        zmienna::nadaj_wart_zmiennej("x", i);
        std::cout << "x = " << zmienna::sprawdz_wart_zmiennej("x") << std::endl;
        std::cout << "A = " << A->oblicz() << std::endl;
        std::cout << "B = " << A->oblicz() << std::endl;
        std::cout << "C = " << A->oblicz() << std::endl;
        std::cout << "D = " << A->oblicz() << std::endl << std::endl;
    }
}