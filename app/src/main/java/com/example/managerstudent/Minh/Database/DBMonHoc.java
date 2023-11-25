package com.example.managerstudent.Minh.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.managerstudent.Minh.models.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class DBMonHoc extends DBHocKy {

    public DBMonHoc(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "QLSV", factory, 1);
    }

    public void ThemDLMH(MonHoc monHoc) {
        String sql = "insert into tbMonHoc values(?,?,?,?)";
        this.getWritableDatabase().execSQL(sql, new String[]{monHoc.getMaMonHoc(),monHoc.getTenMonHoc(), monHoc.getSoTinChi(),monHoc.getMaHK()});
    }

    public List<MonHoc> DocDLDSMonHocTheoMa(String mahk) {
        List<MonHoc> data = new ArrayList<>();
        String sql = "Select * from tbMonHoc where mahk=?";
        Cursor cursor=this.getReadableDatabase().rawQuery(sql, new String[]{mahk});
        if (cursor.moveToFirst()) {
            do {
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMonHoc(cursor.getString(0));
                monHoc.setSoTinChi(cursor.getString(1));
                monHoc.setTenMonHoc(cursor.getString(2));
                monHoc.setMaHK(cursor.getString(3));
                data.add(monHoc);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public List<MonHoc> DocDLDSMonHoc() {
        List<MonHoc> data = new ArrayList<>();
        String sql = "Select * from tbMonHoc where mahk=?";
        Cursor cursor=this.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMonHoc(cursor.getString(0));
                monHoc.setSoTinChi(cursor.getString(1));
                monHoc.setTenMonHoc(cursor.getString(2));
                monHoc.setMaHK(cursor.getString(3));
                data.add(monHoc);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void XoaDLMH(MonHoc monHoc) {
        String sql = "delete from tbMonHoc where mamh=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{monHoc.getMaMonHoc()});
    }

    public void SuaDLMH(MonHoc monHoc) {
        String sql = "Update tbMonHoc set stc=?, tenmh=? where mamh=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{String.valueOf(monHoc.getSoTinChi()), monHoc.getTenMonHoc(), monHoc.getMaMonHoc()});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
