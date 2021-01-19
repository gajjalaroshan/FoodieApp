const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const http = require('http');
const connectflash = require('connect-flash');
const session = require('express-session');

const app = express();

// Parsers for POST data
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false}));

app.use(session({
    secret:'accesskey',
    resave: true,
    saveUninitialized: true
    }));

// Point static path to dist
app.use(express.static(path.join(__dirname, 'dist')));

app.use(function(req,res,next){
  if(req.url=="/login"){
    if(req.session.email){
      res.redirect('/home');
    }
  }
  next()
});

// Catch all other routes and return the index file
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/index.html'));
});

//Get port from environment and store in Express
const port = process.env.PORT || '4200';
app.set('port', port);

// Create HTTP server.
const server = http.createServer(app);

// Listen on provided port, on all network interfaces.
server.listen(port, () => console.log(`APP running on localhost:${port}`));
