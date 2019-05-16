package io.upepo.baharirestapi.payload;

public class ChangePasswordDTO {
    Long userid;
    String changepasswordtoken;
    String oldpassword;
    String newpassword;

    public Long getUserId()
    {
        return this.userid;
    }

    public void setUserId(Long userId)
    {
        this.userid = userId;
    }

    public String getChangePasswordToken()
    {
        return this.changepasswordtoken;
    }

    public void setChangePasswordToken(String token)
    {
        this.changepasswordtoken = token;
    }

    public String getOldPassword()
    {
        return this.oldpassword;
    }

    public void setOldPassword(String oldpassword)
    {
        this.oldpassword = oldpassword;
    }

    public String getNewPassword()
    {
        return this.newpassword;
    }

    public void  setNewPassword(String newpassword)
    {
        this.newpassword = newpassword;
    }




}
