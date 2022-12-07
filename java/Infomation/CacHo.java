package Infomation;

public class CacHo extends Info{

    private int soNha;
    private String Duong;
    private String Phuong;
    private String Quan;
    public CacHo() {}

    @Override
    public String toString() {
        return "CacHo{" +
                "soNha=" + soNha +
                ", Duong='" + Duong + '\'' +
                ", Phuong='" + Phuong + '\'' +
                ", Quan='" + Quan + '\'' +
                ", MaHo='" + MaHo + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }

    public CacHo(String maHo, String tenChuHo, int soNha, String Duong, String Phuong, String Quan) {
        this.MaHo = maHo;
        this.ten = tenChuHo;
        this.soNha = soNha;
        this.Duong = Duong;
        this.Phuong = Phuong;
        this.Quan = Quan;
    }

    public int getSoNha() {
        return soNha;
    }

    public void setSoNha(int soNha) {
        this.soNha = soNha;
    }

    public String getDuong() {
        return Duong;
    }

    public void setDuong(String duong) {
        Duong = duong;
    }

    public String getPhuong() {
        return Phuong;
    }

    public void setPhuong(String phuong) {
        Phuong = phuong;
    }

    public String getQuan() {
        return Quan;
    }

    public void setQuan(String quan) {
        Quan = quan;
    }

}
