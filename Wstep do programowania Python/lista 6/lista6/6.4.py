def zle_slowo(w):
    return ' ' in w or '\n' in w or '\t' in w or '\v' in w or '\f' in w or '\r' in w

def RmWhsp(tekst):
    p=0
    bez=[]
    for i in range(len(tekst)+1):
        if zle_slowo(tekst[i]):
            bez.append