package com.example.managerstudent.Minh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.R;
import com.example.managerstudent.Minh.models.Khoa;

import java.util.List;

public class KhoaAdapter extends BaseAdapter {
    List<Khoa> dsKhoa;
    Context context;

    public KhoaAdapter(Context context,List<Khoa> dsKhoa) {
        this.dsKhoa = dsKhoa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsKhoa.size();
    }

    @Override
    public Object getItem(int position) {
        return dsKhoa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,null);

        TextView textView = convertView.findViewById(R.id.tvTitle);

        textView.setText(dsKhoa.get(position).getTenKhoa());

        return convertView;
    }
}
