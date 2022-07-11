//Mateusz Sid³o
#pragma once
#include <iostream>
#include <vector>
#include<string>
#include <utility>

class wyrazenie {
public:
	int priorytet;
	virtual double oblicz() = 0;
	virtual std::string opis() = 0;
};

class stala : public wyrazenie {
public:
	const double wartosc;
	const std::string symbol;
public:
	double oblicz();
	std::string opis();
	stala(double w, std::string naz);
};

class pi : public stala {
public:
	pi();
};

class fi : public stala {
public:
	fi();
};

class e: public stala {
public:
	e();
};

class liczba : public  wyrazenie {
private:
	double wartosc;
public:
	double oblicz();
	std::string opis();
	liczba(double T);
};

class zmienna : public wyrazenie {
private:
	static std::vector<std::pair<std::string, double>> Tabela_wartosci;
	std::string zm;
public:
	double oblicz();
	std::string opis();
	zmienna(std::string x);
	static void nadaj_wart_zmiennej(std::string x, double wart);
	static double sprawdz_wart_zmiennej(std::string x);
	static void wyzeroj_zmienna(std::string x);
	static bool czy_zmienna_ma_wartosc(std::string x);
};


class operat1arg : public wyrazenie {
public:
	wyrazenie* right;
	operat1arg(wyrazenie* T);
};

class sin : public operat1arg {
public:
	sin(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class cos : public operat1arg {
public:
	cos(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class exp : public operat1arg {
public:
	exp(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class ln : public operat1arg {
public:
	ln(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class bezwzgl : public operat1arg {
public:
	bezwzgl(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class przeciw : public operat1arg {
public:
	przeciw(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class odwrot : public operat1arg{
public:
	odwrot(wyrazenie* T);
	double oblicz();
	std::string opis();
};

class operator2arg : public operat1arg {
public:
	wyrazenie* left;
	operator2arg(wyrazenie* l, wyrazenie* r);
};

class dodaj : public operator2arg {
public:
	double oblicz();
	std::string opis();
	dodaj(wyrazenie* l, wyrazenie* r);
};

class odejmij : public operator2arg {
public:
	double oblicz();
	std::string opis();
	odejmij(wyrazenie* l, wyrazenie* r); 
};

class mnoz : public operator2arg {
public:
	double oblicz();
	std::string opis();
	mnoz(wyrazenie* l, wyrazenie* r);
};

class dziel : public operator2arg {
public:
	double oblicz();
	std::string opis();
	dziel(wyrazenie* l, wyrazenie* r);
};

class logarytm : public operator2arg {
public:
	double oblicz();
	std::string opis();
	logarytm(wyrazenie* l, wyrazenie* r);
};

class modulo : public operator2arg {
public:
	double oblicz();
	std::string opis();
	modulo(wyrazenie* l, wyrazenie* r);
};

class potega : public operator2arg {
public:
	double oblicz();
	std::string opis();
	potega(wyrazenie* l, wyrazenie* r);
};