#pragma once
//Mateusz Sidło
#include <iostream>
#include <initializer_list>
#include <cstdlib>
		

class tab_bit {
	typedef uint64_t slowo; // komorka w tablicy
	static const int rozmiarSlowa = sizeof(slowo) * 8; // rozmiar slowa w bitach
	class ref { // klasa pomocnicza do adresowania bitów
		int bit;
		tab_bit* Tablica;
	public:
		operator bool() const { return Tablica->czytaj(bit); }
		ref(int B, tab_bit* G);
		ref& operator = (const ref& T);
		ref& operator = (const bool T);
	};
protected:
	int dl; // liczba bitów
	slowo *tab; // tablica bitów
public:
	explicit tab_bit (int rozm); // wyzerowana tablica bitow [0...rozm]
	explicit tab_bit (slowo tb); // tablica bitów [0...rozmiarSlowa]
	tab_bit(std::initializer_list<bool> L); // zainicjalizowana wzorcem
	tab_bit (const tab_bit &tb); // konstruktor kopiujący
	tab_bit (tab_bit &&tb) noexcept; // konstruktor przenoszący
	tab_bit & operator = (const tab_bit &tb); // przypisanie kopiujące
	tab_bit & operator = (tab_bit &&tb); // przypisanie przenoszące
	~tab_bit(); // destruktor
private:
	bool czytaj (int i) const; // metoda pomocnicza do odczytu bitu
	bool pisz (int i, bool b); // metoda pomocnicza do zapisu bitu 
public:
	bool operator[] (int i) const; // indeksowanie dla stałych tablic bitowych 
	ref operator[] (int i); // indeksowanie dla zwykłych tablic bitowych
	inline int rozmiar () const; // rozmiar tablicy w bitach 
public:
	// operatory bitowe: | i |=, & i &=, ^ i ^= oraz !
	tab_bit& operator| (const tab_bit& tb);
	tab_bit& operator|= (tab_bit tb);
	tab_bit& operator& (tab_bit tb);
	tab_bit& operator&= (tab_bit tb);
	tab_bit& operator^ (tab_bit tb);
	tab_bit& operator^= (tab_bit tb);
	tab_bit& operator! ();

public:
	// zaprzyjaźnione operatory strumieniowe: << i >>
	friend std::istream & operator >> (std::istream &we, tab_bit &tb);
	friend std::ostream& operator << (std::ostream& wy, const tab_bit& tb);
};