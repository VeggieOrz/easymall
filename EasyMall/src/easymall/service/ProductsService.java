package easymall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;

public interface ProductsService {
	//查找商品类别
	public List<Category> allcategorys();
	//查找商品
	public List<Products> prodlist(Map<String,Object> map);
	public Products oneProduct(String pid);
	public List<Products> proclass(Integer proclass);
	public String save(MyProducts myproducts,HttpServletRequest request);
	public List<Products> allProducts();

}
