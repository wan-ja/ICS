package com.example.ItemCheck;

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
                    startActivity(intent);
                }
                else if(id==3){
                    Intent intent = new Intent(LendActivity.this, CountActivity.class);
                    startActivity(intent);
                }
                else if(id==4){
                    Intent intent = new Intent(LendActivity.this, UmbrellaActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LendActivity.this, CountActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Adapter 안에 아이템의 정보 담기
        adapter.addItem(new ItemInfo("일회용품", R.drawable.recycle2));
        adapter.addItem(new ItemInfo( "구급약품", R.drawable.first_aid2));
        adapter.addItem(new ItemInfo( "보조 배터리", R.drawable.battery1));
        adapter.addItem(new ItemInfo( "공학용 계산기", R.drawable.count1));
        adapter.addItem(new ItemInfo( "우산", R.drawable.umbrella1));
        adapter.addItem(new ItemInfo( "물품 추가", R.drawable.add1));

        //그리드뷰에 Adapter 설정
        gridview.setAdapter(adapter);

    }


    /* 그리드뷰 어댑터 */
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

            return convertView;  //뷰 객체 반환

        }
    }

}