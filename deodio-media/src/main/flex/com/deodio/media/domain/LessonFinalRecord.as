package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.LessonFinalRecord")]
    public class LessonFinalRecord
    {
        public var pkId:int;
        public var lessonId:int;
        public var recordName:String;
        public var recordSize:int;
        public var recordExt:String;
        public var recordUrl:String;
        public var recordDir:String;
        public var playTime:Number;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function LessonFinalRecord(){}

    }
}