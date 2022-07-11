// Mateusz Sidło Lista 3


#include <iostream>
#include<string>>
#include<vector>

const std::vector<std::pair<int, std::string>> rzym = {
{1000, "M"},
{900, "CM"}, {500, "D"}, {400, "CD"}, {100, "C"},
{90, "XC"}, {50, "L"}, {40, "XL"}, {10, "X"},
{9, "IX"}, {5, "V"}, {4, "IV"}, {1, "I"}
};

std::string bin2rom(int x)
{
    std::string Liczba;
    if (x < 1 || x>3999) throw std::out_of_range("nie jest z zakresu [1, 3999]");
    for(std::pair<int, std::string> Para : rzym){
        while (x / Para.first != 0) {
            Liczba += Para.second;
            x -= Para.first;
        }
    }
    return Liczba;
}

int main(int argc, char** argv)
{
    int liczba;
    if(argc == 1){
        std::clog << "Program uruchamiamy w następujacy sposob ./nazwa_programu argumenty_do_konwersji_oddzielone_spacjami" << std::endl;
        return 0;
    }
    for (int i = 1; i < argc; i++) {
        try {
            liczba = std::stoi(argv[i], nullptr, 10);

            if ((std::string) argv[i] != std::to_string(liczba)) {
                std::clog << "Niewlasciwy argument: " << argv[i] << std::endl;
                continue;
            }
            std::cout << argv[i] << " = " << bin2rom(liczba) << std::endl;
        }
        catch(std::invalid_argument& ia){
            std::clog << "Niewlasciwy argument: " << argv[i] << " " << ia.what() << std::endl;
        }
        catch (std::out_of_range & ia) {
            std::clog << "Niewlasciwy argument: " << argv[i] << " " << ia.what() << std::endl;
        }

    }

    
}

