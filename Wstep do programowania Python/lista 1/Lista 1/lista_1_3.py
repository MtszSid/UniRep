def krzyzyk(n):
    for i in range(n):
        print(str(n*" ")+str(n*"*")+str(n*" "))
    for i in range(n):
        print(str(n*"***"))
    for i in range(n):
        print(str(n*" ")+str(n*"*")+str(n*" "))


krzyzyk(5)

