package MySupermarket;

import MySupermarket.Drinks.Milk;
import MySupermarket.Drinks.Yogurt;
import MySupermarket.Phone.Phone;
import MySupermarket.Phone.ShellColorChangePhone;

import java.time.LocalDate;
import java.util.Date;

public class BigSupermarket {
    public String superMarketName;
    public String address;
    public int parkingCount;
    public double incomingSum;
    public Merchandise[] merchandises;
    public int[] merchandiseSold;
    public double activityDiscount = 1;

    // 构造方法
    public BigSupermarket(String superMarketName,String address,int parkingCount,int merchandiseCount,int count){
        this.superMarketName = superMarketName;
        this.address = address;
        this.parkingCount = parkingCount;

        Merchandise giftForYogurt = new Merchandise(
                Category.FOOD,
                "酸奶赠品-水果麦片",
                "GIFT001",
                200,
                10,
                6

        );
        merchandises = new Merchandise[merchandiseCount];

        for (int i = 0; i < merchandises.length; i++) {
            // 创建手机、手机壳变色手机、牛奶、酸奶、和普通商品，都放在一个商品数组里
            Merchandise m = null;
            if(i>= 150){
                m = new ShellColorChangePhone(
                        Category.ELECTRIC,
                        "变色壳手机" + i,
                        "ID" + i,
                        200,
                        1999,
                        999,
                        LocalDate.of(2020,2,16),
                        LocalDate.of(2022,2,16),
                        4.5,
                        3.5,
                        4,
                        128,
                        "三星",
                        "安卓"
                );
            } else if(i>=100 && i<150){
                m = new Phone(
                        Category.ELECTRIC,
                        "手机" + i,
                        "ID" + i,
                        200,
                        1999,
                        999,
                        LocalDate.of(2020,2,16),
                        LocalDate.of(2022,2,16),
                        4.5,
                        3.5,
                        4,
                        128,
                        "索尼",
                        "Android");
            } else if(i>=70 && i<100){
                m = new Milk(
                        Category.FOOD,
                        "牛奶"+i,
                        "ID"+i,
                        500,
                        18,
                        10,
                        LocalDate.of(2020,5,16),
                        LocalDate.of(2020,5,30),
                        "蒙牛",
                        3.8,
                        true,
                        "盒装",
                        "内蒙古",
                        1000
                );
            }else if(i>30 && i<70){
                m = new Yogurt(
                        Category.FOOD,
                        "酸奶"+i,
                        "ID"+i,
                        500,
                        28,
                        10,
                        LocalDate.of(2020,5,16),
                        LocalDate.of(2020,5,30),
                        "天润",
                        3.8,
                        true,
                        "碗装",
                        "新疆",
                        1000,
                        "原味",
                        giftForYogurt
                );
            } else{
                double purchasePrice = Math.random()*200;
                m = new Merchandise(
                        Category.COOK,
                        "商品" + i,
                        "ID" + i,
                        200,
                        purchasePrice*(1+Math.random()),
                        purchasePrice);
            }
            // 用创建的商品，给商品数组的第i个引用赋值
            merchandises[i] = m;
        }
        merchandiseSold = new int[merchandises.length];
    }


    public BigSupermarket(){}

    /**
     *  简单地访问成员变量
     */
    public String getSuperMarketName(){
        return superMarketName;
    }

    public String getAddress(){
        return address;
    }

    public int getParkingCount(){
        return parkingCount;
    }

    public double getIncomingSum(){
        return incomingSum;
    }

    public void setSuperMarketName(String superMarketName){
        this.superMarketName = superMarketName;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setParkingCount(int parkingCount){
        this.parkingCount = parkingCount;
    }

    public void setIncomingSum(double incomingSum){
        this.incomingSum = incomingSum;
    }

    public void setMerchandises(Merchandise[] merchandises){
        this.merchandises = merchandises;
    }

    public void setMerchandiseSold(int[] merchandiseSold){
        this.merchandiseSold = merchandiseSold;
    }

    /**
     * 根据索引获取商品
     */
    public Merchandise getMerchandiseOf(int merchandiseIndex){
        if (merchandiseIndex<0 || merchandiseIndex>=merchandises.length){
            return null;
        }
        return merchandises[merchandiseIndex];
    }

    /**
     * 超市赚钱
     */
    public void addIncomingSum(double toBeAdded){
        this.incomingSum += toBeAdded;
    }

    /**
     * 超市花钱
     */
    public boolean spendMoney(double toBeSpend){
        if (toBeSpend>incomingSum){
            return false;
        }
        incomingSum -= toBeSpend;
        return true;
    }

    /**
     * 获取超市单价最贵的商品单价
     */
    public double getBiggestPurchase() {
        double maxPurchasePrice = -1;
        for (Merchandise m : merchandises) {
            if (m.getPurchasePrice() > maxPurchasePrice) {
                maxPurchasePrice = m.getPurchasePrice();
            }
        }
        return maxPurchasePrice;
    }

    /**
     * 给定一款商品，找到该商品的位置
     */
    public boolean findMerchandise(Merchandise target){
        int i = 0;
        for (Merchandise m:merchandises){
            boolean match = m.equals(target);
            if(match){
                System.out.println("找到了商品，位置在"+i);
                return true;
            }
            i++;
        }
        return false;
    }
}
