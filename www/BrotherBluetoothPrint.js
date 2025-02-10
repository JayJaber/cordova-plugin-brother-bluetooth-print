/*global cordova, module*/

module.exports = {
  greet: function (name, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'BrotherBluetoothPrint',
      'greet',
      [name]
    );
  },
  scan: function (successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'BrotherBluetoothPrint',
      'scan'
    );
  },
  print: function (macAddress, path, successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'BrotherBluetoothPrint',
      'print',
      [macAddress, path]
    );
  },
};
