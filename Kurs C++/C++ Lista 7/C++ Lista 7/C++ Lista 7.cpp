// Mateusz Sid³o
//


#include "obliczenia.hpp"
using namespace obliczenia;
int main()
{
    try {
        wymierna Z(1, INT_MIN);
    }
    catch (niewlasciwy_mianownik& P) {
        std::cout << "Niewlasciwy mianownik" << std::endl;
    }
    try {
        wymierna Z1(1, 0);
    }
    catch (dzielenie_przez_0& P) {
        std::cout << "Dzielenie przez 0" << std::endl;
    }


    wymierna X(1, INT_MIN / 2);
    wymierna Y = X;
    wymierna* A = new wymierna (5, 10);
    wymierna* B = new wymierna(1, 3);
    wymierna B1(*B);
    B1.set_licznik(152);
    wymierna* C = new wymierna(1, 110);
    wymierna D(15, 2);
    std::cout << A->get_licznik() << A->get_mianownik() <<"\n";
    std::cout << B1 << std::endl;
    std::cout << *B << std::endl;
    std::cout << (int)*B << std::endl;
    std::cout << *A << std::endl;
    std::cout << (double)*A << std::endl;
    std::cout << *C << std::endl;
    std::cout << D << std::endl;
    std::cout << (int)D << std::endl;
    std::cout << *C + *A<< std::endl;
    std::cout << *C * *A<< std::endl;
    std::cout << *B / *A<< std::endl;
    std::cout << D - *A << std::endl;
    std::cout << -D << std::endl;
    std::cout << !*A << std::endl;
    try {
        std::cout << X + *A << std::endl;
    }
    catch (przekroczenie_zakresu& P) {
        std::cout << "Przekroczenie zakresu" << std::endl;
    }
    try {
        std::cout << X * *A << std::endl;
    }
    catch (przekroczenie_zakresu& P) {
        std::cout << "Przekroczenie zakresu" << std::endl;
    }

}

