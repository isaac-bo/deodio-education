package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.LessonSyncPoints")]
    public class LessonSyncPoints
    {
        public var pointId:int;
        public var lessonId:int;
        public var pointSize:int;
        public var pointExt:String;
        public var pointUrl:String;
        public var pointDir:String;
        public var pointStart:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;
        public var pointEnd:String;
        public var slideId:int;



        public function LessonSyncPoints(){}

    }
}