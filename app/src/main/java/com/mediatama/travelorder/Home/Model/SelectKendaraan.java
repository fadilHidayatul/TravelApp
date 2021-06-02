package com.mediatama.travelorder.Home.Model;

import java.util.List;

public class SelectKendaraan {

    /**
     * success : 0
     * status : 200
     * message : Data Ada
     * DATA : [{"id_kendaraan":"2","nama_kendaraan":"Nissan Juke","kapasitas":"4"}]
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
         * id_kendaraan : 2
         * nama_kendaraan : Nissan Juke
         * kapasitas : 4
         */

        private String id_kendaraan;
        private String nama_kendaraan;
        private String kapasitas;

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

        public String getKapasitas() {
            return kapasitas;
        }

        public void setKapasitas(String kapasitas) {
            this.kapasitas = kapasitas;
        }
    }
}
