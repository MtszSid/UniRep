

def wybory(W):
    Komitety=[]
    for wiersz in W:
        A=wiersz.split('\t')[:9]
        if A[8]=='–':
            A[8]='0'
        Komitety.append(A)
    Komitety.pop(0)
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
        #Q=(Głosy,n,ID)
        A=(I[3],1,3)
        B=(I[4],1,4)
        C=(I[5],1,5)
        D=(I[6],1,6)
        E=(I[7],1,7)
        F=(I[8],1,8)
        L=[A,B,C,D,E,F]
        for j in range(int(I[2])):
            L.sort(key=lambda x:(-x[0],-x[1]))
            
            if(L[0][2]==3):
                PiS+=1
                L[0]=(I[3]/(L[0][1]+1),L[0][1]+1,3)
            elif(L[0][2]==4):
                KO+=1
                L[0]=(I[4]/(L[0][1]+1),L[0][1]+1,4)
            elif(L[0][2]==5):
                SLD+=1
                L[0]=(I[5]/(L[0][1]+1),L[0][1]+1,5)
            elif(L[0][2]==6):
                PSL+=1
                L[0]=(I[6]/(L[0][1]+1),L[0][1]+1,6)
            elif(L[0][2]==7):
                KWiN+=1
                L[0]=(I[7]/(L[0][1]+1),L[0][1]+1,7)
            else:
                MN+=1
                L[0]=(I[8]/(L[0][1]+1),L[0][1]+1,8)

        

    print('PiS:',PiS,' KO:',KO,' SLD:', SLD,' PSL:',PSL,' KWiN:', KWiN,' MN:', MN)





Wyniki=open('wyniki_wyborow.tsv', encoding="utf-8")
wybory(Wyniki)