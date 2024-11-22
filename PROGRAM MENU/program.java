import java.util.ArrayList;
import java.util.Scanner;

public class Restoran {

    static class Menu {
        String nama;
        int harga;
        String kategori;

        Menu(String nama, int harga, String kategori) {
            this.nama = nama;
            this.harga = harga;
            this.kategori = kategori;
        }
    }

    static ArrayList<Menu> daftarMenu = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Inisialisasi menu awal
        daftarMenu.add(new Menu("Nasi Goreng", 25000, "Makanan"));
        daftarMenu.add(new Menu("Mie Ayam", 20000, "Makanan"));
        daftarMenu.add(new Menu("Sate Ayam", 30000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Geprek", 28000, "Makanan"));
        daftarMenu.add(new Menu("Teh Manis", 5000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 7000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Hitam", 10000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 15000, "Minuman"));

        while (true) {
            System.out.println("\n=== Restoran ===");
            System.out.println("1. Pesan Menu");
            System.out.println("2. Kelola Menu");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Bersihkan buffer

            switch (pilihan) {
                case 1 -> pesanMenu();
                case 2 -> kelolaMenu();
                case 3 -> {
                    System.out.println("Terima kasih!");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void tampilkanMenu() {
        System.out.println("\n=== Daftar Menu ===");
        System.out.println("Kategori: Makanan");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i).kategori.equals("Makanan")) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i).nama + " - Rp " + daftarMenu.get(i).harga);
            }
        }
        System.out.println("\nKategori: Minuman");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i).kategori.equals("Minuman")) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i).nama + " - Rp " + daftarMenu.get(i).harga);
            }
        }
    }

    static void pesanMenu() {
        tampilkanMenu();

        ArrayList<String> pesanan = new ArrayList<>();
        ArrayList<Integer> jumlah = new ArrayList<>();
        int totalHarga = 0;

        while (true) {
            System.out.print("\nMasukkan nama menu (ketik 'selesai' untuk selesai): ");
            String namaMenu = scanner.nextLine();

            if (namaMenu.equalsIgnoreCase("selesai")) break;

            Menu menuDipesan = null;

            for (Menu m : daftarMenu) {
                if (m.nama.equalsIgnoreCase(namaMenu)) {
                    menuDipesan = m;
                    break;
                }
            }

            if (menuDipesan == null) {
                System.out.println("Menu tidak ditemukan, coba lagi.");
                continue;
            }

            System.out.print("Jumlah: ");
            int jumlahPesanan = scanner.nextInt();
            scanner.nextLine(); // Bersihkan buffer

            pesanan.add(menuDipesan.nama);
            jumlah.add(jumlahPesanan);
            totalHarga += menuDipesan.harga * jumlahPesanan;
        }

        double pajak = totalHarga * 0.1;
        int biayaPelayanan = 20000;
        double diskon = totalHarga > 100000 ? totalHarga * 0.1 : 0;
        double totalAkhir = totalHarga + pajak + biayaPelayanan - diskon;

        // Cetak struk
        System.out.println("\n=== Struk Pesanan ===");
        for (int i = 0; i < pesanan.size(); i++) {
            System.out.println(pesanan.get(i) + " x " + jumlah.get(i));
        }
        System.out.println("Subtotal       : Rp " + totalHarga);
        System.out.println("Pajak (10%)    : Rp " + (int) pajak);
        System.out.println("Biaya Pelayanan: Rp " + biayaPelayanan);
        if (diskon > 0) System.out.println("Diskon (10%)   : -Rp " + (int) diskon);
        System.out.println("Total Bayar    : Rp " + (int) totalAkhir);
    }

    static void kelolaMenu() {
        while (true) {
            System.out.println("\n=== Kelola Menu ===");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Bersihkan buffer

            switch (pilihan) {
                case 1 -> tambahMenu();
                case 2 -> ubahMenu();
                case 3 -> hapusMenu();
                case 4 -> { return; }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void tambahMenu() {
        System.out.print("Masukkan nama menu: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga menu: ");
        int harga = scanner.nextInt();
        scanner.nextLine(); // Bersihkan buffer
        System.out.print("Masukkan kategori menu (Makanan/Minuman): ");
        String kategori = scanner.nextLine();

        daftarMenu.add(new Menu(nama, harga, kategori));
        System.out.println("Menu berhasil ditambahkan.");
    }

    static void ubahMenu() {
        tampilkanMenu();
        System.out.print("Masukkan nomor menu yang ingin diubah: ");
        int nomor = scanner.nextInt() - 1;
        scanner.nextLine(); // Bersihkan buffer

        if (nomor >= 0 && nomor < daftarMenu.size()) {
            System.out.print("Masukkan harga baru: ");
            int hargaBaru = scanner.nextInt();
            scanner.nextLine(); // Bersihkan buffer

            daftarMenu.get(nomor).harga = hargaBaru;
            System.out.println("Harga menu berhasil diubah.");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }

    static void hapusMenu() {
        tampilkanMenu();
        System.out.print("Masukkan nomor menu yang ingin dihapus: ");
        int nomor = scanner.nextInt() - 1;
        scanner.nextLine(); // Bersihkan buffer

        if (nomor >= 0 && nomor < daftarMenu.size()) {
            System.out.print("Yakin ingin menghapus menu ini? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine();

            if (konfirmasi.equalsIgnoreCase("Ya")) {
                daftarMenu.remove(nomor);
                System.out.println("Menu berhasil dihapus.");
            } else {
                System.out.println("Menu tidak jadi dihapus.");
            }
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }
}
