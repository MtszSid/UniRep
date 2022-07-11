//Mateusz Sid³o
#pragma once
#include <initializer_list>
#include <iostream>
//#include <cstdlib>


class Invalid_Index{
};

template <typename T>
class List {
private:
	
	//
	// Node
	//

	class Node 
	{
	public:
		T value;
		Node* next;

		Node() = default;

		Node(T el)
		{
			value = el;
			next = NULL;
		}
		
		Node(T el, Node& next)
		{
			value = el;
			next = next;
		}


		~Node() {
			delete next;
		}
	};


private:
	Node* first;
public:

	//
	//Konstruktory
	//

	List()
	{
		first = NULL;
	}
	
	List(const List& L)
	{
		Node* P = L.first;
		if (P == NULL) {
			first = NULL;
			return;
		}
		first = new Node(P->value);
		P = P->next;
		Node* Pom = first;
		while (P != NULL) {
			Pom->next = new Node(P->value);
			P = P->next;
			Pom = Pom->next;
		}

	}

	List(List&& L) noexcept
	{
		first = L.first;
		L.first = NULL;
	}

	List(std::initializer_list<T> L)
	{
		first = NULL;
		Node* P = NULL;
		for (T i : L) {
			if (first == NULL) {
				first = new Node(i);
				P = first;
			}
			else {
				P->next = new Node(i);
				P = P->next;
			}
		}
	}

	~List() {
		delete first;
	}

	//
	// Operatory przypisania i kopiuj¹cy
	//

	
	List<T>& operator=(const List& L)
	{

		List<T>* P = new List<T>(L);

		std::swap(first, P->first);

		delete P;
		return *this;
	}

	List<T> &operator=(const List&& L) noexcept
	{
		first = std::move(L.first);
		return *this;
	}

	//
	// Operacje na liscie
	//
	
	void push(T elem)
	{
		if (first != NULL) {
			Node* P = first;
			while (P->next != NULL)
				P = P->next;
			P->next = new Node(elem);
		}
		else {
			first = new Node(elem);
		}
	}

	void set(int position, T elem)
	{
		Node* P = first;
		while (position != 0) {
			if (P == NULL) {
				throw (Invalid_Index());
			}
			position--;
			P = P->next;
		}
		if (P == NULL) {
			throw (Invalid_Index());
		}
		else {
			P->value = elem;
		}
	}

	void delete_elem(T elem)
	{
		Node* P = first;
		if (P == NULL)
			return;
		if (P != NULL && P->value == elem) {
			first = P->next;
			return;
		}
		while (P->next != NULL) {
			if (P->next->value == elem) {
				Node* R = P->next;
				P->next = R->next;
				delete R;
				return;
			}
			P = P->next;
		}
	}

	bool in_list(T elem)
	{
		Node* P = first;
		while (P != NULL) {
			if (P->value == elem) {
				return true;
			}
			P = P->next;
		}
		return false;
	}

	int length()
	{
		Node* P = first;
		int len = 0;
		while (P != NULL) {
			P = P->next;
			len++;
		}
		return len;
	}


	template<typename m>
	friend std::ostream& operator<<(std::ostream& wyj, const List<m> L);

	template<typename R, typename C >
	friend bool check(const List<R>& L);

	template<typename R, typename C >
	friend void sort(List<R>& L);
};




//
//operator strumieniowy
//

template<typename T>
std::ostream& operator<<(std::ostream& wyj, const List<T> L)
{
	typename List<T>::Node* P = L.first;

	wyj << "[ ";
	while (P != NULL) {
		wyj << P->value << ", ";
		P = P->next;
	}
	wyj << "]";

	return wyj;
}



template <typename T>
class por_malej 
{
public:
	static bool por(const T& a, const T& b) 
	{
		if (a >= b)
			return true;
		return false;
	}
};

template <typename T>
class por_rosn
{
public:
	static bool por(const T& a, const T& b)
	{
		if (a <= b)
			return true;
		return false;
	}
};


//
// Check
//

template<typename T, typename C = por_rosn<T>>
bool check(const List<T> &L) 
{
	if (L.first == NULL)
		return true;
	typename List<T>::Node* P1 = L.first;
	typename List<T>::Node* P2 = P1->next;
	while(P2 != NULL) {
		if (C::por(P1->value, P2->value) == false)
			return false;
		P1 = P1->next;
		P2 = P2->next;
	}
	return true;
}


//
// Sort
//

template<typename T, typename C = por_rosn<T>>
void sort(List<T>& L)
{
	if (L.first == NULL || L.first->next == NULL)
		return;

	typename List<T>::Node* M = L.first; //Max/Min
	typename List<T>::Node* Lst = L.first; //Lista do sortowania
	typename List<T>::Node* P1 = Lst->next;
	T P_val;

	while (Lst->next != NULL) {
		while (P1 != NULL) {
			if (C::por(M->value, P1->value) == false) {
				M = P1;
			}
			P1 = P1->next;
		}
		P_val = Lst->value;
		Lst->value = M->value;
		M->value = P_val;

		Lst = Lst->next;
		P1 = Lst->next;
		M = Lst;
	}
}