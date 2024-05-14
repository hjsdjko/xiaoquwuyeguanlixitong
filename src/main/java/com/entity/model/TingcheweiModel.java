package com.entity.model;

import com.entity.TingcheweiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 停车位
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TingcheweiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 停车位编号
     */
    private String tingcheweiUuidNumber;


    /**
     * 停车位位置
     */
    private String tingcheweiAddress;


    /**
     * 停车位备注
     */
    private String tingcheweiContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：停车位编号
	 */
    public String getTingcheweiUuidNumber() {
        return tingcheweiUuidNumber;
    }


    /**
	 * 设置：停车位编号
	 */
    public void setTingcheweiUuidNumber(String tingcheweiUuidNumber) {
        this.tingcheweiUuidNumber = tingcheweiUuidNumber;
    }
    /**
	 * 获取：停车位位置
	 */
    public String getTingcheweiAddress() {
        return tingcheweiAddress;
    }


    /**
	 * 设置：停车位位置
	 */
    public void setTingcheweiAddress(String tingcheweiAddress) {
        this.tingcheweiAddress = tingcheweiAddress;
    }
    /**
	 * 获取：停车位备注
	 */
    public String getTingcheweiContent() {
        return tingcheweiContent;
    }


    /**
	 * 设置：停车位备注
	 */
    public void setTingcheweiContent(String tingcheweiContent) {
        this.tingcheweiContent = tingcheweiContent;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
