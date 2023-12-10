package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity.MainActivity;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.NguoiDungDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Log_in extends AppCompatActivity {
    EditText user,pass;
    Button button;
    TextView textView,txtregister;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        user=findViewById(R.id.sign_username);
        pass=findViewById(R.id.sign_password);
        button=findViewById(R.id.sign_button);
        textView=findViewById(R.id.loginRead);
        txtregister=findViewById(R.id.loginRegister);
        txtregister.setOnClickListener(v -> {
            Intent intent = new Intent(Log_in.this, Register.class);
            startActivity(intent);
        });
        nguoiDungDAO = new NguoiDungDAO(this);
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
            boolean login = nguoiDungDAO.checklogin(txtus, txtpsw);
            if (!login) {
                Toast.makeText(getApplicationContext(), text3, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), text2, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USERNAME", txtus);
                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("USERNAME", txtus);
                editor.apply();
                startActivity(intent);
            }
        });
        txtregister.setPaintFlags(txtregister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}