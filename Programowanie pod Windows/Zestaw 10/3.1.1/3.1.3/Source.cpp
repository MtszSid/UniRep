
#include <windows.h>
#include <string.h>
#include <iostream>

WNDPROC lpEditOldWndProc = NULL;
LRESULT CALLBACK WindowProcedure(HWND, UINT, WPARAM, LPARAM);

/* Nazwa klasy okna */
char szClassName[] = "PRZYKLAD";
int WINAPI WinMain(HINSTANCE hThisInstance, HINSTANCE hPrevInstance,
	LPSTR lpszArgument, int nFunsterStil)
{
	HWND hwnd; /* Uchwyt okna */
	MSG messages; /* Komunikaty okna */
	WNDCLASSEX wincl; /* Struktura klasy okna */
	/* Klasa okna */
	wincl.hInstance = hThisInstance;
	wincl.lpszClassName = szClassName;
	wincl.lpfnWndProc = WindowProcedure; // wskaŸnik na funkcjê obs³ugi okna
	wincl.style = CS_DBLCLKS;
	wincl.cbSize = sizeof(WNDCLASSEX);
	/* Domyœlna ikona i wskaŸnik myszy */
	wincl.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wincl.hIconSm = LoadIcon(NULL, IDI_APPLICATION);
	wincl.hCursor = LoadCursor(NULL, IDC_ARROW);
	wincl.lpszMenuName = NULL;
	wincl.cbClsExtra = 0;
	wincl.cbWndExtra = 0;
	/* Jasnoszare t³o */
	wincl.hbrBackground = (HBRUSH)GetStockObject(LTGRAY_BRUSH);
	/* Rejestruj klasê okna */
	if ( !RegisterClassEx(&wincl) ) return 0;
	/* Twórz okno */
	hwnd = CreateWindowEx(
		WS_EX_CLIENTEDGE,
		szClassName,
		"Wybór uczelni",
		WS_OVERLAPPED | WS_SYSMENU,
		CW_USEDEFAULT, CW_USEDEFAULT, 400, 310,
		NULL, NULL, hThisInstance, NULL
	);
	ShowWindow(hwnd, nFunsterStil);
	/* Pêtla obs³ugi komunikatów */
	while ( GetMessage(&messages, NULL, 0, 0) )
	{
		/* T³umacz kody rozszerzone */
		TranslateMessage(&messages); 
			/* Obs³u¿ komunikat */
			DispatchMessage(&messages);
	}
	/* Zwróæ parametr podany w PostQuitMessage( ) */
	return messages.wParam;
}
int xSize, ySize;
/* Tê funkcjê wo³a DispatchMessage( ) */
LRESULT CALLBACK WindowProcedure(HWND hwnd, UINT message,
	WPARAM wParam, LPARAM lParam)
{
	static HWND hwndEdit;
	static HWND hwnd_nazwa;
	static HWND hwnd_nazwa_text;
	static HWND hwnd_adres;
	static HWND hwnd_adres_text;
	static HWND hwnd_cykl;
	static HWND hwnd_dzienne;
	static HWND hwnd_uzupel;
	static HWND hwnd_combo;
	static int cxChar, cyChar;
	static RECT r;
	TCHAR t;
	HDC hdc;
	int i;
	PAINTSTRUCT ps;
	
	switch ( message )
	{
	case WM_CREATE:
		CreateWindowW(L"Button", L"Uczelnia",
			WS_CHILD | WS_VISIBLE | BS_GROUPBOX,
			10, 10, 360, 100, hwnd, (HMENU)0, ((LPCREATESTRUCT)lParam)->hInstance, NULL);
		
		hwnd_nazwa = CreateWindow("static", "ST_U",
			WS_CHILD | WS_VISIBLE | WS_TABSTOP,
			30, 40, 50, 20,
			hwnd, (HMENU)(501),
			(HINSTANCE)GetWindowLong(hwnd, GWL_HINSTANCE), NULL);
		SetWindowText(hwnd_nazwa, "Nazwa:");

		hwnd_nazwa_text = CreateWindow("Edit", "",
			WS_CHILD | WS_VISIBLE | WS_BORDER, 
			125, 40, 220, 20,
			hwnd, (HMENU)301,
			((LPCREATESTRUCT)lParam)->hInstance, NULL);

		hwnd_adres = CreateWindow("static", "ST_U",
			WS_CHILD | WS_VISIBLE | WS_TABSTOP,
			30, 70, 50, 20,
			hwnd, (HMENU)(502),
			(HINSTANCE)GetWindowLong(hwnd, GWL_HINSTANCE), NULL);
		SetWindowText(hwnd_adres, "Adres:");

		hwnd_adres_text = CreateWindow("Edit", "",
			WS_CHILD | WS_VISIBLE | WS_BORDER,
			125, 70, 220, 20,
			hwnd, (HMENU)302,
			((LPCREATESTRUCT)lParam)->hInstance, NULL);
		
		CreateWindowW(L"Button", L"Rodzaj studiów",
			WS_CHILD | WS_VISIBLE | BS_GROUPBOX,
			10, 120, 360, 100, hwnd, (HMENU)0, ((LPCREATESTRUCT)lParam)->hInstance, NULL);

		hwnd_cykl = CreateWindow("static", "ST_U",
			WS_CHILD | WS_VISIBLE | WS_TABSTOP,
			30, 150, 90, 20,
			hwnd, (HMENU)(501),
			((LPCREATESTRUCT)lParam)->hInstance, NULL);
		SetWindowText(hwnd_cykl, "Cykl nauki:");

		hwnd_combo = CreateWindow("COMBOBOX", "MyCombo1",
			CBS_DROPDOWN | CBS_HASSTRINGS | WS_CHILD | WS_OVERLAPPED | WS_VISIBLE,
			125, 148, 220, 160,
			hwnd, (HMENU)100, 
			((LPCREATESTRUCT)lParam)->hInstance, NULL);
		
		SendMessage(hwnd_combo, (UINT)CB_ADDSTRING, (WPARAM)0, (LPARAM)"2-letnie");
		SendMessage(hwnd_combo, (UINT)CB_ADDSTRING, (WPARAM)0, (LPARAM)"2,5-letnie");
		SendMessage(hwnd_combo, (UINT)CB_ADDSTRING, (WPARAM)0, (LPARAM)"3-letnie");
		SendMessage(hwnd_combo, (UINT)CB_ADDSTRING, (WPARAM)0, (LPARAM)"3,5-letnie");
		SendMessage(hwnd_combo, (UINT)CB_ADDSTRING, (WPARAM)0, (LPARAM)"4-letnie");
		SendMessage(hwnd_combo, (UINT)CB_ADDSTRING, (WPARAM)0, (LPARAM)"5-letnie");
		SendMessage(hwnd_combo, CB_SETCURSEL, (WPARAM)6, (LPARAM)0);

		CreateWindow(TEXT("button"), TEXT("dzienne"),
			WS_VISIBLE | WS_CHILD | BS_CHECKBOX,
			30, 180, 90, 20,
			hwnd, (HMENU)1, 
			((LPCREATESTRUCT)lParam)->hInstance, NULL);

		CreateWindow(TEXT("button"), TEXT("uzupe³niaj¹ce"),
			WS_VISIBLE | WS_CHILD | BS_CHECKBOX,
			130, 180, 140, 20,
			hwnd, (HMENU)2, 
			((LPCREATESTRUCT)lParam)->hInstance, NULL);
		
		CreateWindow( "BUTTON", "Anuluj",      
			WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,  
			250, 230, 120, 25, 
			hwnd, (HMENU)601, 
			((LPCREATESTRUCT)lParam)->hInstance, NULL);

		CreateWindow("BUTTON", "Akceptuj",      
			WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,  
			10, 230, 120, 25,
			hwnd, (HMENU)602,
			((LPCREATESTRUCT)lParam)->hInstance, NULL);

		break;
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	case WM_COMMAND:
	{

		if ( LOWORD(wParam) == 601 )
		{
			PostQuitMessage(0);
			break;
		}
		else if ( LOWORD(wParam) == 602 )
		{
			
			TCHAR S[1024];
			std::string W = "";
			GetWindowTextA(hwnd_nazwa_text, S, 1024);
			W += std::string((char*)S) + "\n";
			GetWindowTextA(hwnd_adres_text, S, 100);
			W += std::string((char*)S) + "\n";
			GetWindowTextA(hwnd_combo, S, 100);
			W += std::string((char*)S) + "\n";

			W += (IsDlgButtonChecked(hwnd, 1) ? "Dzienne\n" : "");
			W += (IsDlgButtonChecked(hwnd, 2) ? "Uzupe³niaj¹ce\n" : "");
			MessageBox(
				NULL,
				(LPSTR)W.c_str(),
				"Uczelnia",
				MB_OK
			);
			
			break;
		}
		else
		{
			BOOL checked = IsDlgButtonChecked(hwnd, LOWORD(wParam));
			if ( checked )
			{
				CheckDlgButton(hwnd, LOWORD(wParam), BST_UNCHECKED);
			}
			else
			{
				CheckDlgButton(hwnd, LOWORD(wParam), BST_CHECKED);
			}
			break;
		}
		
	}
	
	case WM_PAINT:
		hdc = BeginPaint(hwnd, &ps);
		EndPaint(hwnd, &ps);
		break;
	default:
		return DefWindowProc(hwnd, message, wParam, lParam);
	}
	return 0;
}
