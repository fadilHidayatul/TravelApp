package com.mediatama.travelorder.Pemesanan.Model;

import java.util.List;

public class BelumBayar {

    /**
     * success : 0
     * status : 200
     * message : Data ada
     * DATA : [{"id_pemesanan":"2","id_pelanggan":"4","rute_awal":"Solok","rute_tujuan":"Padang","mobil":"Toyota Yaris","jumlah_pesan":"1","foto":"yaris.jpg","tgl_pergi":"2021-05-01","tgl_kembali":"2021-05-11","tarif":"21000"},{"id_pemesanan":"3","id_pelanggan":"4","rute_awal":"Solok","rute_tujuan":"Padang","mobil":"Nissan Juke","jumlah_pesan":"1","foto":"nissan.jpg","tgl_pergi":"2021-05-01","tgl_kembali":"2021-05-11","tarif":"21000"},{"id_pemesanan":"17","id_pelanggan":"4","rute_awal":"Solok","rute_tujuan":"Padang","mobil":"Honda Brio","jumlah_pesan":"6","foto":"brio.jpg","tgl_pergi":"2021-05-28","tgl_kembali":"2021-05-31","tarif":"21000"}]
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
         * id_pemesanan : 2
         * id_pelanggan : 4
         * rute_awal : Solok
         * rute_tujuan : Padang
         * mobil : Toyota Yaris
         * jumlah_pesan : 1
         * foto : yaris.jpg
         * tgl_pergi : 2021-05-01
         * tgl_kembali : 2021-05-11
         * tarif : 21000
         */

        private String id_pemesanan;
        private String id_pelanggan;
        private String rute_awal;
        private String rute_tujuan;
        private String mobil;
        private String jumlah_pesan;
        private String foto;
        private String tgl_pergi;
        private String tgl_kembali;
        private String tarif;

        public String getId_pemesanan() {
            return id_pemesanan;
        }

        public void setId_pemesanan(String id_pemesanan) {
            this.id_pemesanan = id_pemesanan;
        }

        public String getId_pelanggan() {
            return id_pelanggan;
        }

        public void setId_pelanggan(String id_pelanggan) {
            this.id_pelanggan = id_pelanggan;
        }

        public String getRute_awal() {
            return rute_awal;
        }

        public void setRute_awal(String rute_awal) {
            this.rute_awal = rute_awal;
        }

        public String getRute_tujuan() {
            return rute_tujuan;
        }

        public void setRute_tujuan(String rute_tujuan) {
            this.rute_tujuan = rute_tujuan;
        }

        public String getMobil() {
            return mobil;
        }

        public void setMobil(String mobil) {
            this.mobil = mobil;
        }

        public String getJumlah_pesan() {
            return jumlah_pesan;
        }

        public void setJumlah_pesan(String jumlah_pesan) {
            this.jumlah_pesan = jumlah_pesan;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public String getTgl_pergi() {
            return tgl_pergi;
        }

        public void setTgl_pergi(String tgl_pergi) {
            this.tgl_pergi = tgl_pergi;
        }

        public String getTgl_kembali() {
            return tgl_kembali;
        }

        public void setTgl_kembali(String tgl_kembali) {
            this.tgl_kembali = tgl_kembali;
        }

        public String getTarif() {
            return tarif;
        }

        public void setTarif(String tarif) {
            this.tarif = tarif;
        }
    }
}
