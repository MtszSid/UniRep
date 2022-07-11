//Mateusz Sid³o - proste

#include "prosta.hpp"
#include<iostream>

//Wektor

wektor::wektor() : dx(0), dy(0){}
wektor::wektor(double x, double y) : dx(x), dy(y) {}
wektor::wektor(const wektor& w) : dx(w.dx), dy(w.dy){}

wektor wektor::add (const wektor& a)
{
	double x, y;
	x = this->dx + a.dx;
	y = a.dy + this->dy;
	return wektor(x, y);
}


//Punkt

punkt::punkt() :x(0), y(0) {};
punkt::punkt(double a, double b):x(a), y(b) {}
punkt::punkt(const punkt& P,const wektor& v): punkt(P.x + v.dx, P.y + v.dy) {}
punkt::punkt(const punkt& P) : punkt(P.x, P.y) {}



//Prosta

double prosta::get_A() {
	return this->a;
}

double prosta::get_B() {
	return this->b;
}

double prosta::get_C() {
	return this->c;
}

void prosta::set_A(double x) {
	this->a = x;
	if (x == 0 && this->b == 0) throw std::invalid_argument("nie jest prost¹");
	this->w = wsp(this->a, this->b, this->c);
	this->a *= this->w;
	this->b *= this->w;
	this->c *= this->w;
}

void prosta::set_B(double x) {
	if ((this->a) == 0 && x == 0) throw std::invalid_argument("nie jest prost¹");
	this->b = x;
	this->w = wsp(this->a, this->b, this->c);
	this->a *= this->w;
	this->b *= this->w;
	this->c *= this->w;
}

void prosta::set_C(double x) {
	this->c = this->w*x;
	if ((this->a) == 0 && (this->b) == 0) throw std::invalid_argument("nie jest prost¹");
}

double wsp(double A, double B, double C) {
	if (C > 0) return 1 / (sqrt(A * A + B * B));
	return -1 / (sqrt(A * A + B * B));
}

prosta::prosta(double A, double B, double C) {
	if (A == 0 && B == 0) throw std::invalid_argument("nie mo¿na jednoznacznie utworzyæ prostej");
	w = wsp(A, B, C);
	a = A * w;
	b = B * w;
	c = C * w;
}

prosta::prosta(const prosta& k, const wektor& v) {
	w = k.w;
	a = k.a;
	b = k.b;
	if (a == 0 && b == 0) throw std::invalid_argument("argument nie jest prost¹");
	c = k.c + ((-a) * v.dx - b * v.dy);
}

prosta::prosta(const punkt& X, const punkt& Y) {
	double A, B, C;
	if (X.x == Y.x && X.y == Y.y) throw std::invalid_argument("nie mo¿na jednoznacznie utworzyæ prostej");

	A = Y.y - X.y;
	B = X.x - Y.x;
	C = (Y.x * X.y - X.x * Y.y);

	w = wsp(A, B, C);
	a = A * w;
	b = B * w;
	c = C * w;
}

prosta::prosta(const wektor& v) {
	double A, B, C;
	if (v.dx == 0 && v.dy == 0) throw std::invalid_argument("nie mo¿na jednoznacznie utworzyæ prostej");
	A = v.dx;
	B = v.dy;
	C = ((-A) * v.dx - B * v.dy);
	
	w = wsp(A, B, C);
	a = A * w;
	b = B * w;
	c = C * w;
}

bool prosta::wektor_prostopadly(const wektor& v) {
	double dok = 0.000000000000001;
	double p = v.dx / v.dy - this->a / this->b;
	if (p < dok && -p < dok) return true;
	return false;
}

bool prosta::wektor_rownolegly(const wektor& v) {
	double dok = 0.000000000000001;
	double p = v.dy / -v.dx - this->a / this->b;
	if (p < dok && -p < dok) return true;
	return false;
}

bool prosta::punkt_na_prostej(const punkt& P) {
	double dok = 0.000000000000001;
	if (this->a * P.x + this->b * P.y + this->c < dok) return true;
	return false;
}

bool prostopadle(prosta& k, prosta& l) {
	double p = k.get_A() * l.get_A() + k.get_B() * l.get_B();
	double dok = 0.000000000000001;
	if (p < dok && -p < dok) return true;
	return false;
}
bool rownolegle(prosta& k, prosta& l) {
	double p = k.get_A() * l.get_B() - k.get_B() * l.get_A();
	double dok = 0.000000000000001;
	if (p < dok && -p < dok) return true;
	return false;
}
punkt punkt_przeciecia(prosta& k, prosta& l) {
	double W_AB, W_BC, W_CA;
	if (rownolegle(k, l) == true) throw std::invalid_argument("proste równoleg³e");
	if ((k.get_A() == 0 && k.get_B() == 0) || (l.get_A() == 0 && l.get_B() == 0)) throw std::invalid_argument("argument nie jest prost¹");
	W_AB = k.get_A() * l.get_B() - l.get_A() * k.get_B();
	W_BC = k.get_B() * l.get_C() - l.get_B() * k.get_C();
	W_CA = k.get_C() * l.get_A() - l.get_C() + k.get_A();
	return punkt(W_BC / W_AB, W_CA / W_AB);
}
