from math import sqrt
def pierwsze(n, lpierwsze):
    pierw=[]
    for i in range (n):
        pierw.append(i+1)
    pierw[0]=0
    f=int(sqrt(n))
    for i in range(1,f):
        if (pierw[i]>1):
            g=i
            for j in range(1,n//(i+1)):
                g+=pierw[i]
                pierw[g]=0
    for i in range (n):
        if (pierw[i]!=0):
            lpierwsze.append(i+1)
    return lpierwsze

def szczesliwe(lpierwsze):
    print("pierwsze liczby szczęśliwe:", end=" ")
    n=0
    for i in (lpierwsze):
        if ((str(777) in str(i))==True):
            print(i, end=", ")
            n+=1
    print()
    print("liczba szczęśliwych liczb pierwszych: ", n)
lpierwsze=[]
n=100000
pierwsze(n,lpierwsze)
szczesliwe(lpierwsze)