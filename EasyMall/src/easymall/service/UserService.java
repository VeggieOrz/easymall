package easymall.service;

import easymall.po.User;

public interface UserService {
	//����û��Ƿ��Ѿ���¼
	public User login(User user);
	//����û��Ƿ��ѱ�ע��
	public Boolean checkUsername(String username);
	//ע���û�
	public int regist(User user);
}
