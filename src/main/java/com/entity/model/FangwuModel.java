package com.entity.model;

import com.entity.FangwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 房屋
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FangwuModel implements Serializable {
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
     * 房屋编号
     */
    private String fangwuUuidNumber;


    /**
     * 楼栋
     */
    private String fangwuLoudong;


    /**
     * 单元
     */
    private String fangwuDanyuan;


    /**
     * 房号
     */
    private String fangwuFanghao;


    /**
     * 房屋备注
     */
    private String fangwuContent;


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
	 * 获取：房屋编号
	 */
    public String getFangwuUuidNumber() {
        return fangwuUuidNumber;
    }


    /**
	 * 设置：房屋编号
	 */
    public void setFangwuUuidNumber(String fangwuUuidNumber) {
        this.fangwuUuidNumber = fangwuUuidNumber;
    }
    /**
	 * 获取：楼栋
	 */
    public String getFangwuLoudong() {
        return fangwuLoudong;
    }


    /**
	 * 设置：楼栋
	 */
    public void setFangwuLoudong(String fangwuLoudong) {
        this.fangwuLoudong = fangwuLoudong;
    }
    /**
	 * 获取：单元
	 */
    public String getFangwuDanyuan() {
        return fangwuDanyuan;
    }


    /**
	 * 设置：单元
	 */
    public void setFangwuDanyuan(String fangwuDanyuan) {
        this.fangwuDanyuan = fangwuDanyuan;
    }
    /**
	 * 获取：房号
	 */
    public String getFangwuFanghao() {
        return fangwuFanghao;
    }


    /**
	 * 设置：房号
	 */
    public void setFangwuFanghao(String fangwuFanghao) {
        this.fangwuFanghao = fangwuFanghao;
    }
    /**
	 * 获取：房屋备注
	 */
    public String getFangwuContent() {
        return fangwuContent;
    }


    /**
	 * 设置：房屋备注
	 */
    public void setFangwuContent(String fangwuContent) {
        this.fangwuContent = fangwuContent;
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
