// Mateusz Sid³o
//

#pragma once
#include <iostream>
#include <climits>

namespace obliczenia {
	class wymierna {
		int licznik, mianownik; 
	public:
		wymierna();
		wymierna(int a, int b) noexcept(false);
		wymierna(int a) noexcept(false);
		wymierna(wymierna& w) = default;

		int get_licznik() const noexcept;
		int get_mianownik() const noexcept;
		void set_licznik(int a) noexcept;
		void set_manownik(int a)noexcept(false);

		wymierna& operator=(const wymierna& w) = default;

		wymierna& operator+(const wymierna& w)noexcept(false);
		wymierna& operator-(const wymierna& w)noexcept(false);
		wymierna& operator*(const wymierna& w)noexcept(false);
		wymierna& operator/(const wymierna& w)noexcept(false);
		wymierna& operator-()noexcept(false);
		wymierna& operator!()noexcept(false);

		operator double() const;
		operator int() const ;

		friend std::ostream& operator<< (std::ostream& wyj, const wymierna& w);
	private:
		void skroc();
		void mianownik_OK(int a) noexcept(false);
	};

	class wyjatek_wymierny {};
	class dzielenie_przez_0 : wyjatek_wymierny {};
	class niewlasciwy_mianownik : wyjatek_wymierny {};
	class przekroczenie_zakresu : wyjatek_wymierny {};
	class przeciwna_do_INT_MIN: wyjatek_wymierny {};
	
}