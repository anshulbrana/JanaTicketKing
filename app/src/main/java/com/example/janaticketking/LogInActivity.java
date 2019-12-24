package com.example.janaticketking;

import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LogInActivity extends AppCompatActivity {

    Button logIn;
    TextView signUp, resetPassword;
    EditText etUserName, etPassword;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        signUp = (TextView) findViewById(R.id.text_signup);
        resetPassword = (TextView) findViewById(R.id.text_reset_password);
        logIn = (Button) findViewById(R.id.btn_login);
        etUserName = (EditText) findViewById(R.id.etemail);
        etPassword = (EditText) findViewById(R.id.etpassword);
        loading = (ProgressBar) findViewById(R.id.loading);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://ticketking.com.au/password-reset/"));
                startActivity(intent);

            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = etUserName.getText().toString().trim();
                String mPassword = etPassword.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                    Login();
                } else {
                    etUserName.setError("Please enter username");
                    etPassword.setError("Please enter password");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void Login() {
        final String username = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String URL_LOGIN = "https://ticketking.com.au/api/user/generate_auth_cookie/?username=" + username + "&password=" + password;
        loading.setVisibility(View.VISIBLE);
        logIn.setVisibility(View.GONE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String status) {
                        try {
                            //status has all outputs
                            JSONObject jsonObject = new JSONObject(status);

                            //for status ok or error
                            String statusString = jsonObject.getString("status");
                            if (statusString.equalsIgnoreCase("ok")) {


                                SharedPreferences.Editor editor = getSharedPreferences(Constant.SHAREDPREF, MODE_PRIVATE).edit();
                                editor.putString(Constant.SHAREDPREFNAME, username);
                                editor.apply();

                                Log.d("errors", username);


                                Toast.makeText(LogInActivity.this, "Success login.", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(intent);

                                loading.setVisibility(View.GONE);
                            } else if (statusString.equalsIgnoreCase("error")) {
                                String errorString = jsonObject.getString("error");
                                Toast.makeText(LogInActivity.this, "error login........\n" + errorString, Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                logIn.setVisibility(View.VISIBLE);
                            }
                            Log.d("check", statusString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        logIn.setVisibility(View.VISIBLE);

                        Toast.makeText(LogInActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                    }
                }) {

        };
        requestQueue.add(stringRequest);
    }

}
