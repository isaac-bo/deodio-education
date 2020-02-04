package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.UserLessonPlayback")]
    public class UserLessonPlayback
    {
        public var playbackId:int;
        public var lessonId:int;
        public var userId:int;
        public var viewStatus:int;
        public var lastEndTime:Date;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function UserLessonPlayback(){}

    }
}