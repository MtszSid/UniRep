from turtle import *
from random import randint
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
    pom=0
    while(T!=[]):
        pom+=1
        i=randint(0,len(T)-1)
        j=randint(0,len(T[i])-1)
        g=T[i].pop(j)
        
        kwadrat(g[1][0],-g[1][1],g[0],5)
        if (T[i]==[]):
            T.pop(i)
        if(pom==25):
            pom=0
            update()




tracer(0,0)
dane=open('niespodzianka.txt')
obraz=[]

for w in dane:
    obraz.append(list(w.split()))
a=len(obraz)
for i in range(a):
    for j in range(len(obraz[i])):
        obraz[i][j]=[eval(obraz[i][j]),(i,j)]

rysoj(obraz)

update()
