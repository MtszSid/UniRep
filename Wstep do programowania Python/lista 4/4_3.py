from random import randint, choice
def randperm(n):
    liczby=[]
    for i in range(n):
        liczby.append(i)
    for i in range(n):
        a=randint(0,len(liczby)-1)
        c=liczby[a]
        liczby[a]=liczby[i]
        liczby[i]=c
    print("Permutacja:",end=" ")
    for i in liczby:
        print(i,end=", ")
    print()

randperm(1000000)
randperm(10)
randperm(10)
randperm(10)


