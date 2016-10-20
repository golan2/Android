package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    18/03/2015 10:54
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class AutoCompleteExample extends Activity implements TextWatcher {

  private String[]             items;
  private AutoCompleteTextView autoCompleteTextView;
  private TextView             txtSelectedItem;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.auto_complete_example_layout);

    autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
    txtSelectedItem      = (TextView            ) findViewById(R.id.txtSelectedItem     );
    items                = getResources().getStringArray(R.array.listForAutoCompleteExample);

    autoCompleteTextView.addTextChangedListener(this);
    autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items));
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    txtSelectedItem.setText(autoCompleteTextView.getText());
  }
}