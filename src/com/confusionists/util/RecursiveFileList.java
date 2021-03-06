/*
Mjdj MIDI Morph - an extensible MIDI processor and translator.
Copyright (C) 2010 Confusionists, LLC (www.confusionists.com)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>. 

You may contact the author at mjdj_midi_morph [at] confusionists.com
*/
package com.confusionists.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class RecursiveFileList {

	/* returns null if it is not a directory. Otherwise returns list of length >= 0 */
	public static File[] getList(File startPoint, String extension) throws FileNotFoundException {
		if (!startPoint.exists())
			throw new FileNotFoundException("File " + startPoint.getAbsolutePath() + " not found.");
		if (!startPoint.isDirectory()) 
			return null;
		List<File> files = new ArrayList<File>();
		analyze(startPoint, files, extension);
		return files.toArray(new File[files.size()]);
	} 

	private static void analyze(File startPoint, List<File> files, String extension) {
		File[] children = startPoint.listFiles(new ExtensionAndDirectoryFilter(extension));
		if (children == null)
			return;
		for (File file : children) {
			if (file.isDirectory()) 
				analyze(file, files, extension);
			else
				files.add(file);
		}
	}
	
	/* testing main */
	public static void main(String[] args) throws FileNotFoundException {
		File[] files = getList(new File("/dans-data/java/Mjdj"), "java");
		for (File file : files) {
			System.out.println("found " + file.getAbsolutePath());
		}
	}

	

}

class ExtensionAndDirectoryFilter implements FilenameFilter {

	private String extension;

	public ExtensionAndDirectoryFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File dir, String filename) {
		if (new File(dir, filename).isDirectory())
			return true;

		int period = filename.lastIndexOf('.');

		if (period == -1)
			return false;

		String extension = filename.substring(period + 1, filename.length());
		return (extension.equals(this.extension));
	}

}