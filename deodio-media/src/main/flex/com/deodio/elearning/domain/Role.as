package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.Role")]
    public class Role
    {
        public var roleId:int;
        public var roleName:String;
        public var systemDefault:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Role(){}

    }
}