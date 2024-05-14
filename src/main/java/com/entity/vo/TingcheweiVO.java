package com.entity.vo;

import com.entity.TingcheweiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 停车位
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tingchewei")
public class TingcheweiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 停车位编号
     */

    @TableField(value = "tingchewei_uuid_number")
    private String tingcheweiUuidNumber;


    /**
     * 停车位位置
     */

    @TableField(value = "tingchewei_address")
    private String tingcheweiAddress;


    /**
     * 停车位备注
     */

    @TableField(value = "tingchewei_content")
    private String tingcheweiContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：停车位编号
	 */
    public String getTingcheweiUuidNumber() {
        return tingcheweiUuidNumber;
    }


    /**
	 * 获取：停车位编号
	 */

    public void setTingcheweiUuidNumber(String tingcheweiUuidNumber) {
        this.tingcheweiUuidNumber = tingcheweiUuidNumber;
    }
    /**
	 * 设置：停车位位置
	 */
    public String getTingcheweiAddress() {
        return tingcheweiAddress;
    }


    /**
	 * 获取：停车位位置
	 */

    public void setTingcheweiAddress(String tingcheweiAddress) {
        this.tingcheweiAddress = tingcheweiAddress;
    }
    /**
	 * 设置：停车位备注
	 */
    public String getTingcheweiContent() {
        return tingcheweiContent;
    }


    /**
	 * 获取：停车位备注
	 */

    public void setTingcheweiContent(String tingcheweiContent) {
        this.tingcheweiContent = tingcheweiContent;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
