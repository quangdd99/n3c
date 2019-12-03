/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author duyqu
 */
package model;
public class UserBean {
    private String name, password;
public String getName(){    
    return name;
}
public void setName(String name){
this.name=name;}
public String getPassword(){
return password;}
public void setPassword(String password){
this.password=password;}

public boolean validate(){
        return password.equals("admin");
}
}