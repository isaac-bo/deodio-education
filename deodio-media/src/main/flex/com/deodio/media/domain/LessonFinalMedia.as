package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.LessonFinalMedia")]
    public class LessonFinalMedia
    {
        public var pkId:int;
        public var lessonId:int;
        public var mediaName:String;
        public var mediaSize:int;
        public var mediaExt:String;
        public var mediaUrl:String;
        public var mediaDir:String;
        public var playTime:Number;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function LessonFinalMedia(){}

    }
}