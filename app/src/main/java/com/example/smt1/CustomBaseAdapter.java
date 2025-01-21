package com.example.smt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String listpay [];
    int listpayimages [];
    LayoutInflater inflater;
    public CustomBaseAdapter(Context ctx, String [] paymethod, int [] payimages) {
       this.context=ctx;
       this.listpay=paymethod;
       this.listpayimages=payimages;
       inflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listpay.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.activity_l1,null);
//        ImageView imageView=convertView.findViewById(R.id.imageicon);
//        TextView textView=convertView.findViewById(R.id.imgtext);
//        imageView.setImageResource(listpayimages[position]);
//        textView.setText(listpay[position]);
        return convertView;
    }
}
