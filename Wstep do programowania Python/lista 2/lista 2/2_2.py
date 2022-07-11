def koperta(n):
    print ((2*n+1)*"*")
    for i in range (n-1):
        print("*"+i*" "+"*"+(n-2-i)*" "+" "+(n-2-i)*" "+"*"+i*" "+"*")
    print("*"+(n-1)*" "+"*"+(n-1)*" "+"*")
    for i in range (n-1):
        print("*"+(n-2-i)*" "+"*"+i*" "+" "+i*" "+"*"+(n-2-i)*" "+"*")
    print ((2*n+1)*"*")
koperta(12)
