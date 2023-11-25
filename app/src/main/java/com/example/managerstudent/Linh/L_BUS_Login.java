package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.managerstudent.Activity_Main;
import com.example.managerstudent.R;

public class L_BUS_Login extends AppCompatActivity{

    //DECLARE
    Button ms_login_btnLogin;
    EditText ms_login_edtUser, ms_login_edtPass;

    String pass;
    String user;

    L_DAO_DBSinhVien dbSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_login);

        DemoAccount();
        setControls();
        setEvents();
    }

    private void DemoAccount() {
        user = " ";
        pass = " ";
    }

    private void setEvents() {
        ms_login_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ms_login_edtUser.getText().toString().equals("") ||
//                        ms_login_edtPass.getText().toString().equals("")) {
//                    Toast.makeText(L_BUS_Login.this, "User or Password is not Empty!!", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (ms_login_edtUser.getText().toString().equals(user) &&
//                            ms_login_edtPass.getText().toString().equals(pass)) {
//                        Intent intent = new Intent(L_BUS_Login.this, Activity_Main.class);
//                        startActivity(intent);
//                    } else
//                        Toast.makeText(L_BUS_Login.this, "User or Password is Wrong!", Toast.LENGTH_SHORT).show();
//                }

                Intent intent = new Intent(L_BUS_Login.this, Activity_Main.class);
                startActivity(intent);
            }
        });
    }

    private void setControls() {
        ms_login_btnLogin = findViewById(R.id.ms_login_btnLogin);
        ms_login_edtUser = findViewById(R.id.ms_login_edtUser);
        ms_login_edtPass = findViewById(R.id.tt_edtNamHoc);
    }

}