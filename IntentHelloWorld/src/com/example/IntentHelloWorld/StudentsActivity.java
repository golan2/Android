package com.example.IntentHelloWorld;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    25/03/2015 14:36
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class StudentsActivity extends ListActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_students);

    List<Student> students = new ArrayList<>();
    students.add(new Student("A", "a"));
    students.add(new Student("|B", "b"));
    students.add(new Student("C", "c"));
    setListAdapter(new StudentListAdapter(this, students));
  }
}