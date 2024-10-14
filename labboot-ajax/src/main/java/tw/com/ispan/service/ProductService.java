package tw.com.ispan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.repository.ProductRepository;
import tw.com.ispan.util.DatetimeConverter;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public boolean existsByName(String name) {
		if(name!=null && name.length()!=0) {
			Long count = productRepository.countByName(name);
			if(count!=null && count.longValue()!=0) {
				return true;
			}
		}
		return false;
	}

	public ProductBean findById(Integer id) {
		if(id!=null) {
			Optional<ProductBean> optional = productRepository.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}
	public boolean exists(Integer id) {
		if(id!=null) {
			return productRepository.existsById(id);
		}
		return false;
	}
	public boolean remove(Integer id) {
		if(id!=null) {
			Optional<ProductBean> optional = productRepository.findById(id);
			if(optional.isPresent()) {
				productRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}
	public ProductBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double price = obj.isNull("price") ? null : obj.getDouble("price");
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");

			if(id!=null) {
				Optional<ProductBean> optional = productRepository.findById(id);
				if(optional.isEmpty()) {
					ProductBean insert = new ProductBean();
					insert.setId(id);
					insert.setName(name);
					insert.setPrice(price);
					insert.setMake(null);
					insert.setMake(DatetimeConverter.parse(make, "yyyy-MM-dd"));
					insert.setExpire(expire);

					return productRepository.save(insert);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ProductBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double price = obj.isNull("price") ? null : obj.getDouble("price");
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");

			if(id!=null) {
				Optional<ProductBean> optional = productRepository.findById(id);
				if(optional.isPresent()) {
					ProductBean update = new ProductBean();
					update.setId(id);
					update.setName(name);
					update.setPrice(price);
					update.setMake(null);
					update.setMake(DatetimeConverter.parse(make, "yyyy-MM-dd"));
					update.setExpire(expire);
					return productRepository.save(update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Long result = productRepository.count(obj);
			if(result!=null) {
				return result.longValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public List<ProductBean> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {
			Optional<ProductBean> optional = productRepository.findById(bean.getId());
			if(optional.isPresent()) {
				result = new ArrayList<ProductBean>();
				result.add(optional.get());
			}
		} else {
			result = productRepository.findAll();
		}
		return result;
	}
	public ProductBean insert(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<ProductBean> optional = productRepository.findById(bean.getId());
			if(optional.isEmpty()) {
				return productRepository.save(bean);
			}
		}
		return null;
	}
	public ProductBean update(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<ProductBean> optional = productRepository.findById(bean.getId());
			if(optional.isPresent()) {
				return productRepository.save(bean);
			}
		}
		return null;
	}
	public boolean delete(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<ProductBean> optional = productRepository.findById(bean.getId());
			if(optional.isPresent()) {
				productRepository.deleteById(bean.getId());
				return true;
			}
		}
		return false;		
	}
}
