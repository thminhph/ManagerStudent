package com.example.managerstudent.Minh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.managerstudent.Minh.Adapter.ChuyenNganhAdapter;
import com.example.managerstudent.Minh.Database.DBChuyenNganh;
import com.example.managerstudent.Minh.Database.DBHocKy;
import com.example.managerstudent.Minh.Database.DBKhoa;
import com.example.managerstudent.Minh.Database.DBMonHoc;
import com.example.managerstudent.Minh.models.ChuyenNganh;
import com.example.managerstudent.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChuyenNganhActivity extends AppCompatActivity {
    Toolbar toolbarChuyenNganh;
    TextView tvCountItem;
    ListView lvChuyenNganh;
    Button btnCancel, btnRemove, btnRename;
    LinearLayout grEdit;
    ChuyenNganhAdapter adapter;
    FloatingActionButton btnThem;
    static int selectedIndex = -1;
    DBKhoa dbKhoa = new DBKhoa(this,null,null,1);
    DBChuyenNganh dbChuyenNganh = new DBChuyenNganh(this,null,null,1);
    DBHocKy dbHocKy = new DBHocKy(this,null,null,1);
    DBMonHoc dbMonHoc = new DBMonHoc(this,null,null,1);

    static List<ChuyenNganh>dsChuyenNganh=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_nganh);
        setControl();
        dsChuyenNganh=dbChuyenNganh.DocDLDSChuyenNganhTheoMa(KhoaActivity.dsKhoa.get(KhoaActivity.selectedIndex).getMaKhoa());
        adapter = new ChuyenNganhAdapter(this, dsChuyenNganh);
        lvChuyenNganh.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        setSupportActionBar(toolbarChuyenNganh);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(KhoaActivity.dsKhoa.get(KhoaActivity.selectedIndex).getTenKhoa());

        tvCountItem.setText(String.valueOf(lvChuyenNganh.getCount()));
        lvChuyenNganh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                Intent nextPage = new Intent(ChuyenNganhActivity.this, HocKiActivity.class);
                startActivity(nextPage);
            }
        });
        lvChuyenNganh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                dsChuyenNganh.remove(selectedIndex);
                dbChuyenNganh.XoaDLChuyenNganh(dsChuyenNganh.get(selectedIndex));
                adapter.notifyDataSetChanged();
                grEdit.setVisibility(View.GONE);
                tvCountItem.setText(String.valueOf(lvChuyenNganh.getCount()));
            }
        });
        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRenameChuyenNganh();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddNewChuyenNganh();
            }

        });

    }

    private void showDialogRenameChuyenNganh() {
        Dialog dialog = new Dialog(ChuyenNganhActivity.this);
        dialog.setTitle("Sửa Tên Chuyên Ngành");
        dialog.setContentView(R.layout.dialog_rename);
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnOK = dialog.findViewById(R.id.btnOK);
        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);
        editTenMoi.setText(dsChuyenNganh.get(selectedIndex).getTenChuyenNganh());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTenMoi.getText().toString().isEmpty()) {
                    dsChuyenNganh.get(selectedIndex).setTenChuyenNganh(editTenMoi.getText().toString());
                    dbChuyenNganh.SuaDLChuyenNganh(dsChuyenNganh.get(selectedIndex));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
    }

    private void showDialogAddNewChuyenNganh() {
        Dialog dialog = new Dialog(ChuyenNganhActivity.this);
        dialog.setTitle("Them Chuyen Nganh Moi");
        dialog.setContentView(R.layout.dialog_rename);
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnOk = dialog.findViewById(R.id.btnOK);
        EditText edtTenMoi = dialog.findViewById(R.id.edtNhapTen);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtTenMoi.getText().toString().isEmpty()) {
                    ChuyenNganh nganh=new ChuyenNganh(String.valueOf(dbChuyenNganh.DocDLDSChuyenNganh().size()),edtTenMoi.getText().toString(),KhoaActivity.dsKhoa.get(KhoaActivity.selectedIndex).getMaKhoa());
                    dbChuyenNganh.ThemDLChuyenNganh(nganh);
                   dsChuyenNganh.add(nganh);
                    adapter.notifyDataSetChanged();
                    tvCountItem.setText(String.valueOf(lvChuyenNganh.getCount()));
                    dialog.dismiss();
                }
            }


        });
    }

    private void setControl() {
        toolbarChuyenNganh = findViewById(R.id.toolbarChuyenNganh);
        tvCountItem = findViewById(R.id.tvCountItem);
        lvChuyenNganh = findViewById(R.id.lvChuyenNganh);
        btnCancel = findViewById(R.id.btnCancel);
        btnRemove = findViewById(R.id.btnRemove);
        btnRename = findViewById(R.id.btnRename);
        grEdit = findViewById(R.id.grEdit);
        btnThem = findViewById(R.id.btnThem);
    }
}