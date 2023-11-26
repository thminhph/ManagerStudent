package com.example.managerstudent.Linh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerstudent.Activity_Main;
import com.example.managerstudent.Minh.Database.DBAccount;
import com.example.managerstudent.Minh.models.M_Account;
import com.example.managerstudent.R;

import java.util.ArrayList;
import java.util.List;

public class L_BUS_Login extends AppCompatActivity{

    //DECLARE
    Button btnSignIn, btnSignUp;
    EditText edtUser, edtPassword;

    List<M_Account>Acc=new ArrayList<>();

    DBAccount dbacconut=new DBAccount(this ,null,null,1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_login);
        Acc=dbacconut.getListAccount();
        setControls();
        setEvents();
    }



    private void setEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M_Account acount = new M_Account();
                for (M_Account item : dbacconut.getListAccount()) {
                    if (item.getNameAcc().equals(edtUser.getText().toString().trim())) {
                        acount = item;
                        break;
                    }
                }
                if (acount.getNameAcc()!="") {
                    if (acount.getPass().equals(edtPassword.getText().toString().trim())) {
                        Intent inte = new Intent(L_BUS_Login.this, Activity_Main.class);
                        startActivity(inte);
                    } else {
                        Toast.makeText(L_BUS_Login.this,"Mat Khau Sai", Toast.LENGTH_SHORT).show();
//                        AlertDialog.Builder da = new AlertDialog.Builder(L_BUS_Login.this);
//                        da.setTitle("Thông Báo");
//                        da.setMessage("Mật Khẩu Sai");
//                        da.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        });
                    }
                }
                else {
                    Toast.makeText(L_BUS_Login.this,"tai khoan khong ton tai", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder da = new AlertDialog.Builder(L_BUS_Login.this);
//                    da.setTitle("Thông Báo");
//                    da.setMessage("Tai khoan khong ton tai");
//                    da.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
//                        }
//                    });
                }

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(L_BUS_Login.this,L_BUS_SignUp.class);
                startActivity(inte);
            }
        });

    }

    private void setControls() {
        btnSignIn= findViewById(R.id.btnSignIn);
        btnSignUp= findViewById(R.id.btnSignUp);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

}