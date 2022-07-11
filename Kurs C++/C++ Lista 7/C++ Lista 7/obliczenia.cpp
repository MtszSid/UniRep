// Mateusz Sid³o
//

#include "obliczenia.hpp"
#include <vector>

using namespace obliczenia;


wymierna::wymierna() {
	licznik = 0;
	mianownik = 1;
}

wymierna::wymierna(int a, int b) {
	licznik = a;
	mianownik_OK(b);
	mianownik = b;
	skroc();
	if (mianownik < 0) {
		licznik *= -1;
		mianownik *= -1;
	}
}

wymierna::wymierna(int a) : wymierna(a, 1) {}

int wymierna::get_licznik() const noexcept{
	return licznik;
}

int wymierna::get_mianownik() const noexcept{
	return mianownik;
}

void wymierna::set_licznik(int a) noexcept{
	licznik = a;
	skroc();
}

void wymierna::set_manownik(int a) {
	mianownik_OK(a);
	mianownik = a;
	skroc();
	if (mianownik < 0) {
		licznik *= -1;
		mianownik *= -1;
	}
}


wymierna& wymierna::operator+(const wymierna& w) {
	if (licznik > INT_MAX / w.mianownik || licznik < INT_MIN / w.mianownik)
		throw(przekroczenie_zakresu());
	if (w.licznik > INT_MAX / mianownik || w.licznik < INT_MIN / mianownik)
		throw(przekroczenie_zakresu());
	if (w.mianownik > INT_MAX / mianownik || w.mianownik < INT_MIN / mianownik)
		throw(przekroczenie_zakresu());
	int L1 = licznik * w.mianownik;
	int L2 = w.licznik * mianownik;
	if ((L1 > 0 && INT_MAX - L1 < L2) || (L1 < 0 && INT_MIN - L1 > L2))
		throw(przekroczenie_zakresu());
	int licz = L1 + L2;
	int mian = mianownik * w.mianownik;
	wymierna* A = new wymierna(licz, mian);
	return *A;
}

wymierna& wymierna::operator-(const wymierna& w) {
	if(licznik > INT_MAX / w.mianownik || licznik < INT_MIN / w.mianownik)
		throw(przekroczenie_zakresu());
	if (w.licznik > INT_MAX / mianownik || w.licznik < INT_MIN / mianownik)
		throw(przekroczenie_zakresu());
	if (w.mianownik > INT_MAX / mianownik || w.mianownik < INT_MIN / mianownik)
		throw(przekroczenie_zakresu());
	int L1 = licznik * w.mianownik;
	int L2 = w.licznik * mianownik;
	if ((L2 < 0 && INT_MAX + L2 < L1) || (L2 > 0 && INT_MIN + L2 > L1))
		throw(przekroczenie_zakresu());
	int licz = L1 - L2;
	int mian = mianownik * w.mianownik;
	wymierna* A = new wymierna(licz, mian);
	return *A;
}

wymierna& wymierna::operator*(const wymierna& w) {
	if (w.mianownik > INT_MAX / mianownik || w.mianownik < INT_MIN / mianownik)
		throw(przekroczenie_zakresu());
	if (w.licznik > INT_MAX / licznik || w.licznik < INT_MIN / licznik)
		throw(przekroczenie_zakresu());
	int licz = licznik * w.licznik;
	int mian = mianownik * w.mianownik;
	wymierna* B = new wymierna(licz, mian);
	return *B;
}

wymierna& wymierna::operator/(const wymierna& w) {
	wymierna* B = new wymierna(w.mianownik, w.licznik);
	return *B * *this;
}

wymierna& wymierna::operator-(){
	if (licznik == INT_MIN)
		throw(przeciwna_do_INT_MIN ());
	wymierna* A = new wymierna(-licznik,  mianownik);
	return *A;
}

wymierna& wymierna::operator!() {
	wymierna* A = new wymierna(mianownik, licznik);
	return *A;
}

wymierna::operator double() const{
	return (double)licznik / (double)mianownik;
}

wymierna::operator int() const {
	return licznik / mianownik;
}

void wymierna::mianownik_OK(int a) {
	if (a == 0)
		throw(dzielenie_przez_0());
	if (licznik % 2 != 0 && a == INT_MIN)
		throw(niewlasciwy_mianownik());
}

void wymierna::skroc() {
	int a;
	
	(abs(licznik) > mianownik ? a = mianownik : a = abs(licznik));
	while (licznik % 2 == 0 && mianownik % 2 == 0) {
		licznik /= 2;
		mianownik /= 2;
		a /= 2;
	}
	for (int i = 3; i <= a ; i += 2) {
		while (licznik % i == 0 && mianownik % i == 0) {
			licznik /= i;
			mianownik /= i;
		}
	}
}

bool not_in_vect_of_pair(std::vector <std::pair<int, int>> V, int l) {
	for (std::pair<int, int> P : V) {
		if (P.first == l) return false;
	}
	return true;
}

std::ostream& obliczenia::operator<< (std::ostream& wyj, const wymierna& w) {
	
	int L = w.get_licznik();
	int M = w.get_mianownik();
	int P = L / M;
	std::vector <std::pair<int, int>> Pom;
	L = L % M * 10;
	wyj << P;
	wyj << ',';
	while (not_in_vect_of_pair(Pom, L)) {
		P = L / M;
		if (P < 0)
			Pom.push_back(std::pair<int, int>(L, -P));
		else
			Pom.push_back(std::pair<int, int>(L, P));
		L = L % M * 10;
	}
	for (std::pair<int, int> P : Pom) {
		if (P.first == L) wyj << '(';
		wyj << P.second;
	}
	wyj << ')';
	return wyj;
}
