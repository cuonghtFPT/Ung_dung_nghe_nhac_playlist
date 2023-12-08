package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DATA";
    private static final int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private int anh_14,
            anh_15,anh_16,anh_17,anh_18,anh_19,anh_20,anh_21,
            anh_22;
    private int nhac_1, nhac_2, nhac_3,nhac_4,nhac_5,nhac_6,nhac_7,nhac_8,
            nhac_9,nhac_10,nhac_11,nhac_12,nhac_13,nhac_14,nhac_15,nhac_16,
            nhac_17,nhac_18,nhac_19,nhac_20,nhac_21,nhac_22,nhac_23,nhac_24,
            nhac_25,nhac_26,nhac_27,nhac_28,nhac_29,nhac_30,nhac_31, nhac_32,
            nhac_33,nhac_34,nhac_35,nhac_36,nhac_37,nhac_38,nhac_39,nhac_40,
            nhac_41,nhac_42,nhac_43,nhac_44,nhac_45,nhac_46,nhac_47,nhac_48;
    @Override
    public void onCreate(SQLiteDatabase db) {
        LuuAnh();
        LuuNhac();
        String createTABLEsig = "CREATE TABLE sig(TENDANGKY TEXT PRIMARY KEY ," +
                "USERNAME TEXT," +
                "PASSWORD TEXT)";
        db.execSQL(createTABLEsig);

        String dbAdmin = "CREATE TABLE ADMIN(maadmin text primary key, hoten text, matkhau text)";
        db.execSQL(dbAdmin);
        db.execSQL("INSERT INTO ADMIN VALUES('admin','admin','abc123')");

        String dbNhanFeedback = "CREATE TABLE NHANFEEDBACK(mafeedback integer primary key," +
                "fullname TEXT," +
                "phone TEXT, " +
                "email TEXT, " +
                "comment TEXT)";
        db.execSQL(dbNhanFeedback);
        String insertFeeback = "INSERT INTO NHANFEEDBACK VALUES (1,'Hoàng Trọng Cường','0987654321','kk8@gmail.com','Bài nhạc rất hay')," +
                "(2,'Nguyễn Viết Mạnh','08765954321','mm7@gmail.com','Cần thêm nhiều bài hát hơn')";
        db.execSQL(insertFeeback);

        String createTableAlbum = "CREATE TABLE Album (\n" +
                "            IdAlbum INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            TenAlbum TEXT NOT NULL,\n" +
                "            AnhAlbum INTEGER NOT NULL,\n" +
                "            IdCaSi INTEGER NOT NULL,\n" +
                "            FOREIGN KEY (IdCaSi) REFERENCES CaSi(IdCaSi)\n" +
                "        )";
        db.execSQL(createTableAlbum);
        String insertTableAlbum = "INSERT INTO Album(TenAlbum,AnhAlbum,IdCaSi)" +
                " VALUES('Daoism',"+R.drawable.img_album1+",5)," +
                "('Buddhism',"+R.drawable.img_album2+",5),('Winter trip',"+R.drawable.img_album3+",6)," +
                "('Aurora',"+R.drawable.img_album4+",4),('Jackpot',"+R.drawable.img_album5+",2)," +
                "('Summersong 2020',"+R.drawable.img_album6+",3),('Inspire',"+R.drawable.img_album7+",7)," +
                "('Royalty Free Romantic Music',"+ R.drawable.img_bensound+",1)";
        db.execSQL(insertTableAlbum);

        String createTableCaSi = "CREATE TABLE CaSi (\n" +
                "            IdCaSi INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            TenCaSi TEXT NOT NULL,\n"+
                "            AnhCaSi INTEGER NOT NULL\n" +
                "            )";
        db.execSQL(createTableCaSi);

        String insertTableCaSi = "INSERT INTO CaSi(TenCaSi,AnhCaSi)" +
                " VALUES('Bensound',"+anh_15+"),('TheFatRat'," +anh_16+ ")," +
                "('Elektronomia'," +anh_17+ "),('Electro Light'," +anh_18+ ")," +
                "('Ungern Sternberg'," +anh_14+ "),('lukrembo'," +anh_19+ ")," +
                "('Wavecount'," +anh_22+ "),('Đức Phúc'," +anh_20+ ")," +
                "('Olews'," +anh_21+ ")";
        db.execSQL(insertTableCaSi);

        String createTableTheLoai = "CREATE TABLE TheLoai (\n" +
                "            IdTheLoai INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            TenTheLoai TEXT NOT NULL\n" +
                "        )";
        db.execSQL(createTableTheLoai);
        String insertTableTheLoai = "INSERT INTO TheLoai(TenTheLoai)" +
                " VALUES('Tình Cảm')," +
                "('Thiền'),('Điện Tử')," +
                "('Lofi'),('Nhảy')";
        db.execSQL(insertTableTheLoai);

        String createTableBaiHat = "CREATE TABLE BaiHat (\n" +
                "            IdBaiHat INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            TenBaiHat TEXT NOT NULL,\n" +
                "            AnhBaiHat INTEGER,\n" +
                "            DuongDan INTEGER,\n" +
                "            IdTheLoai INTEGER,\n" +
                "            IdAlbum INTEGER,\n" +
                "            IdCaSi INTEGER,\n" +
                "            LuotNghe INTEGER DEFAULT 0,\n"+
                "            FOREIGN KEY (IdTheLoai) REFERENCES TheLoai(IdTheLoai),\n" +
                "            FOREIGN KEY (IdAlbum) REFERENCES Album(IdAlbum),\n" +
                "            FOREIGN KEY (IdCaSi) REFERENCES CaSi(IdCaSi)\n" +
                "        )";
        db.execSQL(createTableBaiHat);

        String createTableLichSuNghe = "CREATE TABLE LichSuNghe (\n" +
                "            IdLichSu INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            IdBaiHat INTEGER,\n" +
                "            IdCaSi INTEGER,\n" +
                "            ThoiGian TEXT,\n" +
                "            FOREIGN KEY (IdBaiHat) REFERENCES BaiHat(IdBaiHat),\n" +
                "            FOREIGN KEY (IdCaSi) REFERENCES CaSi(IdCaSi)\n" +
                "            )";
        db.execSQL(createTableLichSuNghe);

        String insertTableBaiHat1 = "INSERT INTO BaiHat(TenBaiHat,AnhBaiHat,DuongDan,IdTheLoai,IdAlbum,IdCaSi)" +
                "VALUES('Em đồng ý', " +anh_20+ " , " +nhac_44+ " ,1,0,8),('HƠN CẢ YÊU', " +anh_20+ " , " +nhac_48+ " ,1,0,8)," +
                "('VƯỜN HOA CON CÁ', " +anh_21+ " , " +nhac_46+ " ,1,0,9),('RỒI TA SẼ NGẮM PHÁO HOA CÙNG NHAU', " +anh_21+ " , " +nhac_47+ " ,1,0,9)," +
                "('Một người đánh mất một người', " +anh_21+ " , " +nhac_45+ " ,1,0,9),('Energy', " +anh_17+ " , " + nhac_1 + " ,3,0,3)," +
                "('Breeze', " +anh_17+ " , " + nhac_2 + " ,1,0,3),('Summersong', " +anh_17+ " , " + nhac_3 + " ,3,6,3)," +
                "('The Other Side', " +anh_17+ " , " +nhac_4+ " ,1,6,3),('Limitless', " +anh_17+ " , " +nhac_5+ " ,3,6,3)," +
                "('Free', " +anh_17+ " , " +nhac_6+ " ,3,6,3),('Monkeys', " +anh_16+ " , " +nhac_7+ " ,2,0,2)," +
                "('Windfall', " +anh_16+ " , " +nhac_8+ " ,3,0,2),('Unity', " +anh_16+ " , " + nhac_9 + " ,3,5,2)," +
                "('Monody', " +anh_16+ " , " +nhac_10+ " ,1,5,2),('Xenogenesis', " +anh_16+ " , " +nhac_11+ " ,3,5,2)," +
                "('We Will Meet Again', " +anh_16+ " , " +nhac_12+ " ,3,5,2),('The Edge', " +anh_18+ " , " +nhac_13+ " ,3,4,4)," +
                "('Symbolism', " +anh_18+ " , " +nhac_14+ " ,3,4,4),('Hold On To Me', " +anh_18+ " , " +nhac_15+ " ,3,4,4)," +
                "('Throwback', " +anh_18+ " , " +nhac_16+ " ,3,4,4),('Where It All Began', " +anh_18+ " , " +nhac_17+ " ,3,4,4)," +
                "('The Ways', " +anh_18+ " , " +nhac_18+ " ,3,4,4),('Jade Emperor', " +anh_14+ " , " +nhac_19+ " ,2,1,5)," +
                "('Three Treasures', " +anh_14+ " , " +nhac_20+ " ,2,1,5),('Tianzun', " +anh_14+ " , " +nhac_21+ " ,2,1,5)," +
                "('Wuji', " +anh_14+ " , " +nhac_22+ " ,2,1,5),('Wuliang Tianzun', " +anh_14+ " , " +nhac_23+ " ,2,1,5)," +
                "('Numinous Treasure', " +anh_14+ " , " +nhac_24+ " ,2,1,5),('Great Compassion Mantra', " +anh_14+ " , " +nhac_25+ " ,2,2,5)," +
                "('Dreams', " +anh_15+ " , " +nhac_26+ " ,6,8,1),('Elevate', " +anh_15+ " , " +nhac_27+ " ,6,8,1)," +
                "('Hey', " +anh_15+ " , " +nhac_28+ " ,6,8,1),('Better Days', " +anh_15+ " , " +nhac_29+ " ,6,8,1)," +
                "('Dubstep', " +anh_15+ " , " +nhac_30+ " ,6,8,1),('Memories', " +anh_15+ " , " +nhac_31+ " ,6,8,1)," +
                "('Moose', " +anh_15+ " , " + nhac_32 + " ,6,8,1),('empty', " +anh_19+ " , " +nhac_33+ " ,4,3,6)," +
                "('together', " +anh_19+ " , " + nhac_34 + " ,4,3,6),('mug', " +anh_19+ " , " +nhac_35+ " ,4,3,6)," +
                "('biscuit', " +anh_19+ " , " + nhac_36 + " ,4,3,6),('train covered in white', " +anh_19+ " , " +nhac_37+ " ,4,3,6)," +
                "('Business Background Music', " +anh_22+ " , " + nhac_38 + " ,5,7,7),('Inspire', " +anh_22+ " , " +nhac_39+ " ,5,7,7)," +
                "('Motivational Corporate', " +anh_22+ " , " + nhac_40 + " ,5,7,7),('Uplifting Corporate', " +anh_22+ " , " +nhac_41+ " ,5,7,7)," +
                "('Uplifting And Inspiring Acoustic', " +anh_22+ " , " + nhac_42 + " ,5,7,7),('Uplifting Inspiration Ccorporate', " +anh_22+ " , " +nhac_43+ " ,5,7,7)";
        db.execSQL(insertTableBaiHat1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTABLEsig = "DROP TABLE IF EXISTS sig";
        db.execSQL(dropTABLEsig);
        db.execSQL("DROP TABLE IF EXISTS ADMIN");
        onCreate(db);
    }
    public void LuuNhac(){
        nhac_1 = R.raw.elektronomia_energy_melodic_house_ncs_copyright_free_music;
        nhac_2 = R.raw.elektronomia_breeze;
        nhac_3 = R.raw.elektronomia_summersong_2018_ncs_release;
        nhac_4 = R.raw.elektronomia_the_other_side;
        nhac_5 = R.raw.elektronomia_limitless_progressive_house_ncs_copyright_free_music;
        nhac_6 = R.raw.elektronomia_and_jjd_free_ncs_copyright_free_music;
        nhac_7 = R.raw.thefatrat_monkeys;
        nhac_8 = R.raw.thefatrat_windfall;
        nhac_9 = R.raw.thefatrat_unity;
        nhac_10 = R.raw.thefatrat_monody_feat_laura_brehm;
        nhac_11 = R.raw.thefatrat_xenogenesis;
        nhac_12 = R.raw.thefatrat_and_laura_brehm_we_will_meet_again;
        nhac_13 = R.raw.electro_light_ft_kathryn_maclean_the_edge_ncs_release;
        nhac_14 = R.raw.electro_light_symbolism_trap_ncs_copyright_free_music;
        nhac_15 = R.raw.electro_light_feat_sidekicks_hold_on_to_me_ncs_release;
        nhac_16 = R.raw.electro_light_throwback_ncs_release;
        nhac_17 = R.raw.electro_light_where_it_all_began_feat_danyka_nadeau_ncs_release;
        nhac_18 = R.raw.electro_light_the_ways_feat_aloma_steele_ncs_release;
        nhac_19 = R.raw.jade_emperor;
        nhac_20 = R.raw.numinous_treasure;
        nhac_21 = R.raw.three_tresure;
        nhac_22 = R.raw.tianzun;
        nhac_23 = R.raw.wuji;
        nhac_24 = R.raw.wuliang_tianzun;
        nhac_25 = R.raw.great_compassion_mantra_foguang_shan_buddhist_chant;
        nhac_26 = R.raw.bensound_dreams_chill_royalty_free_music;
        nhac_27 = R.raw.bensound_elevate_motivational_royalty_free_nusic;
        nhac_28 = R.raw.bensound_hey_joyful_royalty_free_music;
        nhac_29 = R.raw.better_days_bensound;
        nhac_30 = R.raw.dubstep_bensound_royalty_free_music_no_copyright_music;
        nhac_31 = R.raw.memories_bensound_royalty_free_music_no_copyright_music;
        nhac_32 = R.raw.moose_bensound_royalty_free_music_no_copyright_music_bensound_music;
        nhac_33 = R.raw.lukrembo_empty_royalty_free_vlog_music;
        nhac_34 = R.raw.lukrembo_together_royalty_free_vlog_music;
        nhac_35 = R.raw.lukrembo_mug_royalty_free_vlog_music;
        nhac_36 = R.raw.no_copyright_music_lofi_type_beat_biscuit_free_vlog_music_prod_by_lukrembo;
        nhac_37 = R.raw.lukrembo_train_covered_in_white_royalty_free_vlog_music;
        nhac_38 = R.raw.wavecont_business_background_music_no_copyright_corporate_music;
        nhac_39 = R.raw.wavecont_inspire_corporate_background_music_copyright_free;
        nhac_40 = R.raw.wavecont_motivational_corporate_no_copyright_background_music;
        nhac_41 = R.raw.wavecont_uplifting_corporate_no_copyright_background_music;
        nhac_42 = R.raw.wavecont_uplifting_and_inspiring_acoustic_corporate_no_copyright_background_music;
        nhac_43 = R.raw.wavecont_uplifting_inspiration_corporate_copyright_free_background_music;
        nhac_44 = R.raw.emdongy;
        nhac_45 = R.raw.mot_nguoi_danh_mat_mot_nguoi;
        nhac_46 = R.raw.olew_va_ngan_vuon_hoa_con_ca_official_mv;
        nhac_47 = R.raw.roi_ta_se_ngam_phao_hoa_cung_nhau_mv_official_olew;
        nhac_48 = R.raw.hon_ca_yeu_duc_phuc_official_music_video;
    }
    public void LuuAnh(){
        anh_14 = R.drawable.anh_14;
        anh_15 = R.drawable.img_album8;
        anh_16 = R.drawable.img_thefatrat;
        anh_17 = R.drawable.imt_elektronomia;
        anh_18 = R.drawable.img_electrolight;
        anh_19 = R.drawable.img_lukrembo;
        anh_20 = R.drawable.img_ducphuc;
        anh_21 = R.drawable.img_olew;
        anh_22 = R.drawable.img_wavecont;
    }
}

