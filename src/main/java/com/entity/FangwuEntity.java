package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 房屋
 *
 * @author 
 * @email
 */
@TableName("fangwu")
public class FangwuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangwuEntity() {

	}

	public FangwuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 房屋编号
     */
    @TableField(value = "fangwu_uuid_number")

    private String fangwuUuidNumber;


    /**
     * 楼栋
     */
    @TableField(value = "fangwu_loudong")

    private String fangwuLoudong;


    /**
     * 单元
     */
    @TableField(value = "fangwu_danyuan")

    private String fangwuDanyuan;


    /**
     * 房号
     */
    @TableField(value = "fangwu_fanghao")

    private String fangwuFanghao;


    /**
     * 房屋备注
     */
    @TableField(value = "fangwu_content")

    private String fangwuContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：房屋编号
	 */
    public String getFangwuUuidNumber() {
        return fangwuUuidNumber;
    }
    /**
	 * 获取：房屋编号
	 */

    public void setFangwuUuidNumber(String fangwuUuidNumber) {
        this.fangwuUuidNumber = fangwuUuidNumber;
    }
    /**
	 * 设置：楼栋
	 */
    public String getFangwuLoudong() {
        return fangwuLoudong;
    }
    /**
	 * 获取：楼栋
	 */

    public void setFangwuLoudong(String fangwuLoudong) {
        this.fangwuLoudong = fangwuLoudong;
    }
    /**
	 * 设置：单元
	 */
    public String getFangwuDanyuan() {
        return fangwuDanyuan;
    }
    /**
	 * 获取：单元
	 */

    public void setFangwuDanyuan(String fangwuDanyuan) {
        this.fangwuDanyuan = fangwuDanyuan;
    }
    /**
	 * 设置：房号
	 */
    public String getFangwuFanghao() {
        return fangwuFanghao;
    }
    /**
	 * 获取：房号
	 */

    public void setFangwuFanghao(String fangwuFanghao) {
        this.fangwuFanghao = fangwuFanghao;
    }
    /**
	 * 设置：房屋备注
	 */
    public String getFangwuContent() {
        return fangwuContent;
    }
    /**
	 * 获取：房屋备注
	 */

    public void setFangwuContent(String fangwuContent) {
        this.fangwuContent = fangwuContent;
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

    @Override
    public String toString() {
        return "Fangwu{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", fangwuUuidNumber=" + fangwuUuidNumber +
            ", fangwuLoudong=" + fangwuLoudong +
            ", fangwuDanyuan=" + fangwuDanyuan +
            ", fangwuFanghao=" + fangwuFanghao +
            ", fangwuContent=" + fangwuContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
