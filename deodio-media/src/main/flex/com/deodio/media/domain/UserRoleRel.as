package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.UserRoleRel")]
    public class UserRoleRel
    {
        public var pkId:int;
        public var userId:int;
        public var roleId:int;
        public var ctrator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function UserRoleRel(){}

    }
}