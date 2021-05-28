package com.mediatama.travelorder.Home.Model;

import java.util.List;

public class Rute {

    /**
     * success : 0
     * status : 200
     * message : Data Ada
     * DATA : [{"id_rute":"1","rute_awal":"Solok","rute_tujuan":"Padang","tarif":"21000"},{"id_rute":"2","rute_awal":"Solok","rute_tujuan":"Bukittinggi","tarif":"24000"},{"id_rute":"3","rute_awal":"Solok","rute_tujuan":"Padang","tarif":"20000"}]
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
         * id_rute : 1
         * rute_awal : Solok
         * rute_tujuan : Padang
         * tarif : 21000
         */

        private String id_rute;
        private String rute_awal;
        private String rute_tujuan;
        private String tarif;

        public String getId_rute() {
            return id_rute;
        }

        public void setId_rute(String id_rute) {
            this.id_rute = id_rute;
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

        public String getTarif() {
            return tarif;
        }

        public void setTarif(String tarif) {
            this.tarif = tarif;
        }
    }
}
