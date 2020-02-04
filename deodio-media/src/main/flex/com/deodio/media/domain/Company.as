package com.deodio.media.domain
{
    [RemoteClass(alias="com.deodio.persistence.model.Company")]
    public class Company
    {
        public var companyId:int;
        public var companyName:String;
        public var companyAddr:String;
        public var companyTel:String;
        public var companyStatus:int;
        public var creator:int;
        public var createTime:Date;
        public var modifier:int;
        public var modifyTime:Date;



        public function Company(){}

    }
}