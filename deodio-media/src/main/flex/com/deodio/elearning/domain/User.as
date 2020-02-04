package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.User")]
    public class User
    {
        public var userId:int;
        public var accountId:int;
        public var userName:String;
        public var userPwd:String;
        public var userRealName:String;
        public var userGender:int;
        public var userEmail:String;
        public var userMobile:int;
        public var userStatus:int;
        public var userType:int;
        public var userRelId:int;
        public var lastLoginTime:Date;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function User(){}

    }
}