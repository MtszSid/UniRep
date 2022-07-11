
from turtle import *
import random



txt = """
...kkkkkkkkkkkk.......
......................
......................
......................
..nnnn................
..nnnn................
..n...................
......................
.........kkkkkkkkkkk..
......................
......................
......................
......................
.........ppppp........
......................

"""



tracer(0,0)
ht()
def move(x, y):
    pu()
    goto(x, y)
    pd()
    
def kwadrat(x,y, kolor):
    fillcolor(kolor)
    begin_fill()
    
    bok=10
    move(bok*x,bok*y)
    for i in range(4):
        fd(bok)
        rt(90)
    end_fill()

def walka(c,d):
    s={c,d}
    if (s=={'k','p'}):
        return 'p'
    if (s=={'n','p'}):
        return 'n'
    if (s=={'k','n'}):
        return 'k'


def dobre_pola(y,x):
    #22,15
    D=[]
    if (y<14):
        D.append((y+1,x))
    if(y>0):
        D.append((y-1,x))
    if(x>0):
        D.append((y,x-1))
    if(x<21):
        D.append((y,x+1))
    return D
tab = [ list(wiersz) for wiersz in txt.split()]
tab.reverse()

MY = len(tab)
MX = len(tab[0])
for i in range(MY):
    for j in range(MX):
        if (tab[i][j]!='.'):
            tab[i][j]=[tab[i][j],random.randint(1,5)]
        else:
            tab[i][j]=[tab[i][j],0]

def rysuj_plansze(tab):
    clear()
    for y in range(MY):
        for x in range(MX):
            kolor='gray'
            if tab[y][x][0] == 'k':
                kolor = 'green'
            elif tab[y][x][0] == 'p':
                kolor = 'blue' 
            elif tab[y][x][0] == 'n':
                kolor = 'red'
            kwadrat(x, y, kolor)
    update()        


   

            

nowa = tab[:]



while True:
    p=set()
    rysuj_plansze(tab)

    for y in range(MY):
        for x in range(MX):
           if (tab[y][x][0]!='.'):
               if (tab[y][x][0] not in p):
                   p.add(tab[y][x][0])
               D=dobre_pola(y,x)
               a=random.choice(D)
               
               if (tab[a[0]][a[1]][0]=='.' and tab[y][x][1]>=1):
                   
                   nowa[a[0]][a[1]]=[tab[y][x][0],tab[y][x][1]-1]
               elif(tab[a[0]][a[1]][0]!=tab[y][x][0] and tab[a[0]][a[1]][0]!='.'):
                   if (walka(tab[a[0]][a[1]][0],tab[y][x][0])==tab[y][x][0]):
                       if(tab[y][x][1]<5):
                           nowa[y][x][1]+=1
                       nowa[a[0]][a[1]][1]-=1
                       if(nowa[a[0]][a[1]][1]<1):
                           nowa[a[0]][a[1]]=['.',0]
                   else:
                       if(tab[a[0]][a[1]][1]<5):
                           nowa[a[0]][a[1]][1]+=1
                       nowa[y][x][1]-=1
                       if(nowa[y][x][1]<1):
                           nowa[y][x]=['.',0]

    tab = nowa  
    if len(p)==1:
        break
    
       

print ('Koniec')
input()


