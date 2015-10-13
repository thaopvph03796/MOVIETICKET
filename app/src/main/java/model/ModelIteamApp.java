package model;

/**
 * Model Custom Item Application
 */
public class ModelIteamApp {
    private String nameapp;
    private int coin;

    public ModelIteamApp(String nameapp, int coin) {
        this.nameapp = nameapp;
        this.coin = coin;
    }

    public void setNameapp(String nameapp) {
        this.nameapp = nameapp;
    }

    public String getNameapp() {
        return nameapp;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }
}
