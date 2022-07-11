import urllib
import argparse

import pyodbc as pyodbc
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, ForeignKey, String, create_engine
from sqlalchemy.orm import relationship, sessionmaker

Base = declarative_base()


class Znajomi(Base):
    __tablename__ = 'Znajomi'

    ID = Column(Integer, primary_key=True, nullable=False, autoincrement=True)
    Imie = Column(String(15), nullable=False)
    Email = Column(String(50), nullable=False)
    ksiazki = relationship('Ksiazki')


class Ksiazki(Base):
    __tablename__ = 'Ksiazki'

    ID = Column(Integer, primary_key=True, nullable=False)
    Tytul = Column(String(50), nullable=False)
    Autor = Column(String(30), nullable=False)
    Rok = Column(Integer, nullable=False)
    Wypozyczono = Column(Integer, ForeignKey(Znajomi.ID))


def dodaj_ksiazke(sesja, tytul, autor, rok):
    ksiazka = Ksiazki(Tytul=tytul, Autor=autor, Rok=rok)
    sesja.add(ksiazka)
    sesja.commit()


def wypozycz_ksiazke(sesja, tytul, autor, imie, email=""):
    if (email == ""):
        listaOsob = sesja.query(Znajomi).filter(Znajomi.Imie == imie).all()
    else:
        listaOsob = sesja.query(Znajomi).filter(Znajomi.Imie == imie).filter(Znajomi.Email == email).all()

    listaKsiazek = sesja.query(Ksiazki).filter(Ksiazki.Tytul == tytul).filter(Ksiazki.Autor == autor).all()

    if len(listaOsob) <= 0:
        print("Nie znaleziono osoby.")
        return
    elif len(listaOsob) > 1:
        print("Znaleziono więcej niż jedną osobę, spróbuj podając dodatkowo email lub wersji z identyfikatorem osoby:")
        for i in listaOsob:
            print(str(i.ID) + " " + i.Imie + " " + i.Email)
        return

    if len(listaKsiazek) <= 0:
        print("Nie znaleziono książki.")
        return
    elif len(listaKsiazek) > 1:
        print("Znaleziono więcej niż jedną książkę, spróbuj podając identyfikator książki:")
        for i in listaKsiazek:
            print(str(i.ID) + " " + i.Tytul + " " + i.Autor + " " + str(i.Wypozyczono))
        return

    if (listaKsiazek[0].Wypozyczono != None):
        print("Książka wypożyczona")
        return

    listaOsob[0].ksiazki.append(listaKsiazek[0])
    sesja.commit()


def wypozycz_ksiazke_id(sesja, id_ksiazki, id_znajomego):
    listaOsob = sesja.query(Znajomi).filter(Znajomi.ID == id_znajomego).all()
    listaKsiazek = sesja.query(Ksiazki).filter(Ksiazki.ID == id_ksiazki).all()

    if len(listaOsob) <= 0:
        print("Nie znaleziono osoby.")
        return

    if len(listaKsiazek) <= 0:
        print("Nie znaleziono książki.")
        return

    if listaKsiazek[0].Wypozyczono != None:
        print("Książka wypożyczona")
        return

    listaOsob[0].ksiazki.append(listaKsiazek[0])
    sesja.commit()


def oddaj_ksiazke(sesja, tytul, autor, imie, email=""):
    if (email == ""):
        listaOsob = sesja.query(Znajomi).filter(Znajomi.Imie == imie).all()
    else:
        listaOsob = sesja.query(Znajomi).filter(Znajomi.Imie == imie).filter(Znajomi.Email == email).all()

    if len(listaOsob) <= 0:
        print("Nie znaleziono osoby.")
        return
    elif len(listaOsob) > 1:
        print("Znaleziono więcej niż jedną osobę, spróbuj podając dodatkowo email lub wersji z identyfikatorem osoby:")
        for i in listaOsob:
            print(str(i.ID) + " " + i.Imie + " " + i.Email)
        return

    listaKsiazek = sesja.query(Ksiazki).filter(Ksiazki.Tytul == tytul).filter(Ksiazki.Autor == autor).filter(
        Ksiazki.Wypozyczono == listaOsob[0].ID).all()

    if len(listaKsiazek) <= 0:
        print("Nie znaleziono książki o zadanym tytule i autorze lub oddano.")
        return
    elif len(listaKsiazek) > 1:
        print("Znaleziono więcej niż jedną książkę, spróbuj podając identyfikator książki:")
        for i in listaKsiazek:
            print(str(i.ID) + " " + i.Tytul + " " + i.Autor + " " + str(i.Wypozyczono))
        return

    listaKsiazek[0].Wypozyczono = None
    sesja.commit()


