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

function* fib() { // (2)
    var f1 = 0
    var f2 = 1
    while (true){
        [f1, f2] = [f2, f1 + f2]
        yield f1
    }
}

var _it = fib();
for (var _result; _result = _it.next(), !_result.done;) {
    console.log(_result.value);
}

/* w przypadku (2) możliwe jest iterowanie się po kolejnych wartościach za pomocą for-of */

for ( var i of fib() ) {  
    console.log( i );
}
