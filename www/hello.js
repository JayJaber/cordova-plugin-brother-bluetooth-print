/*global cordova, module*/

module.exports = {
  greet: function (name, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'Hello', 'greet', [name]);
  },
  scan: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'Hello', 'scan');
  },
  print: function (macAddress, path, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'Hello', 'print', [
      macAddress,
      path,
    ]);
  },
};
