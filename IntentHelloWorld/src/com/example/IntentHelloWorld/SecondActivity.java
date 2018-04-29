package com.example.IntentHelloWorld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    25/03/2015 12:07
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class SecondActivity extends Activity {
  static final String PARAM_EMAIL = "txtEmail";
  static final String PARAM_PHONE = "txtPhone";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    final String email = getIntent().getStringExtra(PARAM_EMAIL);
    final TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
    txtEmail.setText(email);

    final String phone = getIntent().getStringExtra(PARAM_PHONE);
    final TextView txtPhone = (TextView) findViewById(R.id.txtPhone);
    txtPhone.setText(phone);

  }
}