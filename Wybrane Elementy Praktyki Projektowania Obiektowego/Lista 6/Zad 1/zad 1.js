var http = require('http');
var express = require('express');
var multer = require('multer')

var storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, 'uploads/')
  },
  filename: function (req, file, cb) {
    cb(null, Date.now() + '.pdf') //Appending .jpg
  }
})

var upload = multer({ storage: storage });


var app = express();


app.set('view engine', 'ejs');
app.set('views', './views');

app.post('/', upload.single('avatar'), function (req, res, next) {
  // req.file is the `avatar` file
  // req.body will hold the text fields, if there were any
  res.render('index', {});
})


app.get('/', (req, res) => {
  res.render('index', {});
});

http.createServer(app).listen(3000);
