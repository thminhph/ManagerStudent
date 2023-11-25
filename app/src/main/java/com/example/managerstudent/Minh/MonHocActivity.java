package com.example.managerstudent.Minh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.managerstudent.Minh.Adapter.MonHocAdapter;
import com.example.managerstudent.Minh.Database.DBMonHoc;
import com.example.managerstudent.Minh.models.MonHoc;
import com.example.managerstudent.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MonHocActivity extends AppCompatActivity {
    TextView tvKhoa, tvChuyenNganh;
    Toolbar toolbarMonHoc;
    static TextView tvCountItem;
    ListView lvMonHoc;
    FloatingActionButton btnThem;
    LinearLayout grEdit;
    Button btnCancel, btnRemove, btnEdit;
    static MonHocAdapter adapter;
    static int selectedIndex = -1;
    static List<MonHoc> dsMH=new ArrayList<>();
    DBMonHoc dbMonHoc = new DBMonHoc(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);
        dsMH=dbMonHoc.DocDLDSMonHocTheoMa(HocKiActivity.dsHK.get(HocKiActivity.selectedIndexHocKy).getMaHK());
        setControl();
        setEvent();

    }

    private void setEvent() {
        tvKhoa.setText(KhoaActivity.dsKhoa.get(KhoaActivity.selectedIndex).getTenKhoa());
        tvChuyenNganh.setText(ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getTenChuyenNganh());

        adapter = new MonHocAdapter(this, dsMH);
        lvMonHoc.setAdapter(adapter);

        setSupportActionBar(toolbarMonHoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(HocKiActivity.dsHK.get(HocKiActivity.selectedIndexHocKy).getTenHocKi());
        tvCountItem.setText(String.valueOf(lvMonHoc.getCount()));
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MonHocActivity.this, ThemMonHoc.class);
                startActivity(nextPage);
            }
        });
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                grEdit.setVisibility(View.VISIBLE);
                selectedIndex = position;
                return false;
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grEdit.setVisibility(View.GONE);
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbMonHoc.XoaDLMH(dsMH.get(selectedIndex));
                dsMH.remove(selectedIndex);
                adapter.notifyDataSetChanged();
                grEdit.setVisibility(View.GONE);
                tvCountItem.setText(String.valueOf(lvMonHoc.getCount()));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MonHocActivity.this, SuaMonHoc.class);
                startActivity(nextPage);
            }
        });
    }

//    private void showDialogRenameMonHoc() {
//        Dialog dialog = new Dialog(MonHocActivity.this);
//        dialog.setTitle("Sửa tên hôn học");
//        dialog.setContentView(R.layout.dialog_rename);
//        dialog.show();
//
//        Button btnCancel = dialog.findViewById(R.id.btnCancel);
//        Button btnOK = dialog.findViewById(R.id.btnOK);
//        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);
//        editTenMoi.setText(dsMonHoc.get(selectedIndex).getTenMonHoc());
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//        btnOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!editTenMoi.getText().toString().isEmpty()) {
//                    dsMonHoc.get(selectedIndex).setTenMonHoc(editTenMoi.getText().toString());
//                    adapter.notifyDataSetChanged();
//                    dialog.dismiss();
//                }
//            }
//        });
//    }

//    private void showDialogAddNewMonHoc() {
//        Dialog dialog = new Dialog(MonHocActivity.this);
//        dialog.setTitle("Thêm môn học mới");
//        dialog.setContentView(R.layout.dialog_rename);
//        dialog.show();
//
//        Button btnCancel = dialog.findViewById(R.id.btnCancel);
//        Button btnAdd = dialog.findViewById(R.id.btnOK);
//        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);
//
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!editTenMoi.getText().toString().isEmpty()) {
//                    dsMonHoc.add(new MonHoc(editTenMoi.getText().toString(), ));
//                    adapter.notifyDataSetChanged();
//                    tvCountItem.setText(String.valueOf(lvMonHoc.getCount()));
//                    dialog.dismiss();
//                }
//            }
//        });
//    }

    private void setControl() {
        toolbarMonHoc = findViewById(R.id.toolbarMonHoc);
        tvCountItem = findViewById(R.id.tvCountItem);
        lvMonHoc = findViewById(R.id.lvMonHoc);
        btnThem = findViewById(R.id.btnThem);
        grEdit = findViewById(R.id.grEdit);
        btnCancel = findViewById(R.id.btnCancel);
        btnRemove = findViewById(R.id.btnRemove);
        btnEdit = findViewById(R.id.btnEdit);
        tvKhoa = findViewById(R.id.tvKhoa);
        tvChuyenNganh = findViewById(R.id.tvChuyenNganh);
    }
}