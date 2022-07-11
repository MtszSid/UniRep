from math import sqrt
def licz(liczby, n, hiper):
    for i in range(1, n//10**len(str(hiper))):
        g=str(i)+str(hiper)
        if (len(g)<len(str(n))-1):
            for j in range(0, n//hiper):
                g=str(i)+str(hiper)
                while (len(str(n))-1-len(g)-len(str(j))>0):
                    g+='0'
                if(len(g)+len(str(j))<=(len(str(n))-1)):
                    w=g
                    w+=str(j)
                    liczby.append(int(w))
                
        else:
            liczby.append(int(g))
    return liczby






def pierwsze(liczby, pierw):
    for j in liczby:
        Tak=True
        for i in range(2, int(sqrt(j))+1):
            if (j%i==0):
                Tak=False
                break
        if(Tak==True):
            pierw.append(j)
    return pierw



liczby=[]
pierw=[]
hiper=7777777
n=10000000000


licz(liczby, n, hiper)
pierwsze(liczby, pierw)
print("hiperszęśliwe liczby pierwsze:")
for i in pierw:
	print(i)


