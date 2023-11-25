package com.example.managerstudent.Minh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerstudent.Minh.Database.DBMonHoc;
import com.example.managerstudent.Minh.models.MonHoc;
import com.example.managerstudent.R;

public class ThemMonHoc extends AppCompatActivity {

    EditText edtMaMH, edtTenMH, edtSoTinChi;
    Button btnCancel, btnAdd;
    DBMonHoc dbMonHoc = new DBMonHoc(this,null,null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_hoc);

        setControl();

        setEvent();
    }

    private void setEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maMH = edtMaMH.getText().toString();
                String tenMH = edtTenMH.getText().toString();
                String soTC = edtSoTinChi.getText().toString();
                MonHoc monHoc=new MonHoc(maMH,tenMH,soTC,HocKiActivity.dsHK.get(HocKiActivity.selectedIndexHocKy).getMaHK());
                dbMonHoc.ThemDLMH(monHoc);
                MonHocActivity.dsMH.add(monHoc);
                MonHocActivity.adapter.notifyDataSetChanged();
                onBackPressed();
                Toast.makeText(ThemMonHoc.this,"Sua thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        edtMaMH = findViewById(R.id.edtMaMH);
        edtTenMH = findViewById(R.id.edtTenMH);
        edtSoTinChi = findViewById(R.id.edtSoTinChi);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
    }
}