package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.Course")]
    public class Course
    {
        public var courseId:int;
        public var courseName:String;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Course(){}

    }
}