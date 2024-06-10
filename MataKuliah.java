public class MataKuliah {
    String kode;
    String nama;
    int sks;

    public MataKuliah(String kode, String nama, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
    }

    @Override
    public String toString() {
        return "MataKuliah{" + "kode='" + kode + '\'' + ", nama='" + nama + '\'' + ", sks=" + sks + '}';
    }
}
