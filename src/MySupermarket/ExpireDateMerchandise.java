package MySupermarket;

import java.time.LocalDate;
import java.util.Date;

public interface ExpireDateMerchandise {

    /**
     * 截止到当前，商品的保质期天数是否超过传递的天数
     * @param days 截止到当前，保质期超过这么多天
     * @return 截止到当前，true如果保质期剩余天数比参数长，false如果保质期不到这么多天
     */
    boolean notExpireIndays(long days);

    /**
     * @return 商品生产日期
     */
    LocalDate getProduceDate();

    /**
     * @return 商品保质期到期日
     */
    LocalDate getExpireDate();

    /**
     * @return 截止到当前，剩余保质期还剩下总保质期长度的百分比
     */
    double leftDatePercentage();

    /**
     * 根据剩余的有效期百分比，得出商品现在实际的价值
     * @param leftDatePercentage 剩余有效期百分比
     * @return 剩余的实际价值
     */
    double actualValueNow(double leftDatePercentage);

    /**
     * 计算还有多少天过期
     * @return 返回剩余的保质期天数
     */
    default long daysBeforeExpire(){
        return daysBetween(System.currentTimeMillis(),getExpireDate().getDayOfMonth());
    }

    /**
     * 计算当天距商品生产日期过了多少天
     * @return
     */
    default long daysAfterProduce(){
        return daysBetween(getProduceDate().getDayOfMonth(),System.currentTimeMillis());
    }

    /**
     * 静态方法，计算两个日期的间隔天数
     * @param from 开始日期
     * @param to 结束日期
     * @return 相隔天数
     */
    public static long daysBetween(long from,long to){
        long gap = to - from;
        if(gap<0){
            return -1;
        }
        return gap / (24*3600*1000);
    }

}
