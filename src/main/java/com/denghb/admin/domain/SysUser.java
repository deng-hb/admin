package com.denghb.admin.domain;

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Id;
import com.denghb.dbhelper.annotation.Table;

import java.util.Date;

/**
 * DDL
 * <pre>
 * CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL COMMENT '账户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `created_by` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 * <pre>
 * @author DbHelper
 * @generateTime Wed Jul 06 12:45:48 CST 2016
 */
@Table(name="sys_user",database="admin")
public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 账户ID */
	@Id@Column(name="id")
	private Long id;
	
	/** 名字 */
	@Column(name="name")
	private String name;
	
	/** 邮箱 */
	@Column(name="email")
	private String email;
	
	/** 生日 */
	@Column(name="birthday")
	private Date birthday;
	
	/** 手机号 */
	@Column(name="mobile")
	private String mobile;
	
	/** 创建人 */
	@Column(name="created_by")
	private Long createdBy;
	
	/** 创建时间 */
	@Column(name="created_time")
	private Date createdTime;
	
	/** 更新人 */
	@Column(name="updated_by")
	private Long updatedBy;
	
	/** 更新时间 */
	@Column(name="updated_time")
	private Date updatedTime;
	
	/** 删除标记 */
	@Column(name="deleted")
	private Integer deleted;
	
	/** 版本 */
	@Column(name="version")
	private Integer version;
	

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public Long getCreatedBy(){
		return createdBy;
	}

	public void setCreatedBy(Long createdBy){
		this.createdBy = createdBy;
	}

	public Date getCreatedTime(){
		return createdTime;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	public Long getUpdatedBy(){
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy){
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime(){
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime){
		this.updatedTime = updatedTime;
	}

	public Integer getDeleted(){
		return deleted;
	}

	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}

	public Integer getVersion(){
		return version;
	}

	public void setVersion(Integer version){
		this.version = version;
	}

	@Override
	public String toString(){
		return "SysUser [id=" + id +", name=" + name +", email=" + email +", birthday=" + birthday +", mobile=" + mobile +", createdBy=" + createdBy +", createdTime=" + createdTime +", updatedBy=" + updatedBy +", updatedTime=" + updatedTime +", deleted=" + deleted +", version=" + version +", ]";
	}
}