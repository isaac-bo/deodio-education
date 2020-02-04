package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.QuizItems")]
    public class QuizItems
    {
        public var quizItemId:String;
        public var quizId:String;
        public var quizItemAnswer:String;
        public var quizItemQuestion:String;



        public function QuizItems(){}

    }
}