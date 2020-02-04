package com.deodio.media.model {
	
	[Bindable]
	public class ModelLocator {
		
		private static var _model:ModelLocator;
		
		public function ModelLocator() {
		}
		
		public static function getInstance():ModelLocator {
			if (_model == null) {
				_model=new ModelLocator();
			}
			return _model;
		}
	}
}

