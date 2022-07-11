from turtle import rt, lt, pu, pd, goto, fd, speed, pencolor, fillcolor, begin_fill, end_fill, colormode
from duze_cyfry import daj_cyfre
from random import randint
def kwadrat(bok):
    lt(90)
    begin_fill()
    fd(bok)
    rt(90)
    fd(bok)
    rt(90)
    fd(bok)
    rt(90)
    fd(bok)
    end_fill()
    rt(180)
    fd(bok)



speed('fastest')
colormode(255)
a=randint(10000,10000000)
liczba=[]
bok=15
pu()
goto(-300,0)
for i in str(a):
    liczba=daj_cyfre(int(i))
    fillcolor(randint(0,255),randint(0,255),randint(0,255))
    for j in liczba:
        
        for k in j:
            if (k==' '):
                pu()
                fd(bok)
            else:
                pd()
                kwadrat(bok)
        pu()
        rt(90)
        fd(bok)
        rt(90)
        fd(5*bok)
        rt(180)
    pu()
    fd(5*bok+5)
    lt(90)
    fd(5*bok)
    rt(90)

input()