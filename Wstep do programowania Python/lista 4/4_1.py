from random import random, randint, uniform
from turtle import fd, bk, lt, rt,ht,  speed, pencolor, fillcolor, begin_fill, end_fill, pu, goto, pd, circle
import numpy as np


speed('fastest')


    
def prostokat(bok, kolor):

    fillcolor(kolor)
    begin_fill()
    for i in range(2):
        fd(bok)
        rt(90)
        fd(10)
        rt(90)
    end_fill()
 
     
kolor=['red','orange','green','blue']

for i in range(36):
    prostokat(100+3*i, kolor[i%4])
    rt(90)
    fd(5)
    lt(100)
pu()
goto(30,-60)
pd()
fillcolor('yellow')
begin_fill()
circle(60)
end_fill()
