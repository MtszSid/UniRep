import urllib

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, ForeignKey, String, create_engine, DateTime, func, Text
from sqlalchemy.orm import relationship, sessionmaker, validates

Base = declarative_base()


class Osoba(Base):
    __tablename__ = 'Osoba'
    id = Column(Integer, primary_key=True)
    imie = Column(Text, nullable=False)
    wiek = Column(Integer, default=18)
    created = Column(DateTime, server_default=func.now())
    adresy = relationship('Adres')


class Adres(Base):
    __tablename__ = 'Adresy'
    id = Column(Integer, primary_key=True)
    email = Column(String)
    miasto = Column(String)
    mieszkaniec = Column(Integer, ForeignKey("Osoba.id"))

    @validates('email')
    def validate_email(self, key, value):
        assert "@" in value
        return value


engine = create_engine('sqlite:///wyklad.db', echo=True)
#Base.metadata.create_all(engine)

Session = sessionmaker(bind=engine)
sesja = Session()

o = Osoba(imie='Debeściak')
adr1 = Adres(email='Joliot@Curie', miasto='Wrocław')
o.adresy = [adr1]
sesja.add(o)
sesja.add(adr1)
sesja.commit()

lista = sesja.query(Osoba).filter(Osoba.imie.in_(['Debeściak'])).all()
print(lista)
lista = sesja.query(Osoba).filter(Osoba.imie == 'Debeściak').all()
print(lista)
sesja.close()
