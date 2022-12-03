package com.example.ItemCheck.Manage.userManage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ItemCheck.R;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter{
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    Context context;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        ListItem listItem = items.get(position);

        //list_list를 inflate해서 convertview를 참조합니다.
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_list, parent, false);
        }

        //화면에 보여질 데이터를 참조합니다.
        TextView numText = convertView.findViewById(R.id.num);
        TextView nameText = convertView.findViewById(R.id.name);
        TextView stateText = convertView.findViewById(R.id.state);

        //데이터를 set해줍니다.
        numText.setText(listItem.getNum());
        nameText.setText(listItem.getName());
        stateText.setText(listItem.getState());

        return convertView; // convertView View 객체를 리턴.

    }

    public void addItem(ListItem item){
        items.add(item);
    }
}
