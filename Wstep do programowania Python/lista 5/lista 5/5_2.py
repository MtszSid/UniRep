from turtle import rt, lt, fd, speed, pu, pd, goto
from random import randint
speed('fastest')
def prostokat(a,b):
    lt(90)
    fd(b)
    rt(90)
    fd(a)
    rt(90)
    fd(b)
    rt(90)
    fd(a)
    rt(180)
    fd(a)
pu()
goto(-200,0)
pd()
for i in range(40):
    prostokat(10,randint(10,150))

