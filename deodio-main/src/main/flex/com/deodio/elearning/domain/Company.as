package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.Company")]
    public class Company
    {
        public var id:String;
        public var name:String;
        public var address:String;
        public var businessLicence:String;
        public var status:int;
        public var creator:String;
        public var createTime:Date;
        public var modifier:String;
        public var modifyTime:Date;
        public var businessLicenceType:int;



        public function Company(){}

    }
}