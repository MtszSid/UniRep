def permutacyjna(s):
    dic={}
    i=1
    perm=[]
    for j in s:
        if j in dic:
            perm.append(str(dic[j]))
        else:
            perm.append(str(i))
            dic[j]=i
            i+=1
    return "-".join(perm)

print (permutacyjna('indianin'))
