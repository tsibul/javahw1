public class LapTop {
    private String serno;
    private String model;
    private int ram;
    private int hd;
    private String os;
    private String color;
    private int price;

    LapTop (String serno, String model, int ram, int hd, String os, String color, int price){
        this.serno = serno;
        this.model = model;
        this.ram = ram;
        this.hd = hd;
        this.os = os;
        this.color = color;
        this.price = price;
    }

    public int getHd() {
        return hd;
    }

    public int getRam() {
        return ram;
    }

    public String getModel() {
        return model;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public int getNPrice() {
        return -price;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setSerno(String serno) {
        this.serno = serno;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(String ser) {
        return serno.equals(ser);
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    @Override
    public String toString() {
        return  model + " | " +
                serno + " | "  +
                ram +
                "Gb | hd=" + hd +
                "Gb | os=" + os + " | " +
                color + " | " +
                "price=" + price + "RUR";
    }

    public String toStringRam(){
        return  model + " | " + ram + "Gb";
    }
    public String toStringHDD(){
        return  model + " | " + hd + "Gb";
    }
    public String toStringOs(){
        return  model + " | " + os;
    }
    public String toStringColor(){
        return  model + " | " + color;
    }
}

