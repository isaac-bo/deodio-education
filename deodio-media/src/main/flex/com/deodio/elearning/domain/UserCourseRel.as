package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.UserCourseRel")]
    public class UserCourseRel
    {
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;



        public function UserCourseRel(){}

    }
}