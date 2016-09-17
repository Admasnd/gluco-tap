package com.example.jasongibson.myapplication;

/**
 * Created by JasonGibson on 9/17/16.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JasonGibson on 9/17/16.
 */
public class SendPost {
    /**
     * the rest queue
     */
    private static RequestQueue queue;
    /**
     * the context
     */
    private Context context;

    public SendPost(Context context) {
        this.context = context;
    }

    public void post(final String[] test) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST,"https://gluco-tap.run.aws-usw02-pr.ice.predix.io/upload", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("problem", "onResponse" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("problem", "onError");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("patientId", test[0]);
                long dtMili = System.currentTimeMillis();
                String Long = String.valueOf(dtMili);
                params.put("dateTime", Long);
                params.put("glucoseLevel", test[2]);
                return params;
            }

        };
        queue.add(sr);
    }
}
