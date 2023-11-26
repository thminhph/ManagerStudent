package com.example.managerstudent.Minh.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.managerstudent.Minh.models.Khoa;

import java.util.ArrayList;
import java.util.List;

public class DBKhoa extends DBAccount {

    public DBKhoa(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "ManagerStudent", factory, 1);
    }
    public void ThemDLKhoa(Khoa khoa) {
        String sql = "insert into tbKhoa values(?,?)";
        this.getWritableDatabase().execSQL(sql, new String[]{khoa.getMaKhoa(),khoa.getTenKhoa()});
    }

    public List<Khoa> DocDLDSKhoa() {
        List<Khoa> data = new ArrayList<>();
        String sql = "Select * from tbKhoa";
        Cursor cursor=this.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Khoa khoa = new Khoa();
                khoa.setMaKhoa(cursor.getString(0));
                khoa.setTenKhoa(cursor.getString(1));
                data.add(khoa);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void XoaDLKhoa(Khoa khoa) {
        String sql = "delete from tbKhoa where makhoa=?";
        this.getWritableDatabase().execSQL(sql, new String[]{khoa.getMaKhoa()});
    }

    public void SuaDLKhoa(Khoa khoa) {
        String sql = "Update tbKhoa set tenkhoa=? where makhoa=?";
        this.getWritableDatabase().execSQL(sql, new String[]{khoa.getTenKhoa(), khoa.getMaKhoa()});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
