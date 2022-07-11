def wybieranie(slowo):
    Litery={}
    wybieralne=set()
    for l in slowo:
        if l in Litery:
            Litery[l]+=1
        else:
            Litery[l]=1
    
    for z in open('popularne_slowa.txt', encoding='utf-8'):
        y=z.split()
        x=y[0]
        Pom=Litery.copy()
        D=1
        #print (x)
        for i in x:
            if i in Pom:
                Pom[i]-=1
                if (Pom[i]<0):
                    D=0
                    break
            elif i not in Pom:
                D=0
                break
        if (D==1 and x!=slowo):
           
            wybieralne.add(x)
    return wybieralne

Slowo='Groch'
print(Slowo,'\n',wybieranie(Slowo))
