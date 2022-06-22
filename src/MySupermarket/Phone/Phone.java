package MySupermarket.Phone;

import MySupermarket.Category;
import MySupermarket.ExpireDateMerchandise;
import MySupermarket.Merchandise;

import java.time.LocalDate;
import java.util.Date;

public class Phone extends Merchandise implements ExpireDateMerchandise {
    // 给Phone增加新的属性和方法
    private LocalDate produceDate; // 手机出厂日期
    private LocalDate expireDate;  // 手机没有保质日期，设置此日期为热销期截止日期
    protected double screenSize;
    protected double cpuHZ;
    int memoryG;
    int storageG;
    private String brand;
    private String os;
    // 静态变量，限制热销手机一次最多只能买9台
    private final static int MAX_BUY_ONE_ORDER = 5;

    // 构造方法
    public Phone(Category category, String name, String id, int count, double soldPrice, double purchasePrice,
                 LocalDate produceDate, LocalDate expireDate,double screenSize, double cpuHZ, int memoryG, int storageG, String brand, String os) {
        super(category,name,id,count,soldPrice,purchasePrice);
        init(produceDate,expireDate,screenSize,cpuHZ,memoryG,storageG,brand,os);
    }

    public void init(LocalDate produceDate,LocalDate expireDate,double screenSize, double cpuHZ, int memoryG, int storageG, String brand, String os) {
        this.produceDate = produceDate;
        this.expireDate = expireDate;
        this.screenSize = screenSize;
        this.cpuHZ = cpuHZ;
        this.memoryG = memoryG;
        this.storageG = storageG;
        this.brand = brand;
        this.os = os;
    }

    // >> TODO 重写手机商品的buy方法，限制手机购买次数，且根据手机的实际价值付款
    @Override
    public double buy(int count) {
        System.out.println("Phone里的buy(int count)");
        if (count > MAX_BUY_ONE_ORDER) {
            System.out.println("购买失败，手机一次最多只能买" + MAX_BUY_ONE_ORDER + "个");
            return -1;
        }
        if (this.count < count) {
            System.out.println("购买失败，商品库存不够。 商品库存为"+ this.count);
            return -1;
        }
        this.count -= count;
        double cost = count*actualValueNow(leftDatePercentage());
        System.out.println("购买成功，花费为" + cost);
        return cost;
    }


    // 了解手机的信息，简单的访问和调用手机的信息
    public void describe() {
        System.out.println("此手机商品属性如下：");
        super.describe();
        System.out.println("手机厂商为" + brand + "; 系统为" + os + "; 硬件配置如下：\n" +
                "屏幕：" + screenSize + "寸\n" +
                "cpu主频" + cpuHZ + "GHz\n" +
                "内存" + memoryG + "Gb\n" +
                "存储空间" + screenSize + "Gb\n");
    }

    protected String getNameOfPhone() {
        return this.brand + ":" + this.os + ":" + super.getName();
    }

    public Phone getThisPhone() {
        return this;
    }

    public void setProduceDate(LocalDate produceDate){this.produceDate = produceDate;}

    public void setExpireDate(LocalDate expireDate){ this.expireDate = expireDate;}
    public double getScreenSize() {return screenSize;}
    public void setScreenSize(double screenSize) {this.screenSize = screenSize;}

    public double getCpuHZ() {return cpuHZ;}
    public void setCpuHZ(double cpuHZ) {this.cpuHZ = cpuHZ;}

    public int getMemoryG() {return memoryG;}
    public void setMemoryG(int memoryG) {this.memoryG = memoryG;}

    public int getStorageG() {return storageG;}
    public void setStorageG(int storageG) {this.storageG = storageG;}

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}

    public String getOs() {return os;}
    public void setOs(String os) {this.os = os;}

    @Override
    // 计算手机距离过热销期是否还够多少天
    public boolean notExpireIndays(long days) {
        return daysBeforeExpire()>days;
    }

    @Override
    public LocalDate getProduceDate() {
        return produceDate;
    }

    @Override
    public LocalDate getExpireDate() {return expireDate;}

    @Override
    public double leftDatePercentage() {
        long wholeDays = daysBeforeExpire() + daysAfterProduce();
        long beforeExpire = daysBeforeExpire();
        double percentage = beforeExpire/wholeDays;
        return percentage;
    }

    @Override
    public double actualValueNow(double leftDatePercentage) {
        double percentage = leftDatePercentage();
        double actualPrice;
        if(percentage>0.75){
            actualPrice = this.soldPrice;
        } else if(percentage>=0.5 && percentage<0.75){
                actualPrice = this.soldPrice*0.8;
            } else if(percentage>=0.2){
            actualPrice = this.soldPrice*0.7;
        } else{
           actualPrice = this.soldPrice*0.6;
        }
        return actualPrice;
    }

    public double calculateProfit(){
        double profit = actualValueNow(leftDatePercentage()) - purchasePrice;
        if (profit<=0){
            return 0;
        }
        return profit;
    }

    public static class CPU {
        private double speed;
        private String producer;

        public CPU(double speed, String producer) {
            this.speed = speed;
            this.producer = producer;
        }

        public double getSpeed() {
            // >> TODO 静态内部类，代码和这个类本身的访问权限一样，可以访问外部（Phone）的private属性
            // >> TODO 注意，这并不是说它可以访问private变量。
            // >> TODO 静态内部类是静态的，就好像静态方法一样，没有this自引用，可以通过引用访问Phone对象的private属性
            // 仅作演示访问性，不具有实际意义
            Phone phone = null;
            phone.memoryG = 99;
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public String toString() {
            return "CPU{" +
                    "speed=" + speed +
                    ",producer" + producer + '\'' + '}';
        }

        // >> TODO 静态内部类，里面可以有任意合法的类的组成部分，包括静态内部类
//        public static class ABC{}
//    }
    }

    public static class Memory{
        private long capacity;
        private String producer;

        public Memory(long capacity, String producer) {
            this.capacity = capacity;
            this.producer = producer;
        }

        public long getCapacity() {return capacity;}
        public void setCapacity(long capacity) {this.capacity = capacity;}

        public String getProducer() {return producer;}
        public void setProducer(String producer) {this.producer = producer;}
    }
}