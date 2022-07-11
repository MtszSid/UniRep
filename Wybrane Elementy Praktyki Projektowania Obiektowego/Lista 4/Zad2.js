
var Foo = (function(){
    function Qux (){
        return 12
    }

    function Foo (){

    }

    Foo.prototype.Bar = function(){
        return Qux()
    }
    return Foo
})();



var a = new Foo()
console.log(a.Bar())
console.log(a.Qux)