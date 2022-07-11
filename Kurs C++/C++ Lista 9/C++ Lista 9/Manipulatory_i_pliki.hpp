#pragma once
#include <iostream>
#include <fstream>

std::istream& clearline(std::istream& str);
std::ostream& comma(std::ostream& str);
std::ostream& colon(std::ostream& str);

class ignore {
private:
	int amount;
	friend std::istream& operator>>(std::istream& stream, ignore Ign);
public:
	ignore(int x) : amount(x) {}
};

class index {
private:
	int Index; // index 
	int Znaki; // liczba znaków
	friend std::ostream& operator<<(std::ostream& stream, index Index);
public:
	index(int x, int w) : Index(x), Znaki(w) {}
};

template<typename T>
class wejscie {
	std::ifstream ifs;
	wejscie(const std::string file_name) {
		ifs.open(file_name, std::ios::binary);
	}
	~wejscie() {
		ifs.close();
	}
	
};


class wyjscie {
	std::ofstream ofs;
public:
	wyjscie(const std::string file_name) {
			ofs.open(file_name, std::ios::out | std::ios::binary);
	}
	~wyjscie() {
		ofs.close();
	}
	template<typename T>
	void append(T ar) {
		ofs << ar;
		if (ofs.fail() || ofs.bad()) {
			throw(std::ios_base::failure(""));
		}
	}

};