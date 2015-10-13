package model;

public class ModelKiemXu {
    public int image;
    public String app_name;
    public String app_information;
    public String status;
    public int money;

    public ModelKiemXu(int image, String app_name, String app_information, String status, int money) {
        this.image = image;
        this.app_name = app_name;
        this.app_information = app_information;
        this.status = status;
        this.money = money;
    }
}
