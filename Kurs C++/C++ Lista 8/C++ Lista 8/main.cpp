//Mateusz Sid³o
#include "Lista.hpp"

int main() {
	List<int>* Test_list = new List<int>();
	bool loop = true;
	short opcja;
	int a;
	int b;
	while (loop) {
		std::cout << "Co chcesz zrobic?" << std::endl << "1 - push, 2 - set, 3 - delete_elem, 4 - in_list, 5 - length, 6 - write_out, 7 - check_incr, 8 - check_decr, 9 - sort_inc, 10 - sort_dec, 11 - exit" << std::endl;
		std::cin >> opcja;
		switch (opcja) {
		case(11):
			delete Test_list;
			loop = false;
			break;
		case(1):
			std::cin >> a;
			Test_list->push(a);
			break;
		case(2):
			std::cin >> a >> b;
			try {
				Test_list->set(a, b);
			}
			catch (Invalid_Index) {
				std::cout << "Invalid index " << a << std::endl;
			}
			break;
		case(3):
			std::cin >> a;
			Test_list->delete_elem(a);
			break;
		case(4):
			std::cin >> a;
			if (Test_list->in_list(a))
				std::cout << a << " is in the list" << std::endl;
			else
				std::cout << a << " is not in the list" << std::endl;
			break;
		case(5):
			std::cout << "length = " << Test_list->length() << std::endl;
			break;
		case(6):
			std::cout << *Test_list << std::endl;
			break;
		case(7):
			if (check<int, por_rosn<int>>(*Test_list))
				std::cout << "Increasing" << std::endl;
			else
				std::cout << "Not increasing" << std::endl;
			break;
		case(8):
			if (check<int, por_malej<int>>(*Test_list))
				std::cout << "Decreasing" << std::endl;
			else
				std::cout << "Not decreasing" << std::endl;
			break;
		case(9):
			sort<int, por_rosn<int>>(*Test_list);
			break;
		case(10):
			sort<int, por_malej<int>>(*Test_list);
			break;
		}
	}
}