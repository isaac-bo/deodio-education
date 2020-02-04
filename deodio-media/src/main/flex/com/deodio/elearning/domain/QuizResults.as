package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.QuizResults")]
    public class QuizResults
    {
        public var resultId:String;
        public var userId:String;
        public var quizItemResult:String;
        public var quizItemScore:int;
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;
        public var quizItemId:String;



        public function QuizResults(){}

    }
}