from losowanie_fragmentow import losuj_fragment
def losuj_haslo(n):
    while (n>5):
        f=losuj_fragment()
        n=n-len(f)
        print(f," ",end='')
    while (n==5):
        f=losuj_fragment()
        if (len(f)!=4):
            n=n-len(f)
            print(f," ",end='')
        else:
            continue
    while (n==4):
        f=losuj_fragment()
        if (len(f)!=3):
            n=n-len(f)
            print(f," ",end='')
        else:
            continue
    while (n==3):
        f=losuj_fragment()
        if (len(f)==3):
            n=n-len(f)
            print(f," ",end='')
        else:
            continue
    while (n==2):
        f=losuj_fragment()
        if (len(f)==2):
            n=n-len(f)
            print(f," ",end='')
        else:
            continue
    print()


for i in range (10):
    losuj_haslo(8)
for i in range (10):
    losuj_haslo(12)