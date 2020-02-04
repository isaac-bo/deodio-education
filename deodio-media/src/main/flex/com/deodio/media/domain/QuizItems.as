package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.QuizItems")]
    public class QuizItems
    {
        public var quizItemId:int;
        public var quizId:int;
        public var quizItemAnswer:String;
        public var quizItemQuestion:String;



        public function QuizItems(){}

    }
}