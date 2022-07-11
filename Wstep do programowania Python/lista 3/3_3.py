def usun_nawiasy(s):
    tak=True
    bez=""
    for i in s:
        if (i=="("):
            tak=False
        elif(i==")"):
            tak=True
        elif (tak==True):
            bez=bez+i
    print(bez)

usun_nawiasy("Ala ma kota (perskiego)!")
usun_nawiasy("Pojedziemy po zakupy później (najpierw musimy iść wybrać pieniądze z bankomatu)")
usun_nawiasy("Jutro dostanę (tak twierdzi mój tata) wspaniałe, nowe auto.")
usun_nawiasy("W ciągu trzech ostatnich lat (jak wynika z badań instytutu) wzrosło spożycie alkoholu przez młodzież o 3%.")
usun_nawiasy("Henryk Sienkiewicz (1846-1916) urodził się w Woli Okrzejskiej.")