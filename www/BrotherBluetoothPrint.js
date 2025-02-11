/*global cordova, module*/

// module.exports = {
//   greet: function (name, successCallback, errorCallback) {
//     cordova.exec(
//       successCallback,
//       errorCallback,
//       'BrotherBluetoothPrint',
//       'greet',
//       [name]
//     );
//   },
//   scan: function (successCallback, errorCallback) {
//     cordova.exec(
//       successCallback,
//       errorCallback,
//       'BrotherBluetoothPrint',
//       'scan'
//     );
//   },
//   print: function (macAddress, path, successCallback, errorCallback) {
//     cordova.exec(
//       successCallback,
//       errorCallback,
//       'BrotherBluetoothPrint',
//       'print',
//       [macAddress, path]
//     );
//   },
// };

var exec = require('cordova/exec');

exports.greet = function (name, successCallback, errorCallback) {
  cordova.exec(
    successCallback,
    errorCallback,
    'BrotherBluetoothPrint',
    'greet',
    [name]
  );
};

exports.scan = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, 'BrotherBluetoothPrint', 'scan');
};

exports.print = function (macAddress, path, successCallback, errorCallback) {
  cordova.exec(
    successCallback,
    errorCallback,
    'BrotherBluetoothPrint',
    'print',
    [macAddress, path]
  );
};
