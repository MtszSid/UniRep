//Mateusz Sid³o

#pragma once
#include <iostream>
#include<string>
#include<initializer_list>
#include<utility>

class stack {
	int pojemnosc;
	int ile;
	std::string* tablica;
public:
	void wloz(std::string);
	std::string sciagnij();
	std::string sprawdz();
	stack odwroc(const stack&);
	int rozmiar();
	stack(int);
	stack();
	stack(const stack&);
	stack(const stack&&);
	stack(std::initializer_list<std::string>);
	~stack();
	//stack& operator=(const stack& P);
	//stack& operator=(const stack&&);
};