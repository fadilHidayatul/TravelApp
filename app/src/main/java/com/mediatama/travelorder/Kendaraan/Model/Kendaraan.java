package com.mediatama.travelorder.Kendaraan.Model;

import java.util.List;

public class Kendaraan {

    /**
     * success : 1
     * status : 200
     * message : Data ada
     * DATA : [{"id_kendaraan":"3","nama_kendaraan":"Honda Brio","kursi":"4","kursi_tersedia":"4","transmisi":"Manual","tahun":"2018","plat":"BA 2906 PA","deskripsi":"Pilihan trendy dan stylish untuk have fun bareng sama teman atau keluarga.\r\nAll New Honda Brio RS untuk kamu yang suka dengan sensasi berkendara bergaya sporty dan lebih seru.Perjalanan lebih menyenangkan dengan interior two tone color dan ruang kabin yang luas sehingga memberikan kenyamanan ekstra untuk pengemudi dan penumpang.All New Honda Brio dilengkapi dengan fitur keselamatan yang baik sehingga memberikan rasa lebih aman dalam berkendara.","foto":"brio.jpg","thumbnail":"tb_brio.jpg"},{"id_kendaraan":"2","nama_kendaraan":"Nissan Juke","kursi":"4","kursi_tersedia":"4","transmisi":"Manual","tahun":"2017","plat":"BA 4989 PT","deskripsi":"Nissan Juke 2015 ini memiliki mesin zippy dan penanganan yang sporty dan dinamis. Penghematan bahan bakar yang bagus jika menggunakan bensin premium. SUV crossover ini adalah mesin empat silinder 1,6 liter turbocharged yang menghasilkan 188 tenaga kuda dan torsi 177 pon-kaki. Di Nismo RS, mesin ini mengeluarkan 215 tenaga kuda dengan penggerak roda depan dan transmisi manual enam percepatan, atau 211 tenaga kuda dengan penggerak semua roda dan transmisi otomatis (CVT) yang terus-menerus bervariasi.?","foto":"nissan.jpg","thumbnail":"tb_nissan.jpg"},{"id_kendaraan":"1","nama_kendaraan":"Toyota Yaris","kursi":"4","kursi_tersedia":"4","transmisi":"Manual","tahun":"2011","plat":"BA 3180 PG","deskripsi":"Dengan peringkat penghematan bahan bakar yang sangat baik dan, banyak fitur keselamatan standar, Toyota Yaris 2011 adalah pilihan cerdas bagi penyewa yang menginginkan mobil komuter kecil, meskipun beberapa saingan menawarkan lebih banyak penyempurnaan interior dan skor keamanan yang lebih baik. Toyota Yaris nyaman dibawa berpergian jauh atau dekat","foto":"yaris.jpg","thumbnail":"tb_yaris.jpg"}]
     */

    private int success;
    private int status;
    private String message;
    private List<DATABean> DATA;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * id_kendaraan : 3
         * nama_kendaraan : Honda Brio
         * kursi : 4
         * kursi_tersedia : 4
         * transmisi : Manual
         * tahun : 2018
         * plat : BA 2906 PA
         * deskripsi : Pilihan trendy dan stylish untuk have fun bareng sama teman atau keluarga.
         All New Honda Brio RS untuk kamu yang suka dengan sensasi berkendara bergaya sporty dan lebih seru.Perjalanan lebih menyenangkan dengan interior two tone color dan ruang kabin yang luas sehingga memberikan kenyamanan ekstra untuk pengemudi dan penumpang.All New Honda Brio dilengkapi dengan fitur keselamatan yang baik sehingga memberikan rasa lebih aman dalam berkendara.
         * foto : brio.jpg
         * thumbnail : tb_brio.jpg
         */

        private String id_kendaraan;
        private String nama_kendaraan;
        private String kursi;
        private String kursi_tersedia;
        private String transmisi;
        private String tahun;
        private String plat;
        private String deskripsi;
        private String foto;
        private String thumbnail;

        public String getId_kendaraan() {
            return id_kendaraan;
        }

        public void setId_kendaraan(String id_kendaraan) {
            this.id_kendaraan = id_kendaraan;
        }

        public String getNama_kendaraan() {
            return nama_kendaraan;
        }

        public void setNama_kendaraan(String nama_kendaraan) {
            this.nama_kendaraan = nama_kendaraan;
        }

        public String getKursi() {
            return kursi;
        }

        public void setKursi(String kursi) {
            this.kursi = kursi;
        }

        public String getKursi_tersedia() {
            return kursi_tersedia;
        }

        public void setKursi_tersedia(String kursi_tersedia) {
            this.kursi_tersedia = kursi_tersedia;
        }

        public String getTransmisi() {
            return transmisi;
        }

        public void setTransmisi(String transmisi) {
            this.transmisi = transmisi;
        }

        public String getTahun() {
            return tahun;
        }

        public void setTahun(String tahun) {
            this.tahun = tahun;
        }

        public String getPlat() {
            return plat;
        }

        public void setPlat(String plat) {
            this.plat = plat;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
