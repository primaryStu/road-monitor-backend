package cn.edu.zju.rushrushrush.roadmonitorbackend.service.impl;

import cn.edu.zju.rushrushrush.roadmonitorbackend.common.utils.EncryptUtil;
import cn.edu.zju.rushrushrush.roadmonitorbackend.dao.UserDao;
import cn.edu.zju.rushrushrush.roadmonitorbackend.pojo.UserPojo;
import cn.edu.zju.rushrushrush.roadmonitorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private EncryptUtil encryptUtil;

    @Autowired
    private UserDao userDao;


    @Override
    public UserPojo findUserByRegisterId(UserPojo user) {

        if(user.getPassword() == null || "".equals(user.getPassword())) {
            user = userDao.findUserById(user.getUserId());
            return user;
        }

        String md5Password = encryptUtil.encrypt(user.getPassword());
        UserPojo searchedUser = userDao.findUserByRegisterId(user.getRegisterId());
        if(md5Password.equals(searchedUser.getPassword())){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            searchedUser.setLastLoginTime(timestamp);

//            System.out.println(user.getUserId());
            int rows = userDao.updateUser(searchedUser);

            if(rows <= 0)
                searchedUser = null;

        }
        else
            searchedUser = null;

        return searchedUser;
    }

    @Override
    public List<UserPojo> loadAllUser() {
        return userDao.loadAllUser();
    }

    @Override
    public UserPojo registerUser(UserPojo user) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String md5Password = encryptUtil.encrypt(user.getPassword());
        user.setPassword(md5Password);
        user.setRegisterTime(timestamp);
        user.setLastLoginTime(timestamp);

        int rows = userDao.createUser(user);
        if(rows <= 0) {
            user = null;
        }

        return user;
    }

    @Override
    public UserPojo updateUser(UserPojo user) {
        if(user.getPassword() != null) {
            user.setPassword(encryptUtil.encrypt(user.getPassword()));
        }

        if(user.getAvatar() != null && !("".equals(user.getAvatar()))) {
            user.setAvatar(copy(user.getAvatar(), "images/avatar"));
        }

        int rows = userDao.updateUser(user);

        if (rows <= 0) {
            user = null;
        } else {
            user = userDao.findUserById(user.getUserId());
        }

        return user;
    }

    @Override
    public UserPojo insertLikeNote(UserPojo user, String noteId) {

        Integer userId = user.getUserId();

        int rows = userDao.insertLikeNote(userId, noteId);

        if (rows <= 0) {
            user = null;
        } else {
            user = userDao.findUserById(user.getUserId());
        }
        return null;
    }

    @Override
    public UserPojo insertCollectNote(UserPojo user, String noteId) {

        int rows = userDao.insertCollectNote(user.getUserId(), noteId);

        if (rows <= 0) {
            user = null;
        } else {
            user = userDao.findUserById(user.getUserId());
        }
        return null;
    }

    @Override
    public Boolean enableUser(Integer userId) {
        int rows = userDao.enableUser(userId);

        if (rows <= 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Boolean disableUser(Integer userId) {
        int rows = userDao.disableUser(userId);

        if (rows <= 0) {
            return false;
        } else {
            return true;
        }
    }


    private static String copy(String srcPathStr, String desPathStr) {
        //????????????????????????
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("/")+1); //??????????????????
        System.out.println("?????????:"+newFileName);

        String preFile = srcPathStr.substring(0, srcPathStr.lastIndexOf("/"));
        System.out.println(preFile);

        String webPath = desPathStr + File.separator + newFileName;

        desPathStr = preFile.substring(0, preFile.lastIndexOf("/")) + "/" + webPath; //???????????????
        System.out.println("??????????????????:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//?????????????????????
            FileOutputStream fos = new FileOutputStream(desPathStr); //?????????????????????
            byte datas[] = new byte[1024*8];//??????????????????
            int len = 0;//????????????
            while((len = fis.read(datas))!=-1)//??????????????????
            {
                fos.write(datas,0,len);
            }
            fis.close();//????????????
            fis.close();//????????????
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  webPath;
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println(userService.enableUser(1));
    }

}