package com.rcc.dev.backend.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ResponseCode {

    public static final String SUCCESS_RESPONSE_CODE = "00";
    public static final String ERROR_RESPONSE_CODE = "99";

    public static class CommonIdn{
        public static final String ERROR = "Error";
        public static final String SUCCESS_GET_DATA_DETAIL = "Berhasil mendapatkan data detail";
        public static final String SUCCESS_SAVE_DATA = "Berhasil menyimpan data";
        public static final String SUCCESS_GET_ALL_DATA = "Berhasil mendapatkan semua data";
        public static final String DATA_NOT_FOUND = "Data tidak ditemukan";
        public static final String SUCCESS_DELETED_DATA = "Data berhasil dihapus";
    }

    public static class CommonEng{
        public static final String ERROR = "Error";
        public static final String SUCCESS_GET_DATA_DETAIL = "Success get data detail";
        public static final String SUCCESS_SAVE_DATA = "Data saved successfully";
        public static final String SUCCESS_GET_ALL_DATA = "Success get all data";
        public static final String DATA_NOT_FOUND = "Data not found";
        public static final String SUCCESS_DELETED_DATA = "Success deleted data";
    }
}
