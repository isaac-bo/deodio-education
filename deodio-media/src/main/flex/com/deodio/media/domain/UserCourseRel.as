package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.UserCourseRel")]
    public class UserCourseRel
    {
        public var courseId:int;
        public var userId:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function UserCourseRel(){}

    }
}