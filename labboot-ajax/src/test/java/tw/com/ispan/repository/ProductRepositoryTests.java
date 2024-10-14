package tw.com.ispan.repository;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.ProductBean;

@SpringBootTest
public class ProductRepositoryTests {
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testCountByName() {
		Long count1 = productRepository.countByName("");
		System.out.println("count1="+count1);

		Long count2 = productRepository.countByName(null);
		System.out.println("count2="+count2);

		Long count3 = productRepository.countByName("Cookie1");
		System.out.println("count3="+count3);

		Long count4 = productRepository.countByName("Cookie");
		System.out.println("count4="+count4);
	}

	@Test
	public void testCount() {
		JSONObject obj = new JSONObject()
			.put("name", "a")
			.put("start", 2)
			.put("max", 5)
			.put("dir", true)
			.put("order", "expire");

		Long count = productRepository.count(obj);
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
		List<ProductBean> find = productRepository.find(obj);
		System.out.println("find="+find);
	}
}
