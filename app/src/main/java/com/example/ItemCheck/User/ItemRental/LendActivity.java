package com.example.ItemCheck.User.ItemRental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ItemCheck.R;
import com.example.ItemCheck.User.ItemRental.Item.AddedActivity;
import com.example.ItemCheck.User.ItemRental.Item.BatteryActivity;
import com.example.ItemCheck.User.ItemRental.Item.CountActivity;
import com.example.ItemCheck.User.ItemRental.Item.FirstaidActivity;
import com.example.ItemCheck.User.ItemRental.Item.RecycleActivity;
import com.example.ItemCheck.User.ItemRental.Item.UmbrellaActivity;

import java.util.ArrayList;

public class LendActivity extends AppCompatActivity {

    private String TAG = LendActivity.class.getSimpleName();

    private GridView gridview = null;
    private GridViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend);

        gridview = (GridView) findViewById(R.id.gridview);
        adapter = new GridViewAdapter();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long id) {
                if(id==0){
                    Intent intent = new Intent(LendActivity.this, RecycleActivity.class);
                    startActivity(intent);
                }
                else if(id==1){
                    Intent intent = new Intent(LendActivity.this, FirstaidActivity.class);
                    startActivity(intent);
                }
                else if(id==2){
                    Intent intent = new Intent(LendActivity.this, BatteryActivity.class);
                    intent.putExtra("str", adapter.items.get(2).getName());
                    startActivity(intent);
                }
                else if(id==3){
                    Intent intent = new Intent(LendActivity.this, CountActivity.class);
                    intent.putExtra("str", adapter.items.get(3).getName());
                    startActivity(intent);
                }
                else if(id==4){
                    Intent intent = new Intent(LendActivity.this, UmbrellaActivity.class);
                    intent.putExtra("str", adapter.items.get(4).getName());
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LendActivity.this, AddedActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Adapter ?????? ???????????? ?????? ??????
        adapter.addItem(new ItemInfo("????????????", R.drawable.recycle2));
        adapter.addItem(new ItemInfo( "????????????", R.drawable.first_aid2));
        adapter.addItem(new ItemInfo( "???????????????", R.drawable.battery1));
        adapter.addItem(new ItemInfo( "??????????????????", R.drawable.count1));
        adapter.addItem(new ItemInfo( "??????", R.drawable.umbrella1));
        adapter.addItem(new ItemInfo( "????????? ??????", R.drawable.add1));

        //??????????????? Adapter ??????
        gridview.setAdapter(adapter);

    }


    /* ???????????? ????????? */
    class GridViewAdapter extends BaseAdapter {
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ItemInfo item) {
            items.add(item);
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
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final ItemInfo ItemInfo = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gridview_list_item, viewGroup, false);

                TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);

                tv_name.setText(ItemInfo.getName());
                iv_icon.setImageResource(ItemInfo.getResId());
                Log.d(TAG, "getView() - [ "+position+" ] "+ItemInfo.getName());

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            return convertView;  //??? ?????? ??????

        }
    }

}