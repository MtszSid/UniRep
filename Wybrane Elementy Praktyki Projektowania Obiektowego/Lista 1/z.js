function f({name, surname, address: {city, number}={}}) {
    console.log(name);
    console.log(surname);
    console.log(city);
    console.log(number);
   }
   f({});
   f({name:"jan"});
   f({name:"jan", surname: "kowalski", address:{city:'wroclaw', number: 17}})
   function returnTwoValues() {
    return [1,2];
   }
   var [a,b] = returnTwoValues();
   console.log(a,b);