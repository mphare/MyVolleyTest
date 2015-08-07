package com.example.mhare.myvolleytest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends Activity
{

  private TextView responseText;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    responseText = (TextView) findViewById(R.id.txt1);

//    String url = "https://192.168.43.141";
    String url = "https://myweb.com";

    final ProgressDialog pDialog = new ProgressDialog(this);
    pDialog.setMessage("Loading....");
    pDialog.show();

    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                    new Response.Listener<String>()
                                                    {
                                                      @Override
                                                      public void onResponse(String response)
                                                      {
                                                        Log.d("App", response);
                                                        responseText.setText("Response:" + " " + response);
                                                        pDialog.hide();
                                                      }

                                                    }, new Response.ErrorListener()
    {
      @Override public void onErrorResponse(VolleyError error)
      {
        VolleyLog.d("App " + "Error:" + " " + error.getMessage());
        pDialog.hide();
      }

    });

    VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
    {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
