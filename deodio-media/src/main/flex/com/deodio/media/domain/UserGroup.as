package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.UserGroup")]
    public class UserGroup
    {
        public var groupId:int;
        public var groupName:String;
        public var needCheck:int;
        public var autoCreate:String;
        public var creator:int;
        public var cteateTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function UserGroup(){}

    }
}