package com.moovers.moovers.model;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import com.moovers.moovers.R;
import com.moovers.moovers.ui.todo.MainTodoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TodoListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public TodoListAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListTaskViewHolder holder;
        if (convertView == null) {
            holder = new ListTaskViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.todo_task_list_item, parent, false);
            holder.task_image = convertView.findViewById(R.id.task_image);
            holder.task_name = convertView.findViewById(R.id.task_name);
            holder.task_date = convertView.findViewById(R.id.task_date);
            convertView.setTag(holder);
        } else {
            holder = (ListTaskViewHolder) convertView.getTag();
        }
        holder.task_image.setId(position);
        holder.task_name.setId(position);
        holder.task_date.setId(position);

        HashMap<String, String> song;
        song = data.get(position);

        try {
            holder.task_name.setText(song.get(MainTodoActivity.KEY_TASK));
            holder.task_date.setText(song.get(MainTodoActivity.KEY_DATE));

            /* Image */
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(getItem(position));
            holder.task_image.setTextColor(color);
            holder.task_image.setText(Html.fromHtml("&#11044;"));
            /* Image */

        } catch (Exception e) {
        }
        return convertView;
    }


    class ListTaskViewHolder {
        TextView task_image;
        TextView task_name, task_date;
    }
}
