package com.example.janaticketking;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {
    Button signUp;
    TextView back;
    EditText userName, firstName, lastName, email, password, rePassword;
    String finalNonce;
    private ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        back = (TextView) findViewById(R.id.back_button);
        userName = (EditText) findViewById(R.id.etusername);
        firstName = (EditText) findViewById(R.id.etfirstname);
        lastName = (EditText) findViewById(R.id.etlastname);
        email = (EditText) findViewById(R.id.eemail);
        password = (EditText) findViewById(R.id.epassword);
        rePassword = (EditText) findViewById(R.id.etrepassword);
        signUp = (Button) findViewById(R.id.sign_up_button);
        loading = findViewById(R.id.loadingg);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = userName.getText().toString().trim();
                String mFirstName = firstName.getText().toString().trim();
                String mLastName = lastName.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();
                String mRePassword = rePassword.getText().toString().trim();


                if (!mUserName.isEmpty() && !mFirstName.isEmpty() && !mLastName.isEmpty() && !mEmail.isEmpty()
                        && !mPassword.isEmpty() && !mRePassword.isEmpty()) {
                    if (mPassword.equals(mRePassword)) {
                        Signup();

                    } else {
                        rePassword.setError("Password do not match!");


                    }
                } else {
                    if (mUserName.isEmpty()) {
                        userName.setError("Please enter username");
                    }
                    if (mFirstName.isEmpty()) {
                        firstName.setError("Please enter first name");
                    }
                    if (mLastName.isEmpty()) {
                        lastName.setError("Please enter last name");
                    }
                    if (mEmail.isEmpty()) {
                        email.setError("Please enter email");
                    }
                    if (mPassword.isEmpty()) {
                        password.setError("Please enter password");
                    }
                    if (mRePassword.isEmpty()) {
                        rePassword.setError("Please re enter password");
                    }

                }

            }
        });
    }

    private void Signup() {

        loading.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.GONE);

        String URL_NONCE = "http://ticketking.com.au/api/get_nonce/?controller=user&method=register";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NONCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String status) {
                        try {
                            //status has all outputs
                            JSONObject jsonObject = new JSONObject(status);


                            //for status ok or error
                            String statusString = jsonObject.getString("status");
                            Log.d("stat", statusString);

                            if (statusString.equalsIgnoreCase("ok")) {
                                finalNonce = jsonObject.getString("nonce");
                                register();

                            } else {
                                Toast.makeText(SignUpActivity.this, "Error sigining up", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                signUp.setVisibility(View.VISIBLE);
                            }
                            Log.d("find", statusString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            signUp.setVisibility(View.VISIBLE);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        back.setVisibility(View.VISIBLE);

                        Toast.makeText(SignUpActivity.this, "Error sigining up", Toast.LENGTH_SHORT).show();

                    }
                }) {

        };
        requestQueue.add(stringRequest);
    }

    private void register() {
        Log.d("nonceValue", finalNonce);


        String username = userName.getText().toString().trim();
        String firstname = firstName.getText().toString().trim();
        String lastname = lastName.getText().toString().trim();
        String emaill = email.getText().toString().trim();
        String passwordd = password.getText().toString().trim();
        String repassword = rePassword.getText().toString().trim();
        String URL_SignUp = "https://ticketking.com.au/api/user/register/?username=" + username + "&email=" + emaill + "&nonce=" + finalNonce +
                "&display_name=" + firstname + "&notify=both&user_pass=" + passwordd + "&first_name=" + firstname + "&last_name=" + lastname;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SignUp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String status) {
                        try {
                            //status has all outputs
                            JSONObject jsonObject = new JSONObject(status);


                            //for status ok or error
                            String statusString = jsonObject.getString("status");

                            if (statusString.equalsIgnoreCase("ok")) {
                                Toast.makeText(SignUpActivity.this, "ID sucessfully registered.", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                signUp.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(SignUpActivity.this, SplashScreenActivity.class);
                                startActivity(intent);

                            } else if (statusString.equalsIgnoreCase("error")) {
                                String errorString = jsonObject.getString("error");
                                Log.d("error", errorString);

                                Toast.makeText(SignUpActivity.this, "Registration error\n" + errorString, Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                signUp.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            signUp.setVisibility(View.VISIBLE);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Username or email id already exists.\n" +
                                "Please try again", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        signUp.setVisibility(View.VISIBLE);

                    }
                }) {

        };
        requestQueue.add(stringRequest);
    }
}
