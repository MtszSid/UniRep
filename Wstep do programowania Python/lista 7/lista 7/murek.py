from turtle import *
from random import randint
colormode(255)
def kwadrat(bok):
    begin_fill()
    for i in range(4):
        fd(bok)
        rt(90)
    end_fill()
    
def murek(s,bok):
    for a in s:
        if a == 'f':
            kwadrat(bok)
            fd(bok)
        elif a == 'b':
            kwadrat(bok)
            fd(bok)         
        elif a == 'l':
            bk(bok)
            lt(90)
        elif a == 'r':
            rt(90)
            fd(bok)
        elif a=='0':
            color((0,0,0),(204,153,255))
        elif a=='1':
            color((0,0,0),(255,102,102))
        elif a=='2':
            color((0,0,0),(102,0,81))
        elif a=='3':
            color((0,0,0),(255,0,107))
        elif a=='4':
            color((0,0,0),(255,153,51))
        elif a=='5':
            color((0,0,0),(153,51,255))



        
color('black', 'yellow')

ht()

tracer(0,0) # szybkie rysowanie     
#murek('fffffffffrfffffffffflfffffffffrfffffl',10)
murek('0f1fr2f3fr4f5f0fr1f2f3f4fr5f0f1f2f3fr4f5f0f1f2f3fr4f5f0f1f2f3f4fr5f0f1f2f3f4f5f0fr1f2f3f4f5f0f1f2f3fr4f5f0f1f2f3f4f5f0f1fr2f3f4f5f0f1f2f3f4f5f0f',10)    
murek(4 * '0f1f0f1f0f1fr', 10)

    
update() # uaktualnienie rysunku

input()
