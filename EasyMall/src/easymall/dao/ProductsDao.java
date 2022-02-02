package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import easymall.po.Category;
import easymall.po.Products;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
	//������Ʒ���
	public List<Category> allcategorys();
	//������Ʒ
	public List<Products> prodlist(Map<String, Object> map);
	public Products oneProduct(String pid);
	public List<Products> proclass(Integer category);
	public void save(Products products);
	public Object findByImgurl(String imgurl);
	public List<Products> allProducts();
}
