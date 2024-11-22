import java.util.Scanner;

public class GajiKaryawan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input golongan dan jam lembur
        System.out.print("Masukkan Golongan (A/B/C): ");
        String golongan = scanner.nextLine().toUpperCase();

        System.out.print("Masukkan Jam Lembur: ");
        int jamLembur = scanner.nextInt();

        // Tentukan gaji pokok
        int gajiPokok = golongan.equals("A") ? 5000000 : golongan.equals("B") ? 6500000 : golongan.equals("C") ? 9500000 : 0;

        // Validasi golongan
        if (gajiPokok == 0) {
            System.out.println("Golongan tidak valid!");
            return;
        }

        // Hitung gaji lembur
        double[] persentaseLembur = {0, 0.30, 0.32, 0.34, 0.36, 0.38};
        double gajiLembur = jamLembur >= 5 ? gajiPokok * persentaseLembur[5] : gajiPokok * persentaseLembur[jamLembur];

        // Hitung total penghasilan
        double totalPenghasilan = gajiPokok + gajiLembur;

        // Output hasil
        System.out.printf("Gaji Pokok       : Rp %,d\n", gajiPokok);
        System.out.printf("Gaji Lembur      : Rp %,d\n", (int) gajiLembur);
        System.out.printf("Total Penghasilan: Rp %,d\n", (int) totalPenghasilan);

        scanner.close();
    }
}
