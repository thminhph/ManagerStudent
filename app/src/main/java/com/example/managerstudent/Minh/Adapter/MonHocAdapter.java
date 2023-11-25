package com.example.managerstudent.Minh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.R;
import com.example.managerstudent.Minh.models.MonHoc;

import java.util.List;

public class MonHocAdapter extends BaseAdapter {
    Context context;
    List<MonHoc> dsMonHoc;
    public MonHocAdapter(Context context, List<MonHoc> dsMonHoc) {
        this.context = context;
        this.dsMonHoc = dsMonHoc;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<MonHoc> getDsMonHoc() {
        return dsMonHoc;
    }

    public void setDsMonHoc(List<MonHoc> dsMonHoc) {
        this.dsMonHoc = dsMonHoc;
    }

    @Override
    public int getCount() {
        return  dsMonHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return dsMonHoc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_monhoc,null);

        MonHoc monHoc=dsMonHoc.get(position);
        TextView tvMaMH = convertView.findViewById(R.id.tvMaMH);
        TextView tvTenMH = convertView.findViewById(R.id.tvTenMH);
        TextView tvSoTinChi = convertView.findViewById(R.id.tvSoTinChi);
        tvMaMH.setText(monHoc.getMaMonHoc());
        tvTenMH.setText(monHoc.getTenMonHoc());
        tvSoTinChi.setText(monHoc.getSoTinChi());
        return convertView;
    }


}
