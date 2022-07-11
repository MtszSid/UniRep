def podzialy(L):
    if L==[]:
        return [[set()]]
    elif len(L)==1:
        return [L]
    l=[]
    
    for i in podzialy(L[1:]):
       
       
        for j in range (0, len(i)+1):
            b=i.copy()
            
            if (j<len(i)):
                c=b[j].union(set())
                b[j]=c.union(L[0])
            else:
                b.append(L[0])
            l.append(b)
            

    return l
            


Relacja={1,2,3}
F=[set((i,)) for i in Relacja]


print(podzialy(F))