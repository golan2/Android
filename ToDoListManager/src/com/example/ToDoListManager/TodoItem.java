package com.example.ToDoListManager;

/**
* <pre>
* <B>Copyright:</B>   Izik Golan
* <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
* <B>Creation:</B>    18/03/2015 21:06
* <B>Since:</B>       BSM 9.21
* <B>Description:</B>
*
* </pre>
*/
class TodoItem {
  private final String itemTitle;
  private final String itemImage;

  TodoItem(String itemTitle, String itemImage) {
    this.itemTitle = itemTitle;
    this.itemImage = itemImage;
  }

  public String getItemTitle() {
    return itemTitle;
  }

  public String getItemImage() {
    return itemImage;
  }

  @Override
  public String toString() {
    return itemTitle;
  }
}
