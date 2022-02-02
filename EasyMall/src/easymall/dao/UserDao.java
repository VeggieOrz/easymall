package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.User;

@Repository("userDao")
@Mapper
public interface UserDao {
	
    //�û���¼���ܣ�����User�����
	public User login(User user);
	//����û��Ƿ��Ѿ���ע��
	public User checkUsername(String username);
	//ע���û�
	public int regist(User user);
}
