package cn.edu.zju.rushrushrush.roadmonitorbackend.redis;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisTestUser implements Serializable {

     private static final long serialVersionUID = 4332651162911740406L;
     private String userName;
     private String password;
     private String phoneNum;
 
     public String getUserName() {
         return userName;
     }
 
     public void setUserName(String userName) {
         this.userName = userName;
     }
 
     public String getPassword() {
    	return password;
     }
 
     public void setPassword(String password) {
         this.password = password;
     }
 
    public String getPhoneNum() {
         return phoneNum;
     }
 
     public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
     }
 }