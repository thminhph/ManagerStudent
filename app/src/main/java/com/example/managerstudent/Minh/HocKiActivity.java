package com.example.managerstudent.Minh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.managerstudent.Minh.Adapter.HocKiAdapter;
import com.example.managerstudent.Minh.Database.DBHocKy;
import com.example.managerstudent.Minh.models.HocKi;
import com.example.managerstudent.R;

import java.util.ArrayList;
import java.util.List;

public class HocKiActivity extends AppCompatActivity {

    Toolbar toolbarHocKi;
    ListView lvHocKi;
    HocKiAdapter adapter;
    static int selectedIndexHocKy = -1;
    DBHocKy dbHocKy = new DBHocKy(this, null, null, 1);
    static List<HocKi> dsHK = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_ki);
        setControl();
        dsHK = dbHocKy.DocDLDSHocKyTheoMa(ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
        if (dsHK.size() == 0) {
            HocKi hk1=new HocKi(String.valueOf(dbHocKy.DocDLDSHocKy().size()), "Hoc kì 1", ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
            HocKi hk2=new HocKi(String.valueOf(dbHocKy.DocDLDSHocKy().size()+1), "Hoc kì 2", ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
            HocKi hk3=new HocKi(String.valueOf(dbHocKy.DocDLDSHocKy().size()+2), "Hoc kì 3", ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
            HocKi hk4=new HocKi(String.valueOf(dbHocKy.DocDLDSHocKy().size()+3), "Hoc kì 4", ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
            HocKi hk5=new HocKi(String.valueOf(dbHocKy.DocDLDSHocKy().size()+4), "Hoc kì 5", ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
            dbHocKy.ThemDLHocKy(hk1);
            dbHocKy.ThemDLHocKy(hk2);
            dbHocKy.ThemDLHocKy(hk3);
            dbHocKy.ThemDLHocKy(hk4);
            dbHocKy.ThemDLHocKy(hk5);
            dsHK = dbHocKy.DocDLDSHocKyTheoMa(ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getMaNganh());
        }
        adapter = new HocKiAdapter(this, dsHK);
        lvHocKi.setAdapter(adapter);
        setEvent();
    }

    private void setEvent() {
        setSupportActionBar(toolbarHocKi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(ChuyenNganhActivity.dsChuyenNganh.get(ChuyenNganhActivity.selectedIndex).getTenChuyenNganh());

        lvHocKi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndexHocKy = position;
                Intent nextPage = new Intent(HocKiActivity.this, MonHocActivity.class);
                startActivity(nextPage);
            }
        });
    }

    private void setControl() {
        toolbarHocKi = findViewById(R.id.toolbarHocKi);
        lvHocKi = findViewById(R.id.lvHocKi);
    }
}