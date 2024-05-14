package com.entity.vo;

import com.entity.ShoufeiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 收费
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shoufei")
public class ShoufeiVO implements Serializable {
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
     * 收费编号
     */

    @TableField(value = "shoufei_uuid_number")
    private String shoufeiUuidNumber;


    /**
     * 收费名称
     */

    @TableField(value = "shoufei_name")
    private String shoufeiName;


    /**
     * 收费类型
     */

    @TableField(value = "shoufei_types")
    private Integer shoufeiTypes;


    /**
     * 收费金额
     */

    @TableField(value = "shoufei_jine")
    private Double shoufeiJine;


    /**
     * 收费详情
     */

    @TableField(value = "shoufei_content")
    private String shoufeiContent;


    /**
     * 是否缴费
     */

    @TableField(value = "jiaofei_types")
    private Integer jiaofeiTypes;


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
	 * 设置：收费编号
	 */
    public String getShoufeiUuidNumber() {
        return shoufeiUuidNumber;
    }


    /**
	 * 获取：收费编号
	 */

    public void setShoufeiUuidNumber(String shoufeiUuidNumber) {
        this.shoufeiUuidNumber = shoufeiUuidNumber;
    }
    /**
	 * 设置：收费名称
	 */
    public String getShoufeiName() {
        return shoufeiName;
    }


    /**
	 * 获取：收费名称
	 */

    public void setShoufeiName(String shoufeiName) {
        this.shoufeiName = shoufeiName;
    }
    /**
	 * 设置：收费类型
	 */
    public Integer getShoufeiTypes() {
        return shoufeiTypes;
    }


    /**
	 * 获取：收费类型
	 */

    public void setShoufeiTypes(Integer shoufeiTypes) {
        this.shoufeiTypes = shoufeiTypes;
    }
    /**
	 * 设置：收费金额
	 */
    public Double getShoufeiJine() {
        return shoufeiJine;
    }


    /**
	 * 获取：收费金额
	 */

    public void setShoufeiJine(Double shoufeiJine) {
        this.shoufeiJine = shoufeiJine;
    }
    /**
	 * 设置：收费详情
	 */
    public String getShoufeiContent() {
        return shoufeiContent;
    }


    /**
	 * 获取：收费详情
	 */

    public void setShoufeiContent(String shoufeiContent) {
        this.shoufeiContent = shoufeiContent;
    }
    /**
	 * 设置：是否缴费
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }


    /**
	 * 获取：是否缴费
	 */

    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
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
