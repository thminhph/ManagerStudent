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

import com.example.managerstudent.Minh.Adapter.KhoaAdapter;
import com.example.managerstudent.Minh.Database.DBKhoa;
import com.example.managerstudent.Minh.models.Khoa;
import com.example.managerstudent.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KhoaActivity extends AppCompatActivity {

    Toolbar toolbarDanhSachKhoa;
    ListView lvKhoa;
    FloatingActionButton btnThem;
    TextView tvCountItem;
    KhoaAdapter adapter;
    LinearLayout grEdit;
    Button btnCancel, btnRename, btnRemove;
    static int selectedIndex = -1;
    DBKhoa dbKhoa = new DBKhoa(this,null,null,1);

    static List<Khoa>dsKhoa=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khoa);
        setControl();

        dsKhoa=dbKhoa.DocDLDSKhoa();
        adapter = new KhoaAdapter(this, dsKhoa);
        lvKhoa.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        setSupportActionBar(toolbarDanhSachKhoa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Danh Sách Khoa");

        tvCountItem.setText(String.valueOf(lvKhoa.getCount()));

        lvKhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                Intent nextPage = new Intent(KhoaActivity.this, ChuyenNganhActivity.class);
                startActivity(nextPage);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddNewKhoa();
            }
        });

        lvKhoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                dsKhoa.remove(selectedIndex);
                adapter.notifyDataSetChanged();
                grEdit.setVisibility(View.GONE);
                tvCountItem.setText(String.valueOf(lvKhoa.getCount()));
            }
        });

        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRenameKhoa();
            }
        });

    }

    private void showDialogRenameKhoa() {
        Dialog dialog = new Dialog(KhoaActivity.this);
        dialog.setTitle("Sửa Tên Khoa");
        dialog.setContentView(R.layout.dialog_rename);
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnOK = dialog.findViewById(R.id.btnOK);
        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);
        editTenMoi.setText(dsKhoa.get(selectedIndex).getTenKhoa());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Khoa khoa=new Khoa(dsKhoa.get(selectedIndex).getMaKhoa(),editTenMoi.getText().toString());
                    dbKhoa.SuaDLKhoa(khoa);
                    dsKhoa.get(selectedIndex).setTenKhoa(editTenMoi.getText().toString());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
            }
        });
    }

    private void showDialogAddNewKhoa() {
        Dialog dialog = new Dialog(KhoaActivity.this);
        dialog.setTitle("Them Khoa moi");
        dialog.setContentView(R.layout.dialog_rename);
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnAdd = dialog.findViewById(R.id.btnOK);
        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTenMoi.getText().toString().isEmpty()) {
                    dbKhoa.ThemDLKhoa(new Khoa(String.valueOf(dsKhoa.size()), editTenMoi.getText().toString()));
                    dsKhoa.add(new Khoa(String.valueOf(dsKhoa.size()), editTenMoi.getText().toString()));
                    adapter.notifyDataSetChanged();
                    tvCountItem.setText(String.valueOf(lvKhoa.getCount()));
                    dialog.dismiss();
                }
            }
        });
    }

    private void setControl() {
        toolbarDanhSachKhoa = findViewById(R.id.toolbarDanhSachKhoa);
        lvKhoa = findViewById(R.id.lvKhoa);
        btnThem = findViewById(R.id.btnThem);
        tvCountItem = findViewById(R.id.tvCountItem);
        grEdit = findViewById(R.id.grEdit);
        btnCancel = findViewById(R.id.btnCancel);
        btnRename = findViewById(R.id.btnRename);
        btnRemove = findViewById(R.id.btnRemove);
    }
}