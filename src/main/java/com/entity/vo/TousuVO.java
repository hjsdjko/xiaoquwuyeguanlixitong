package com.entity.vo;

import com.entity.TousuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 投诉
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tousu")
public class TousuVO implements Serializable {
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
     * 投诉编号
     */

    @TableField(value = "tousu_uuid_number")
    private String tousuUuidNumber;


    /**
     * 投诉名称
     */

    @TableField(value = "tousu_name")
    private String tousuName;


    /**
     * 投诉类型
     */

    @TableField(value = "tousu_types")
    private Integer tousuTypes;


    /**
     * 投诉详情
     */

    @TableField(value = "tousu_content")
    private String tousuContent;


    /**
     * 是否处理
     */

    @TableField(value = "chuli_types")
    private Integer chuliTypes;


    /**
     * 处理结果
     */

    @TableField(value = "chuli_content")
    private String chuliContent;


    /**
     * 投诉时间
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
	 * 设置：投诉编号
	 */
    public String getTousuUuidNumber() {
        return tousuUuidNumber;
    }


    /**
	 * 获取：投诉编号
	 */

    public void setTousuUuidNumber(String tousuUuidNumber) {
        this.tousuUuidNumber = tousuUuidNumber;
    }
    /**
	 * 设置：投诉名称
	 */
    public String getTousuName() {
        return tousuName;
    }


    /**
	 * 获取：投诉名称
	 */

    public void setTousuName(String tousuName) {
        this.tousuName = tousuName;
    }
    /**
	 * 设置：投诉类型
	 */
    public Integer getTousuTypes() {
        return tousuTypes;
    }


    /**
	 * 获取：投诉类型
	 */

    public void setTousuTypes(Integer tousuTypes) {
        this.tousuTypes = tousuTypes;
    }
    /**
	 * 设置：投诉详情
	 */
    public String getTousuContent() {
        return tousuContent;
    }


    /**
	 * 获取：投诉详情
	 */

    public void setTousuContent(String tousuContent) {
        this.tousuContent = tousuContent;
    }
    /**
	 * 设置：是否处理
	 */
    public Integer getChuliTypes() {
        return chuliTypes;
    }


    /**
	 * 获取：是否处理
	 */

    public void setChuliTypes(Integer chuliTypes) {
        this.chuliTypes = chuliTypes;
    }
    /**
	 * 设置：处理结果
	 */
    public String getChuliContent() {
        return chuliContent;
    }


    /**
	 * 获取：处理结果
	 */

    public void setChuliContent(String chuliContent) {
        this.chuliContent = chuliContent;
    }
    /**
	 * 设置：投诉时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：投诉时间
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
