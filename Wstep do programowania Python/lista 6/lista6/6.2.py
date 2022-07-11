
def odwrotne(slowa):
    pomoc=slowa.union(set())
    odw=set()
    pomoc2=set()
    for i in slowa:
        n=len(pomoc)
        i1=i[::-1]
        pomoc.add(i1)
        if(len(pomoc)==n and i1!=i and pomoc2.intersection({i})==set()):
            odw.add((i,i1))
            pomoc2=pomoc2.union({i,i1})
    return odw


caly_tekst=open('slowa.txt', encoding='utf8').read()

#slowa=caly_tekst.split()
zb_slow=set(caly_tekst.split())
"""for i in slowa:
    zb_slow.add(i)"""
   
   
print(odwrotne(zb_slow))