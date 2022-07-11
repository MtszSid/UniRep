function sum(...args) {
  var wynik = 0;
  for (let i in args) {
    wynik += args[i];
  }
  return wynik;
}
console.log(sum( 1, 2,3));
// 6
console.log(sum(1, 2, 3, 4, 5));
// 15
