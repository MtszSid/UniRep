import matplotlib.pyplot as plt

def wybory(Komitety,g):
    
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
                L[0]=(I[3]/((L[0][1]+1)**g),L[0][1]+1,3)
            elif(L[0][2]==4):
                KO+=1
                L[0]=(I[4]/((L[0][1]+1)**g),L[0][1]+1,4)
            elif(L[0][2]==5):
                SLD+=1
                L[0]=(I[5]/((L[0][1]+1)**g),L[0][1]+1,5)
            elif(L[0][2]==6):
                PSL+=1
                L[0]=(I[6]/((L[0][1]+1)**g),L[0][1]+1,6)
            elif(L[0][2]==7):
                KWiN+=1
                L[0]=(I[7]/((L[0][1]+1)**g),L[0][1]+1,7)
            else:
                MN+=1
                L[0]=(I[8]/((L[0][1]+1)**g),L[0][1]+1,8)

    return [PiS, KO, SLD, PSL, KWiN, MN]



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

N=460 #Liczba posłów w Sejmie 
Mandaty=[0.4359*N,0.2740*N,0.1256*N,0.0855*N,0.0681*N,0.0017*N] #PiS KO SLD PSL KWiN MN Proporcjonalny przydział mandatów

G=100
      
K1=[] #PiS
K2=[] #KO
K3=[] #SLD
K4=[] #PSL
K5=[] #KWiN
K6=[] #MN
Wsp=[] #Współczynniki

for i in range(1,G):
    L=wybory(Komitety,2*i/G)
    Wsp.append(2*i/G)
    K1.append(Mandaty[0]-L[0])
    K2.append(Mandaty[1]-L[1])
    K3.append(Mandaty[2]-L[2])
    K4.append(Mandaty[3]-L[3])
    K5.append(Mandaty[4]-L[4])
    K6.append(Mandaty[5]-L[5])

plt.plot(Wsp,K1, '#003F87', label='PiS')
plt.plot(Wsp,K2, '#FF4500', label="KO")
plt.plot(Wsp,K3, '#CD0000', label="SLD")
plt.plot(Wsp,K4, '#0AC92B', label="PSL")
plt.plot(Wsp,K5, '#000033', label="KWiN")
plt.plot(Wsp,K6, '#964B00', label="MN")
plt.ylabel('Stopień nieproporcjonalności ordynacji')
plt.xlabel('Wsółczynnik')
plt.legend(bbox_to_anchor=(0., 1.02, 1., .102), loc='lower left',
           ncol=2, mode="expand", borderaxespad=0.)
plt.grid(True)
plt.show()


