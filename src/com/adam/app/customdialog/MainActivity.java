
package com.adam.app.customdialog;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "CustDialog";
    private Button show;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        show = (Button)this.findViewById(R.id.btn_show);
        show.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CustomDialog custDialog = new CustomDialog(MainActivity.this);
                custDialog.show();
            }
            
        });
        
//        show = new Button(this);
//        show.setText("Show custom dialog");
//        show.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, 
//                LayoutParams.FILL_PARENT));
//        setContentView(show);
//        
//        show.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
//                CustomDialog custDialog = new CustomDialog(MainActivity.this);
//                custDialog.show();
//            }
//            
//        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public class CustomDialog extends Dialog {
//        Button btnClose;
        ListView lvList;
        
        
        private CustListAdapter custListAdapter;
        
        
        public CustomDialog(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.cust_dialog_demo);
//            
//            AdapterView<? > lvList = (ListView)findViewById(android.R.id.list);
//            custListAdapter = new CustListAdapter(context);
//            ((AbsListView) lvList).setAdapter(custListAdapter);
            
//            btnClose = (Button)findViewById(R.id.btn_close);
//            btnClose.setOnClickListener(this);
            
            lvList = (ListView)findViewById(R.id.list_view);
            custListAdapter = new CustListAdapter(context);
            lvList.setAdapter(custListAdapter);
            
            //set list data
//            lvList.setAdapter(custListAdapter);
//            lvList.setAdapter(new ArrayAdapter<String>(MainActivity.this,
//                    android.R.layout.simple_list_item_activated_1,getData()));
            //config list item data
//            lvList.setBackgroundColor(Color.TRANSPARENT);
//            lvList.setTextFilterEnabled(true);
            lvList.setChoiceMode(lvList.CHOICE_MODE_SINGLE);
            lvList.setItemChecked(0, true);
            lvList.setOnItemClickListener(new OnItemClickListener() {
              
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(MainActivity.this, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
                    
                    
//                    lvList.setItemChecked(position, true);
//                    lvList.setSelection(position);
//                    CustomDialog.this.dismiss();
                    
                }
                
            });
        }

//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//            switch(v.getId()) {
//                case R.id.btn_close:
//                    this.dismiss();
//                    break;
//            }
//        }
        
        //list test
        private List<String> getData() {
            List<String> data = new ArrayList<String>();
            data.add("One");
            data.add("Two");
            data.add("Three");
            return data;
        }
        
        //customize item adapter
        public class CustListAdapter extends BaseAdapter {
            private LayoutInflater mInflater;
            
            private final Integer[] mContentIds = {
                    R.string.str_item_one,
                    R.string.str_item_two,
                    R.string.str_item_three,

            };
            
            private final Integer[] mImageIds = {
                    R.drawable.logo160dpi,
                    R.drawable.logo160dpi,
                    R.drawable.logo160dpi,

            };
            
            private int count = mContentIds.length;
            
            public CustListAdapter(Context context) {
//                mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mInflater = LayoutInflater.from(context);
            }
            
            
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return count;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                Log.i(TAG, "getView");
                ViewHolder holder;
                
                if(convertView == null) {
                    holder = new ViewHolder();
                    
                    convertView = mInflater.inflate(
                            R.layout.list_item, null);
                    
                    holder.itemIcon = (ImageView)convertView.findViewById(R.id.item_icon);
                    holder.itemContent = (TextView)convertView.findViewById(R.id.item_text);
                    
                    convertView.setTag(holder);
                    
                }
                else {
                    holder = (ViewHolder)convertView.getTag();
                }
                
                holder.itemIcon.setImageResource(mImageIds[position]);
                holder.itemContent.setText(mContentIds[position]);
                
                holder.id = position;
                return convertView;
            }
            
        }
        
        
        //list content
        class ViewHolder {
            ImageView itemIcon;
            TextView  itemContent;
            int id;
        }
        
    }

}
