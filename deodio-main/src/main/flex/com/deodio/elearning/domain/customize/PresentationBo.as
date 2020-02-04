package com.deodio.elearning.domain.customize {
	import com.deodio.elearning.domain.Presentation;

	import mx.collections.ArrayCollection;

	[RemoteClass(alias="com.deodio.elearning.persistence.model.customize.PresentationBo")]
	public class PresentationBo extends Presentation {

		public var slides:ArrayCollection=new ArrayCollection();
		public var points:ArrayCollection=new ArrayCollection();
		public var medias:ArrayCollection=new ArrayCollection();
		public var quizs:ArrayCollection=new ArrayCollection();

		public function Presentation() {
		}
	}
}
