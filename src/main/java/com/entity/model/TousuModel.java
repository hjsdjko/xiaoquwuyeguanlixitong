package com.entity.model;

import com.entity.TousuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 投诉
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TousuModel implements Serializable {
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
     * 投诉编号
     */
    private String tousuUuidNumber;


    /**
     * 投诉名称
     */
    private String tousuName;


    /**
     * 投诉类型
     */
    private Integer tousuTypes;


    /**
     * 投诉详情
     */
    private String tousuContent;


    /**
     * 是否处理
     */
    private Integer chuliTypes;


    /**
     * 处理结果
     */
    private String chuliContent;


    /**
     * 投诉时间
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
	 * 获取：投诉编号
	 */
    public String getTousuUuidNumber() {
        return tousuUuidNumber;
    }


    /**
	 * 设置：投诉编号
	 */
    public void setTousuUuidNumber(String tousuUuidNumber) {
        this.tousuUuidNumber = tousuUuidNumber;
    }
    /**
	 * 获取：投诉名称
	 */
    public String getTousuName() {
        return tousuName;
    }


    /**
	 * 设置：投诉名称
	 */
    public void setTousuName(String tousuName) {
        this.tousuName = tousuName;
    }
    /**
	 * 获取：投诉类型
	 */
    public Integer getTousuTypes() {
        return tousuTypes;
    }


    /**
	 * 设置：投诉类型
	 */
    public void setTousuTypes(Integer tousuTypes) {
        this.tousuTypes = tousuTypes;
    }
    /**
	 * 获取：投诉详情
	 */
    public String getTousuContent() {
        return tousuContent;
    }


    /**
	 * 设置：投诉详情
	 */
    public void setTousuContent(String tousuContent) {
        this.tousuContent = tousuContent;
    }
    /**
	 * 获取：是否处理
	 */
    public Integer getChuliTypes() {
        return chuliTypes;
    }


    /**
	 * 设置：是否处理
	 */
    public void setChuliTypes(Integer chuliTypes) {
        this.chuliTypes = chuliTypes;
    }
    /**
	 * 获取：处理结果
	 */
    public String getChuliContent() {
        return chuliContent;
    }


    /**
	 * 设置：处理结果
	 */
    public void setChuliContent(String chuliContent) {
        this.chuliContent = chuliContent;
    }
    /**
	 * 获取：投诉时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：投诉时间
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
