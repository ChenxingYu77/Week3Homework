package MySupermarket.Drinks;

import MySupermarket.Category;
import MySupermarket.Merchandise;

import java.time.LocalDate;
import java.util.Date;

public class Yogurt extends Milk{
    // 增加酸奶商品的属性
    private String flavor; // 增加酸奶味道属性
    private Merchandise gift; // 购买酸奶送赠品麦片
    public Sugar sugar; // 添加的糖成分

    // 酸奶的构造方法
    public Yogurt(Category category, String name, String id, int count, double soldPrice, double purchasePrice, LocalDate produceDate, LocalDate expireDate,
                  String brand, double proteinContent, boolean isNFC, String packaging, String sourceArea, double capacity, String flavor, Merchandise gift) {
        super(category, name, id, count, soldPrice, purchasePrice, produceDate, expireDate, brand, proteinContent, isNFC, packaging, sourceArea, capacity);
        this.flavor = flavor;
        this.gift = gift;
    }

    public void describe(){
        System.out.println("此酸奶商品的属性如下：");
        super.describe();
        System.out.println("此酸奶的味道是："+flavor+"口味， 赠送的礼品是"+gift.getName());
    }

    // 此商品买二赠一
    public double buy(int count){
        System.out.println("Yogurt里的buy(int count)");
        if (this.count < count) {
            System.out.println("商品库存不够。");
            System.out.println("商品剩余库存为" + count);
            return -1;
        }
        System.out.println("商品单价为"+purchasePrice);
        int fullPriceCount = count / 2 + count % 2;
        int halfPriceCount = count - fullPriceCount;
        double totalCost = purchasePrice * fullPriceCount + purchasePrice * halfPriceCount / 2;
        this.count -= count;
        return totalCost;
    }

    public class Sugar{
        private String kind; // 糖的种类
        private double sweetness; // 糖的甜度

        public Sugar(String kind,double sweetness){
            this.kind = kind;
            this.sweetness = sweetness;
        }

        public String getKind() {return kind;}
        public void setKind(String kind) {this.kind = kind;}

        public double getSweetness() {return sweetness;}
        public void setSweetness(double sweetness) {this.sweetness = sweetness;}
    }
}
