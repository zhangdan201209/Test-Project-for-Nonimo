var express = require('express');
var router = express.Router();
var data = require('./profile.json');
/* GET users listing. */

router.get('/', function(req, res) {


  res.send(JSON.stringify(data,null,8));
console.log("haha");
console.log(data);
});

module.exports = router;

