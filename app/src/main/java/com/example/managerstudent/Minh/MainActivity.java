package com.example.managerstudent.Minh;

import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent.Minh.Database.DBChuyenNganh;
import com.example.managerstudent.Minh.Database.DBHocKy;
import com.example.managerstudent.Minh.Database.DBKhoa;
import com.example.managerstudent.Minh.Database.DBMonHoc;


public class MainActivity extends AppCompatActivity {
    DBKhoa dbKhoa = new DBKhoa(this,null,null,1);
    DBChuyenNganh dbChuyenNganh = new DBChuyenNganh(this,null,null,1);
    DBHocKy dbHocKy = new DBHocKy(this,null,null,1);
    DBMonHoc dbMonHoc = new DBMonHoc(this,null,null,1);


}