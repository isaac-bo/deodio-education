package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.QuizResults")]
    public class QuizResults
    {
        public var resultId:int;
        public var userId:int;
        public var quizItemResult:String;
        public var quizItemScore:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;
        public var quizItemId:int;



        public function QuizResults(){}

    }
}