def szachownica (n,k):
    for i in range (n):
        for j in range (k):
            print (n*(k*" "+k*"#"))
        for j in range (k):
            print (n*(k*"#"+k*" "))

szachownica (5,4)
