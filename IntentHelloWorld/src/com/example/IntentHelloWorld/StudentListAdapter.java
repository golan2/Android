package com.example.IntentHelloWorld;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    25/03/2015 14:21
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class StudentListAdapter extends ArrayAdapter<Student> {

  public StudentListAdapter(Activity activity, List<Student> students) {
    super(activity, R.layout.student_layout, students);
  }

  @Override
  public Activity getContext() {
    return (Activity) super.getContext();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    System.out.println("*************** convertView=["+convertView+"] ****************");
    if (convertView==null) {
      System.out.println("*************** convertView==null ****************");
      convertView = getContext().getLayoutInflater().inflate(R.layout.student_layout, null);
      System.out.println("*************** convertView was created: ["+convertView+"] ****************");
    }

    final TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
    final TextView txtSurname = (TextView) convertView.findViewById(R.id.txtSurname);
    final Student student = getItem(position);
    txtName.setText(student.getName());
    txtSurname.setText(student.getSurname());

    return convertView;
  }
}
