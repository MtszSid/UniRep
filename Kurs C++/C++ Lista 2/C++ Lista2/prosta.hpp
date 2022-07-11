//Mateusz Sid³o - proste

#pragma once
#include <cmath>
#include <stdexcept>

class wektor {
public:
	const double dx, dy;
	wektor();     
	wektor(double, double);
	wektor(const wektor&);
	wektor& operator=(const wektor&) = delete;
	wektor add(const wektor&);

};

class punkt {
public: const double x, y; 
	  punkt();     
	  punkt(double, double);
	  punkt(const punkt&, const wektor&);     
	  punkt(const punkt&);
	  punkt& operator=(const punkt&) = delete;  
};



class prosta {
private:
	double a, b, c, w;
public:
	double get_A();
	double get_B();
	double get_C();
	void set_A(double);
	void set_B(double);
	void set_C(double);
	prosta() = default;
	prosta(double, double, double);
	prosta(const prosta&, const wektor&);
	prosta(const punkt&, const punkt&);
	prosta(const wektor&);
	prosta& operator= (const prosta&) = delete;
	bool wektor_prostopadly(const wektor&);
	bool wektor_rownolegly(const wektor&);
	bool punkt_na_prostej(const punkt&);
};
double wsp(double, double, double);
bool prostopadle(prosta&, prosta&);
bool rownolegle(prosta&, prosta&);
punkt punkt_przeciecia(prosta&, prosta&);