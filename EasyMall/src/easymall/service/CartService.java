package easymall.service;

import java.util.List;

import easymall.po.Cart;
import easymall.pojo.MyCart;

public interface CartService {
	public int addCart(Cart cart);
	public Cart findCart(Cart cart);
	public int updateCart(Cart cart);
	public List<MyCart> showcart(int user_id);
	public void updateBuyNum(Cart cart);
	public void delCart(Integer cartID);
	public MyCart findByCartID(Integer cartID);
}
