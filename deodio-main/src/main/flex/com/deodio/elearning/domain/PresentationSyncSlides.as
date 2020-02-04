package com.deodio.elearning.domain
{
    [RemoteClass(alias="com.deodio.elearning.persistence.model.PresentationSyncSlides")]
    public class PresentationSyncSlides
    {
        public var id:String;
        public var presentationId:String;
        public var slideId:String;
        public var slideName:String;
        public var slideSize:Number;
        public var slideExt:String;
        public var slideUrl:String;
        public var slideDir:String;
        public var slideOrder:int;
        public var createId:String;
        public var createTime:Date;
        public var updateId:String;
        public var updateTime:Date;
        public var slideOriginalName:String;



        public function PresentationSyncSlides(){}

    }
}