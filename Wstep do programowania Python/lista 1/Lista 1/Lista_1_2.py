def silnia(n):
    j=1
    wynik=1
    for i in range (n):
        wynik=wynik*j
        dlugosc=len(str(wynik)
                    )
        if(dlugosc==1):
            print(j,"! ma ",dlugosc,"cyfrę")
            j+=1
        elif(dlugosc>=5):
            print(j,"! ma ",dlugosc,"cyfr")
            j+=1
        else:
            print(j,"! ma ",dlugosc,"cyfry")
            j+=1
            
       
silnia(100)