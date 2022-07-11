function fib() {  // (1)
    var f1 = 0
    var f2 = 1
    return {
        next: function () {
            [f1, f2] = [f2, f1 + f2]
            return {
                value: f1,
                done: false
            }
        }
    }
}


function* fib2() { // (2)
    var f1 = 0
    var f2 = 1
    while (true){
        [f1, f2] = [f2, f1 + f2]
        yield f1
    }
}

function* take(it, top) {
     var n = it.next()
     for(var i = 0; i < top ; i++){
        yield n.value
        n = it.next()
     }
}

// zwróć dokładnie 10 wartości z potencjalnie
// "nieskończonego" iteratora/generatora
for (let num of take( fib(), 10 ) ) {
    console.log(num);
}

for (let num of take( fib2(), 10 ) ) {
    console.log(num);
}