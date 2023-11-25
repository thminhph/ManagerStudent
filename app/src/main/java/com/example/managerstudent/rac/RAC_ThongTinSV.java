//package com.example.managerstudent;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class BUS_ThongTinSV extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//    private ImageView imgAvatar;
//    private EditText edtMSSV, edtTen, edtNgaySinh, edtNamHoc;
//    private RadioGroup radioGroup;
//    private RadioButton rbtnNam, rbtnNu;
//    private CheckBox ckbGraduate;
//    private Button btnThem, btnSua, btnXoa, btnClear;
//    private Spinner comboBoxNganhHoc;
//
//    //ListView
//    private ListView lvSinhVien;
//    private ArrayList<L_DTO_SinhVien> dsSinhVien;
//    private ArrayAdapter<L_DTO_SinhVien> adapterListView;
//
//    //DataBase
//    private SQLiteDatabase databaseSinhVien;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.gui_thongtin_sv);
//
//        setControl();
//
//
//        setEvent();
//    }
//
//    private void setEvent() {
//
//        //
//
//        ShowListView();
//
//        // List item selected is White
////        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View v, int i, long index) {
////
////                lvSinhVien.getChildAt(i).setBackgroundColor(Color.WHITE);
////                for (int j = 0; j < DAO_DSSinhVien.size(); j++) {
////                    if (j != i) lvSinhVien.getChildAt(j).setBackgroundColor(Color.YELLOW);
////                }
////
////                L_DTO_SinhVien st = DAO_DSSinhVien.getList().get((int) index);
////                UpdateInfomation(st);
////            }
////        });
//
//        // Button Add
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (edtMSSV.getText().toString().equals("") || edtTen.getText().toString().equals("") ||
//                        edtNgaySinh.getText().toString().equals("") || edtNamHoc.getText().toString().equals("")) {
//                    CatchExceptionInfo("Phải Nhập Đầy Đủ Thông Tin!!");
//                    return;
//
//                } else {
//
//                    // Add Data
//                    if (CheckItemOnly(edtMSSV.getText().toString()) == true) {
//                        L_DTO_SinhVien st = new L_DTO_SinhVien(edtMSSV.getText().toString(), edtTen.getText().toString(),
//                                KiemTraGioiTinh(radioGroup), edtNgaySinh.getText().toString(),
//                                edtNamHoc.getText().toString(), comboBoxNganhHoc.getSelectedItem().toString());
//
//                        DAO_DSSinhVien.add(st);
//                        lvSinhVien.setAdapter(adapterListView);
//
//                        Toast.makeText(BUS_ThongTinSV.this, "Thêm Thành Công \n" + edtMSSV.getText().toString(), Toast.LENGTH_SHORT).show();
//                    } else
//                        Toast.makeText(BUS_ThongTinSV.this, "MSSV Đã Tồn Tại!! \n" + edtMSSV.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        // Button Edit
//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int index = FindPositionItemOfList(edtMSSV.getText().toString());
//
//                if (index != -1) {
//                    L_DTO_SinhVien st = DAO_DSSinhVien.getList().get(index);
//
//                    st.set_MSSV(edtMSSV.getText().toString());
//                    st.set_Ten(edtTen.getText().toString());
//                    st.set_NgaySinh(edtNgaySinh.getText().toString());
//                    st.set_NamHoc(edtNamHoc.toString());
//
//                    //Giới Tính
//                    if (rbtnNam.isChecked() == true) st.set_GioiTinh("Nam");
//                    if (rbtnNu.isChecked() == true) st.set_GioiTinh("Nu");
//
//                    //
//                    ShowListView();
//                    Toast.makeText(BUS_ThongTinSV.this, "Edit.", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (DAO_DSSinhVien.size() == 0) CatchExceptionInfo("Danh Sách Rỗng!!");
//                    else CatchExceptionInfo("Chọn Item Để Sửa!!");
//                }
//            }
//        });
//
//        // Button Remove
//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int index = FindPositionItemOfList(edtMSSV.getText().toString());
//
//                if (index > -1) {
//                    DAO_DSSinhVien.remove(index);
//                    ShowListView();
//                    Toast.makeText(BUS_ThongTinSV.this, "Remove.", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    if (DAO_DSSinhVien.size() == 0) CatchExceptionInfo("Danh Sách Rỗng!!");
//                    else CatchExceptionInfo("Chọn Item Để Xoá!!");
//                }
//
//            }
//        });
//
//        // Button Clear
//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edtMSSV.setText(null);
//                edtTen.setText(null);
//                rbtnNam.setChecked(true);
//                edtNgaySinh.setText(null);
//                edtNamHoc.setText(null);
//                comboBoxNganhHoc.setSelection(0);
//                imgAvatar.setImageResource(R.drawable.info_avatar_unknown);
//                ckbGraduate.setChecked(false);
//
//                RefreshListView();
//            }
//        });
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (rbtnNu.isChecked())
//                    imgAvatar.setImageResource(R.drawable.info_avatar_female);
//                if (rbtnNam.isChecked())
//                    imgAvatar.setImageResource(R.drawable.login_avatar_male);
//            }
//        });
//    }
//
//    private String KiemTraGioiTinh(RadioGroup radioGroup) {
//        if (radioGroup.getCheckedRadioButtonId() == R.id.info_rbNam) {
//            return "Nam";
//        }
//        return "Nu";
//    }
//
//
//    //Hiển thị ListView
//    private void ShowListView() {
////        adapterListView = new ArrayAdapter(BUS_ThongTinSV.this, android.R.layout.simple_list_item_1, DAO_DSSinhVien.dsSinhVien);
////        lvSinhVien.setBackgroundColor(Color.YELLOW);
////        lvSinhVien.setAdapter(adapterListView);
//    }
//
//    /**
//     * Find Position of Item in List
//     */
//    private int FindPositionItemOfList(String MSSV) {
//        for (int i = 0; i < DAO_DSSinhVien.size(); i++) {
//            if (DAO_DSSinhVien.dsSinhVien.get(i).get_MSSV().toString().equals(MSSV) == true)
//                return i;
//        }
//        return -1;
//    }
//
//    // Click in ListView and Update to EditText
//    private void UpdateInfomation(L_DTO_SinhVien st) {
//        edtMSSV.setText(st.get_MSSV());
//        edtTen.setText(st.get_Ten());
//        edtNgaySinh.setText(st.get_NgaySinh());
//        edtNamHoc.setText(st.get_NamHoc());
//        comboBoxNganhHoc.getSelectedItem().equals(st.get_Khoa());
//
//        //22.10.23 (Update RadioButton Giới Tính và Checkbox Graduate)
//        if (st.get_GioiTinh().equals("Nam")) {
//            rbtnNam.setChecked(true);
//            imgAvatar.setImageResource(R.drawable.login_avatar_male);
//        } else {
//            rbtnNu.setChecked(true);
//            imgAvatar.setImageResource(R.drawable.info_avatar_female);
//        }
//    }
//
//    private void RefreshListView() {
//        try {
//            lvSinhVien.setAdapter(adapterListView);
//            Toast.makeText(BUS_ThongTinSV.this, "Refresh.", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            CatchExceptionInfo(e.toString());
//        }
//    }
//
//    //Kiểm tra xem trước khi thêm sv mới có trùng với sv cũ
//    private boolean CheckItemOnly(String mssv) {
//        for (int i = 0; i < DAO_DSSinhVien.dsSinhVien.size(); i++) {
//            if (mssv.equals(DAO_DSSinhVien.dsSinhVien.get(i).get_MSSV().toString()) == true)
//                return false;
//        }
//
//        return true;
//    }
//
//    //TOAST: THONG BAO LOI
//    private void CatchExceptionInfo(String error) {
//        Toast.makeText(this, "Lỗi! \n" + error, Toast.LENGTH_SHORT).show();
//    }
//
//
//    private void setControl() {
//        imgAvatar = findViewById(R.id.ms_info_imgAvatar);
//
//        edtMSSV = findViewById(R.id.tt_edtMSSV);
//        edtTen = findViewById(R.id.ms_info_edtName);
//        edtNgaySinh = findViewById(R.id.info_edtNgaySinh);
//        edtNamHoc = findViewById(R.id.info_edtNamHoc);
//
//        btnThem = findViewById(R.id.ms_info_btnAdd);
//        btnSua = findViewById(R.id.ms_info_btnEdit);
//        btnXoa = findViewById(R.id.ms_info_btnDelete);
//        btnClear = findViewById(R.id.ms_info_btnClear);
//
//        radioGroup = findViewById(R.id.info_RadioGroupGioiTinh);
//        rbtnNam = findViewById(R.id.info_rbNam);
//        rbtnNu = findViewById(R.id.info_rbNu);
//
//        //Spinner
//        comboBoxNganhHoc = findViewById(R.id.info_SpinnerNganhHoc);
//
//        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
//                R.array.NganhHoc, android.R.layout.select_dialog_item);
//        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
//        comboBoxNganhHoc.setAdapter(adapterSpinner);
//        comboBoxNganhHoc.setOnItemSelectedListener(this);
//
//        //
//        dsSinhVien = new ArrayList<>();
//        lvSinhVien = findViewById(R.id.thongtin_lvSinhVien);
//        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsSinhVien);
//
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//
//    }
//}