package util;

import java.awt.List;

public class ListUtil       
{

public static void clearList(java.awt.List target)
{

  int item_loop=0;
  int item_max=0;

  item_max=target.getItemCount();
  for (item_loop=0;item_loop<item_max;item_loop++)
    target.deselect(item_loop);
}


}
