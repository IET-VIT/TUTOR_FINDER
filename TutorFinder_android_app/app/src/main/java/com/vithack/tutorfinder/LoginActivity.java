package com.vithack.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.vithack.tutorfinder.utils.MD5Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.newAccountBtn) Button newAccountBtn;
    @BindView(R.id.loginEmail) TextInputEditText loginEmail;
    @BindView(R.id.loginPassword) TextInputEditText loginPassword;
    @BindView(R.id.loginBtn) Button loginBtn;
    @BindView(R.id.loginProgressBar) ProgressBar progressBar;

    String URL = "https://abc-institutions.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        SharedPreferences loginPrefs = getSharedPreferences("Login", MODE_PRIVATE);

        String username_check = loginPrefs.getString("Username", null);

        if(username_check == null) {
            newAccountBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://findtutor.infinityfreeapp.com/registration.php"));
                    startActivity(browserIntent);
                }
            });

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pass = MD5Util.getMd5(loginPassword.getText().toString());
                    String email = String.valueOf(loginEmail.getText());
                    if (email.isEmpty() || pass.equals("d41d8cd98f00b204e9800998ecf8427e")) {
                        Snackbar.make(v, "Please enter all the details", Snackbar.LENGTH_LONG).show();
                    } else {
                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("Please Wait...");

                        progressDialog.show();

                        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();

                                if (!response.equalsIgnoreCase("0 results")) {
                                    Log.i("Info:", response);
                                    try {
                                        JSONArray userInfo = new JSONArray(response);
                                        JSONObject jsonObj = userInfo.getJSONObject(0);
                                        SharedPreferences.Editor Ed = loginPrefs.edit();
                                        Ed.putString("Username", email);
                                        Ed.putString("ID", jsonObj.get("id").toString());
                                        Ed.putString("FullName", jsonObj.get("fullname").toString());
                                        Ed.putString("Phone", jsonObj.get("phone").toString());
                                        Ed.putString("Type", jsonObj.get("type").toString());
                                        Ed.putString("Gender", jsonObj.get("gender").toString());
                                        Ed.putString("Address", jsonObj.get("address").toString());
                                        Ed.commit();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "E-mail and password do not match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", email);
                                params.put("pass", pass);
                                return params;
                            }
                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                        requestQueue.add(request);
                    }
                }
            });
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}