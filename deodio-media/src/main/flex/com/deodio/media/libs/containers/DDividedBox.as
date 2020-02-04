package com.deodio.media.libs.containers {
	import com.deodio.media.libs.containers.dividedbox.DBoxDivider;
	import com.deodio.media.libs.skins.DVBoxDividerSkin;
	import com.deodio.media.libs.skins.DVDividerSkin;

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
