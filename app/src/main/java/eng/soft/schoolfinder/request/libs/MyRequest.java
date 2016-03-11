package eng.soft.schoolfinder.request.libs;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyRequest extends AsyncTask<String, Void, String> {
    protected Context context;
    protected MyRequestInterface myInterface;

    public MyRequest(Context context, MyRequestInterface myInterface) {
        super();
        this.context = context;
        this.myInterface = myInterface;
    }

    @Override
    protected String doInBackground(String... param) {
        String strResponse = null;
        
        try {
            URL url = new URL(param[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");
            con.setRequestProperty("Accept","application/json");
            con.connect();

            JSONObject json = new JSONObject(param[1]);

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            wr.close();

            int response = con.getResponseCode();

            if(response == HttpURLConnection.HTTP_OK) {
            	//response == 200
                System.out.println("STATUS OK");
                InputStream is = con.getInputStream();
                strResponse = Helper.getResponse(is);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return strResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        
        try {
            // you can use now the model here because we have the copy of context from activity
            // SomeModel model = new SomeModel(context);
            // do something if success
            JSONObject obj = new JSONObject(s);
            // pass the responses in the interface
            myInterface.onComplete(1, "OK", obj);
        }
        catch (JSONException e) {
            e.printStackTrace();
            //do something if fail
            myInterface.onComplete(0, "FAIL", null);
        }
    }
    
}
