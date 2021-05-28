package com.mediatama.travelorder.Pemesanan.Model;

import java.util.List;

public class SudahSelesai {

    /**
     * success : 0
     * status : 200
     * message : Data ada
     * DATA : [{"id_pemesanan":"1","id_pelanggan":"4","rute_awal":"Solok","rute_tujuan":"Bukittinggi","mobil":"Nissan Juke","tgl_pesan":"2021-05-04","tgl_pergi":"2021-05-07","tgl_kembali":"2021-05-17","tarif":"24000"}]
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
         * id_pemesanan : 1
         * id_pelanggan : 4
         * rute_awal : Solok
         * rute_tujuan : Bukittinggi
         * mobil : Nissan Juke
         * tgl_pesan : 2021-05-04
         * tgl_pergi : 2021-05-07
         * tgl_kembali : 2021-05-17
         * tarif : 24000
         */

        private String id_pemesanan;
        private String id_pelanggan;
        private String rute_awal;
        private String rute_tujuan;
        private String mobil;
        private String tgl_pesan;
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

        public String getTgl_pesan() {
            return tgl_pesan;
        }

        public void setTgl_pesan(String tgl_pesan) {
            this.tgl_pesan = tgl_pesan;
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
