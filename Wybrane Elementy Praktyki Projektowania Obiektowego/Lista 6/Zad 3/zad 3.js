var http = require("http");
var express = require("express");
var cookieParser = require("cookie-parser")
var app = express();



app.set("view engine", "ejs");
app.set("views", "./views");

app.get("/", (req, res) => {
 
  res.render("index", {});
});

http.createServer(app).listen(3000);
