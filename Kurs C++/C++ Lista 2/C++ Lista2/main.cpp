//Mateusz Sidło - proste

#include <iostream>
#include "prosta.hpp"

int main()
{
	punkt P = punkt(1.1,3);
	punkt R = P;
	wektor a = wektor();
	wektor b = wektor(2, 1.5);
	wektor v = wektor();
	wektor c = b.wektor::add(b);
	punkt Z = punkt(R, b);
	prosta k = prosta();
	k.set_A(5);
	k.set_B(4);
	prosta n = prosta(P,Z);
	prosta l = prosta(-1.5, 2, 0.5);
	prosta m = prosta(l, b);
	prosta o = prosta(b);
	punkt przeciecie = punkt_przeciecia(n, o);
	std::cout << prostopadle(l, o) << " " << rownolegle(l, m)<<" " << prostopadle(l, m) << " " << rownolegle(k, n) << std::endl;
	std::cout << o.wektor_prostopadly(c) << " " << o.wektor_prostopadly(b) << " " << m.wektor_prostopadly(a) << std::endl;
	std::cout << m.wektor_rownolegly(b) << " " << l.wektor_rownolegly(b) << " " << m.wektor_rownolegly(a) << std::endl;
	std::cout << n.punkt_na_prostej(P) << " " << o.punkt_na_prostej(punkt(2, 1.5)) << " " << l.punkt_na_prostej(R) << std::endl << std::endl;
	std::cout << przeciecie.x << " " << przeciecie.y << std::endl;
	
	try {
		prosta t = prosta(0, 0, 12);
		std::cout << t.get_A() << std::endl;
	}
	catch (std::invalid_argument & ia) {
		std::cout << "invalid_argument: " << ia.what() << std::endl;
	}

	try {
		punkt przeciecie2 = punkt_przeciecia(n, l);
		std::cout << przeciecie2.x << " " << przeciecie2.y << std::endl;
	}
	catch (std::invalid_argument & ia) {
		std::cout << "invalid_argument: " << ia.what() << std::endl;
	}

	try {
		prosta r = prosta(v);
		std::cout << r.get_A() << std::endl;
	}
	catch (std::invalid_argument & ia) {
		std::cout << "invalid_argument: " << ia.what() << std::endl;
	}

	


}


