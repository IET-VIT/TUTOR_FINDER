package com.vithack.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.name_profile) TextView name_profile;
    @BindView(R.id.email_profile) TextView email_profile;
    @BindView(R.id.acc_type_profile) TextView acc_type_profile;
    @BindView(R.id.phone_profile) TextView phone_profile;
    @BindView(R.id.address_profile) TextView address_profile;
    @BindView(R.id.gender_profile) TextView gender_profile;
    @BindView(R.id.sub_profile) TextView sub_profile;
    @BindView(R.id.sub_text) TextView sub_text;
    @BindView(R.id.sub_colon) TextView sub_colon;

    String URL = "https://abc-institutions.000webhostapp.com/tutor.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        SharedPreferences accPrefs = getSharedPreferences("Login", MODE_PRIVATE);

        String type = accPrefs.getString("Type", null);

        name_profile.setText(accPrefs.getString("FullName", null));
        email_profile.setText(accPrefs.getString("Username", null));
        acc_type_profile.setText(type);
        phone_profile.setText(accPrefs.getString("Phone", null));
        address_profile.setText(accPrefs.getString("Address", null));
        gender_profile.setText(accPrefs.getString("Gender", null));

        if(type.equals("teacher")){
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (!response.equalsIgnoreCase("0 results")) {
                        JSONObject jsonObj;
                        Log.e("Info:", response);
                        try {
                            JSONArray userInfo = new JSONArray(response);
                            jsonObj = userInfo.getJSONObject(0);
                            sub_profile.setText(jsonObj.getString("prefer_sub"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        sub_colon.setVisibility(View.VISIBLE);
                        sub_profile.setVisibility(View.VISIBLE);
                        sub_text.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Error Loading", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("t_id", accPrefs.getString("ID", null));
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
            requestQueue.add(request);
        }

    }
}