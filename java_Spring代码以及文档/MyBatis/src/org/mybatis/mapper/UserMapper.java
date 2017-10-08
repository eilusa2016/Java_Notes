package org.mybatis.mapper;

import java.util.List;

import org.mybatis.pojo.User;
import org.mybatis.pojo.UserCustom;
import org.mybatis.pojo.UserQueryVo;



/**
 * Mapper接口 
 * 相当于Dao接口
 * @author Guardians
 *
 */
public interface UserMapper {
		/**
		 * 用户信息综合查询
		 * 方法名和UserMapper.xml中定义的每个statement的id相同
		 * @param userQueryVo 输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
		 * @return 输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
		 * @throws Exception
		 */
		public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
		
		//用户信息综合查询总数
		public int findUserCount(UserQueryVo userQueryVo) throws Exception;
		
		//根据id查询用户信息
		public User findUserById(int id) throws Exception;
		
		//根据id查询用户信息，使用resultMap输出
		public User findUserByIdResultMap(int id) throws Exception;
		
		//根据用户名列查询用户列表
		public List<User> findUserByName(String name)throws Exception;
		/**
		 * 更具用户对象找到用户集合
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public List<User> findUserByUser(User user)throws Exception;
		//插入用户
		public void insertUser(User user)throws Exception;
		
		//删除用户
		public void deleteUser(int id)throws Exception;
}
