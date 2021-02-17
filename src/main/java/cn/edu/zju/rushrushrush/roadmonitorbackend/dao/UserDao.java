package cn.edu.zju.rushrushrush.roadmonitorbackend.dao;

import cn.edu.zju.rushrushrush.roadmonitorbackend.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public List<UserPojo> loadAllUser();

    public UserPojo findUserByRegisterId(@Param("registerId") String registerId);

    public UserPojo findUserById(@Param("id") Integer id);

    public int createUser(UserPojo user);

    public int updateUser(UserPojo user);

    public int insertLikeNote(@Param("userId") Integer userId, @Param("noteId") String noteId);

    public int insertCollectNote(@Param("userId")Integer userId, @Param("noteId") String noteId);

    public int enableUser(@Param("userId") Integer userId);
    public int disableUser(@Param("userId") Integer userId);
}
