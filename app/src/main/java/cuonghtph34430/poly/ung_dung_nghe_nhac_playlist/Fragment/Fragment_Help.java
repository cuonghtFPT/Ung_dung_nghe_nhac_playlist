package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class Fragment_Help extends Fragment {
    TextView content_1,content_2,content_3,content_4;
    ImageView arrow_1,arrow_2,arrow_3,arrow_4;
    RelativeLayout question_1,question_2,question_3,question_4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__help, container, false);
        initComponents(view);
        question_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(content_1.getVisibility() == View.GONE){
                    content_1.setVisibility(View.VISIBLE);
                    arrow_1.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                }else{
                    content_1.setVisibility(View.GONE);
                    arrow_1.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }
            }
        });
        question_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(content_2.getVisibility() == View.GONE){
                    content_2.setVisibility(View.VISIBLE);
                    arrow_2.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                }else{
                    content_2.setVisibility(View.GONE);
                    arrow_2.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }
            }
        });
        question_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(content_3.getVisibility() == View.GONE){
                    content_3.setVisibility(View.VISIBLE);
                    arrow_3.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                }else{
                    content_3.setVisibility(View.GONE);
                    arrow_3.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }
            }
        });
        question_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(content_4.getVisibility() == View.GONE){
                    content_4.setVisibility(View.VISIBLE);
                    arrow_4.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                }else{
                    content_4.setVisibility(View.GONE);
                    arrow_4.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }
            }
        });

        return view;
    }
    public void initComponents(@NonNull View view){
        question_1 = view.findViewById(R.id.question_1);
        question_2 = view.findViewById(R.id.question_2);
        question_3 = view.findViewById(R.id.question_3);
        question_4 = view.findViewById(R.id.question_4);
        content_1 = view.findViewById(R.id.content_1);
        content_2 = view.findViewById(R.id.content_2);
        content_3 = view.findViewById(R.id.content_3);
        content_4 = view.findViewById(R.id.content_4);
        arrow_1 = view.findViewById(R.id.arrow_1);
        arrow_2 = view.findViewById(R.id.arrow_2);
        arrow_3 = view.findViewById(R.id.arrow_3);
        arrow_4 = view.findViewById(R.id.arrow_4);
    }
}