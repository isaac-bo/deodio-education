package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.Media;
import com.deodio.elearning.persistence.model.MediaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MediaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int countByExample(MediaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int deleteByExample(MediaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int insert(Media record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int insertSelective(Media record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	List<Media> selectByExample(MediaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	Media selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Media record,
			@Param("example") MediaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int updateByExample(@Param("record") Media record,
			@Param("example") MediaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int updateByPrimaryKeySelective(Media record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_media
	 * @mbggenerated  Fri May 13 17:23:44 CST 2016
	 */
	int updateByPrimaryKey(Media record);
}