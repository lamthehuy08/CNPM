package Infomation;

public class KhoanThu extends Info{

    private double SoTienDaThu;
    private double ConPhaiDong;

    public double getSoTienDaThu() {
        return SoTienDaThu;
    }
    public void setSoTienDaThu(double soTienDaThu) {
        SoTienDaThu = soTienDaThu;
    }
    public double getConPhaiDong() {
        return ConPhaiDong;
    }
    public void setConPhaiDong(double conPhaiDong) {
        ConPhaiDong = conPhaiDong;
    }
    public KhoanThu(String maHo, String ten, double soTienDaThu, double conPhaiDong) {
        this.MaHo = maHo;
        this.ten = ten;
        SoTienDaThu = soTienDaThu;
        ConPhaiDong = conPhaiDong;
    }
}
