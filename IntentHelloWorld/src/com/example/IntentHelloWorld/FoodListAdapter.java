package com.example.IntentHelloWorld;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    25/03/2015 15:30
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class FoodListAdapter extends ArrayAdapter<Food> {

  private final List<Food> foodList;

  public FoodListAdapter(Activity context, List<Food> foodList) {
    super(context, 0, foodList);
    this.foodList = foodList;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    return super.getView(position, convertView, parent);
  }
}
