package tw.com.ispan.dto;

import java.util.List;
import tw.com.ispan.domain.ProductBean;

public class ProductSelectDTO {
    private long count;
    private List<ProductBean> list;
    @Override
    public String toString() {
        return "ProductSelectDTO [count=" + count + "]";
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public List<ProductBean> getList() {
        return list;
    }
    public void setList(List<ProductBean> list) {
        this.list = list;
    }
}
