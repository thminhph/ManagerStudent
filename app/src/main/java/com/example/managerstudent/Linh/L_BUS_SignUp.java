package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerstudent.Minh.Database.DBAccount;
import com.example.managerstudent.R;

public class L_BUS_SignUp extends AppCompatActivity {

EditText edtUser,edtPassword,edtConfirmPassword;
Button btnCear, btnConfirm;
DBAccount dbAccount = new DBAccount(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lbus_sign_up);
        SetControl();
        SetEvent();
    }

    private void SetEvent() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAccount.addAccount(edtUser.getText().toString().trim(), edtPassword.getText().toString().trim());
                if (dbAccount.getListAccount().size() > 0) {
                    Intent inte = new Intent(L_BUS_SignUp.this, L_BUS_Login.class);
                    startActivity(inte);
                }
                Toast.makeText(L_BUS_SignUp.this, String.valueOf(dbAccount.getListAccount().size()), Toast.LENGTH_SHORT).show();
            }
        });
        btnCear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUser.getText().clear();
                edtPassword.getText().clear();
                edtConfirmPassword.getText().clear();

            }
        });
    }

    private void SetControl() {
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnCear = findViewById(R.id.btnClear);
        btnConfirm = findViewById(R.id.btnConfirm);
    }
}