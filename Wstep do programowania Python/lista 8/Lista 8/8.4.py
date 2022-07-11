from random import randint
from turtle import *
tracer(0,0)
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

def rysoj(L):

    kolory = ['green', (0.5, 1, 0) , 'yellow', 'orange', 'red', (0.5, 0,0) ]
    for i in range(100):
        for j in range(100):
            kwadrat(i,-j,kolory[L[i][j]],3)
L=[]
for i in range(100):
    L.append([])
    for j in range(100):
        L[i].append(0)

for i in range(500):
    a=randint(0,99)
    b=randint(0,99)
    L[a][b]=randint(8,14)


for i in range(70000):
    a=randint(0,99)
    b=randint(0,99)
    a1=(a-1)%100
    a2=(a+1)%100
    b1=(b+1)%100
    b2=(b-1)%100
    w=(2*L[a1][b]+2*L[a2][b]+2*L[a][b1]+2*L[a][b2]+4*L[a][b]+L[a1][b1]+L[a2][b1]+L[a2][b2]+L[a1][b2])/16
    L[a][b]=w

for i in range(100):
    a=i
    for j in range(100):
        b=j
        if (L[a][b]<0.5):
            L[a][b]=0
        elif (L[a][b]<1):
            L[a][b]=1
        elif (L[a][b]<1.5):
            L[a][b]=2
        elif (L[a][b]<2):
            L[a][b]=3
        elif (L[a][b]<2.5):
            L[a][b]=4
        else:
            L[a][b]=5

rysoj(L)
update()