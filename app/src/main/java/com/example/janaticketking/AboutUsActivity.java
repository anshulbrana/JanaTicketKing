package com.example.janaticketking;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView call, email;
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5, expandableLayout6,
            expandableLayout7, expandableLayout8, expandableLayout9, expandableLayout10, expandableLayout11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        call = (TextView) findViewById(R.id.sendcall);
        email = (TextView) findViewById(R.id.sendmail);
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
//        expandableLayout1.setOnClickListener(this);
        expandableLayout1.collapse();

        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
//        expandableLayout2.setOnClickListener(this);
        expandableLayout2.collapse();


        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
//        expandableLayout3.setOnClickListener(this);
        expandableLayout3.collapse();


        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
//        expandableLayout4.setOnClickListener(this);
        expandableLayout4.collapse();


        expandableLayout5 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout5);
//        expandableLayout5.setOnClickListener(this);
        expandableLayout5.collapse();


        expandableLayout6 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout6);
//        expandableLayout6.setOnClickListener(this);
        expandableLayout6.collapse();


        expandableLayout7 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout7);
//        expandableLayout7.setOnClickListener(this);
        expandableLayout7.collapse();

        expandableLayout8 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout8);
//        expandableLayout8.setOnClickListener(this);
        expandableLayout8.collapse();


        expandableLayout9 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout9);
//        expandableLayout9.setOnClickListener(this);
        expandableLayout9.collapse();

        expandableLayout10 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout10);
//        expandableLayout10.setOnClickListener(this);
        expandableLayout10.collapse();

        expandableLayout11 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout11);
//        expandableLayout11.setOnClickListener(this);
        expandableLayout11.collapse();


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0423743157"));
                startActivity(callIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@ticketking.com.au"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT, "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AboutUsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void expandableButton1(View view) {
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void expandableButton2(View view) {
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void expandableButton4(View view) {
        expandableLayout4.toggle(); // toggle expand and collapse
    }

    public void expandableButton5(View view) {
        expandableLayout5.toggle(); // toggle expand and collapse
    }

    public void expandableButton6(View view) {
        expandableLayout6.toggle(); // toggle expand and collapse
    }

    public void expandableButton7(View view) {
        expandableLayout7.toggle(); // toggle expand and collapse
    }

    public void expandableButton8(View view) {
        expandableLayout8.toggle(); // toggle expand and collapse
    }

    public void expandableButton9(View view) {
        expandableLayout9.toggle(); // toggle expand and collapse
    }

    public void expandableButton10(View view) {
        expandableLayout10.toggle(); // toggle expand and collapse
    }

    public void expandableButton11(View view) {
        expandableLayout11.toggle(); // toggle expand and collapse
    }

    @Override
    public void onClick(View v) {

    }
}

