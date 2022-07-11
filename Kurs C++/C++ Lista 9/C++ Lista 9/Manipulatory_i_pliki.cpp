#include "Manipulatory_i_pliki.hpp"
#include <string>
std::istream& clearline(std::istream& str) {
    char T;
    while (1) {
        str.get(T);
        if (T == '\n')
            break;
        if (str.eof()) {
            str >> T;
            break;
        }
    }
    return str;
}

std::istream& operator>>(std::istream& stream, ignore Ign) {
    char T;
    while (Ign.amount > 0) {
        stream.get(T);
        if (T == '\n')
            break;
        if (stream.eof()) {
            stream >> T;
            break;
        }
        Ign.amount--;
    }
    return stream;
}

std::ostream& comma(std::ostream& str) {
    str << ", ";
    return str;
}

std::ostream& colon(std::ostream& str) {
    str << ": ";
    return str;
}

std::ostream& operator<<(std::ostream& stream, index Ind) {
    std::string liczba = std::to_string(Ind.Index);
    int length = Ind.Znaki - liczba.length();
    stream << "[";
    for (int i = 0; i < length; i++)
        stream << " ";
    stream << liczba <<"]";
    return stream;
}
