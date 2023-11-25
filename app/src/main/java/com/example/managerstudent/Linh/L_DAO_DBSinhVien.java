package com.example.managerstudent.Linh;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class L_DAO_DBSinhVien extends SQLiteOpenHelper {

    public L_DAO_DBSinhVien(@Nullable Context context) {
        super(context, "QLSinhVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String quertySinhVien = "CREATE TABLE IF NOT EXISTS tbSINHVIEN(mssv TEXT NOT NULL PRIMARY KEY, ten TEXT, " +
                "gioitinh TEXT, ngaysinh TEXT, khoa TEXT, namhoc Text)";
        String quertyDiem = "CREATE TABLE IF NOT EXISTS tbDIEM(mssv TEXT NOT NULL PRIMARY KEY, diem1 TEXT, diem2 TEXT, diem3 TEXT, " +
                " FOREIGN KEY(mssv) REFERENCES tbSINHVIEN(mssv))";

        database.execSQL(quertySinhVien);
        database.execSQL(quertyDiem);

    }

    //Them dl
    public void Chen_SinhVien_DB(L_DTO_SinhVien sv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyChen = "INSERT INTO tbSINHVIEN VALUES(?,?,?,?,?,?)";
        database.execSQL(quertyChen, new String[]{sv.get_MSSV(), sv.get_Ten(), sv.get_GioiTinh(), sv.get_NgaySinh(), sv.get_Khoa(), sv.get_NamHoc()});
    }

    public void Chen_Diem_DB(L_DTO_Diem d) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyChen = "INSERT INTO tbDIEM VALUES(?,?,?,?)";
        database.execSQL(quertyChen, new String[]{d.get_mssv(), d.get_Diem1(), d.get_Diem2(), d.get_Diem3()});
    }

    //Xoa dl
    public void Xoa_SinhVien_DB(String mssv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyXoa = "DELETE FROM tbSINHVIEN WHERE mssv=?";
        database.execSQL(quertyXoa, new String[]{mssv});
    }

      public void Xoa_Diem_DB(String mssv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyXoa = "DELETE FROM tbDIEM WHERE mssv=?";
        database.execSQL(quertyXoa, new String[]{mssv});
    }

    //Sua dl
    public void Sua_SinhVien_DB(L_DTO_SinhVien sv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertySua = "UPDATE tbSINHVIEN SET ten=?, gioitinh=?, ngaysinh=?, khoa=?, namhoc=? WHERE mssv=?";
        database.execSQL(quertySua, new String[]{sv.get_Ten(), sv.get_GioiTinh(), sv.get_NgaySinh(), sv.get_Khoa(), sv.get_NamHoc(), sv.get_MSSV()});
    }

    public void Sua_Diem_DB(L_DTO_Diem d) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyDiem = "UPDATE tbDIEM SET diem1=?, diem2=?, diem3=? WHERE mssv=?";
        database.execSQL(quertyDiem, new String[]{d.get_Diem1(), d.get_Diem2(), d.get_Diem3(), d.get_mssv()});
    }

    //Doc dl
    public List<L_DTO_SinhVien> Doc_SinhVien_DB() {
        List<L_DTO_SinhVien> dsSinhVien = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String quertyDoc = "SELECT * FROM tbSINHVIEN";
        Cursor cursor = sqLiteDatabase.rawQuery(quertyDoc, null);

        if (cursor.moveToFirst()) {
            do {
                L_DTO_SinhVien sv = new L_DTO_SinhVien();
                sv.set_MSSV(cursor.getString(0));
                sv.set_Ten(cursor.getString(1));
                sv.set_GioiTinh(cursor.getString(2));
                sv.set_NgaySinh(cursor.getString(3));
                sv.set_Khoa(cursor.getString(4));
                sv.set_NamHoc(cursor.getString(5));
                dsSinhVien.add(sv);
            } while (cursor.moveToNext());
        }

        return dsSinhVien;
    }

    public List<L_DTO_Diem> Doc_Diem_DB() {
        List<L_DTO_Diem> dsDiem = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String quertyDiem = "SELECT * FROM tbDIEM";
        Cursor cursor = sqLiteDatabase.rawQuery(quertyDiem, null);

        if (cursor.moveToFirst()) {
            do {
                L_DTO_Diem d = new L_DTO_Diem();
                d.set_mssv(cursor.getString(0));
                d.set_Diem1(cursor.getString(1));
                d.set_Diem2(cursor.getString(2));
                d.set_Diem3(cursor.getString(3));

                dsDiem.add(d);
            } while (cursor.moveToNext());
        }
        return dsDiem;
    }

//    public void DL_Mau(SQLiteDatabase database)
//    {
//        String quertySinhVien = "CREATE TABLE IF NOT EXISTS tbSINHVIEN(MSSV TEXT PRIMARY KEY, Ten TEXT, " +
//                "GioiTinh TEXT, NgaySinh TEXT, Khoa TEXT, NamHoc Text)";
//        String quertyDiem = "CREATE TABLE IF NOT EXISTS tbDIEM(MSSV TEXT PRIMARY KEY, Diem1 TEXT, Diem2 TEXT, Diem3 TEXT, " +
//                " FOREIGN KEY(MSSV) REFERENCES tbSINHVIEN(MSSV))";
//
//        database.execSQL(quertySinhVien);
//        database.execSQL(quertyDiem);
//
//        database.execSQL("INSERT INTO tbSINHVIEN VALUES (\"1\", \"LINH\", \"NAM\", \"05/04/2000\", \"CNTT\", \"2022\")");
//        database.execSQL("INSERT INTO tbSINHVIEN VALUES (\"2\", \"LONG\", \"NAM\", \"05/04/2001\", \"DO HOA\", \"2020\")");
//        database.execSQL("INSERT INTO tbDIEM VALUES (\"1\", \"8\", \"9\", \"10\")");
//        database.execSQL("INSERT INTO tbDIEM VALUES (\"2\", \"8.5\", \"9.5\", \"1\")");
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //tạo table -phải sử dụng try_catch
//        try {
//        //sqlite
//        DBSinhVien = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);
//
//        String quertySinhVien = "CREATE TABLE tbSINHVIEN(MSSV TEXT PRIMARY KEY, Ten TEXT, " +
//                "GioiTinh TEXT, NgaySinh TEXT, Khoa TEXT, NamHoc Text)";
//        String quertyDiem = "CREATE TABLE tbDIEM(MSSV TEXT PRIMARY KEY, Diem1 TEXT, Diem2 TEXT, Diem3 TEXT, " +
//                " FOREIGN KEY(MSSV) REFERENCES tbSINHVIEN(MSSV))";
//
//        DBSinhVien.execSQL(quertySinhVien);
//        DBSinhVien.execSQL(quertyDiem);
//
//        DBSinhVien.execSQL("INSERT INTO tbSINHVIEN VALUES (\"1\", \"LINH\", \"NAM\", \"05/04/2000\", \"CNTT\", \"2022\")");
//        DBSinhVien.execSQL("INSERT INTO tbSINHVIEN VALUES (\"2\", \"LONG\", \"NAM\", \"05/04/2001\", \"DO HOA\", \"2022\")");
//
//        DBSinhVien.execSQL("INSERT INTO tbDIEM VALUES (\"1\", \"8\", \"9\", \"10\")");
//        DBSinhVien.execSQL("INSERT INTO tbDIEM VALUES (\"2\", \"8.5\", \"9.5\", \"1\")");
//
//    }catch (Exception e){
//        Log.e("Error DataBase","DB đã tồn tại");
//    }
}
