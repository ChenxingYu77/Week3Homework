package MySupermarket.Phone;

import MySupermarket.Category;

import java.time.LocalDate;
import java.util.Date;

public class ShellColorChangePhone extends Phone{
    private boolean enableShellColorChange;
    public ShellColorChangePhone(Category category, String name, String id, int count, double soldPrice, double purchasePrice, LocalDate produceDate, LocalDate expireDate, double screenSize, double cpuHZ, int memoryG, int storageG, String brand, String os) {
        super(category, name, id, count, soldPrice, purchasePrice, produceDate, expireDate, screenSize, cpuHZ, memoryG, storageG, brand, os);
        enableShellColorChange = false;
    }

    public void describe(){
        super.describe();
        System.out.println("壳色随着屏幕色变的功能开启状态：" + enableShellColorChange);
    }

    public boolean isEnableShellColorChange(){ return enableShellColorChange;}
    public void setEnableShellColorChange(boolean enableShellColorChange){
        this.enableShellColorChange = enableShellColorChange;
    }

    public double calculateProfit(){
        // TODO 厂家提供10个点的返点
        double profit = super.calculateProfit();
        return profit+profit*0.1;
    }

    public double buy(int count){
        System.out.println("ShellColorChangePhone里的buy(int count)");
        if(count<2){
            System.out.println("买一送一了解一下，不单卖哦");
            return -1;
        }
        return super.buy(count);
    }
}
