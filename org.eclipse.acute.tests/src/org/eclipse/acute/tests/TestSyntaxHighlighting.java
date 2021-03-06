/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Mickael Istria (Red Hat Inc.) - Initial implementation
 *******************************************************************************/
package org.eclipse.acute.tests;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.tests.harness.util.DisplayHelper;
import org.junit.Assert;
import org.junit.Test;

public class TestSyntaxHighlighting extends AbstractAcuteTest {

	@Test
	public void testSyntaxHighlighting() throws CoreException, IOException {
		IFile csharpSourceFile = getProject("basic").getFile("test.cs");
		TextEditor editor = (TextEditor) IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), csharpSourceFile, "org.eclipse.ui.genericeditor.GenericEditor");
		StyledText editorTextWidget = (StyledText)editor.getAdapter(Control.class);
		new DisplayHelper() {
			@Override
			protected boolean condition() {
				return editorTextWidget.getStyleRanges().length > 1;
			}
		}.waitForCondition(editorTextWidget.getDisplay(), 4000);
		Assert.assertTrue("There should be multiple styles in editor", editorTextWidget.getStyleRanges().length > 1);
	}

}
