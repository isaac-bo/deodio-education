package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.UserGroupRel")]
    public class UserGroupRel
    {
        public var pkId:int;
        public var userId:int;
        public var groupId:int;
        public var checkStatus:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function UserGroupRel(){}

    }
}