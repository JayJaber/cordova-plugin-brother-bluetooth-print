public class BrotherBluetoothPrint extends CordovaPlugin {
    private static final String TAG = "MyCordovaPlugin";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        Log.d(TAG, "Initializing MyCordovaPlugin");
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("echo")) {
            String phrase = args.getString(0);
            // Echo back the first argument
            Log.d(TAG, phrase);
        } else if (action.equals("getDate")) {
            // An example of returning data back to the web layer
            final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
            callbackContext.sendPluginResult(result);
        }
        return true;
    }
}