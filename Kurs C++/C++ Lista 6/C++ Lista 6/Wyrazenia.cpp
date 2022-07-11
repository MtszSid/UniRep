#include "Wyrazenia.hpp"
//#include <cmath>

std::vector<std::pair<std::string, double>> zmienna::Tabela_wartosci;

stala::stala(double w, std::string naz) : wartosc(w), symbol(naz){
	priorytet = 30;

}

pi::pi() : stala(3.14159265, "pi"){}

fi::fi() : stala(1.6180339, "fi") {}

e::e() : stala(2.7182818, "e") {}

double stala::oblicz() {
	return wartosc;
}

std::string stala::opis() {
	return symbol;
}

double liczba::oblicz() {
	return wartosc;
}

std::string liczba::opis() {
	if (wartosc < 0)
		return "(" + std::to_string(wartosc) + ")";
	return std::to_string(wartosc);
}

liczba::liczba(double T) {
	wartosc = T;
	priorytet = 30;
}

zmienna::zmienna(std::string x) {
	zm = x;
	priorytet = 30;
	if (!czy_zmienna_ma_wartosc(x))
		wyzeroj_zmienna(x);
}

std::string zmienna::opis(){
	return zm;
}

double zmienna::oblicz() {
	return sprawdz_wart_zmiennej(zm);
}

bool zmienna::czy_zmienna_ma_wartosc(std::string x) {
	for (std::pair<std::string, double> P : Tabela_wartosci) {
		if (P.first == x)
			return true;
	}
	return false;
}

void zmienna::nadaj_wart_zmiennej(std::string x, double wart) {
	int i = 0;
	for (std::pair<std::string, double> P : Tabela_wartosci) {
		if (P.first == x) {
			Tabela_wartosci[i].second = wart;
			return;
		}
		i++;
	}
	Tabela_wartosci.push_back(std::pair<std::string, double>(x, wart));
	return;
}

double zmienna::sprawdz_wart_zmiennej(std::string x) {
	for (std::pair<std::string, double> P : Tabela_wartosci) {
		if (P.first == x)
			return P.second;
	}

	return 0;
}

void zmienna::wyzeroj_zmienna(std::string x){
	nadaj_wart_zmiennej(x, 0);
}

operat1arg::operat1arg(wyrazenie* T) {
	right = T;
	priorytet = 30;
}

sin::sin(wyrazenie* T) : operat1arg(T) {}

double sin::oblicz() {
	return ::sin(right->oblicz());
}

std::string sin::opis() {
	return "sin(" + right->opis() + ")";
}

cos::cos(wyrazenie* T) : operat1arg(T) {}

double cos::oblicz() {
	return ::cos(right->oblicz());
}

std::string cos::opis() {
	return "cos(" + right->opis() + ")";
}

exp::exp(wyrazenie* T) : operat1arg(T) {}

double exp::oblicz() {
	return ::exp(right->oblicz());
}

std::string exp::opis() {
	return "exp(" + right->opis() + ")";
}

ln::ln(wyrazenie* T) : operat1arg(T) {}

double ln::oblicz() {
	return ::log(right->oblicz());
}

std::string ln::opis() {
	return "ln(" + right->opis() + ")";
}

bezwzgl::bezwzgl(wyrazenie* T) : operat1arg(T) {}

double bezwzgl::oblicz() {
	return ::abs(right->oblicz());
}

std::string bezwzgl::opis() {
	return "|" + right->opis() + "|";
}

przeciw::przeciw(wyrazenie* T) : operat1arg(T) {
	priorytet = 10;
}

double przeciw::oblicz() {
	return -(right->oblicz());
}

std::string przeciw::opis() {
	if(right->priorytet < priorytet)
		return "(-1)*(" + right->opis() + ")";
	return "(-1)*" + right->opis();
}

odwrot::odwrot(wyrazenie* T) : operat1arg(T) {}

double odwrot::oblicz() {
	return 1/(right->oblicz());
}

std::string odwrot::opis() {
	if(right->priorytet < priorytet)
		return "(" + right->opis() + ")^(-1)";
	return right->opis() + "^(-1)";
}

operator2arg::operator2arg(wyrazenie* l, wyrazenie* r):operat1arg(r) {
	left = l;
}

dodaj::dodaj(wyrazenie* l, wyrazenie* r) : operator2arg (l,r) {
	priorytet = 5;
}

double dodaj::oblicz() {
	return left->oblicz() + right->oblicz();
}

std::string dodaj::opis() {
	std::string l = left->opis();
	std::string r = right->opis();
	if (left->priorytet < priorytet)
		l = "(" + l + ")";
	if (right->priorytet < priorytet)
		r = "(" + r + ")";
	return l + "+" + r;
}

odejmij::odejmij(wyrazenie* l, wyrazenie* r) : operator2arg(l, r) {
	priorytet = 5;
}

std::string odejmij::opis() {
	std::string l = left->opis();
	std::string r = right->opis();
	if (left->priorytet < priorytet)
		l = "(" + l + ")";
	if (right->priorytet - 1 < priorytet)
		r = "(" + r + ")";
	return l + "-" + r;
}

double odejmij::oblicz() {
	return left->oblicz() - right->oblicz();
}

mnoz::mnoz(wyrazenie* l, wyrazenie* r) : operator2arg(l, r) {
	priorytet = 10;
}

double mnoz::oblicz() {
	return left->oblicz() * right->oblicz();
}

std::string mnoz::opis() {
	std::string l = left->opis();
	std::string r = right->opis();
	if (left->priorytet < priorytet)
		l = "(" + l + ")";
	if (right->priorytet < priorytet)
		r = "(" + r + ")";
	return l + "*" + r;
}

dziel::dziel(wyrazenie* l, wyrazenie* r) : operator2arg(l, r) {
	priorytet = 10;
}

std::string dziel::opis() {
	std::string l = left->opis();
	std::string r = right->opis();
	if (left->priorytet < priorytet)
		l = "(" + l + ")";
	if (right->priorytet-1 < priorytet)
		r = "(" + r + ")";
	return l + "/" + r;
}

double dziel::oblicz() {
	return left->oblicz() / right->oblicz();
}

logarytm::logarytm(wyrazenie* l, wyrazenie* base) : operator2arg(l, base) {
	priorytet = 10;
}

std::string logarytm::opis() {
	std::string l = "log(" + left->opis() + ")";
	std::string r = "log(" + right->opis() + ")";
	return l + "/" + r;
}

double logarytm::oblicz() {
	return log(left->oblicz()) / log(right->oblicz());
}

modulo::modulo(wyrazenie* l, wyrazenie* r) : operator2arg(l, r) {
	priorytet = 10;
}

std::string modulo::opis() {
	std::string l = left->opis();
	std::string r = right->opis();
	if (left->priorytet < priorytet)
		l = "(" + l + ")";
	if (right->priorytet < priorytet)
		r = "(" + r + ")";
	return l + "%" + r;
}

double modulo::oblicz() {
	return fmod(left->oblicz(), right->oblicz());
}

potega::potega(wyrazenie* l, wyrazenie* r) : operator2arg(l, r) {
	priorytet = 15;
}

std::string potega::opis() {
	std::string l = left->opis();
	std::string r = right->opis();
	if (left->priorytet - 1 < priorytet)
		l = "(" + l + ")";
	if (right->priorytet < priorytet)
		r = "(" + r + ")";
	return l + "^" + r;
}

double potega::oblicz() {
	return pow(left->oblicz(), right->oblicz());
}