

def RmWhsp(tekst):
    p=0
    bez=[]
    for i in range(len(tekst)):
        if tekst[i].isspace() :
            bez.append(tekst[p:i])
            p=i+1
    if p<len(tekst):
        bez.append(tekst[p:len(tekst)+1])
            
    return bez



tekst="""Ala ma kota
I psa
las
ty i ja"""

print(RmWhsp(tekst))