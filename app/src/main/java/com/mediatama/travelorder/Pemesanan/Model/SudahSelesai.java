package com.mediatama.travelorder.Pemesanan.Model;

import java.util.List;

public class SudahSelesai {

    /**
     * success : 0
     * status : 200
     * message : Data ada
     * DATA : [{"id_pemesanan":"23","id_pelanggan":"5","rute_awal":"Solok","rute_tujuan":"Bukittinggi","mobil":"Toyota Yaris","tgl_pergi":"2021-06-04","jml_pesan":"3","tarif":"24000","invoice":""}]
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
         * id_pemesanan : 23
         * id_pelanggan : 5
         * rute_awal : Solok
         * rute_tujuan : Bukittinggi
         * mobil : Toyota Yaris
         * tgl_pergi : 2021-06-04
         * jml_pesan : 3
         * tarif : 24000
         * invoice :
         */

        private String id_pemesanan;
        private String id_pelanggan;
        private String rute_awal;
        private String rute_tujuan;
        private String mobil;
        private String tgl_pergi;
        private String jml_pesan;
        private String tarif;
        private String invoice;

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

        public String getTgl_pergi() {
            return tgl_pergi;
        }

        public void setTgl_pergi(String tgl_pergi) {
            this.tgl_pergi = tgl_pergi;
        }

        public String getJml_pesan() {
            return jml_pesan;
        }

        public void setJml_pesan(String jml_pesan) {
            this.jml_pesan = jml_pesan;
        }

        public String getTarif() {
            return tarif;
        }

        public void setTarif(String tarif) {
            this.tarif = tarif;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }
    }
}
