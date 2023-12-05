package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.NhanFeedback;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class NhanFeedbackAdapter extends RecyclerView.Adapter<NhanFeedbackAdapter.ViewHolder>{
    private final ArrayList<NhanFeedback> list;
    private final Context context;

    public NhanFeedbackAdapter(ArrayList<NhanFeedback> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NhanFeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhan_feedback,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanFeedbackAdapter.ViewHolder holder, int position) {
        holder.txtFullname.setText("Full name: "+list.get(position).getFullname());
        holder.txtPhone.setText("Phone number: "+list.get(position).getNumber());
        holder.txtEmail.setText("Email: "+list.get(position).getEmail());
        holder.txtComment.setText("Comment: "+list.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtFullname,txtPhone,txtEmail,txtComment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullname= itemView.findViewById(R.id.feed_Fullname);
            txtPhone=itemView.findViewById(R.id.feed_Phonenumber);
            txtEmail=itemView.findViewById(R.id.feed_Email);
            txtComment=itemView.findViewById(R.id.feed_Comment);
        }
    }
}
