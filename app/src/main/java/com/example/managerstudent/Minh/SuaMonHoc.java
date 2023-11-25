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

public class SuaMonHoc extends AppCompatActivity {

    String maMH;
    EditText edtMaMH, edtTenMH, edtSoTinChi;
    Button btnCancel, btnConfirm;
    DBMonHoc dbMonHoc = new DBMonHoc(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon_hoc);
        setControl();
        setEvent();
    }

    private void setEvent() {

        edtMaMH.setText(MonHocActivity.dsMH.get(MonHocActivity.selectedIndex).getMaMonHoc());
        edtTenMH.setText(MonHocActivity.dsMH.get(MonHocActivity.selectedIndex).getTenMonHoc());
        edtSoTinChi.setText(MonHocActivity.dsMH.get(MonHocActivity.selectedIndex).getSoTinChi());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String maMH = edtMaMH.getText().toString();
                edtMaMH.setEnabled(false);
                String tenMH = edtTenMH.getText().toString();
                String soTC = edtSoTinChi.getText().toString();
                MonHoc monHoc=new MonHoc(maMH,tenMH,soTC,HocKiActivity.dsHK.get(HocKiActivity.selectedIndexHocKy).getMaHK());
                dbMonHoc.SuaDLMH(monHoc);
                MonHocActivity.dsMH.get(MonHocActivity.selectedIndex).setTenMonHoc(tenMH);
                MonHocActivity.dsMH.get(MonHocActivity.selectedIndex).setSoTinChi(soTC);
                MonHocActivity.adapter.notifyDataSetChanged();
                Toast.makeText(SuaMonHoc.this,"Sua thanh cong",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        edtMaMH = findViewById(R.id.edtMaMH);
        edtTenMH = findViewById(R.id.edtTenMH);
        edtSoTinChi = findViewById(R.id.edtSoTinChi);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
    }
}