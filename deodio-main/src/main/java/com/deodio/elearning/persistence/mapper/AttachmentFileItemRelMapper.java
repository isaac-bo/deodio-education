package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.AttachmentFileItemRel;
import com.deodio.elearning.persistence.model.AttachmentFileItemRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachmentFileItemRelMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int countByExample(AttachmentFileItemRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int deleteByExample(AttachmentFileItemRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int insert(AttachmentFileItemRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int insertSelective(AttachmentFileItemRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	List<AttachmentFileItemRel> selectByExample(AttachmentFileItemRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	AttachmentFileItemRel selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int updateByExampleSelective(@Param("record") AttachmentFileItemRel record,
			@Param("example") AttachmentFileItemRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int updateByExample(@Param("record") AttachmentFileItemRel record,
			@Param("example") AttachmentFileItemRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int updateByPrimaryKeySelective(AttachmentFileItemRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_attachment_file_item_rel
	 * @mbggenerated  Fri Feb 24 16:40:49 CST 2017
	 */
	int updateByPrimaryKey(AttachmentFileItemRel record);
}