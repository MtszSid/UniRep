//Mateusz Sidło

#include"stos.hpp"

int main()
{
    stack Stos = stack({"Ala", "ma"});
    stack P = stack(Stos);
    std::cout << Stos.sciagnij() <<"\n";
    std::cout << P.sprawdz() << "\n";
    std::cout << Stos.sciagnij() << "\n";
    std::cout << Stos.sprawdz() << "\n";
    delete &P;
    
}

