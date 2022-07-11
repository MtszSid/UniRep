from turtle import fd, bk, lt, rt,ht,  speed
from random import randint

speed('fastest')

def kwadracik(bok):
    for i in range(4):
        fd(bok)
        rt(90)
    lt(60)
    fd(bok)
    rt(120)
    fd(bok)
    lt(96)
        


for i in range(10):
    kwadracik(50)
    
#input()
