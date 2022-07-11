var obj = {
    a: "a",
    val: 12,
    b: function () {
        return val + a;
    },
    get value() {
        return this.val;
    },
    set value(v) {
        value = v;
    },
};

/*
    Przy użyciu Object.defineProperty mogą zostać dodane: pole, metoda
    Właściwość muszą być dodane  za pomocą Object.defineProperty 
*/ 

obj.c = 15;
obj.d = function (x) {
    return x + this.val;
};

console.log(obj.value);
obj.value = 100;
console.log(obj.d(12));

Object.defineProperty(obj, "cc", {
    get: function () {
        return c;
    },
    set: function (x) {
        c = x 
    }
});




obj.cc = 1
console.log(obj.cc)