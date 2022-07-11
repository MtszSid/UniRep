function iter( n ){
    var f0 = 0
    var f1 = 1
    if( n == 0)
    return f0
    if( n == 1)
    return f1

    var f2

    for (i = 2; i <= n; i++){
        f2 = f1 + f0
        f0 = f1
        f1 = f2
    }

    return f1
}

function recur(n){
    if( n == 0)
    return 0
    if(n == 1)
    return 1
    return recur(n-1) + recur(n-2)
}


for (n = 10; n <= 45; n++){
    console.time("iter " + n)
    iter(n)
    console.timeEnd("iter " + n)

    console.time("rec " + n)
    recur(n)
    console.timeEnd("rec " + n)
}

