package dev.demax.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.demax.finders.ProductFinder;
import util.Filter;

@Component
@Transactional
public class ProductService {
	private final ProductFinder productFinder;
	
	@Autowired
	public ProductService(ProductFinder productFinder) {
		this.productFinder = productFinder;
	}
	
	public void test() {
		System.out.println(productFinder.getCount(new Filter(null, Timestamp.valueOf("1111-11-11 11:11:11"), 
						null, null, null, null, null, null, null)));
	}
}
