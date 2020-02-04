package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.LessonSyncSlides")]
    public class LessonSyncSlides
    {
        public var slideId:int;
        public var lessonId:int;
        public var sildeName:String;
        public var sildeSize:int;
        public var sildeExt:String;
        public var sildeUrl:String;
        public var sildeDir:String;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function LessonSyncSlides(){}

    }
}