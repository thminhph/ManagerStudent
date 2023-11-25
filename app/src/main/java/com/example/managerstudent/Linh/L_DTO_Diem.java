package com.example.managerstudent.Linh;

import java.io.Serializable;

public class L_DTO_Diem implements Serializable {


    private String _mssv, _Diem1, _Diem2, _Diem3;


    public L_DTO_Diem(String _mssv, String _Diem1, String _Diem2, String _Diem3) {
        this._mssv = _mssv;
        this._Diem1 = _Diem1;
        this._Diem2 = _Diem2;
        this._Diem3 = _Diem3;
    }

    public L_DTO_Diem() {
    }

    public String get_mssv() {
        return _mssv;
    }

    public void set_mssv(String _mssv) {
        this._mssv = _mssv;
    }

    public String get_Diem1() {
        return _Diem1;
    }

    public void set_Diem1(String _Diem1) {
        this._Diem1 = _Diem1;
    }

    public String get_Diem2() {
        return _Diem2;
    }

    public void set_Diem2(String _Diem2) {
        this._Diem2 = _Diem2;
    }

    public String get_Diem3() {
        return _Diem3;
    }

    public void set_Diem3(String _Diem3) {
        this._Diem3 = _Diem3;
    }


}
