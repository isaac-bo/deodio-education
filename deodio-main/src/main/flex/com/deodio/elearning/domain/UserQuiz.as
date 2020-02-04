package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.UserQuiz")]
    public class UserQuiz
    {
        public var quizFinalScore:int;
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;
        public var quizItemsNum:int;
        public var quizCorrectNum:int;



        public function UserQuiz(){}

    }
}