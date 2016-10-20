package com.example.ToDoListManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {

  private       boolean        emptyList    = true;
  private final List<TodoItem> todoList     = new ArrayList<TodoItem>();
  private       EditText       txtNewTodo;
  private       ListView       lstTodoItems;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    lstTodoItems = (ListView) findViewById(R.id.lstTodoItems);
    txtNewTodo = (EditText) findViewById(R.id.txtNewTodo);


    //txtNewTodo.setVisibility(View.INVISIBLE);


    txtNewTodo.setOnKeyListener(new View.OnKeyListener() {

      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {

        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
          //add item to the the list and refresh the adapter
          if (emptyList) {
            todoList.clear();
            emptyList=false;
          }
          todoList.add(new TodoItem(txtNewTodo.getText().toString(),""));
          ((ArrayAdapter)lstTodoItems.getAdapter()).notifyDataSetChanged();
          txtNewTodo.setText("");
          return true;
        }
        return false;
      }
    });

    todoList.add(new TodoItem("<<empty>>", "NoImage"));

    //ArrayAdapter<TodoItem> adapter = new ArrayAdapter<TodoItem>(this, android.R.layout.simple_list_item_1, this.todoList);
    CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, todoList);

    lstTodoItems.setAdapter(customArrayAdapter);

    Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Confetti Stream.ttf");
    txtNewTodo.setTypeface(myTypeface);


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.todo_item_crud_actions, menu);
    return true;
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    if (R.id.itmAddNewTodoItem==item.getItemId()) {
      findViewById(R.id.txtNewTodo).setVisibility(View.VISIBLE);
    }
    return super.onMenuItemSelected(featureId, item);
  }

  // http://stackoverflow.com/questions/21833181/arrayadapter-text-and-image

  public static class CustomListViewAdapter extends ArrayAdapter<TodoItem> {

    private final Context context;  //maybe use getContext() instead?


    public CustomListViewAdapter(Context context, int resource, List<TodoItem> objects) {
      super(context, resource, objects);
      this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder = null;
      TodoItem rowItem = getItem(position);

      LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
      if (convertView == null) {
        convertView = mInflater.inflate(R.layout.single_todo_item_layout, null);
        holder = new ViewHolder();
        holder.txtTitle = (TextView) convertView.findViewById(R.id.txtSingleTodoItemTitle);
        holder.imageView = (ImageView) convertView.findViewById(R.id.imgSingleTodoItemImage);
        convertView.setTag(holder);
      } else
        holder = (ViewHolder) convertView.getTag();

      holder.txtTitle.setText(rowItem.getItemTitle());
      holder.imageView.setImageResource(android.R.drawable.btn_plus);

      return convertView;

      //return super.getView(position, convertView, parent);
    }

    private static class ViewHolder {
      ImageView imageView;
      TextView txtTitle;
    }
  }
}
