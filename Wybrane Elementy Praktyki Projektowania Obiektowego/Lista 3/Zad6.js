function createGenerator(n) {
    return function () {
        var _state = 0;
        return {
            next: function () {
                return {
                    value: _state,
                    done: _state++ >= n
                }
            }
        }
    }
}

var foo = {
    [Symbol.iterator]: createGenerator(5)
};
for (var f of foo){
    console.log(f);
}

var boo = {
    [Symbol.iterator]: createGenerator(3)
};
for (var f of boo){
    console.log(f);
}

var goo = {
    [Symbol.iterator]: createGenerator(2)
};
for (var f of goo){
    console.log(f);
}

