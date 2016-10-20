package com.example.IntentHelloWorld;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    25/03/2015 15:26
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class FoodListActivity extends ListActivity {

  private final ArrayList<Food> foodList = new ArrayList<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_food_list);

    foodList.add(new Food("Pitta", 30));
    foodList.add(new Food("Mitta", 40));

    //add adaptet
  }
}