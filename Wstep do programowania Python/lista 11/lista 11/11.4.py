

def Hare(Komitety):#metoda Hare'a-Niemayera
    
    
    #Komitety=[NR Nazwa	Wlk	PiS	KO SLD PSL KWiN	MN]
               
            #ID   
    PiS=0   #3 
    KO=0    #4
    SLD=0   #5
    PSL=0   #6
    KWiN=0  #7
    MN=0    #8   
    for I in Komitety:
        I[3]=float(I[3].replace(',','.'))
        I[4]=float(I[4].replace(',','.'))
        I[5]=float(I[5].replace(',','.'))
        I[6]=float(I[6].replace(',','.'))
        I[7]=float(I[7].replace(',','.'))
        I[8]=float(I[8].replace(',','.'))
        S=int(I[2])
        
        #Q=(I[x]*I[2]/100)
        A=I[3]*S/100
        B=I[4]*S/100
        C=I[5]*S/100
        D=I[6]*S/100
        E=I[7]*S/100
        F=I[8]*S/100
        
        PiS+=int(A)  
        KO+=int(B)    
        SLD+=int(C)  
        PSL+=int(D)   
        KWiN+=int(E)  
        MN+=int(F)
        
        S-=(int(A)+int(B)+int(C)+int(D)+int(E)+int(F))

        A=(A-int(A),3)
        B=(B-int(B),4)
        C=(C-int(C),5)
        D=(D-int(D),6)
        E=(E-int(E),7)
        F=(F-int(F),8)

        L=[A,B,C,D,E,F]
        L.sort(key=lambda x: -x[0])
        for j in range(S):
            
            
            if(L[0][1]==3):
                PiS+=1
                
            elif(L[0][1]==4):
                KO+=1
                
            elif(L[0][1]==5):
                SLD+=1
                
            elif(L[0][1]==6):
                PSL+=1
                
            elif(L[0][1]==7):
                KWiN+=1
                
            else:
                MN+=1
            L.pop(0)
           
   
    print("Metoda Hare'a-Niemayera:")
    print('PiS:',PiS,' KO:',KO,' SLD:', SLD,' PSL:',PSL,' KWiN:', KWiN,' MN:', MN)


def Sainte(Komitety):#Metoda Sainte-Laguë 
    
    #Komitety=[NR Nazwa	Wlk	PiS	KO SLD PSL KWiN	MN]
            #ID   
    PiS=0   #3 
    KO=0    #4
    SLD=0   #5
    PSL=0   #6
    KWiN=0  #7
    MN=0    #8
    for I in Komitety:
        
        #Q=(Głosy,n,ID)
        A=(I[3]/1.4,1,3)
        B=(I[4]/1.4,1,4)
        C=(I[5]/1.4,1,5)
        D=(I[6]/1.4,1,6)
        E=(I[7]/1.4,1,7)
        F=(I[8]/1.4,1,8)
        L=[A,B,C,D,E,F]
        for j in range(int(I[2])):
            L.sort(key=lambda x:(-x[0],-x[1]))
            if(L[0][2]==3):
                PiS+=1
                L[0]=(I[3]/(L[0][1]+2),L[0][1]+2,3)
            elif(L[0][2]==4):
                KO+=1
                L[0]=(I[4]/(L[0][1]+2),L[0][1]+2,4)
            elif(L[0][2]==5):
                SLD+=1
                L[0]=(I[5]/(L[0][1]+2),L[0][1]+2,5)
            elif(L[0][2]==6):
                PSL+=1
                L[0]=(I[6]/(L[0][1]+2),L[0][1]+2,6)
            elif(L[0][2]==7):
                KWiN+=1
                L[0]=(I[7]/(L[0][1]+2),L[0][1]+2,7)
            else:
                MN+=1
                L[0]=(I[8]/(L[0][1]+2),L[0][1]+2,8)

        
    print('Metoda Sainte-Laguë:')
    print('PiS:',PiS,' KO:',KO,' SLD:', SLD,' PSL:',PSL,' KWiN:', KWiN,' MN:', MN)


Wyniki=open('wyniki_wyborow.tsv', encoding="utf-8")

Komitety=[]
for wiersz in Wyniki:
    A=wiersz.split('\t')[:9]
    if A[8]=='–':
        A[8]='0'
    Komitety.append(A)
Komitety.pop(0)

Hare(Komitety)
Sainte(Komitety)