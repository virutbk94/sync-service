package sync.tookan;

import jdk.jfr.Description;
import org.junit.Test;

public class TestBlockUnBlockTookan {

    @Test
    @Description("Id không tồn tại => có thông báo lỗi id")
    public void testWrongId(){

    }

    @Test
    @Description("Rider ở trạng thái ACTIVE , agent trên tookan đồng bộ về UNBLOCK")
    public void testUnBlockAgent(){

    }

    @Test
    @Description("Rider ở trạng thái UNACTIVE hoặc PENDING , agent trên tookan đồng bộ về BLOCK")
    public void testBlockAgent(){

    }
}
