var exec = require('cordova/exec');

var PLUGIN_NAME = 'BrotherBluetoothPrint';

var BrotherBluetoothPrint = {
  echo: function (phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  getDate: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
};

module.exports = BrotherBluetoothPrint;
