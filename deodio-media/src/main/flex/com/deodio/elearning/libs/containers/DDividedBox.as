package com.deodio.elearning.libs.containers {
	import com.deodio.elearning.libs.containers.dividedbox.DBoxDivider;
	import com.deodio.elearning.libs.skins.DVBoxDividerSkin;
	import com.deodio.elearning.libs.skins.DVDividerSkin;

	import mx.containers.DividedBox;

	[Style(name="hSkin", type="Class", inherit="no")]

	[Style(name="vSkin", type="Class", inherit="no")]

	[Style(name="hDividerSkin", type="Class", inherit="no")]

	[Style(name="vDividerSkin", type="Class", inherit="no")]

	public class DDividedBox extends DividedBox {
		public function DDividedBox() {
			super();
			this.dividerClass=DBoxDivider;
			this.setStyle("vSkin", DVBoxDividerSkin);
			this.setStyle("vDividerSkin", DVDividerSkin);
		}
	}
}
