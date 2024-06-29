package net.cowfish.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PasswordModel {
    private String  newPassword;
    private String  oldPassword;
    private String  name;
    private Date updateDt;
}
