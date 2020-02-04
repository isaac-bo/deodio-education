package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.Template")]
    public class Template
    {
        public var tempId:int;
        public var tempName:String;
        public var tempContent:String;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Template(){}

    }
}