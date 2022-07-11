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

def palindromy(a,b):
    lpierwsze=[]
    pierwsze(b,lpierwsze)
    print("lczby pierwsze będące palindromami:", end=" ")
    for i in lpierwsze:
        i2=int(str(i)[::-1])
        if (i>a and i==i2):
            print(i,end=", ")
    print()
    


palindromy(10,10000)
