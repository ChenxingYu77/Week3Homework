package MySupermarket;

public enum Category {
    // >> TODO 创建该超市拥有的商品的种类
    FOOD(1),
    COOK(2),
    SNACK(3),
    CLOTHES(4),
    ELECTRIC(5);

    // 枚举的属性
    private int id;

    // 枚举的构造方法
    Category(int id){this.id = id;}

    public int getId(){return id;}
    private void setId(int id){this.id = id;}

    public String toString(){ return "Category{ id="+id+" }";}

}
