
var Pierwsze = new Set()

Pierwsze.add(2)
var czy_pierwsza
for ( i = 3; i <= 100000; i++){
    
    czy_pierwsza = true

    for (item of Pierwsze ){
        if(i % item == 0){
            czy_pierwsza = false
            break
        }
    }

    if(czy_pierwsza)
        Pierwsze.add(i)

}


for (item of Pierwsze ){
    console.log(item)
}