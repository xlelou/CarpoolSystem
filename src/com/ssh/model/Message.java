package com.ssh.model;



/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer fromId;
     private Integer toId;
     private Integer status;
     private String createtime;
     private String title;
     private String detail;


    // Constructors

    /** default constructor */
    public Message() {
    }

    
    /** full constructor */
    public Message(Integer fromId, Integer toId, Integer status, String createtime, String title, String detail) {
        this.fromId = fromId;
        this.toId = toId;
        this.status = status;
        this.createtime = createtime;
        this.title = title;
        this.detail = detail;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return this.fromId;
    }
    
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return this.toId;
    }
    
    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
   








}