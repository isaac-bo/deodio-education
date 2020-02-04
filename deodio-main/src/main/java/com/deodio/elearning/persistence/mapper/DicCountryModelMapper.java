package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.DicCountryModel;
import com.deodio.elearning.persistence.model.DicCountryModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DicCountryModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int countByExample(DicCountryModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int deleteByExample(DicCountryModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int deleteByPrimaryKey(Integer countryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int insert(DicCountryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int insertSelective(DicCountryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    List<DicCountryModel> selectByExample(DicCountryModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    DicCountryModel selectByPrimaryKey(Integer countryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int updateByExampleSelective(@Param("record") DicCountryModel record, @Param("example") DicCountryModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int updateByExample(@Param("record") DicCountryModel record, @Param("example") DicCountryModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int updateByPrimaryKeySelective(DicCountryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dic_country
     *
     * @mbggenerated Tue Feb 23 17:58:29 CST 2016
     */
    int updateByPrimaryKey(DicCountryModel record);
}