package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.AdminDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database.MyPlayerDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Log_in extends AppCompatActivity {

    EditText user,pass;
    Button button;
    TextView textView,txtregister;
    MyPlayerDAO myPlayerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        AdminDAO adminDAO = new AdminDAO(this);
        user=findViewById(R.id.sign_username);
        pass=findViewById(R.id.sign_password);
        button=findViewById(R.id.sign_button);
        textView=findViewById(R.id.loginRead);
        txtregister=findViewById(R.id.loginRegister);
        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this, Register.class);
                startActivity(intent);
            }
        });
        myPlayerDAO = new MyPlayerDAO(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtus = user.getText().toString();
                String txtpsw = pass.getText().toString();

                if (TextUtils.isEmpty(txtus) || TextUtils.isEmpty(txtpsw)) {
                    Toast.makeText(Log_in.this, "K được để trống thông tin ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(adminDAO.checkDangNhap(txtus,txtpsw)) {
                    startActivity(new Intent(Log_in.this, MainActivity.class));
                    Toast.makeText(Log_in.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Log_in.this,"User và pass không đúng.Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }

                Boolean login = myPlayerDAO.checklogin(txtus, txtpsw);
                if (!login) {
                    // Hiển thị thông báo "Tên đăng nhập hoặc mật khẩu không đúng"
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị thông báo "Đăng nhập thành công"
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển đến màn hình chính
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        txtregister.setPaintFlags(txtregister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}