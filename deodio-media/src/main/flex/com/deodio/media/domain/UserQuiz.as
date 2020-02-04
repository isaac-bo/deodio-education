package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.UserQuiz")]
    public class UserQuiz
    {
        public var quizId:int;
        public var userId:int;
        public var quizFinalScore:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;
        public var quizItemsNum:int;
        public var quizCorrectNum:int;



        public function UserQuiz(){}

    }
}