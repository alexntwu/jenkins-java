package tw.com.ispan.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.ProductBean;

@SpringBootTest
public class ProductServiceTests {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testExistsByName() {
		boolean count1 = productService.existsByName("");
		System.out.println("exists1="+count1);

		boolean count2 = productService.existsByName(null);
		System.out.println("exists2="+count2);

		boolean count3 = productService.existsByName("Cookie1");
		System.out.println("exists3="+count3);

		boolean count4 = productService.existsByName("Cookie");
		System.out.println("exists4="+count4);
	}

	@Test
	public void testCount() {
		JSONObject obj = new JSONObject()
				.put("name", "a")
				.put("start", 2)
				.put("max", 5)
				.put("dir", true)
				.put("order", "expire");
		long count = productService.count(obj.toString());
		System.out.println("count="+count);
	}

	@Test
	public void testFind() {
		JSONObject obj = new JSONObject()
				.put("name", "a")
				.put("start", 2)
				.put("max", 5)
				.put("dir", true)
				.put("order", "expire");
		List<ProductBean> find = productService.find(obj.toString());
		System.out.println("find="+find);
	}
}
