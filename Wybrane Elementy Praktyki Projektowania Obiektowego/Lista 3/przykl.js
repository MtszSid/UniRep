function change(n) {
    n = 2;
    console.log(`po zmianie lokalnie w funkcji ${n}`, );
   }
   var n = 1;
   console.log(n);
   change(n);
   console.log(` w miejscu wywołaniania ${n}`);