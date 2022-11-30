package com.example.ItemCheck.Manage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ItemCheck.R;
import com.example.ItemCheck.User.BandActivity;
import com.example.ItemCheck.User.DigestActivity;
import com.example.ItemCheck.User.ItemInfo;
import com.example.ItemCheck.User.PainkillerActivity;

import java.util.ArrayList;

public class FirstaidManageActivity extends AppCompatActivity {

    private String TAG = FirstaidManageActivity.class.getSimpleName();

    private GridView gridview = null;
    private GridViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid_manage);

        gridview = (GridView) findViewById(R.id.gridview);
        adapter = new GridViewAdapter();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long id) {
                if(id==0){
                    Intent intent = new Intent(FirstaidManageActivity.this, BandManageActivity.class);
                    startActivity(intent);
                }
                else if(id==1){
                    Intent intent = new Intent(FirstaidManageActivity.this, PainkillerManageActivity.class);
                    startActivity(intent);
                }
                else if(id==2){
                    Intent intent = new Intent(FirstaidManageActivity.this, DigestManageActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Adapter 안에 아이템의 정보 담기
        adapter.addItem(new ItemInfo("일회용 밴드", R.drawable.band1));
        adapter.addItem(new ItemInfo( "진통제", R.drawable.painkiller1));
        adapter.addItem(new ItemInfo( "소화제", R.drawable.digest1));

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