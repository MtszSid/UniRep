from turtle import *
import random
import numpy as np
from duze_cyfry import daj_cyfre

tracer(0,1)

    
BOK = 20

def move(x, y):
    pu()
    goto(x, y)
    pd()
    
def kwadrat(x,y,kolor,bok):
    move(bok*x,bok*y)
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
    

uzyte = set()
Kolory={}
for i in range(1000):
    l=random.randint(0,9)
    L=daj_cyfre(l)
    x = random.randint(-15,15)
    y = random.randint(-15,15)
    bok=10
    
    nowe = { (nx, 4-ny) for nx in range(x, x + 5)
                      for ny in range(y, y + 5)
                      if  L[int(ny-y)][int(nx-x)]=="#"} 
                      
    if len(nowe & uzyte) == 0:
        uzyte.update(nowe)
        # inna notacja: uzyte |= nowe  
        # bardzo podobnie: uzyte = uzyte | nowe
        Kolory_nowe=set()
        for xk,yk in nowe:
            if(str((xk+1,yk+1)) in Kolory):
                Kolory_nowe.add(Kolory[str((xk+1,yk+1))])
            if(str((xk-1,yk+1)) in Kolory):
                Kolory_nowe.add(Kolory[str((xk-1,yk+1))])
            if(str((xk+1,yk-1)) in Kolory):
                Kolory_nowe.add(Kolory[str((xk+1,yk-1))])
            if(str((xk-1,yk-1)) in Kolory):
                Kolory_nowe.add(Kolory[str((xk-1,yk-1))])

        
        kolor = {'red', 'blue', 'yellow','green', 'magenta', 'pink', 'purple'}  
        kolorek=random.choice(list(kolor-Kolory_nowe))
        for xk, yk in nowe:
            kwadrat(xk, yk, kolorek,bok)
            Kolory[str((xk, yk))]=kolorek
    update()        

print ("Koniec!")            
input()    
