def kolo(n): #n niparzyste n>=7
    if (n==7):
        print(2*" "+3*"#"+2*" ")
        print(1*" "+5*"#"+1*" ")
        print(7*"#")
        print(7*"#")
        print(7*"#")
        print(1*" "+5*"#"+1*" ")
        print(2*" "+3*"#"+2*" ")
    elif (n==9):
        print(2*" "+5*"#"+2*" ")
        print(1*" "+7*"#"+1*" ")
        for i in range(5):
            print(9*"#")
        print(1*" "+7*"#"+1*" ")
        print(2*" "+5*"#"+2*" ")
    elif (n==11):
        print(3*" "+5*"#"+3*" ")
        print(2*" "+7*"#"+2*" ")
        print(1*" "+9*"#"+1*" ")
        for i in range(5):
            print(n*"#")
        print(1*" "+9*"#"+1*" ")
        print(2*" "+7*"#"+2*" ")
        print(3*" "+5*"#"+3*" ")
    else:
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
kolo(25)

