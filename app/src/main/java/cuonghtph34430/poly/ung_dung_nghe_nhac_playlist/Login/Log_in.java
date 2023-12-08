package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.AdminDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.MyPlayerDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Log_in extends AppCompatActivity {
    EditText user,pass;
    Button button;
    TextView textView,txtregister;
    MyPlayerDAO myPlayerDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("language", "vi");

        // Set the retrieved language as the app's locale
        Locale locale = new Locale(selectedLanguage);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_log_in);

        AdminDAO adminDAO = new AdminDAO(this);
        user=findViewById(R.id.sign_username);
        pass=findViewById(R.id.sign_password);
        button=findViewById(R.id.sign_button);
        textView=findViewById(R.id.loginRead);
        txtregister=findViewById(R.id.loginRegister);
        txtregister.setOnClickListener(v -> {
            Intent intent = new Intent(Log_in.this, Register.class);
            startActivity(intent);
        });
        myPlayerDAO = new MyPlayerDAO(this);
        button.setOnClickListener(v -> {
            String txtus = user.getText().toString();
            String txtpsw = pass.getText().toString();
            String text1 = getString( R.string.null_input);
            String text2 = getString(R.string.sign_in_successfully);
            String text3 = getString(R.string.fail_message);
            if (TextUtils.isEmpty(txtus) || TextUtils.isEmpty(txtpsw)) {
                Toast.makeText(Log_in.this, text1, Toast.LENGTH_SHORT).show();
                return;
            }
            if(adminDAO.checkDangNhap(txtus,txtpsw)) {
                startActivity(new Intent(Log_in.this, MainActivity.class));
                Toast.makeText(Log_in.this,text2,Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Log_in.this,text3,Toast.LENGTH_SHORT).show();
            }

            boolean login = myPlayerDAO.checklogin(txtus, txtpsw);
            if (!login) {
                // Hiển thị thông báo "Tên đăng nhập hoặc mật khẩu không đúng"
                Toast.makeText(getApplicationContext(), text3, Toast.LENGTH_SHORT).show();
            } else {
                // Hiển thị thông báo "Đăng nhập thành công"
                Toast.makeText(getApplicationContext(), text2, Toast.LENGTH_SHORT).show();
                // Chuyển đến màn hình chính
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("isAdmin", true);
                startActivity(intent);
            }
        });
        txtregister.setPaintFlags(txtregister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}