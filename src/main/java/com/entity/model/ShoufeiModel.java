package com.entity.model;

import com.entity.ShoufeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 收费
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShoufeiModel implements Serializable {
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
     * 收费编号
     */
    private String shoufeiUuidNumber;


    /**
     * 收费名称
     */
    private String shoufeiName;


    /**
     * 收费类型
     */
    private Integer shoufeiTypes;


    /**
     * 收费金额
     */
    private Double shoufeiJine;


    /**
     * 收费详情
     */
    private String shoufeiContent;


    /**
     * 是否缴费
     */
    private Integer jiaofeiTypes;


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
	 * 获取：收费编号
	 */
    public String getShoufeiUuidNumber() {
        return shoufeiUuidNumber;
    }


    /**
	 * 设置：收费编号
	 */
    public void setShoufeiUuidNumber(String shoufeiUuidNumber) {
        this.shoufeiUuidNumber = shoufeiUuidNumber;
    }
    /**
	 * 获取：收费名称
	 */
    public String getShoufeiName() {
        return shoufeiName;
    }


    /**
	 * 设置：收费名称
	 */
    public void setShoufeiName(String shoufeiName) {
        this.shoufeiName = shoufeiName;
    }
    /**
	 * 获取：收费类型
	 */
    public Integer getShoufeiTypes() {
        return shoufeiTypes;
    }


    /**
	 * 设置：收费类型
	 */
    public void setShoufeiTypes(Integer shoufeiTypes) {
        this.shoufeiTypes = shoufeiTypes;
    }
    /**
	 * 获取：收费金额
	 */
    public Double getShoufeiJine() {
        return shoufeiJine;
    }


    /**
	 * 设置：收费金额
	 */
    public void setShoufeiJine(Double shoufeiJine) {
        this.shoufeiJine = shoufeiJine;
    }
    /**
	 * 获取：收费详情
	 */
    public String getShoufeiContent() {
        return shoufeiContent;
    }


    /**
	 * 设置：收费详情
	 */
    public void setShoufeiContent(String shoufeiContent) {
        this.shoufeiContent = shoufeiContent;
    }
    /**
	 * 获取：是否缴费
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }


    /**
	 * 设置：是否缴费
	 */
    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
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
