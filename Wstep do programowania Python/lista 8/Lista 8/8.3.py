def wybieranie(slowo):
    Litery={}
    wybieralne=set()
    for l in slowo:
        l=l.lower()
        if l in Litery:
            Litery[l]+=1
        elif(l!=' '):
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
    return Litery, wybieralne

def zagadka(Litery, wybieralne):
    pary=set()
    W2=wybieralne.copy()
    for i in W2:
        L2=Litery.copy()
        
        for a in i:
            L2[a]-=1
            if (L2[a]==0):
                del L2[a]
        
        
       
        
        wyberalne=wybieralne-{i}
       
        for j in wybieralne:
            
            Pom=L2.copy()
            D=1
            
            for a in j:
                
                if a in Pom:

                    Pom[a]-=1
                    if (Pom[a]==0):
                        del Pom[a]
                        
                        
                elif a not in Pom:
                    D=0
                    break
              
            if (D==1 and Pom=={}):
                    
                pary.add((i,j))
    return pary

        


Postac='Barac Obama'

Litery, wybieralne=wybieranie(Postac)

print(zagadka(Litery, wybieralne))