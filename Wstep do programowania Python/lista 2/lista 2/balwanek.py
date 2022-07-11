#glowa
print(6*" "+3*"#"+2*" ")
print(5*" "+5*"#"+1*" ")
for i in range(3):
    print(4*" "+7*"#")
print(5*" "+5*"#"+1*" ")
print(6*" "+3*"#"+2*" ")
#bzyszek
print(5*" "+5*"#"+2*" ")
print(4*" "+7*"#"+1*" ")
for i in range(5):
    print(3*" "+9*"#")
print(4*" "+7*"#"+1*" ")
print(5*" "+5*"#"+2*" ")
#część dolna
n=15
print(int((n-5)/2)*" "+5*"#"+int((n-5)/2)*" ")
print(int((n-9)/2)*" "+9*"#"+int((n-9)/2)*" ")
for j in range(int((n-13)/2)):
    print(int((n-9)/2-j-1)*" "+(11+2*j)*"#"+int((n-9)/2-j-1)*" ")
print(" "+(n-2)*"#"+" ")
print(" "+(n-2)*"#"+" ")
for i in range (5):
    print (n*"#")
print(" "+(n-2)*"#"+" ")
print(" "+(n-2)*"#"+" ")
for j in range(int((n-13)/2)):
    print((j+2)*" "+(n-2*j-4)*"#"+(j+2)*" ")
print(int((n-9)/2)*" "+9*"#"+int((n-9)/2)*" ")
print(int((n-5)/2)*" "+5*"#"+int((n-5)/2)*" ")