package MySupermarket.Drinks;

import MySupermarket.Category;
import MySupermarket.ExpireDateMerchandise;
import MySupermarket.Merchandise;

import java.time.LocalDate;
import java.util.Date;

public class Milk extends Merchandise implements ExpireDateMerchandise {
    // 添加牛奶的属性
    private LocalDate produceDate; // 牛奶的生产日期
    private LocalDate expireDate;  // 牛奶的保质期
    private String brand;
    private double proteinContent; // 蛋白质含量
    private boolean isNFC;  // 是否是生牛乳制作
    private String packaging; // 包装方式
    private String sourceArea; // 原产地
    private double capacity; // 容量

    // 牛奶的构造方法
    public Milk(Category category,String name,String id,int count,double soldPrice,double purchasePrice,
                LocalDate produceDate,LocalDate expireDate,String brand,double proteinContent,boolean isNFC,String packaging,String sourceArea,double capacity){
        super(category,name,id,count,soldPrice,purchasePrice);
        init(produceDate,expireDate,brand,proteinContent,isNFC,packaging,sourceArea,capacity);
    }

    public void init(LocalDate produceDate,LocalDate expireDate,String brand,double proteinContent,boolean isNFC,String packaging,String sourceArea,double capacity){
        this.produceDate = produceDate;
        this.expireDate = expireDate;
        this.brand = brand;
        this.proteinContent = proteinContent;
        this.isNFC = isNFC;
        this.packaging = packaging;
        this.sourceArea = sourceArea;
        this.capacity = capacity;
    }

    public void setProduceDate(LocalDate produceDate) {this.produceDate = produceDate;}

    public void setExpireDate(LocalDate expireDate) {this.expireDate = expireDate;}

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}

    public double getProteinContent() {return proteinContent;}
    public void setProteinContent(double proteinContent) {this.proteinContent = proteinContent;}

    public boolean isNFC() {return isNFC;}
    public void setNFC(boolean NFC) {isNFC = NFC;}

    public String getPackaging() {return packaging;}
    public void setPackaging(String packaging) {this.packaging = packaging;}

    public String getSourceArea() {return sourceArea;}
    public void setSourceArea(String sourceArea) {this.sourceArea = sourceArea;}

    public double getCapacity() {return capacity;}
    public void setCapacity(double capacity) { this.capacity = capacity;}

    public void describe(){
        System.out.println("此牛奶商品属性如下：");
        super.describe();
        System.out.println("品牌为"+brand+", 蛋白质含量为"+proteinContent+"g/100ml，原产地为"+sourceArea+"，容量为"+capacity+"ml\n");
        System.out.println(isNFC?"是生牛乳制作。":"不是生牛乳制作。");
    }
    @Override
    public boolean notExpireIndays(long days) {
        return daysBeforeExpire()>days;
    }

    @Override
    public LocalDate getProduceDate() {return produceDate;}

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
            actualPrice = this.soldPrice*0.5;
        } else{
            actualPrice = this.soldPrice*0.3;
        }
        return actualPrice;
    }

    @Override
    public long daysBeforeExpire() {
        return ExpireDateMerchandise.super.daysBeforeExpire();
    }

    @Override
    public long daysAfterProduce() {
        return ExpireDateMerchandise.super.daysAfterProduce();
    }

    public double calculateProfit(){
        double profit = actualValueNow(leftDatePercentage()) - purchasePrice;
        if (profit<=0){
            return 0;
        }
        return profit;
    }

    // 购买酸奶商品
    public double buy(int count) {
        System.out.println("Phone里的buy(int count)");
        if (this.count < count) {
            System.out.println("购买失败，商品库存不够。 商品库存为"+ this.count);
            return -1;
        }
        this.count -= count;
        double cost = count*actualValueNow(leftDatePercentage());
        System.out.println("购买成功，花费为" + cost);
        return cost;
    }
}
