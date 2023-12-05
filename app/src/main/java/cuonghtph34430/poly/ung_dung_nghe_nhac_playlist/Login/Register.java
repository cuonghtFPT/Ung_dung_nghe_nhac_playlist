package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.MyPlayerDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.Login;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Register extends AppCompatActivity {

    EditText user,email,pass;
    Button button,button01;
    MyPlayerDAO myPlayerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user=findViewById(R.id.register_username);
        email=findViewById(R.id.register_email);
        pass=findViewById(R.id.register_password);
        button=findViewById(R.id.register_button);
        button01=findViewById(R.id.register_exit);
        myPlayerDAO = new MyPlayerDAO(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtuser = user.getText().toString();
                String txtemail = email.getText().toString();
                String txtpass = pass.getText().toString();
                if (TextUtils.isEmpty(txtuser) || TextUtils.isEmpty(txtemail)|| TextUtils.isEmpty(txtpass)) {
                    Toast.makeText(Register.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(txtemail)) {
                    Toast.makeText(Register.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (myPlayerDAO.checkIfEmailExists(txtemail)) {
                    Toast.makeText(Register.this, "Địa chỉ email đã được đăng ký", Toast.LENGTH_SHORT).show();
                    return;
                }
                Login login = new Login(txtuser, txtemail, txtpass);
                if (myPlayerDAO.addsig(login) > 0) {
                    Toast.makeText(Register.this, "Đăng Ký Thành Công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Log_in.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isValidEmail(String email) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                        ;
                return email.matches(emailPattern);
            }
        });

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish(); // kết thúc activity hiện tại và trở về activity trước đó
                        // Intent intent = new Intent(Dangky.this, Dangnhap.class);
                        //  startActivity(intent);
                    }
                });
            }
        });
    }
}