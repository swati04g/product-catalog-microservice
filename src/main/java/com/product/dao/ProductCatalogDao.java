package main.java.com.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import main.java.com.product.data.persistent.Product;


@Repository
	public class ProductCatalogDao {
	
		@Autowired
		JdbcTemplate jdbcTemplate;

		class productRowMapper implements RowMapper<Product> {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setProductType(rs.getString("product_type"));
				return product;
			}

		}

		public List<Product> findAll() throws DataAccessException {
			return jdbcTemplate.query("select * from product", new productRowMapper());
		}

		public List<Product> findByProductType(String productType) {
			return jdbcTemplate.query("select * from product where product_type=?", new Object[] { productType },
					new productRowMapper());
		}

		public int delete(String productName) {
			return jdbcTemplate.update("delete from product where name=?", new Object[] { productName });
		}

		public int insert(Product product) {
			return jdbcTemplate.update("insert into product (name, product_type) " + "values(?, ?)",
					new Object[] {product.getName(), product.getProductType() });
		}

		

	}
