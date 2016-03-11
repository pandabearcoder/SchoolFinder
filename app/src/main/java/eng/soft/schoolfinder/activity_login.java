package eng.soft.schoolfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*

    try {
        data.put("username", txt_username.getText().toString());
        data.put("password", txt_password.getText().toString());
    } catch (JSONException e) {
        e.printStackTrace();
    }

    mRequest = new MyRequest(getContext(), new MyRequestInterface() {

        @Override
        public void onComplete(int status, String msg, JSONObject response) {
            if (status == 1) {
                System.out.println("AsyncTask Complete");
                System.out.println("Response is : " + response.toString());

                try {
                    if (response.getInt("success") == 1) {
                        Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        btn_logout.setVisibility(View.VISIBLE);
                        btn_logout.setText("Logout");

                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.USER_ID, response.getJSONObject("details").getInt("user_id"));
                        cv.put(DatabaseHelper.USER_NAME, txt_username.getText().toString());
                        cv.put(DatabaseHelper.USER_PASS, txt_password.getText().toString());
                        cv.put(DatabaseHelper.USER_FNAME, response.getJSONObject("details").getString("first_name"));
                        usrModel.addUser(cv);
                        txt_password.setVisibility(View.INVISIBLE);
                        txt_username.setVisibility(View.INVISIBLE);
                        btn_login.setVisibility(View.INVISIBLE);
                        txt_greeting.setText("Hello " + response.getJSONObject("details").getString("first_name"));
                        Pref.saveToPreferences(getContext(), Pref.LOGGED, true);

                    } else if (response.getInt("success") == 0) {
                        Toast.makeText(getContext(), "Login failed!", Toast.LENGTH_SHORT).show();
                    } else if (response.getInt("success") == -1) {
                        Toast.makeText(getContext(), "Request error! Login details not received!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "There is something with the request. " + msg, Toast.LENGTH_SHORT).show();
            }
        }
    });
    mRequest.execute(requestURL, data.toString());
    */

}
