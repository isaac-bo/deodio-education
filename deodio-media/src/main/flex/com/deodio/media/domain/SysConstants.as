package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.SysConstants")]
    public class SysConstants
    {
        public var constId:int;
        public var constType:int;
        public var constParentId:int;
        public var constName:String;
        public var constValue:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function SysConstants(){}

    }
}