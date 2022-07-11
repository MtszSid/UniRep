def printWyniki(D):
    Id={3:'Pis',
        4:'KO',
        5:'SLD',
        6:'PSL',
        7:'KWiN',
        8:'MN'
        }
    W=''
    for i in D:
        P=''
        Pom=eval(i)
        for j in Pom:
            if P=='':
                P+=Id[j]
            else:
                P+=' + '+Id[j]
        W+=P+': '+str(D[i])+' '
    print(W)




def przydział(I,W):
    L=[]
    for i in W:

        A=[0,0,1,i]
        Pom=eval(i)
        for j in Pom:
            A[1]+=I[j]
            A[0]+=I[j]
        L.append(A)
    return L


def wybory(Komitety, Koalicje):
    
    #Komitety=[NR Nazwa	Wlk	PiS	KO SLD PSL KWiN	MN]
    #Koalicje =[{4,5}{6,7,8}]
   
   
    """     #ID   
    PiS     #3 
    KO      #4
    SLD     #5
    PSL     #6
    KWiN    #7
    MN      #8
    """

    Wyniki={}
    
    for a in Koalicje:
        Wyniki[str(a)]=0

    for I in Komitety:
        Podzialy=przydział(I,Wyniki)
        

        #Podzilay=[[Głosy/n,Głosy,n,ID],[],[]]
        
        for j in range(int(I[2])):
            Podzialy.sort(key=lambda x:(-x[0],-x[2]))
            
            Wyniki[Podzialy[0][3]]+=1
            Podzialy[0][0]=Podzialy[0][1]/(Podzialy[0][2]+1)
            Podzialy[0][2]+=1

        

    return Wyniki





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
            



Wyniki=open('wyniki_wyborow.tsv', encoding="utf-8")
Komitety=[]
for wiersz in Wyniki:
    A=wiersz.split('\t')[:9]
    if A[8]=='–':
        A[8]='0'
    Komitety.append(A)
Komitety.pop(0)

for I in Komitety:
    I[3]=float(I[3].replace(',','.'))
    I[4]=float(I[4].replace(',','.'))
    I[5]=float(I[5].replace(',','.'))
    I[6]=float(I[6].replace(',','.'))
    I[7]=float(I[7].replace(',','.'))
    I[8]=float(I[8].replace(',','.'))

Relacja={4,5,6,7,8}
F=[set((i,)) for i in Relacja]

Relacja={3,4,5,6,7,8}
R=[set((i,)) for i in Relacja]

N=460
Konst=307

print('Większość pomijająca PiS:')
for i in podzialy(F):
    i.append({3})
    
    Nowy_Wynik=wybory(Komitety,i)
    if Nowy_Wynik['{3}']<0.5*N:
        for i in Nowy_Wynik:
            if Nowy_Wynik[i]>0.5*460:
                printWyniki(Nowy_Wynik)

print('Większość konstytucyjna:')
for i in podzialy(R):
    Nowy_Wynik=wybory(Komitety,i)
    for i in Nowy_Wynik:
        if Nowy_Wynik[i]>Konst:
            printWyniki(Nowy_Wynik)
