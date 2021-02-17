package cn.edu.zju.rushrushrush.roadmonitorbackend.service;

import cn.edu.zju.rushrushrush.roadmonitorbackend.pojo.UserPojo;

import java.util.List;

public interface UserService {
    public UserPojo findUserByRegisterId(UserPojo user);
    public List<UserPojo> loadAllUser();
    public UserPojo registerUser(UserPojo user);
    public UserPojo updateUser(UserPojo user);
    public UserPojo insertLikeNote(UserPojo user, String noteId);
    public UserPojo insertCollectNote(UserPojo user, String noteId);

    public Boolean enableUser(Integer userId);
    public Boolean disableUser(Integer userId);
}