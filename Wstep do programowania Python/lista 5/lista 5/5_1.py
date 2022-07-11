def F(n):
    if n % 2 == 0:
        return n / 2
    else:
        return 3 * n + 1

def mediana(tab):
    n=len(tab)
    if(n%2==0):
        return (tab[n//2]+tab[n//2-1])/2
    else:
        return tab[n//2]

def srednia(tab):
    srednia=0
    for i in tab:
        srednia+=i
    return srednia/len(tab)

def analiza_collatza(a,b):
    energie=[]   
    for i in range(a,b+1):
        a=i
        l=0
        while(a!=1):
            l+=1
            a=F(a)
        energie.append(l)
    energie.sort()
    print("mediana:",mediana(energie))
    print("średnia:",srednia(energie))
    print("maksymalna energia:", max(energie))
    print("minimalna energia:", min(energie))

analiza_collatza(1,1000)
