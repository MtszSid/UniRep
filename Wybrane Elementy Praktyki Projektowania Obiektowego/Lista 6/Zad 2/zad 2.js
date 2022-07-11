var http = require('http');
var express = require('express');
var app = express();


app.set('view engine', 'ejs');
app.set('views', './views');


app.get('/', (req, res) => {
    var sel1 = {

        name: 'sel1',
        options: [
            { value: 1, text: 'mężczyzna' },
            { value: 2, text: 'kobieta' },
            { value: 3, text: 'inna' }
        ]
    }
    res.render('index', {sel1});
});
http.createServer(app).listen(3000);