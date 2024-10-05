package tw.com.ispan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.domain.CustomerBean;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerBean, String> {

}
