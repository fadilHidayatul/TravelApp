package com.mediatama.travelorder.LoginRegister.Model;

public class User {

    /**
     * status : 200
     * message : Success Login
     * data : {"id_pelanggan":"4","username":"fadil","nama":"hidayatul","jenis_kelamin":"Laki-Laki","no_hp":"08123434","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2RiX3RyYXZlbFwvIiwiYXVkIjoiaHR0cDpcL1wvbG9jYWxob3N0XC9kYl90cmF2ZWxcLyIsImlzc3VlZEF0IjoxNjIyMTY4NDU5LCJleHBpcmUiOjE2MjIxNzU2NTksImRhdGEiOnsidXNlcl9pZCI6IjQifX0.5QROlofRyDhsaYDc_bByWV6paAXdnwgITs5Plq-lD0M"}
     */

    private int status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_pelanggan : 4
         * username : fadil
         * nama : hidayatul
         * jenis_kelamin : Laki-Laki
         * no_hp : 08123434
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2RiX3RyYXZlbFwvIiwiYXVkIjoiaHR0cDpcL1wvbG9jYWxob3N0XC9kYl90cmF2ZWxcLyIsImlzc3VlZEF0IjoxNjIyMTY4NDU5LCJleHBpcmUiOjE2MjIxNzU2NTksImRhdGEiOnsidXNlcl9pZCI6IjQifX0.5QROlofRyDhsaYDc_bByWV6paAXdnwgITs5Plq-lD0M
         */

        private String id_pelanggan;
        private String username;
        private String nama;
        private String jenis_kelamin;
        private String no_hp;
        private String token;

        public String getId_pelanggan() {
            return id_pelanggan;
        }

        public void setId_pelanggan(String id_pelanggan) {
            this.id_pelanggan = id_pelanggan;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }

        public String getNo_hp() {
            return no_hp;
        }

        public void setNo_hp(String no_hp) {
            this.no_hp = no_hp;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
