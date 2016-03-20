package eng.soft.schoolfinder.request.libs;

import org.json.JSONObject;

public interface MyRequestInterface {

    String HOST_ADDR = "http://192.168.15.6/";

    void onComplete(int status, String msg, JSONObject response);
    
}
