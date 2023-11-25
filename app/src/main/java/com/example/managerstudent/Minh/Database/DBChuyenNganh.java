package com.example.managerstudent.Minh.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.managerstudent.Minh.models.ChuyenNganh;

import java.util.ArrayList;
import java.util.List;

public class DBChuyenNganh extends DBKhoa {


    public DBChuyenNganh(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "QLSV", factory, 1);
    }

    public void ThemDLChuyenNganh(ChuyenNganh nganh) {
        String sql = "insert into tbChuyenNganh values(?,?,?)";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{nganh.getMaNganh(),nganh.getTenChuyenNganh(),nganh.getMaKhoa()});
    }

    public List<ChuyenNganh> DocDLDSChuyenNganhTheoMa(String makhoa) {
        List<ChuyenNganh> data = new ArrayList<>();
        String sql = "Select * from tbChuyenNganh where makhoa=?";
        Cursor cursor = this.getReadableDatabase().rawQuery(sql,new String[]{makhoa});
        if (cursor.moveToFirst()) {
            do {
                ChuyenNganh nganh = new ChuyenNganh();
                nganh.setMaNganh(cursor.getString(0));
                nganh.setTenChuyenNganh(cursor.getString(1));
                nganh.setMaKhoa(cursor.getString(2));
                data.add(nganh);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public List<ChuyenNganh> DocDLDSChuyenNganh() {
        List<ChuyenNganh> data = new ArrayList<>();
        String sql = "Select * from tbChuyenNganh";
        Cursor cursor = this.getReadableDatabase().rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                ChuyenNganh nganh = new ChuyenNganh();
                nganh.setMaNganh(cursor.getString(0));
                nganh.setTenChuyenNganh(cursor.getString(1));
                nganh.setMaKhoa(cursor.getString(2));
                data.add(nganh);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void XoaDLChuyenNganh(ChuyenNganh nganh) {
        String sql = "delete from tbChuyenNganh where manganh=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{nganh.getMaNganh()});
    }

    public void SuaDLChuyenNganh(ChuyenNganh nganh) {
        String sql = "Update tbChuyenNganh set tennganh=? where manganh=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{nganh.getTenChuyenNganh(), nganh.getMaNganh()});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
