//Mateusz Sid³o
#include"tabbit.hpp"

int max(int a, int b) {
	return(a > b ? a : b);
}

int min(int a, int b) {
	return(a < b ? a : b);
}

std::ostream& operator << (std::ostream& wy, const tab_bit& tb) {
	for (int i = tb.rozmiar() - 1; i >= 0; i--) {
		wy << tb[i];
		wy << ", ";
	}
	return wy;
}

std::istream& operator >> (std::istream& we, tab_bit& tb) {
	bool b;
	int ilosc;
	we >> ilosc;
	for (int i = ilosc - 1; i >= 0; i--) {
		we >> b;
		tb.pisz(i, b);
	}
	return we;
}


//Konstruktory//


tab_bit::tab_bit(int rozm) {
	dl = rozm;
	int P = rozm / rozmiarSlowa;
	if (rozm % rozmiarSlowa != 0) P++;
	tab = new slowo[P];
	for (int i = 0; i < P; i++) {
		tab[i] = 0;
	}
}

tab_bit::tab_bit(slowo tb):tab_bit((int)rozmiarSlowa) {
	uint64_t P = tb;
	tab[0] = P;
}

tab_bit::tab_bit(const tab_bit& tb) : tab_bit(tb.dl) {
	int rozm = dl / rozmiarSlowa;
	if (dl % rozmiarSlowa != 0) rozm++;
	for (int i = 0; i < rozm; i++) {
		tab[i] = tb.tab[i];
	}
}

tab_bit::tab_bit(tab_bit&& tb) noexcept : dl(tb.dl), tab(tb.tab) {
	tb.dl = 0;
	tb.tab = nullptr;
}

tab_bit::tab_bit(std::initializer_list<bool> L): tab_bit((int)L.size()) {
	int i = (int)L.size() - 1;
	for (bool b : L) {
		pisz(i, b);
		i--;
	}
}

tab_bit& tab_bit::operator = (const tab_bit& tb) {
	tab_bit* T = new tab_bit(tb);
	std::swap(dl, T->dl);
	std::swap(tab, T->tab);
	delete T;
	return *this;
}

tab_bit& tab_bit::operator = (tab_bit&& tb) {
	dl = std::move(tb.dl);
	tab = std::move(tb.tab);
	return *this;
}

tab_bit::~tab_bit() {
	dl = 0;
	delete[] tab;
}


bool tab_bit::czytaj(int i) const {
	if (i >= dl) throw std::out_of_range("Indeks poza zakresem");
	uint64_t P = tab[(int)i / rozmiarSlowa];
	uint64_t T = (uint64_t)1 << i % rozmiarSlowa;
	P &= T;
	P = P >> i % rozmiarSlowa;
	return P;
}

bool tab_bit::pisz(int i, bool b) {
	if (i >= dl) throw std::out_of_range("Indeks poza zakresem");
	uint64_t P = tab[(int)i / rozmiarSlowa];
	if (b == 0) {
		P = P & ~((uint64_t)1 << (i % rozmiarSlowa));
	}
	else {
		P = P | ((uint64_t)1 << (i % rozmiarSlowa));
	}
	tab[(int)i / rozmiarSlowa] = P;
	return czytaj(i);
}

tab_bit::ref::ref(int B, tab_bit* G) {
	if (B > G->dl) {
		throw std::out_of_range("Indeks poza zakresem");
	}
	bit = B;
	Tablica = G;
}

tab_bit::ref& tab_bit::ref::operator = (const ref& T) {
	Tablica->pisz(bit, T.Tablica->czytaj(T.bit));
	return *this;
}

tab_bit::ref& tab_bit::ref::operator = (const bool T) {
	Tablica->pisz(bit, T);
	return *this;
}
bool tab_bit::operator[](int i) const {
	return czytaj(i);
}

tab_bit::ref tab_bit::operator[] (int i) {
	return ref(i, this);
}

inline int tab_bit::rozmiar() const {
	return dl;
}

// operatory bitowe: | i |=, & i &=, ^ i ^= oraz !

tab_bit& tab_bit::operator| (const tab_bit& tb) {
	tab_bit* T = new tab_bit(max(dl, tb.dl));
	for (int i = 0; i < min(dl, tb.dl); i++) {
		if (czytaj(i) == 1 || tb[i] == 1) T->pisz(i, 1);
	}
	for(int i = min(dl, tb.dl); i < dl ; i++)
		T->pisz(i, czytaj(i));
	for (int i = min(dl, tb.dl); i < tb.dl; i++)
		T->pisz(i, tb[i]);
	return *T;
}

tab_bit& tab_bit::operator|= (tab_bit tb) {
	tab_bit* T = new tab_bit(1);
	*T = *this | tb;
	std::swap(dl, T->dl);
	std::swap(tab, T->tab);
	delete T;
	return *this;
}

tab_bit& tab_bit::operator& (tab_bit tb) {
	tab_bit* T = new tab_bit(max(dl, tb.dl));
	for (int i = 0; i < min(dl, tb.dl); i++) {
		if (czytaj(i) == 1 && tb[i] == 1) T->pisz(i, 1);
	}
	for (int i = min(dl, tb.dl); i < dl; i++)
		T->pisz(i, 0);
	for (int i = min(dl, tb.dl); i < tb.dl; i++)
		T->pisz(i, 0);
	return *T;
}

tab_bit& tab_bit::operator&= (tab_bit tb) {
	tab_bit* T = new tab_bit(1);
	*T = *this & tb;
	std::swap(dl, T->dl);
	std::swap(tab, T->tab);
	delete T;
	return *this;
}

tab_bit& tab_bit::operator^ (tab_bit tb) {
	tab_bit* T = new tab_bit(max(dl, tb.dl));
	for (int i = 0; i < min(dl, tb.dl); i++) {
		if (czytaj(i) != tb[i]) T->pisz(i, 1);
	}
	for (int i = min(dl, tb.dl); i < dl; i++)
		if(czytaj(i) == 1) T->pisz(i, 1);
	for (int i = min(dl, tb.dl); i < tb.dl; i++)
		if(tb[i] == 1) T->pisz(i, 1);
	return *T;
}

tab_bit& tab_bit::operator^= (tab_bit tb) {
	tab_bit* T = new tab_bit(1);
	*T = *this ^ tb;
	std::swap(dl, T->dl);
	std::swap(tab, T->tab);
	delete T;
	return *this;
}

tab_bit& tab_bit::operator! () {
	tab_bit* T = new tab_bit(dl);
	for (int i = 0; i < dl; i++) {
		if (czytaj(i) == 1) T->pisz(i, 0);
		else T->pisz(i, 1);
	}
	return *T;
}