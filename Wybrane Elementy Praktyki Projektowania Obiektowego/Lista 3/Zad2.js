function fib(n){
    if (n == 0){
        return 0
    } else if(n == 1){
        return 1
    } else {
        return fib(n - 1) + fib(n - 2)
    }
}

function mem (f){
    cache = {}
    return function(n){
        if (cache[n] != undefined){
            return cache[n]
        }
        else{
            let i = f(n)
            cache[n] = i
            return i
        }
    }
}

fib = mem(fib)

console.time()
fib(45)
console.timeEnd()

/*
Porównanie czasu potrzebnego na obliczenie 45 elementu ciągu fibonacciego
0.238 ms     -- algorytm iteracyjny
20069.494 ms -- algorytm rekurencyjny bez spamiętywania
0.851ms      -- algorytm rekurencyjny ze spammiętywaniem
*/