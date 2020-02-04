package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.Function")]
    public class Function
    {
        public var funcId:int;
        public var funcName:String;
        public var funcUrl:String;
        public var funcParentId:int;
        public var parentNode:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Function(){}

    }
}