
var Liczby = new Set()

var liczba = 0
var suma = 0

for ( i = 0; i <= 100000; i++){
    liczba = i
    suma = 0
    while (liczba != 0){
        if(i % (liczba % 10) != 0){
            break
        }
         
        suma += liczba % 10
        liczba = (liczba - liczba % 10) / 10
    }
    if(liczba == 0 && i % suma == 0)
        Liczby.add(i)

}

for (item of Liczby ){
    console.log(item)
}
