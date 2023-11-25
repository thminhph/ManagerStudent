package com.example.managerstudent.Linh;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerstudent.R;

import java.util.ArrayList;
import java.util.List;

public class L_BUS_Diem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //DECLARE
    private String luuMssv;
    private TextView tvMssvTen, tvDiem1, tvDiem2, tvDiem3, tvKhoa, tvSoLuongSV;
    private EditText edtDiem1, edtDiem2, edtDiem3;
    private Button btnSave, btnClear, btnBack;

    private LinearLayout layoutDiem;

    private ListView lvSinhVien;
    private ArrayAdapter<L_DTO_SinhVien> adapterListView;

    //DataBase
    private L_DAO_DBSinhVien dbQLSV = new L_DAO_DBSinhVien(this);
    private List<L_DTO_SinhVien> dsSinhVien = new ArrayList<>();
    private List<L_DTO_Diem> dsDiem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_diem);

        setControls();
        setEvents();
    }

    private void setEvents() {
        layoutDiem.setVisibility(View.INVISIBLE);

        // Button Clear Text
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDiem1.setText(null);
                edtDiem2.setText(null);
                edtDiem3.setText(null);
                Toast.makeText(L_BUS_Diem.this, "Đã Clear.", Toast.LENGTH_SHORT).show();
            }
        });

        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_BUS_Diem.super.onBackPressed();
            }
        });

        //Chay DB, đọc db và lấy ds sinhvien
        dsSinhVien.clear();
        dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());
        dsDiem.addAll(dbQLSV.Doc_Diem_DB());

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsSinhVien);
        lvSinhVien.setAdapter(adapterListView);

        String sizeDSSV = "" + dsSinhVien.size();
        tvSoLuongSV.setText(sizeDSSV);

        // item selected is Yellow : ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long index) {
                L_DTO_SinhVien sv = dsSinhVien.get((int) index);
                L_DTO_Diem diem = new L_DTO_Diem();

                //Tìm đối tượng điểm có mssv = mssv của dsSinhvien
                for (int d = 0; d < dsDiem.size(); d++) {
                    if (dsDiem.get(d).get_mssv().equals(sv.get_MSSV())) {
                        diem = dsDiem.get(d);
                    }
                }

                ClickListView(sv, diem);
            }
        });


        // Button SAVE
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Kiểm Format tra điểm
                if (Integer.parseInt(edtDiem1.getText().toString()) < 0 || Integer.parseInt(edtDiem1.getText().toString()) > 10)
                    return;
                if (Integer.parseInt(edtDiem2.getText().toString()) < 0 || Integer.parseInt(edtDiem2.getText().toString()) > 10)
                    return;
                if (Integer.parseInt(edtDiem3.getText().toString()) < 0 || Integer.parseInt(edtDiem3.getText().toString()) > 10)
                    return;

                //Begin
                dsSinhVien.clear();

                L_DTO_Diem d = new L_DTO_Diem();
                d.set_mssv(luuMssv);
                d.set_Diem1(edtDiem1.getText().toString());
                d.set_Diem2(edtDiem2.getText().toString());
                d.set_Diem3(edtDiem3.getText().toString());
                dbQLSV.Sua_Diem_DB(d);

                dsDiem.addAll(dbQLSV.Doc_Diem_DB());
                dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());
                lvSinhVien.setAdapter(adapterListView);

                //End
                Toast.makeText(L_BUS_Diem.this, "Lưu Thành Công!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControls() {
        layoutDiem = findViewById(R.id.d_layout_Diem);
        lvSinhVien = findViewById(R.id.d_lvSinhVien);

        tvMssvTen = findViewById(R.id.d_tvMssvTen);
        tvDiem1 = findViewById(R.id.d_tvDiem1);
        tvDiem2 = findViewById(R.id.d_tvDiem2);
        tvDiem3 = findViewById(R.id.d_tvDiem3);
        tvKhoa = findViewById(R.id.d_tvKhoa);
        tvSoLuongSV = findViewById(R.id.d_tvSoLuongSV);

        edtDiem1 = findViewById(R.id.d_edtDiem1);
        edtDiem2 = findViewById(R.id.d_edtDiem2);
        edtDiem3 = findViewById(R.id.d_edtDiem3);

        btnBack = findViewById(R.id.d_btnBack);
        btnSave = findViewById(R.id.d_btnSave);
        btnClear = findViewById(R.id.d_btnClear);
    }

    // Click List View
    private void ClickListView(L_DTO_SinhVien sv, L_DTO_Diem diem) {
        layoutDiem.setVisibility(View.VISIBLE);

        luuMssv = sv.get_MSSV();
        tvMssvTen.setText(sv.get_MSSV() + "\n" + sv.get_Ten());
        tvKhoa.setText(sv.get_Khoa());

        if (sv.get_Khoa().equals("CNTT")) {
            tvDiem1.setText("Java");
            tvDiem2.setText("LTDD");
            tvDiem3.setText("OOP");
        }

        if (sv.get_Khoa().equals("DO HOA")) {
            tvDiem1.setText("PS");
            tvDiem2.setText("Ve");
            tvDiem3.setText("Chup");
        }

        if (sv.get_Khoa().equals("DIEN TU")) {
            tvDiem1.setText("DienTu1");
            tvDiem2.setText("DienTu2");
            tvDiem3.setText("DienTu3");
        }

        edtDiem1.setText(diem.get_Diem1());
        edtDiem2.setText(diem.get_Diem2());
        edtDiem3.setText(diem.get_Diem3());

    }

    //
    private int FindItem(String MSSV) {
        for (int i = 0; i < dsDiem.size(); i++) {
            if (dsDiem.get(i).get_mssv().toString().equals(MSSV) == true)
                return i;
        }
        return -1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}