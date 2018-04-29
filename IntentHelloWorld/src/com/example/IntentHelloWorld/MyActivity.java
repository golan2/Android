package com.example.IntentHelloWorld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    25/03/2015 11:09
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MyActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);

    final Button btnKo = (Button) findViewById(R.id.btnKo);

    btnKo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final String email = txtEmail.getText().toString();
        final boolean addEmail = email.trim().length() > 0;

        final EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        final String phone = txtPhone.getText().toString();
        final boolean addPhone = phone.trim().length() > 0;

        if (addEmail||addPhone) {
          Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);
          if (addEmail) {
            myIntent.putExtra(SecondActivity.PARAM_EMAIL, email);
          }
          if (addPhone) {
            myIntent.putExtra(SecondActivity.PARAM_PHONE, phone);
          }
          startActivity(myIntent);
        }

      }
    });

  }
}