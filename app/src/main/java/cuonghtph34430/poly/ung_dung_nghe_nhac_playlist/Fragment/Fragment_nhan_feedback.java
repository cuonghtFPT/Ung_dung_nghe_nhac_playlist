package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter.NhanFeedbackAdapter;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.DAO.NhanFeedbackDAO;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.NhanFeedback;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Fragment_nhan_feedback extends Fragment  {
    NhanFeedbackDAO nhanFeedbackDAO;
    RecyclerView recyclerViewQLTV;
    ArrayList<NhanFeedback> list;
    NhanFeedbackAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhan_feedback, container, false);
        recyclerViewQLTV = view.findViewById(R.id.nhanFeedBack);

        FloatingActionButton floatAdd = view.findViewById(R.id.floataddLoaiSach);
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        // Sử dụng requireContext() thay vì getContext()
        return view;
    }

    private void loadData() {
        nhanFeedbackDAO = new NhanFeedbackDAO(requireContext());
        list = nhanFeedbackDAO.getDSFeedback();

        adapter = new NhanFeedbackAdapter(list, requireContext());
        recyclerViewQLTV.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewQLTV.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_feedback, null);
        builder.setView(view);

        final EditText edtFullname = view.findViewById(R.id.edtFullname); // Thay thế bằng EditText tương ứng
        final EditText edtPhoneNumber = view.findViewById(R.id.edtPhonenumber); // Thay thế bằng EditText tương ứng
        final EditText edtEmail = view.findViewById(R.id.edtEmail); // Thay thế bằng EditText tương ứng
        final EditText edtComment = view.findViewById(R.id.edtComment); // Thay thế bằng EditText tương ứng

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String fullname = edtFullname.getText().toString().trim();
                String phonenumber = edtPhoneNumber.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String comment = edtComment.getText().toString().trim();

                if (!fullname.isEmpty() && !phonenumber.isEmpty() && !email.isEmpty() && !comment.isEmpty()) {
                    themFeedback(fullname,phonenumber,email,comment);
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void themFeedback(String fullname,String phonenumber,String email,String comment) {
        NhanFeedback nhanFeedback = new NhanFeedback(fullname, phonenumber,email,comment);
        boolean kiemtra = nhanFeedbackDAO.themFeedback(nhanFeedback);
        if (kiemtra) {
            Toast.makeText(getContext(), "Thêm Feedback thành công", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Thêm Feedback thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
