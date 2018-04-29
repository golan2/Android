package com.example.ToDoListManager;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    18/03/2015 21:01
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class CustomArrayAdapter extends ArrayAdapter<TodoItem> {

  private final Typeface myTypeface;
  private final Context context;
  private final List<TodoItem> items;

  public CustomArrayAdapter(Context context, List<TodoItem> items) {
    super(context,0,items);
    // TODO Auto-generated constructor stub

    this.context = context;
    this.items = items;
    this.myTypeface = Typeface.createFromAsset(context.getAssets(), "Confetti Stream.ttf");
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub

    TodoItem item = this.getItem(position);
    CustomLinearLayout customLayout = (CustomLinearLayout) convertView;

    //*** CHECK IF customLayout is NULL
    if(customLayout == null){
      customLayout = new CustomLinearLayout(context);
    }

    customLayout.textView.setText(item.getItemTitle());
    customLayout.icon.setImageResource(android.R.drawable.btn_plus);

    ////*** IF customLayout IS NOT NULL SET THE TEXT AND BACKGROUND COLOR
    //if(customLayout !=null){
    //
    //  customLayout.textView.setText(text);
    //  customLayout.setBackgroundColor(0);
    //
    //  if(text.equalsIgnoreCase("GREEN")){
    //
    //    customLayout.setBackgroundColor(Color.GREEN);
    //
    //  }else if(text.equalsIgnoreCase("YELLOW")){
    //
    //    customLayout.setBackgroundColor(Color.YELLOW);
    //
    //  }else if(text.equalsIgnoreCase("GRAY")){
    //
    //    customLayout.setBackgroundColor(Color.GRAY);
    //
    //  }else if(text.equalsIgnoreCase("LIGHT GRAY")){
    //
    //    customLayout.setBackgroundColor(Color.LTGRAY);
    //
    //  }else if(text.equalsIgnoreCase("BLUE")){
    //
    //    customLayout.setBackgroundColor(Color.BLUE);
    //
    //  }
    //}


    return customLayout;
  }


  /*
   * OUR CUSTOM LINEARLAYOUT
   *
   */
  class CustomLinearLayout extends LinearLayout{
    Context context;
    protected ImageView icon;
    protected TextView textView;

    public CustomLinearLayout(Context context) {
      super(context);
      this.context = context;
      this.icon = new ImageView(this.context);
      this.textView = new TextView(this.context);
      this.textView.setTypeface(myTypeface);

      //icon.setImageResource(android.R.drawable.btn_plus);


      this.addView(icon);
      this.addView(textView);


      this.setPadding(0, 15, 0, 15);



    }

  }
}
