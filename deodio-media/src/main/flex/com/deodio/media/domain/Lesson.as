package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.Lesson")]
    public class Lesson
    {
        public var lessonId:int;
        public var courseId:int;
        public var lessonName:String;
        public var lesssonTime:Date;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Lesson(){}

    }
}