
def zle_slowo(w):
    return any(znak in w for znak in '"„”,():/–—.-…!?')

def waga(czestosci,W):
    czeste_slowa = sorted( czestosci.keys(), key=lambda x:(czestosci[x][0]*czestosci[x][1]**W)) 
    czeste_slowa.reverse()
    print('Alfa=',W)
    for w in czeste_slowa[:10]:
        print (w)
    print()


czestosci = {}
for x in open('lalka-tom-pierwszy.txt', encoding='utf-8'):
    for slowo in x.split():
        slowo = slowo.lower() # z tym poleceniem utożsamiamy małe i duże litery
        if zle_slowo(slowo):
            pomoc=0
            for i in range(len(slowo)):
                if zle_slowo(slowo[i]):
                    a=slowo[pomoc:i]
                    pomoc=i+1
                    if a in czestosci: #klucz w słowniku?
                        czestosci[a][0] += 1 # c[slowo] = c[slowo] + 1
                    elif(a!=""):
                        czestosci[a] = [1,len(a)] 
        
        
        
        elif slowo in czestosci: #klucz w słowniku?
            czestosci[slowo][0] += 1 # c[slowo] = c[slowo] + 1
        else:
            czestosci[slowo] = [1,len(slowo)]    


waga(czestosci,1)
waga(czestosci,2)
waga(czestosci,4)
waga(czestosci,5)
waga(czestosci,10)
  
    