def oddaj_ksiazke_id(sesja, id_ksiazki):
    listaKsiazek = sesja.query(Ksiazki).filter(Ksiazki.ID == id_ksiazki).all()
    if len(listaKsiazek) <= 0:
        print("Nie znaleziono książki.")
        return
    if listaKsiazek[0].Wypozyczono is None:
        print("Książka oddana.")
        return
    listaKsiazek[0].Wypozyczono = None
    sesja.commit()


cnxn_str = ("Driver={SQL Server Native Client 11.0};"
            "Server=DESKTOP-CFDUH25\\MSSQLSERVER2;"
            "Database=Biblioteczka;"
            "Trusted_Connection=yes;")  # WINDOWS AUTHENTICATION

params = urllib.parse.quote_plus(cnxn_str)
engine = create_engine("mssql+pyodbc:///?odbc_connect=%s" % params)

Session = sessionmaker(bind=engine)
sesja = Session()

parser = argparse.ArgumentParser()
group = parser.add_mutually_exclusive_group()
group.add_argument("-d", "--dodajKsiazke", action="store_true", help="Dodaj książkę, podaj tytuł, autora, rok wydania")
group.add_argument("-w", "--wypozycz", action="store_true", help="Wypożycz podając tytuł, autora, imię i opcjonalnie email.")
group.add_argument("-W", "--wypozycz_id", action="store_true", help="Wypożycz podając ID książki i przyjaciela")
group.add_argument("-o", "--oddaj", action="store_true", help="Wypożycz podając tytuł, autora, imię i opcjonalnie email.")
group.add_argument("-O", "--oddajId", action="store_true", help="Wypożycz podając ID książki")

parser.add_argument("--Imie")
parser.add_argument("--Email")
parser.add_argument("--Tytul")
parser.add_argument("--Autor")
parser.add_argument("--idKsiazki", type=int)
parser.add_argument("--idPrzyjaciela", type=int)
parser.add_argument("--Rok", type=int)

args = parser.parse_args()

if args.dodajKsiazke:
    if args.Tytul is None or args.Autor is None or args.Rok is None:
        print("Nie wszystkie wymagane argumenty podane. Podaj Tytuł, Autora i Rok")
    else:
        dodaj_ksiazke(sesja, args.Tytul, args.Autor, args.Rok)
if args.wypozycz:
    if args.Tytul is None or args.Autor is None or args.Imie is None:
        print("Nie wszystkie wymagane argumenty podane. Podaj Tytuł, Autora, Imie i opcjonalnie Email.")
    else:
        if args.Email is None:
            wypozycz_ksiazke(sesja, args.Tytul, args.Autor, args.Imie)
        else:
            wypozycz_ksiazke(sesja, args.Tytul, args.Autor, args.Imie, email=args.Email)
if args.wypozycz_id:
    if args.idKsiazki is None or args.idPrzyjaciela is None :
        print("Nie wszystkie wymagane argumenty podane. Podaj ID Książki i ID Przyjaciela.")
    else:
        wypozycz_ksiazke_id(sesja, args.idKsiazki, args.idPrzyjaciela)
if args.oddaj:
    if args.Tytul is None or args.Autor is None or args.Imie is None:
        print("Nie wszystkie wymagane argumenty podane. Podaj Tytuł, Autora, Imie i opcjonalnie Email.")
    else:
        if args.Email is None:
            oddaj_ksiazke(sesja, args.Tytul, args.Autor, args.Imie)
        else:
            oddaj_ksiazke(sesja, args.Tytul, args.Autor, args.Imie, email=args.Email)
if args.oddajId:
    if args.idKsiazki is None :
        print("Nie wszystkie wymagane argumenty podane. Podaj ID Książki")
    else:
        oddaj_ksiazke_id(sesja, args.idKsiazki)


sesja.close()
