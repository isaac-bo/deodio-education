package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.Account")]
    public class Account
    {
        public var id:String;
        public var name:String;
        public var contactName:String;
        public var contactEmail:String;
        public var contactPhone:Number;
        public var subDomain:String;
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;
        public var status:int;
        public var expireDate:Date;
        public var activateDate:Date;
        public var cancelDate:Date;



        public function Account(){}

    }
}