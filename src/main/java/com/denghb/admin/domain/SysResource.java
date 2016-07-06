package com.denghb.admin.domain;

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Id;
import com.denghb.dbhelper.annotation.Table;

import java.util.Date;

/**
 * DDL
 * <pre>
 * CREATE TABLE `sys_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `type` int(11) DEFAULT '1' COMMENT '{0:链接,1:菜单}',
  `sort` int(11) DEFAULT '99999999' COMMENT '排序（小的排前面）',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标（菜单才有效）',
  `is_public` int(11) DEFAULT '0' COMMENT '{0:私有,1:公开}',
  `created_by` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_url` (`url`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8
 * <pre>
 * @author DbHelper
 * @generateTime Wed Jul 06 12:46:16 CST 2016
 */
@Table(name="sys_resource",database="admin")
public class SysResource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id@Column(name="id")
	private Integer id;
	
	/** 链接 */
	@Column(name="url")
	private String url;
	
	/** 名称 */
	@Column(name="name")
	private String name;
	
	/** 父ID */
	@Column(name="parent_id")
	private Integer parentId;
	
	/** {0:链接,1:菜单} */
	@Column(name="type")
	private Integer type;
	
	/** 排序（小的排前面） */
	@Column(name="sort")
	private Integer sort;
	
	/** 图标（菜单才有效） */
	@Column(name="icon")
	private String icon;
	
	/** {0:私有,1:公开} */
	@Column(name="is_public")
	private Integer isPublic;
	
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
	

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getParentId(){
		return parentId;
	}

	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public Integer getType(){
		return type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public String getIcon(){
		return icon;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public Integer getIsPublic(){
		return isPublic;
	}

	public void setIsPublic(Integer isPublic){
		this.isPublic = isPublic;
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
		return "SysResource [id=" + id +", url=" + url +", name=" + name +", parentId=" + parentId +", type=" + type +", sort=" + sort +", icon=" + icon +", isPublic=" + isPublic +", createdBy=" + createdBy +", createdTime=" + createdTime +", updatedBy=" + updatedBy +", updatedTime=" + updatedTime +", deleted=" + deleted +", version=" + version +", ]";
	}
}