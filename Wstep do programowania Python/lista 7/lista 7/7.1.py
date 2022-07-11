from turtle import *

colormode(255)



def move(x, y):
    pu()
    goto(x, y)
    pd()

def kwadrat(x,y,kolor,bok):
    move(bok*x,bok*y)
    pencolor(kolor)
    fillcolor(kolor)
    begin_fill()
    fd(bok)
    rt(90)
    fd(bok)
    rt(90)
    fd(bok)
    rt(90)
    fd(bok)
    rt(90)
    end_fill()


def rysoj(T):
    a=len(T)
    for i in range(a):
        for j in range(len(T[i])):
            kwadrat(i,-j,eval(T[i][j]),5)


tracer(0,0)
dane=open('niespodzianka.txt')
obraz=[]

for w in dane:
    obraz.append(list(w.split()))

rysoj(obraz)

update()