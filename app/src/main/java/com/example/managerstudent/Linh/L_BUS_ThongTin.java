package com.example.managerstudent.Linh;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent.R;

import java.util.ArrayList;
import java.util.List;

public class L_BUS_ThongTin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    LinearLayout layoutThongTin;
    ImageView imgAvatar;
    Button btnThem, btnXoa, btnSua, btnClear, btnBack;
    EditText edtMSSV, edtTen, edtNgaySinh, edtNamHoc;
    TextView tvSoLuongSV;
    Spinner spKhoa;
    RadioGroup rgGioiTinh;
    RadioButton rdNam, rdNu;
    //ListView
    private ListView lvSinhVien;
    private ArrayAdapter<L_DTO_SinhVien> adapterListView;

    //DataBase
    private L_DAO_DBSinhVien dbQLSV = new L_DAO_DBSinhVien(this);
    private List<L_DTO_SinhVien> dsSinhVien = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_thong_tin);

        setControl();
        setEvent();
    }

    private void setControl() {
        layoutThongTin = findViewById(R.id.tt_layoutThongTin);

        imgAvatar = findViewById(R.id.tt_imgAvatar);
        btnBack = findViewById(R.id.tt_btnBack);

        tvSoLuongSV = findViewById(R.id.tt_tvSoLuongSV);
        btnThem = findViewById(R.id.tt_btnThem);
        btnXoa = findViewById(R.id.tt_btnXoa);
        btnSua = findViewById(R.id.tt_btnSua);
        btnClear = findViewById(R.id.tt_btnClear);

        edtMSSV = findViewById(R.id.tt_edtMSSV);
        edtTen = findViewById(R.id.tt_edtTen);
        edtNgaySinh = findViewById(R.id.tt_edtNgaySinh);
        edtNamHoc = findViewById(R.id.tt_edtNamHoc);

        rgGioiTinh = findViewById(R.id.tt_RadioGroupGioiTinh);
        rdNam = findViewById(R.id.tt_rbNam);
        rdNu = findViewById(R.id.tt_rbNu);
        spKhoa = findViewById(R.id.tt_SpinnerKhoa);

        //listview
        lvSinhVien = findViewById(R.id.thongtin_lvSinhVien);

        //Spinner
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.Khoa, android.R.layout.select_dialog_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
        spKhoa.setAdapter(adapterSpinner);
        spKhoa.setOnItemSelectedListener(this);
    }

    private void setEvent() {
        //mac dinh
        rdNam.setChecked(true);
        imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        rgGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdNu.isChecked())
                    imgAvatar.setImageResource(R.drawable.l_avatar_nu);
                if (rdNam.isChecked())
                    imgAvatar.setImageResource(R.drawable.l_avatar_nam);
            }
        });

        //Chay DB, đọc db và lấy ds sinhvien
        dbQLSV = new L_DAO_DBSinhVien(this);
        dsSinhVien.clear();
        dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsSinhVien);
        lvSinhVien.setAdapter(adapterListView);

        Sua_SoLuongSV();

        // item selected is Yellow : ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long index) {
                L_DTO_SinhVien sv = dsSinhVien.get((int) index);
                ClickListView(sv);
            }
        });


        //
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_BUS_ThongTin.super.onBackPressed();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tạo 1 đối tg sinh viên
                L_DTO_SinhVien sv = new L_DTO_SinhVien();
                L_DTO_Diem diem = new L_DTO_Diem();

                if (edtMSSV.getText().length() > 0 && KTTrungMSSV(edtMSSV.getText().toString()) == false) {
                    //cập nhật thông tin từ 3 thông tin ng dùng nhp vào
                    sv.set_MSSV(edtMSSV.getText().toString());
                    diem.set_mssv(edtMSSV.getText().toString());

                    sv.set_Ten(edtTen.getText().toString());
                    sv.set_NgaySinh(edtNgaySinh.getText().toString());
                    sv.set_NamHoc(edtNamHoc.getText().toString());

                    if (rdNam.isChecked() == true)
                        sv.set_GioiTinh("Nam");
                    else sv.set_GioiTinh("Nu");

                    sv.set_Khoa(spKhoa.getSelectedItem().toString());

                    //ghi xuống csdl
                    dbQLSV.Chen_SinhVien_DB(sv);
                    dbQLSV.Chen_Diem_DB(diem);

                    //cap nhat ListView, dsSinhVien
                    dsSinhVien.clear();
                    dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());
                    lvSinhVien.setAdapter(adapterListView);

                    Toast.makeText(L_BUS_ThongTin.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(L_BUS_ThongTin.this, "Phải Nhập Đúng Thông Tin!!", Toast.LENGTH_SHORT).show();
                }

                Sua_SoLuongSV();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = edtMSSV.getText().toString();
                int index = FindItem(mssv);

                if (index > -1) {
                    dsSinhVien.remove(index);
                    dbQLSV.Xoa_SinhVien_DB(mssv);
                    dbQLSV.Xoa_Diem_DB(mssv);

                    dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());
                    lvSinhVien.setAdapter(adapterListView);

                    ClearAllText();
                    Toast.makeText(L_BUS_ThongTin.this, "Đã Xoá Sinh Viên [ " + mssv + " ].", Toast.LENGTH_SHORT).show();
                } else {
                    if (dsSinhVien.size() == 0)
                        Toast.makeText(L_BUS_ThongTin.this, "Danh Sách Rỗng!!.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(L_BUS_ThongTin.this, "Chọn SV Để Xoá!!.", Toast.LENGTH_SHORT).show();
                }

                Sua_SoLuongSV();
            }
        });
        // Button Edit
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = FindItem(edtMSSV.getText().toString());

                if (index != -1) {
                    dsSinhVien.clear();

                    L_DTO_SinhVien sv = new L_DTO_SinhVien();
                    sv.set_MSSV(edtMSSV.getText().toString());
                    sv.set_Ten(edtTen.getText().toString());
                    sv.set_NgaySinh(edtNgaySinh.getText().toString());
                    sv.set_NamHoc(edtNamHoc.getText().toString());
                    //Giới Tính
                    if (rdNam.isChecked() == true)
                        sv.set_GioiTinh("Nam");
                    else sv.set_GioiTinh("Nu");
                    //Khoa
                    sv.set_Khoa(spKhoa.getSelectedItem().toString());

                    dbQLSV.Sua_SinhVien_DB(sv);
                    dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());
                    lvSinhVien.setAdapter(adapterListView);

                    Toast.makeText(L_BUS_ThongTin.this, "Sửa Thành Công!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (dsSinhVien.size() == 0)
                        Toast.makeText(L_BUS_ThongTin.this, "Danh Sách Rỗng!!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(L_BUS_ThongTin.this, "Lỗi!!", Toast.LENGTH_SHORT).show();
                }

                Sua_SoLuongSV();
            }
        });
        //Clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAllText();
            }
        });
    }

    //
    private int FindItem(String MSSV) {
        for (int i = 0; i < dsSinhVien.size(); i++) {
            if (dsSinhVien.get(i).get_MSSV().toString().equals(MSSV) == true)
                return i;
        }
        return -1;
    }

    //Kiểm tra xem trước khi thêm sv mới có trùng với sv cũ
    private boolean KTTrungMSSV(String mssv) {
        for (int i = 0; i < dsSinhVien.size(); i++) {
            if (mssv.equals(dsSinhVien.get(i).get_MSSV().toString()) == true)
                return true;
        }
        return false;
    }

    // Click List View
    private void ClickListView(L_DTO_SinhVien sv) {
        edtMSSV.setText(sv.get_MSSV());
        edtTen.setText(sv.get_Ten());
        edtNgaySinh.setText(sv.get_NgaySinh());
        edtNamHoc.setText(sv.get_NamHoc());

        if (sv.get_GioiTinh().equals("Nam")) rdNam.setChecked(true);
        else rdNu.setChecked(true);

        for (int i = 0; i < spKhoa.getCount(); i++) {
            spKhoa.setSelection(i);
            String str = spKhoa.getSelectedItem().toString();
            if (sv.get_Khoa().equals(str)) {
                return;
            }
        }

        if (sv.get_GioiTinh().equals("Nam")) {
            rdNam.setChecked(true);
            imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        } else {
            rdNu.setChecked(true);
            imgAvatar.setImageResource(R.drawable.l_avatar_nu);
        }
    }

    //
    private void Sua_SoLuongSV() {
        String sizeDSSV = "" + dsSinhVien.size();
        tvSoLuongSV.setText(sizeDSSV);
    }

    //Clear
    private void ClearAllText() {
        edtMSSV.setText(null);
        edtTen.setText(null);
        rdNam.setChecked(true);
        imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        edtNgaySinh.setText(null);
        edtNamHoc.setText(null);
        spKhoa.setSelection(0);

        dsSinhVien.clear();
        dsSinhVien.addAll(dbQLSV.Doc_SinhVien_DB());
        lvSinhVien.setAdapter(adapterListView);

        Sua_SoLuongSV();
    }


    //Spinner adapter
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
