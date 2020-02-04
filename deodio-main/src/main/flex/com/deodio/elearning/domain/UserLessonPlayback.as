package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.UserLessonPlayback")]
    public class UserLessonPlayback
    {
        public var playbackId:String;
        public var lessonId:String;
        public var userId:String;
        public var viewStatus:int;
        public var lastEndTime:Number;
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;



        public function UserLessonPlayback(){}

    }
}