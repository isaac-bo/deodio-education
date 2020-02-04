package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.Account")]
    public class Account
    {
        public var accountId:int;
        public var companyId:int;
        public var accountName:String;
        public var accountPwd:String;
        public var realName:String;
        public var accountEmail:String;
        public var accountMobile:int;
        public var accountStatus:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Account(){}

    }
}