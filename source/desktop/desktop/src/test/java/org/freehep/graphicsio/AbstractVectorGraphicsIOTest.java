package org.freehep.graphicsio;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayOutputStream;

import org.freehep.graphicsio.pdf.PDFGraphics2D;
import org.junit.jupiter.api.Test;

class AbstractVectorGraphicsIOTest {

	/**
	 * untransformShape() returns null for a singular transform; getClip() must
	 * not pass that null into new Area(...) (NPE, broke EPS/PDF exports).
	 */
	@Test
	void getClipSurvivesSingularTransform() {
		PDFGraphics2D g = new PDFGraphics2D(new ByteArrayOutputStream(), new Dimension(100, 100));
		g.startExport();
		g.setClip(new Rectangle(0, 0, 10, 10));
		g.transform(new AffineTransform(0, 0, 0, 0, 0, 0));
		assertNull(g.getClip());
	}
}
