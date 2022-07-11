

lista=[9,5,14,1,5,1,9,42,12,12,11,21,22,2,2,1,7,1,23,1,4,7,1]
lista2=[[lista[x], x] for x in range(len(lista))]

lista2.sort()

lista3=[]
lista3.append(lista2[0])
for x in range(1,len(lista2)):
	if (lista3[len(lista3)-1][0]!=lista2[x][0]):
		lista3.append(lista2[x])

lista3.sort(key=lambda x:x[1])

lista4=[]
for x in lista3:
	lista4.append(x[0])
print(lista4)

