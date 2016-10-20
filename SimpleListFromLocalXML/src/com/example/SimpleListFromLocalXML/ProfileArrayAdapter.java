package com.example.SimpleListFromLocalXML;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    23/03/2015 22:39
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
@SuppressLint("ViewHolder")
public class ProfileArrayAdapter extends ArrayAdapter<Profile> {
  private final Activity activity;

  public ProfileArrayAdapter(Activity activity, List<Profile> objects) {
    super(activity.getApplicationContext(), R.layout.single_profile_layout, R.id.profileName, objects);
    this.activity = activity;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = activity.getLayoutInflater();
    View rowView = inflater.inflate(R.layout.single_profile_layout, parent, false);

    TextView txtProfileName = (TextView) rowView.findViewById(R.id.profileName);
    txtProfileName.setText(getItem(position).getName());
    TextView txtGps = (TextView) rowView.findViewById(R.id.gps);
    txtGps.setText(getItem(position).getGps().toString());

    return rowView;
  }
}
