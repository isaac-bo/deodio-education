package com.deodio.elearning.domain {

	[RemoteClass(alias="com.deodio.elearning.persistence.model.Presentation")]
	public class Presentation {
		public var id:String;
		public var presentationName:String;
		public var createId:String;
		public var createTime:Date;
		public var updateId:String;
		public var updateTime:Date;
		public var presentationDesc:String;
		public var presentationCover:String;
		public var presentationModel:*;
		public var persentationPercentage:String;
		public var isPassElements:*;
		public var isPassQuizs:*;
		public var isCourse:*;
		public var isPassHours:*;



		public function Presentation() {
		}

	}
}
