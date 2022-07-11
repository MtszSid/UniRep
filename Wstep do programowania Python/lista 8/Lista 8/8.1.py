def zle_slowo(w):
    return any(znak in w for znak in '"„”,():/–—.-…!?')



def tlumacz(zdanie):
    wynik = []
    for w in zdanie:
        if w in pol_ang:
            max=('',0)
            for T in pol_ang[w]:
                Tslowo=(T,0)
                for x in open('brown.txt', encoding='utf-8'):
                    for slowo in x.split():
                        slowo = slowo.lower()
                        if T in slowo:
                            print()
                        wynik.append()
        else:
            wynik.append('[?]')
    return ' '.join(wynik)








pol_ang = {}  
for wiersz in open('pol_ang.txt'):
    # wiersz = wiersz[:-1] # trochę gorsze
    wiersz = wiersz.strip()
    if '=' not in wiersz:
        continue
    pola = wiersz.split('=')
    if len(pola) != 2:
        continue
    p, a = pola
    if p in pol_ang:
        pol_ang[p].append(a)
    else:
        pol_ang[p] = [a]  


    
print (len(pol_ang))

zdanie = 'chłopiec i dziewczyna pójść do kino'.split()

print (tlumacz(zdanie))

