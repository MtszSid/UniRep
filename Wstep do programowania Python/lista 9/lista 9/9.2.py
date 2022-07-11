from turtle import *
def step(b):
    fd(b+2)
    lt(90)
    fd(b)
    rt(90)
    fd(b)
    rt(90)
    fd(b)
    lt(90)
    fd(b+2)
tracer(0,0)

def rys(b,n):
    if (n>0):
        rys(b/3,n-1)
        lt(90)
        rys(b/3,n-1)
        rt(90)
        rys(b/3,n-1)
        rt(90)
        rys(b/3,n-1)
        lt(90)
        rys(b/3,n-1)
        
    else:
        step(b)



       
for i in range(4):
    rys(81,3)
    rt(90)
update()
input()