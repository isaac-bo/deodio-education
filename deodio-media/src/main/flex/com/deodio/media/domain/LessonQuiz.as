package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.LessonQuiz")]
    public class LessonQuiz
    {
        public var pkId:int;
        public var lessonId:int;
        public var quizId:int;
        public var examStartTime:Date;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function LessonQuiz(){}

    }
}