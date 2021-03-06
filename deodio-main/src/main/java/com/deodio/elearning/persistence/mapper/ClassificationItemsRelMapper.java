package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.ClassificationItemsRel;
import com.deodio.elearning.persistence.model.ClassificationItemsRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassificationItemsRelMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int countByExample(ClassificationItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int deleteByExample(ClassificationItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int insert(ClassificationItemsRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int insertSelective(ClassificationItemsRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	List<ClassificationItemsRel> selectByExample(
			ClassificationItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	ClassificationItemsRel selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int updateByExampleSelective(
			@Param("record") ClassificationItemsRel record,
			@Param("example") ClassificationItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int updateByExample(@Param("record") ClassificationItemsRel record,
			@Param("example") ClassificationItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int updateByPrimaryKeySelective(ClassificationItemsRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_classification_items_rel
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	int updateByPrimaryKey(ClassificationItemsRel record);
}