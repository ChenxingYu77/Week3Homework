package MySupermarket;

public class Merchandise {
    // TODO 设置商品类的属性
    private Category category;
    private String name;
    private String id;
    public double count;
    public double soldPrice;
    public double purchasePrice;

    // 静态变量 VIP会员的折扣
    public final static double DISCOUNT_FOR_VIP = 0.95;

    // 访问静态变量
    public static double getDiscountForVip(){ return DISCOUNT_FOR_VIP;}

    // TODO 商品构造方法
    public Merchandise(Category category, String name, String id, int count, double soldPrice, double purchasePrice) {
        this.category = category;
        this.name = name;
        this.id = id;
        this.count = count;
        this.soldPrice = soldPrice;
        this.purchasePrice = purchasePrice;
    }



    // 添加一个无参数的构造方法
    public Merchandise(){this(null,"无名","000",0,0,0);}

    public void describe(){
        System.out.println("商品的类别是"+category.name()+", 商品的名字叫做" + name + "，id是" + id + "，商品售价是" + soldPrice
                + "。商品进价是" + purchasePrice + "。商品库存量是" + count + "。销售一个的毛利润是" + calculateProfit());

    }

    // TODO 简单地访问与调用商品的属性
    public String getCategory(){return category.name();}
    public void setCategory(Category category){this.category = category;}
    public String getName(){return name;};
    public void setName(String name){this.name = name;}
    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    public double getCount(){return count;}
    public void setCount(int count){this.count = this.count;}
    public double getSoldPrice(){return soldPrice;}
    public void setSoldPrice(double soldPrice){this.soldPrice = soldPrice;}
    public double getPurchasePrice(){return purchasePrice;}
    public void setPurchasePrice(double purchasePrice){this.purchasePrice = purchasePrice;}


    // 计算单个商品的利润
    public double calculateProfit(){
        double profit = soldPrice - purchasePrice;
        if (profit<=0){
            return 0;
        }
        return profit;
    }

    // TODO 购买商品
    public double buy(int count) {
        System.out.println("Merchandise里的buy(int count)");
        if (this.count < count) {
            System.out.println("购买失败，商品库存不够。 商品库存为"+ this.count);
            return -1;
        }
        this.count -= count;
        double cost = count*soldPrice;
        System.out.println("购买成功，花费为" + cost);
        return cost;
    }

    public double buy(){
        return buy(1);
    }

    // 添加商品库存
    public boolean hasEnoughCountFor(int count){
        return this.count>=count;
    }
    public void addCount(int addCount){
        System.out.println("商品的原库存量是"+this.count);
        this.count += addCount;
        System.out.println("补充库存后商品的现库存量是"+this.count);
    }

    // VIP客户购买商品95折
    public double buyVIP(double totalCost,boolean isVIP) {
        if(isVIP){
            return totalCost*0.95;
        }
        return totalCost;

    }

    // 检查商品库存够不够目标量，不够则添加库存
    public void makeEnoughForOneByOne(int count) {
        boolean hasEnough = this.hasEnoughCountFor(count);
        if (!hasEnough) {
            this.addCount(1);
            makeEnoughForOneByOne(count);
        }
    }

}
