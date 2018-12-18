package neet.com.youjidemo.biz;

import java.util.List;

import neet.com.youjidemo.bean.Dynamic;

public interface IDynamic {
    List<Dynamic> getDynamic();
    List<Dynamic> getDynamicOrderHot();
    List<Dynamic> getDynamicByPartitionId(int partition_id);
    List<Dynamic> getDynamicByUserId(int user_id);
    Dynamic getDynamicById(int dynamic_id);
    void addDynamic(Dynamic dynamic);
    void deleteDynamic(int Dynamic_id);
}
