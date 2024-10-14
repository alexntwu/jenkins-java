package tw.com.ispan.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.ispan.domain.ProductBean;

public interface ProductDAO {
	public abstract Long count(JSONObject obj);
	public abstract List<ProductBean> find(JSONObject obj);
}