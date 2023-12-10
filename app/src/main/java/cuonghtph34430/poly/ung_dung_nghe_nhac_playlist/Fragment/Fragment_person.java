package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.NguoiDung;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Fragment_person extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        ImageView btnSetting = view.findViewById(R.id.btnSetting);
        ImageView btnExit = view.findViewById(R.id.btnExit);
        ImageView btnTrogiup = view.findViewById(R.id.btnTrogiup);
        ImageView btnVersion = view.findViewById(R.id.btnVersion);
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), NguoiDung.class);
            startActivity(intent);
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentB = new Fragment_Cai_Dat();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragmentB);
                transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                transaction.commit();
            }
        });
        btnTrogiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentB = new Fragment_Help();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragmentB);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogVersion();
            }
        });

        return view;
    }
    public void DialogVersion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Phiên Bản Cúa Ứng Dụng");
        builder.setMessage("Phiên bản" );
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}
