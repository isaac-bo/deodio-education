package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.LessonSyncMedia")]
    public class LessonSyncMedia
    {
        public var mediaId:int;
        public var lessonId:int;
        public var mediaName:String;
        public var mediaSize:int;
        public var mediaExt:String;
        public var mediaUrl:String;
        public var mediaDir:String;
        public var mediaLength:Number;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;
        public var type:int;



        public function LessonSyncMedia(){}

    }
}