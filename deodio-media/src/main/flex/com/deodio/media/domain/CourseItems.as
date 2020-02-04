package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.CourseItems")]
    public class CourseItems
    {
        public var courseItemId:int;
        public var courseId:int;
        public var courseItemType:int;
        public var courseItemOrder:int;
        public var isDisabled:*;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function CourseItems(){}

    }
}