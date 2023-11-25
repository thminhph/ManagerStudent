package com.example.managerstudent.Minh.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.managerstudent.Minh.models.HocKi;

import java.util.ArrayList;
import java.util.List;

public class DBHocKy extends DBChuyenNganh {

    public DBHocKy(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "QLSV", factory, 1);
    }

    public void ThemDLHocKy(HocKi hocKi) {
        String sql = "insert into tbHocKy values(?,?,?)";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{hocKi.getMaHK(),hocKi.getTenHocKi(),hocKi.getMaNganh()});
    }

    public List<HocKi> DocDLDSHocKyTheoMa(String manganh) {
        List<HocKi> data = new ArrayList<>();
        String sql = "Select * from tbHocKy where manganh=?";
        Cursor cursor=this.getReadableDatabase().rawQuery(sql, new String[]{manganh});
        if (cursor.moveToFirst()) {
            do {
                HocKi hocKi = new HocKi();
                hocKi.setMaHK(String.valueOf(cursor.getString(0)));
                hocKi.setTenHocKi(cursor.getString(1));
                hocKi.setMaNganh(cursor.getString(2));
                data.add(hocKi);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public List<HocKi> DocDLDSHocKy() {
        List<HocKi> data = new ArrayList<>();
        String sql = "Select * from tbHocKy";
        Cursor cursor=this.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                HocKi hocKi = new HocKi();
                hocKi.setMaHK(String.valueOf(cursor.getString(0)));
                hocKi.setTenHocKi(cursor.getString(1));
                hocKi.setMaNganh(cursor.getString(2));
                data.add(hocKi);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void XoaDLHocKy(HocKi hocKi) {
        String sql = "delete from tbHocKy where mahk=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{hocKi.getMaHK()});
    }

    public void SuaDLHocKy(HocKi hocKi) {
        String sql = "Update tbHocKy set tenhocky=? where mahk=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{hocKi.getTenHocKi(), hocKi.getMaHK()});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
