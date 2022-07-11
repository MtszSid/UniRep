//Mateusz Sid³o
#include"stos.hpp"


//Konstruktory

stack::stack(int i) 
{
	pojemnosc = i;
	ile = 0;
	tablica = new std::string [i];
}

stack::stack() : stack(1) {}

stack::stack(const stack& v) 
{
	pojemnosc = v.pojemnosc;
	ile = v.ile;
	tablica = new std::string[pojemnosc];
	for (int i = 0; i < v.ile; i++) {
		tablica[i] = v.tablica[i];
	}
}

stack::stack(const stack&& v) 
{
	std::clog << "Konstruktor przenosz¹cy\n";
	pojemnosc = v.pojemnosc;
	ile = v.ile;
	tablica = v.tablica;
}

stack::stack(std::initializer_list<std::string> L) {
	pojemnosc = L.size();
	ile = L.size();
	tablica = new std::string[ile];
	int i = 0;
	for (auto n : L) {
		tablica[i] = n;
		i++;
	}
}

stack::~stack() {
	delete[] tablica;
}

//
//Metody klasy stack
//

std::string stack::sprawdz()
{
	if (ile > 0) {
		return tablica[ile - 1];
	}
	std::clog << "Stos pusty\n";
	return "\0";
}

void stack::wloz(std::string s) 
{
	if (ile == pojemnosc) {
		std::clog << "Stos pe³ny" << std::endl;
	}
	else {
		tablica[ile] = s;
		ile++;
	}
}

std::string stack::sciagnij() 
{
	if (ile > 0) {
		ile--;
		return tablica[ile];
	}
	std::clog << "Stos pusty\n";
	return "\0";
}

stack stack::odwroc(const stack&)
{
	stack P(pojemnosc);
	for (int i = ile - 1; i >= 0; i-- ) {
		P.wloz(tablica[i]);
	}
	return P;
}

int stack::rozmiar()
{
	return ile;
}
/*
stack& stack::operator=(const stack& P) {
	std::clog << "Przypisanie kopiuj¹ce\n";
	stack G = stack(P);
	return G;
}

stack& stack::operator=(const stack&& P) {
	std::clog << "Przypisanie przenosz¹ce\n";
	stack G = stack(std::move(P));
	return G;
}*/