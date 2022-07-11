//Mateusz Sid³o
#include"tabbit.hpp"

int main()
{
	tab_bit t(46);// tablica 46-bitowa (zainicjalizowana zerami)
	tab_bit u(45ull);// tablica 64-bitowa (sizeof(uint64_t)*8)
	tab_bit v(t);// tablica 46-bitowa (skopiowana z t)
	tab_bit w(tab_bit{1, 0, 1, 1, 0, 0, 0, 1}); // tablica 8-bitowa (przeniesiona)
	v[0] = 1;// ustawienie bitu 0-go bitu na 1
	t[45] = true;// ustawienie bitu 45-go bitu na 1
	bool b = v[1];// odczytanie bitu 1-go
	u[45] = u[46] = u[63]; // przepisanie bitu 63-go do bitow 45-go i 46-go
	std::cout<<t<<std::endl;// wysietlenie zawartoœci tablicy bitów naekranie
	tab_bit T = tab_bit(std::move(tab_bit({ 1, 0, 0, 1, false, true, 0 })));
	tab_bit* G = new  tab_bit(1);
	tab_bit* B = new  tab_bit(1);
	*G = T;
	*B = std::move(tab_bit(21ull));
	tab_bit R = tab_bit({1, 0, 0, 1, false, true, 1});
	std::cout << T << std::endl;
	std::cout << R << std::endl;
	R[5] = 1;
	T[6] = 0;
	T[0] = u[0];
	T[1] = false;
	tab_bit F = tab_bit(t);
	tab_bit TT = T | R;
	tab_bit TR = T & R;
	tab_bit RR = T ^ R;
	std::cout << T << std::endl << std::endl;
	std::cout << *G << std::endl;
	std::cout << *B << std::endl << std::endl;
	std::cout << t << std::endl << std::endl;
	std::cout << F << std::endl;
	std::cout << u << std::endl;
	std::cout << "    T = " << T << std::endl;
	std::cout << "    R = " << R << std::endl;
	std::cout << "T | R = " << TT << std::endl;
	std::cout << "T & R = " << TR << std::endl;
	std::cout << "T ^ R = " << RR << std::endl << std::endl;
	std::cout << "     T = " << T << std::endl;
	std::cout << "     R = " << R << std::endl;
	T |= R;
	std::cout << "T |= R : " << T << std::endl << std::endl;
	std::cout << "     R = " << R << std::endl;
	std::cout << "     G = " << *G << std::endl;
	R &= *G;
	std::cout << "R &= T : " << R << std::endl << std::endl;
	std::cout << "     T = " << T << std::endl;
	std::cout << "     R = " << R << std::endl;
	R ^= T;
	std::cout << "R ^= T : " << R << std::endl << std::endl;
	std::cout << "       T = " << T << std::endl;
	std::cout << "    w = " << w << std::endl ;
	std::cout << "T | w = " << (T | w) << std::endl << std::endl;
	std::cout << "T | w = " << (T & w) << std::endl << std::endl;
	std::cout << "T | w = " << (T ^ w) << std::endl << std::endl;
	std::cout << "     R = " << R << std::endl;
	std::cout << "    !R = " << !R << std::endl;
	std::cin >> R;
	std::cout << R;
	

}

