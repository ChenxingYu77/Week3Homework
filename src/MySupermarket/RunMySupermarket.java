package MySupermarket;

public class RunMySupermarket {
    public static void main(String[] args) {
        // 创建一个小超市类
        BigSupermarket mySupermarket = new BigSupermarket("超级大超市","嘉禾望岗237号",500,200,200);

        Merchandise m40 = mySupermarket.getMerchandiseOf(40);
        m40.describe();

        Merchandise m90 = mySupermarket.getMerchandiseOf(90);
        m90.describe();

        Merchandise m150 = mySupermarket.getMerchandiseOf(150);
        m150.describe();

    }
}
