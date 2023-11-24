package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class fragment_feedback extends Fragment {
    private EditText edtFullname, edtPhonenumber, edtEmail, edtComment;
    private Button btnSendFeedback;
    private TextInputLayout fullNameLayout, phoneNumberLayout, emailLayout, commentLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        // Ánh xạ các thành phần trong layout
        edtFullname = view.findViewById(R.id.edtFullname);
        edtPhonenumber = view.findViewById(R.id.edtPhonenumber);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtComment = view.findViewById(R.id.edtComment);

        fullNameLayout = view.findViewById(R.id.full_name);
        phoneNumberLayout = view.findViewById(R.id.Phone_number);
        emailLayout = view.findViewById(R.id.Email);
        commentLayout = view.findViewById(R.id.Comment);

        btnSendFeedback = view.findViewById(R.id.btnSendBack);

        // Xử lý sự kiện khi nút Send Feedback được nhấn
        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đọc dữ liệu từ các ô nhập và xử lý theo yêu cầu của bạn
                String fullname = edtFullname.getText().toString();
                String phonenumber = edtPhonenumber.getText().toString();
                String email = edtEmail.getText().toString();
                String comment = edtComment.getText().toString();

                // Thực hiện xử lý hoặc gửi dữ liệu đi tùy thuộc vào nhu cầu của bạn

                // Ví dụ: In ra Log dữ liệu
                printData(fullname, phonenumber, email, comment);
            }
        });

        return view;
    }

    // Phương thức để in ra Log dữ liệu (Bạn có thể thay đổi dựa trên yêu cầu cụ thể của bạn)
    private void printData(String fullname, String phonenumber, String email, String comment) {
        // In ra Log để kiểm tra dữ liệu
        // Bạn có thể thực hiện xử lý dữ liệu hoặc gửi dữ liệu đi ở đây
        // Đây chỉ là một ví dụ đơn giản
        String logData = "Fullname: " + fullname +
                "\nPhone number: " + phonenumber +
                "\nEmail: " + email +
                "\nComment: " + comment;

        // In ra Log để kiểm tra dữ liệu
        System.out.println(logData);
    }
}
