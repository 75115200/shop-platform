package item;

import com.shop.Shopping;
import com.shop.base.item.dao.ItemDao;
import com.shop.base.item.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Shopping.class)
public class TestItemDao {
    @Autowired
    private ItemDao itemDao;

    @Test
    public void testSave() {
        Item item = new Item();
        item.setId("test");
        item.setDetail("aaaa");
        itemDao.save(item);
    }
}
