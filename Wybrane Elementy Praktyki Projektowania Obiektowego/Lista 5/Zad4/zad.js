var http = require('http');
var express = require('express');
var app = express();

app.use(express.urlencoded({extended:true}));

app.set('view engine', 'ejs');
app.set('views', './views');


app.get( '/', (req, res) => {
    res.render('index', {imie:'', nazwisko:'', przedmiot:'', zad1:'', zad2:'', zad3:'', zad4:'', zad5:'',
                zad6:'', zad7:'', zad8:'', zad9:'', zad10:''});
});
   
app.post('/', (req, res) => {
    var imie = req.body.imie;
    var nazwisko = req.body.nazwisko;
    var przedmiot = req.body.przedmiot;
    var zad1 = req.body.zad1 || 0;
    var zad2 = req.body.zad2 || 0;
    var zad3 = req.body.zad3 || 0;
    var zad4 = req.body.zad4 || 0;
    var zad5 = req.body.zad5 || 0;
    var zad6 = req.body.zad6 || 0;
    var zad7 = req.body.zad7 || 0;
    var zad8 = req.body.zad8 || 0;
    var zad9 = req.body.zad9 || 0;
    var zad10 = req.body.zad10;
    if(imie == '' || nazwisko == '' || przedmiot == ''){
        res.render('index', {imie:imie, nazwisko:nazwisko, przedmiot:przedmiot, zad1:zad1, zad2:zad2, zad3:zad3,
             zad4:zad4, zad5:zad5, zad6:zad6, zad7:zad7, zad8:zad8, zad9:zad9, zad10:zad10});
    }
    else{
        res.render('print', {imie:imie, nazwisko:nazwisko, przedmiot:przedmiot, zad1:zad1, zad2:zad2, zad3:zad3,
             zad4:zad4, zad5:zad5, zad6:zad6, zad7:zad7, zad8:zad8, zad9:zad9, zad10:zad10});
    }
    
});
   

http.createServer(app).listen(3000);