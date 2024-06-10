import java.util.*;

public class Main {
    static List<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static List<MataKuliah> daftarMataKuliah = new ArrayList<>();
    static List<Nilai> daftarNilai = new ArrayList<>();
    static Queue<Mahasiswa> antrianHapus = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inputDataMahasiswa();
        inputDataMataKuliah();

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    inputNilai();
                    break;
                case 2:
                    tampilkanNilai();
                    break;
                case 3:
                    cariNilaiMahasiswa();
                    break;
                case 4:
                    urutDataNilai();
                    break;
                case 5:
                    hapusMahasiswa();
                    break;
                case 6:
                    tampilkanAntrianHapus();
                    break;
                case 7:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 7);
    }

    private static void tampilkanMenu() {
        System.out.println("******************************************");
        System.out.println("SISTEM PENGOLAHAN DATA NILAI MAHASISWA SEMESTER");
        System.out.println("******************************************");
        System.out.println("1. Input Nilai");
        System.out.println("2. Tampil Nilai");
        System.out.println("3. Mencari Nilai Mahasiswa");
        System.out.println("4. Urut Data Nilai");
        System.out.println("5. Hapus Mahasiswa");
        System.out.println("6. Tampilkan Antrian Hapus");
        System.out.println("7. Keluar");
        System.out.print("Pilih = ");
    }

    private static void inputDataMahasiswa() {
        daftarMahasiswa.add(new Mahasiswa("20001", "Thalhah", "021xxx"));
        daftarMahasiswa.add(new Mahasiswa("20002", "Zubair", "021xxx"));
        daftarMahasiswa.add(new Mahasiswa("20003", "Abdur-Rahman", "021xxx"));
        daftarMahasiswa.add(new Mahasiswa("20004", "Sa'ad", "021xxx"));
        daftarMahasiswa.add(new Mahasiswa("20005", "Sa'id", "021xxx"));
        daftarMahasiswa.add(new Mahasiswa("20006", "Ubaidah", "021xxx"));
    }

    private static void inputDataMataKuliah() {
        daftarMataKuliah.add(new MataKuliah("00001", "Internet of Things", 3));
        daftarMataKuliah.add(new MataKuliah("00002", "Algoritma dan Struktur Data", 2));
        daftarMataKuliah.add(new MataKuliah("00003", "Algoritma dan Pemrograman", 2));
        daftarMataKuliah.add(new MataKuliah("00004", "Praktikum Algoritma dan Struktur Data", 3));
        daftarMataKuliah.add(new MataKuliah("00005", "Praktikum Algoritma dan Pemrograman", 3));
    }

    private static void inputNilai() {
        System.out.println("Masukkan data");
        System.out.print("Kode MK: ");
        String kodeMk = scanner.nextLine();
        MataKuliah mk = null;
        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.kode.equals(kodeMk)) {
                mk = mataKuliah;
                break;
            }
        }
        if (mk == null) {
            System.out.println("Mata Kuliah tidak ditemukan.");
            return;
        }

        System.out.print("NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        Mahasiswa mhs = null;
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.nim.equals(nim)) {
                mhs = mahasiswa;
                break;
            }
        }
        if (mhs == null) {
            System.out.println("Mahasiswa tidak ditemukan.");
            return;
        }

        System.out.print("Nilai: ");
        double nilai = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        daftarNilai.add(new Nilai(mhs, mk, nilai));
    }

    private static void tampilkanNilai() {
        System.out.println("DAFTAR NILAI MAHASISWA");
        System.out.println("******************************************");
        System.out.println("NIM    Nama    Mata Kuliah    SKS    Nilai");
        for (Nilai nilai : daftarNilai) {
            System.out.printf("%s    %s    %s    %d    %.2f\n", nilai.mahasiswa.nim, nilai.mahasiswa.nama,
                    nilai.mataKuliah.nama, nilai.mataKuliah.sks, nilai.nilai);
        }
    }

    private static void cariNilaiMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        boolean found = false;
        int totalSKS = 0;
        for (Nilai nilai : daftarNilai) {
            if (nilai.mahasiswa.nim.equals(nim)) {
                System.out.printf("%s    %s    %s    %d    %.2f\n", nilai.mahasiswa.nim, nilai.mahasiswa.nama,
                        nilai.mataKuliah.nama, nilai.mataKuliah.sks, nilai.nilai);
                found = true;
                totalSKS += nilai.mataKuliah.sks;
            }
        }
        if (found) {
            System.out.printf("Total SKS yang telah diambil: %d\n", totalSKS);
        } else {
            System.out.println("Nilai tidak ditemukan untuk NIM tersebut.");
        }
    }

    private static void urutDataNilai() {
        System.out.print("Urut berdasarkan (1 = Ascending, 2 = Descending): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (pilihan == 1) {
            daftarNilai.sort(Comparator.comparingDouble(n -> n.nilai));
        } else if (pilihan == 2) {
            daftarNilai.sort((n1, n2) -> Double.compare(n2.nilai, n1.nilai));
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        tampilkanNilai();
    }

    private static void hapusMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();
        Mahasiswa mhs = null;
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.nim.equals(nim)) {
                mhs = mahasiswa;
                break;
            }
        }
        if (mhs != null) {
            antrianHapus.add(mhs);
            daftarMahasiswa.remove(mhs);
            daftarNilai.removeIf(nilai -> nilai.mahasiswa.nim.equals(nim));
            System.out.println("Mahasiswa dengan NIM " + nim + " telah dihapus dan ditambahkan ke antrian hapus.");
        } else {
            System.out.println("Mahasiswa tidak ditemukan.");
        }
    }

    private static void tampilkanAntrianHapus() {
        System.out.println("ANTRIAN MAHASISWA YANG DIHAPUS");
        System.out.println("******************************************");
        if (antrianHapus.isEmpty()) {
            System.out.println("Antrian kosong.");
        } else {
            for (Mahasiswa mhs : antrianHapus) {
                System.out.println(mhs);
            }
        }
    }
}
