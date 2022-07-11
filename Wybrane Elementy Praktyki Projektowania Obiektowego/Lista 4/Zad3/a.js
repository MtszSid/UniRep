module.exports = {a};
var b = require("./b")

function a (n){
    if(n > 0){
        console.log(n)
        b.b(n-1)
    }
}