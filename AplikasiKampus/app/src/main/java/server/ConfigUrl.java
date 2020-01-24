package server;

public class ConfigUrl {

//    public static String baseURL = "http://192.168.43.246:5000";
    public static String baseURL = "http://192.168.0.108:5000";

    public static String regismahasiswa = baseURL + "/mahasiswa/postmhs";
    public static String loginmahasiswa = baseURL + "/mahasiswa/loginmhs";

    public static String updatemahasiswa = baseURL + "/mahasiswa/updatemhs/";
    public static String updatedosen= baseURL + "/dosen/updatedosen/";

    public static String getmatakuliah = baseURL + "/matakuliah/getallmk";
    public static String deletematkul = baseURL + "/matakuliah/deletemk/";
    public static String getkeuangan = baseURL + "/keuangan/getalluang";

    public static String logindosen = baseURL + "/dosen/logindosen";
}
