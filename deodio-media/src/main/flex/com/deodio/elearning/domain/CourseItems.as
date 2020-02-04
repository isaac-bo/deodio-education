package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.CourseItems")]
    public class CourseItems
    {
        public var courseItemType:int;
        public var courseItemOrder:int;
        public var isDisabled:*;
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;



        public function CourseItems(){}

    }
}