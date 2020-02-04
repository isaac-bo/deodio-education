package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CompanyModel;
import com.deodio.elearning.persistence.model.CompanyModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyModelMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int countByExample(CompanyModelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int deleteByExample(CompanyModelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int insert(CompanyModel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int insertSelective(CompanyModel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	List<CompanyModel> selectByExample(CompanyModelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	CompanyModel selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int updateByExampleSelective(@Param("record") CompanyModel record, @Param("example") CompanyModelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int updateByExample(@Param("record") CompanyModel record, @Param("example") CompanyModelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int updateByPrimaryKeySelective(CompanyModel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_company
	 * @mbggenerated  Fri Jun 29 10:57:40 CST 2018
	 */
	int updateByPrimaryKey(CompanyModel record);
}