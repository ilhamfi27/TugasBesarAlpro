package tugasbesaralpro;

public class Transaksi {

    String nama, film;
    int totalBayar, jumlahTiket;
    
    public Transaksi(String nama, String film, int jumlahTiket, int totalBayar) {
        this.nama = nama;
        this.film = film;
        this.jumlahTiket = jumlahTiket;
        this.totalBayar = totalBayar;
    }
    
    void setNama(String nama) {
        this.nama = nama;
    }
    
    String getNama() {
        return nama;
    }
    
    void setFilm(String film) {
        this.film = film;
    }
    
    String getFilm() {
        return film;
    }
    
    void setJumlahTiket(int jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }
    
    int getJumlahTiket() {
        return jumlahTiket;
    }
    
    void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }
    
    int getTotalBayar() {
        return totalBayar;
    }
}
