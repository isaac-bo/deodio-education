package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.Quiz")]
    public class Quiz
    {
        public var quizId:int;
        public var tempId:int;
        public var examName:String;
        public var examContent:String;
        public var examTestType:int;
        public var examAutoCalc:int;
        public var examTotalScore:int;
        public var examPassScore:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Quiz(){}

    }
}