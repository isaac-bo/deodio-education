package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.TraineeFavorite;
import com.deodio.elearning.persistence.model.TraineeFavoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraineeFavoriteMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int countByExample(TraineeFavoriteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int deleteByExample(TraineeFavoriteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int insert(TraineeFavorite record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int insertSelective(TraineeFavorite record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	List<TraineeFavorite> selectByExample(TraineeFavoriteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	TraineeFavorite selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int updateByExampleSelective(@Param("record") TraineeFavorite record,
			@Param("example") TraineeFavoriteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int updateByExample(@Param("record") TraineeFavorite record, @Param("example") TraineeFavoriteExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int updateByPrimaryKeySelective(TraineeFavorite record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_trainee_favorite
	 * @mbggenerated  Mon Mar 26 17:10:55 CST 2018
	 */
	int updateByPrimaryKey(TraineeFavorite record);
}