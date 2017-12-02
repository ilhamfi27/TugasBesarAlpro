/**
 * Readme First!
 * copy ini ke netbeans atau ke text Editor lainnya
 * nama file .java nya kondisikan sama nama classnya
 * trus kodingan
 * package tugasbesaralpro;
 * hapus, di netbeans bakal ada peringatan tapi itu buat ganti kodingan tersebut
 * jadi kodingan baru
*/
package tugasbesaralpro; // ntar hapus yang ini

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TugasBesarAlpro {

    String nama, pilihanFilm;
    // array 2 dimensi kalau di representasikan adalah seperti tabel ada kolom dan baris
    // penjelasan : posisiKursi[index baris(kebawah)][index kolom(kesamping)]
    // array 2 dimensi buat posisi kursi penonton
    // diberi alokasi memori 99 baris dan 2 kolom
    String[][] posisiKursi = new String[99][2];
    // array 2 dimensi buat tabel makanan
    // diberi alokasi memori 99 baris dan 2 kolom
    String[][] listCemilan = new String[99][2];
    // variabel untuk jumlah tiket yang dibeli dan total harga
    int jumlahTiket = 0, totalHarga = 0;
    // array list transaksi
    ArrayList<Transaksi> arrTransaksi = new ArrayList<>();
    // instansiasi objek input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // void buat kosongin data
    // karena beberapa data disimpan di variabel global yang nantinya akan
    // dipakai berkali kali, jadi ketika looping selanjutnya maka variabel
    // tersebut harus dikosongkan atau diberi nilai null untuk string dan
    // 0 untuk integer
    void emptyData() {
        nama = "";
        pilihanFilm = "";
        jumlahTiket = 0;
        totalHarga = 0;
        for (int a = 0; a < posisiKursi.length; a++) {
            if (posisiKursi[a][0] != null && posisiKursi[a][1] != null) {
                posisiKursi[a][0] = null;
                posisiKursi[a][1] = null;
            } else {
                break;
            }
        }
        // looping sebanyak index array
        // karena array listCemilan memiliki jumlah index 99, maka akan looping
        // sebanyak 99 kali
        for (int a = 0; a < listCemilan.length; a++) {
            // jika isi index pada listCemilan tidak null atau berisi data
            if (listCemilan[a][0] != null && listCemilan[a][1] != null) {
                // maka index tersebut akan di set null
                listCemilan[a][0] = null;
                listCemilan[a][1] = null;
            } else { // jika isi index pada listCemilan null atau tidak berisi data
                // maka looping berhenti
                break;
            }
        }
    }

    void salahPilihan() {
        System.out.println("Pilihan Salah !");
    }
    
    void konfirmasiKembaliKeMenu(){        
        String pilihan = "";
        System.out.println("Kembali ke menu ? (Y/N)");
        try {
            pilihan = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TugasBesarAlpro.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pilihan.equals("Y") || pilihan.equals("y")) {
            emptyData();
            execute();
        } else if (pilihan.equals("N") || pilihan.equals("n")) {
            prosesPembelianTiket();
        } else {
            salahPilihan();
            konfirmasiKembaliKeMenu();
        }
    }

    // void buat menu utama
    void execute() {
        // inisialisasi variabel pilihan dengan string kosong
        // inisialisasi = pemberian nilai pada variabel
        String pilihan = "";

        System.out.println("Aplikasi Ticketing Bioskop");
        System.out.println("========Menu Awal=========");
        System.out.println("1. Beli Tiket");
        System.out.println("2. Lihat Report Data");
        System.out.println("3. Keluar");
        System.out.print("Pilihan Anda : ");
        try {
            pilihan = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TugasBesarAlpro.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (pilihan) {
            case "1":
                // jika user memilih "1" maka akan melakukan eksekusi method 
                // tiketPelanggan()
                tiketPelanggan();
                break;
            case "2":
                // jika user memilih "2" maka akan melakukan eksekusi method 
                // lihatReportData() kemudian diikuti eksekusi method execute
                // yang merujuk pada method ini
                lihatReportData();
                execute();
                break;
            case "3":
                // jika user memilih "3" maka akan melakukan keluar aplikasi 
                System.exit(1);
                break;
            default:
                // jika user memilih pilihan selain 1, 2, atau 3, maka akan muncul notifikasi
                // mengeksekusi method execute yang merujuk pada method ini
                salahPilihan();
                execute();
                break;
        }
    }

    // void beli tiket
    void tiketPelanggan() {
        // jika user memilih 1 pada menu awal maka akan mengeksekusi method ini
        // looping disini berguna agar aplikasi terus mengulang data proses
        // pembelian tiket
        do {
            prosesPembelianTiket();
        } while (true); // while(true) maksudnya akan looping selamanya
    }

    // tahap tahap pembelian tiket
    // method ini berguna untuk melaksanakan tahap tahap pembelian tiket
    void prosesPembelianTiket() {
        System.out.print("Pembelian tiket atas nama : ");
        try {
            nama = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TugasBesarAlpro.class.getName()).log(Level.SEVERE, null, ex);
        }
        transaksiFilm(); // memanggil method transaksiFilm() untuk proses transaksi film
        transaksiCemilan(); // memanggil method transaksiCemilan() untuk proses pembelian cemilan
        hitungTotal(); // memanggil method hitungTotal() untuk proses hitung total
        simpanData(); // memanggil method simpanData() untuk proses simpan data ke arrayList
        konfirmasiKembaliKeMenu(); // memanggil method konfirmasiKembaliKeMenu() menampilkan pilihan kembali ke menu atau tidak
    }

    // void pembelian tiket
    void transaksiFilm() {
        try {
            String pilihan = "";
            System.out.println("==============FILM==============");
            System.out.println("1. Tenggelamkah Kapal Van Der Wijk ?");
            System.out.println("2. Antara Alpro dan Arsiskom");
            System.out.print("Pilihan : ");
            pilihan = input.readLine(); // input pilihan film 1 atau 2 
            switch (pilihan) {
                case "1":
                    // jika user memilih 1
                    pilihanFilm = "Tenggelamkah Kapal Van Der Wijk ?";
                    break;
                case "2":
                    // jika user memilih 2
                    pilihanFilm = "Antara Alpro dan Arsiskom";
                    break;
                default:
                    // jika user memilih selain 1 dan 2
                    salahPilihan();     // <-- memanggil method salahPilihan() jika salah pilihan
                    transaksiFilm();    // <-- memanggil method diri sendiri agar mengulangi pilihan
                                        // karena pilihan sebelumnya salah maka method ini dipanggil kembali
                                        // untuk memperbaiki pilihan
                    break;
            }

            System.out.print("Jumlah Tiket Yang Akan Dipesan : ");
            jumlahTiket = Integer.parseInt(input.readLine()); // input jumlah tiket
            
            // looping sebanyak jumlah tiket yang diinputkan untuk menentukan tempat duduk
            for (int a = 0; a < jumlahTiket; a++) {
                String baris = "", kolom = "";
                System.out.println("Posisi Kursi " + (a + 1)); // output : Posisi Kursi n
                System.out.print("Kolom (A - J) : ");
                kolom = input.readLine();
                System.out.print("Baris (1 - 5) : ");
                baris = input.readLine();
                // cara isi array itu dengan inisialisasi ke indexnya
                // inisialisasi = pengisian value thdp variabel
                // dibawah ada array yang index [baris][]nya a
                // maksudnya index barisnya mengikuti looping
                // kalo index [][kolom] itu 1 dan 0 maksudnya isi kolom 1 dan 0
                posisiKursi[a][0] = kolom; // memasukkan data pada baris [a] dan kolom [0]
                posisiKursi[a][1] = baris; // memasukkan data pada baris [a] dan kolom [1]
            }
            viewPembelianTiketDanMakanan(); // tampilan yang sudah dipesan seperti makanan dan tiket
        } catch (IOException ex) {
            Logger.getLogger(TugasBesarAlpro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // void pembelian cemilan
    void transaksiCemilan() {
        String pilihan = "", pilihanCemilan = "", konfirmasi = "";
        int harga = 0; // inisialisasi harga = 0
        int a = 0; // variabel a untuk index array listCemilan
        boolean loop = true; // variabel untuk while agar terus loop
        while (loop) {
            System.out.println("=============CEMILAN============");
            System.out.println("=============MAKANAN============");
            System.out.println("a1. Pop Corn");
            System.out.println("a2. Kentang Goreng");
            System.out.println("=============MINUMAN============");
            System.out.println("b1. Air Putih");
            System.out.println("b2. Soft Drink");
            System.out.println("================================");
            System.out.println("x. SELESAI");
            System.out.print("Pilihan : ");
            try {
                pilihan = input.readLine(); // input pilihan cemilan ex: a1 / a2 / b1 / b2
            } catch (IOException ex) {
                Logger.getLogger(TugasBesarAlpro.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (pilihan) {
                case "a1":
                    pilihanCemilan = "Pop Corn"; // set variabel pilihanCemilan
                    harga = 15000; // set variabel harga
                    break;
                case "a2":
                    pilihanCemilan = "Kentang Goreng"; // set variabel pilihanCemilan
                    harga = 10000; // set variabel harga
                    break;
                case "b1":
                    pilihanCemilan = "Air Putih"; // set variabel pilihanCemilan
                    harga = 10000; // set variabel harga
                    break;
                case "b2":
                    pilihanCemilan = "Soft Drink"; // set variabel pilihanCemilan
                    harga = 15000; // set variabel harga
                    break;
                case "x":
                    loop = false;   // <- mengubah value variabel loop menjadi false
                                    // guna menghentikan looping
                    break;
                default:
                    salahPilihan(); // output : pilihan salah!
                    transaksiCemilan(); // memanggil method ini sendiri
                    break;
            }
            if (!pilihan.equals("x")) { // <- !pilihan.equals("x") maksudnya adalah
                                        // jika pilihan TIDAK sama dengan x, tanda seru (!)
                                        // maksudnya not atau lawan dari
                                        // lawan dari true itu false dan sebaliknya
                listCemilan[a][0] = pilihanCemilan;  // memasukkan data pada baris [a] dan kolom [0]
                listCemilan[a][1] = Integer.toString(harga); // memasukkan data pada baris [a] dan kolom [1]
                viewPembelianTiketDanMakanan(); // tampilan yang sudah dipesan seperti makanan dan tiket
            }
            a++;
        }
    }

    // menghitung total
    void hitungTotal() {
        int harga = 0; // inisialisasi nilai awal variabel harga = 0
        harga = harga + (jumlahTiket * 30000);
        // ex harga = harga + 60000
        // harga <- 0 + 60000
        // value dari harga -> 60000
        for (int a = 0; a < listCemilan.length; a++) { // looping banyaknya array listCemilan -> 99 kali
            // jika listCemilan[a][0] dan listCemilan[a][1] berisi atau tidak null 
            if (listCemilan[a][0] != null && listCemilan[a][1] != null) {
                // maka akan mengeksekusi
                harga = harga + Integer.parseInt(listCemilan[a][1]);
                // ex. looping 1
                // harga -> 60000
                // harga <- 60000 + 15000
                // harga -> 75000
                // ex. looping 2
                // harga -> 75000
                // harga <- 75000 + 20000
                // harga -> 95000
            } else {
                // jika listCemilan[a][0] dan listCemilan[a][1] tidak berisi atau null 
                // maka loop akan berhenti
                break;
            }
        }
        totalHarga = harga; // inisialisasi variabel global totalHarga dengan variabel harga yang dipakai untuk menghitung
        System.out.println("Total Harga : " + totalHarga); // menampilkan total harga
    }

    // void simpan data ke array list
    void simpanData() {
        // memasukkan data ke dalam array list
        arrTransaksi.add(new Transaksi(nama, pilihanFilm, jumlahTiket, totalHarga));
    }

    // void lihat report data dari array list
    void lihatReportData() {
        if (arrTransaksi.isEmpty()) { // jika arrTransaksi tidak memiliki isi atau kosong
            // maka akan tampil notifikasi
            System.out.println("Data Film Kosong!");
        } else {
            String tbl = "| %-2d | %-34s | %-58s | %-12s | %-15s |%n"; // string formatting
            System.out.format("+----+------------------------------------+------------------------------------------------------------+--------------+-----------------+%n");
            System.out.format("|%68s%67s|%n", "REPORT", "");
            System.out.format("+----+------------------------------------+------------------------------------------------------------+--------------+-----------------+%n");
            System.out.format("| No |              NAMA                  |                            Judul                           | Jumlah Tiket |      Total      |%n");
            System.out.format("+----+------------------------------------+------------------------------------------------------------+--------------+-----------------+%n");
            for (int a = 0; a < arrTransaksi.size(); a++) {
                // arrTransaksi.get(a) maksudnya adalah mengambil index a dari arrayList arrTransaksi
                // ex. a = 0 maka arrTransaksi.get(a) akan mengambil data dari index ke 0
                // ex. a = 1 maka arrTransaksi.get(a) akan mengambil data dari index ke 1
                // ex. a = 2 maka arrTransaksi.get(a) akan mengambil data dari index ke 2
                String nama = arrTransaksi.get(a).getNama(); // inisialisasi variabel nama dari getter getNama();
                String film = arrTransaksi.get(a).getFilm(); // inisialisasi variabel film dari getter getFilm();
                int jumlahTiket = arrTransaksi.get(a).getJumlahTiket(); // inisialisasi variabel jumlahTiket dari getter getJumlahTiket();
                int totalHarga = arrTransaksi.get(a).getTotalBayar(); // inisialisasi variabel totalHarga dari getter getTotalBayar();
                System.out.format(tbl, (a + 1), nama, film, jumlahTiket, "Rp. " + totalHarga);
            }
            System.out.format("+----+------------------------------------+------------------------------------------------------------+--------------+-----------------+%n");
        }
    }

    // melihat tiket dan data makanan yang sudah dibeli
    void viewPembelianTiketDanMakanan() {
        System.out.println("Tiket Yang Sudah Dipesan");
        System.out.println("Tiket Film : " + pilihanFilm);
        System.out.println("Jumlah Tiket : " + jumlahTiket);
        System.out.println("Posisi Kursi");
        System.out.println("No.\t\tPosisi");
        for (int a = 0; a < posisiKursi.length; a++) {
            if (posisiKursi[a][0] != null && posisiKursi[a][1] != null) {
                System.out.println((a + 1) + "\t\t" + posisiKursi[a][0] + "" + posisiKursi[a][1]);
            } else {
                break;
            }
        }
        System.out.println("List Cemilan");
        System.out.println("No.\t\tNama\tHarga");
        for (int a = 0; a < listCemilan.length; a++) {
            if (listCemilan[a][0] != null && listCemilan[a][1] != null) {
                System.out.println((a + 1) + "\t\t" + listCemilan[a][0] + "\t" + listCemilan[a][1]);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        TugasBesarAlpro tb = new TugasBesarAlpro();
        tb.execute();
    }
}
