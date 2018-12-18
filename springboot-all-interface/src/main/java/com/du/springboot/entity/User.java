package com.du.springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author djg
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String nick;

    private String phone;

    private String address;

    private String email;

    private String username;

    private String passWord;

    private static final long serialVersionUID = 1L;
}