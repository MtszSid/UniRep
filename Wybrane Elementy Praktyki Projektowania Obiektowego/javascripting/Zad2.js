const obj = {
    '1' : 1,
    'a b' : 'ab',
    'c' : ['ala', 'kot', 12]
}

//console.log(obj.c, obj['c'])


//operatora . nie można użyć w przypadku gdy nazwa składowej obiektu rerezentuje liczbę lub gdy nazwa zwiera spację


console.log(obj['1'])
console.log(obj['a b'])



const ob_a = {}
obj.ob_a = 10
obj[ob_a] = 'ob_a'

obj[1] = 10

/*

console.log(obj) -> {
                        1: 10, 
                        a b: 'ab', 
                        c: Array(3), 
                        ob_a: 10, 
                        [object Object]: 'ob_a'
                    }
    
    

*/