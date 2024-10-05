package tw.com.ispan.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.util.DatetimeConverter;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@PersistenceContext
	private EntityManager entityManager;
	public EntityManager getSession() {
		return entityManager;
	}

	@Override
	public List<ProductBean> find(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String name = obj.isNull("name") ? null : obj.getString("name");
		Double startPrice = obj.isNull("startPrice") ? null : obj.getDouble("startPrice");
		Double endPrice = obj.isNull("endPrice") ? null : obj.getDouble("endPrice");
		String startMake = obj.isNull("startMake") ? null : obj.getString("startMake");
		String endMake = obj.isNull("endMake") ? null : obj.getString("endMake");
		Integer startExpire = obj.isNull("startExpire") ? null : obj.getInt("startExpire");
		Integer endExpire = obj.isNull("endExpire") ? null : obj.getInt("endExpire");

		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer max = obj.isNull("max") ? 3 : obj.getInt("max");
		Boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");
		String order = obj.isNull("order") ? "id" : obj.getString("order");
		
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<ProductBean> criteriaQuery = criteriaBuilder.createQuery(ProductBean.class);

//		from product
		Root<ProductBean> table = criteriaQuery.from(ProductBean.class);
		
		List<Predicate> predicates = new ArrayList<>();
//		id = ?
		if(id!=null) {
			Predicate p = criteriaBuilder.equal(table.get("id"), id);
			predicates.add(p);
		}

//		name like ?
		if(name!=null && name.length()!=0) {
			Predicate p = criteriaBuilder.like(table.get("name"), "%"+name+"%");
			predicates.add(p);
		}

//		price >= ?
		if(startPrice!=null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(table.get("price"), startPrice));
		}
//		price < ?
		if(endPrice!=null) {
			predicates.add(criteriaBuilder.lessThan(table.get("price"), endPrice));
		}
		
//		make >= ?
		if(startMake!=null && startMake.length()!=0) {
			java.util.Date temp = DatetimeConverter.parse(startMake, "yyyy-MM-dd");
			criteriaBuilder.greaterThanOrEqualTo(table.get("make"), temp);
		}
//		make < ?
		if(endMake!=null && endMake.length()!=0) {
			java.util.Date temp = DatetimeConverter.parse(endMake, "yyyy-MM-dd");
			criteriaBuilder.lessThan(table.get("make"), temp);
		}

//		expire > ?
		if(startExpire!=null) {
			predicates.add(criteriaBuilder.greaterThan(table.get("expire"), startExpire));
		}
//		expire <= ?
		if(endExpire!=null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(table.get("expire"), endExpire));
		}

//		where ....
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteriaQuery = criteriaQuery.where(array);
		}

//		order by
		if(dir) {
//			order by ? desc
			criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(table.get(order)));
		} else {
//			order by ? asc
			criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(table.get(order)));
		}
		
		TypedQuery<ProductBean> typedQuery = this.getSession().createQuery(criteriaQuery)
				.setFirstResult(start)
				.setMaxResults(max);
		List<ProductBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}
	@Override
	public Long count(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String name = obj.isNull("name") ? null : obj.getString("name");
		Double startPrice = obj.isNull("startPrice") ? null : obj.getDouble("startPrice");
		Double endPrice = obj.isNull("endPrice") ? null : obj.getDouble("endPrice");
		String startMake = obj.isNull("startMake") ? null : obj.getString("startMake");
		String endMake = obj.isNull("endMake") ? null : obj.getString("endMake");
		Integer startExpire = obj.isNull("startExpire") ? null : obj.getInt("startExpire");
		Integer endExpire = obj.isNull("endExpire") ? null : obj.getInt("endExpire");

		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

//		from product
		Root<ProductBean> table = criteriaQuery.from(ProductBean.class);

		List<Predicate> predicates = new ArrayList<>();
//		id = ?
		if(id!=null) {
			Predicate p = criteriaBuilder.equal(table.get("id"), id);
			predicates.add(p);
		}

//		name like ?
		if(name!=null && name.length()!=0) {
			Predicate p = criteriaBuilder.like(table.get("name"), "%"+name+"%");
			predicates.add(p);
		}

//		price >= ?
		if(startPrice!=null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(table.get("price"), startPrice));
		}
//		price < ?
		if(endPrice!=null) {
			predicates.add(criteriaBuilder.lessThan(table.get("price"), endPrice));
		}
		
//		make >= ?
		if(startMake!=null && startMake.length()!=0) {
			java.util.Date temp = DatetimeConverter.parse(startMake, "yyyy-MM-dd");
			criteriaBuilder.greaterThanOrEqualTo(table.get("make"), temp);
		}
//		make < ?
		if(endMake!=null && endMake.length()!=0) {
			java.util.Date temp = DatetimeConverter.parse(endMake, "yyyy-MM-dd");
			criteriaBuilder.lessThan(table.get("make"), temp);
		}

//		expire > ?
		if(startExpire!=null) {
			predicates.add(criteriaBuilder.greaterThan(table.get("expire"), startExpire));
		}
//		expire <= ?
		if(endExpire!=null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(table.get("expire"), endExpire));
		}

//		where ....
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteriaQuery = criteriaQuery.where(array);
		}

//		select count(*)
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));

		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteriaQuery);
		Long result = typedQuery.getSingleResult();
		if(result!=null) {
			return result;
		} else {
			return 0L;
		}
	}
}
