package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_contact extends Fragment {
    private CircleImageView faceImage;
    private CircleImageView emailImage;
    private CircleImageView phoneImage;
    private CircleImageView zaloImage;
    private TextView txtContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho fragment_contact
        View view = inflater.inflate(R.layout.contact, container, false);

        // Ánh xạ các phần tử trong layout
        CircleImageView profileImage = view.findViewById(R.id.profile_image);
        faceImage = view.findViewById(R.id.face_iamge);
        emailImage = view.findViewById(R.id.gmail_iamge);
        phoneImage = view.findViewById(R.id.phone_iamge);
        zaloImage = view.findViewById(R.id.zalo_iamge);
        txtContact = view.findViewById(R.id.txtContact);

        // Thiết lập sự kiện click cho faceImage
        faceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookProfile();
            }
        });

        // Thiết lập sự kiện click cho emailImage
        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        // Thiết lập sự kiện click cho phoneImage
        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        return view;
    }

    private void openFacebookProfile() {
        // Đường dẫn đến trang cá nhân Facebook của bạn
        String facebookUrl = "https://www.facebook.com/profile.php?id=100084175993726";

        // Tạo Intent để mở trình duyệt và chuyển đến trang cá nhân Facebook
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(facebookUrl));

        // Kiểm tra xem có ứng dụng nào có thể xử lý Intent này hay không
        if (facebookIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(facebookIntent);
        }
    }

    private void sendEmail() {
        // Địa chỉ email của bạn
        String email = "trongcuong571@gmail.com";

        // Tạo Intent để mở ứng dụng Email và sẵn sàng gửi email
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));

        // Kiểm tra xem có ứng dụng nào có thể xử lý Intent này hay không
        if (emailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    private void makePhoneCall() {
        // Số điện thoại của bạn
        String phoneNumber = "tel:" + "0348345082";

        // Tạo Intent để thực hiện cuộc gọi điện thoại
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse(phoneNumber));

        // Kiểm tra xem có ứng dụng nào có thể xử lý Intent này hay không
        if (phoneIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(phoneIntent);
        }
    }
}
