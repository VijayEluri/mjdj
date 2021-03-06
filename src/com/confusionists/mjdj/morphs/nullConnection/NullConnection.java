/*
Mjdj MIDI Morph - an extensible MIDI processor and translator.
Copyright (C) 2010 Confusionists, LLC (www.confusionists.com)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>. 

You may contact the author at mjdj_midi_morph [at] confusionists.com
*/
package com.confusionists.mjdj.morphs.nullConnection;
import com.confusionists.mjdjApi.midi.MessageWrapper;
import com.confusionists.mjdjApi.morph.AbstractMorphWithUI;


public class NullConnection extends AbstractMorphWithUI {

	@Override
	public String getName() {
		return "Null-Connection (Right-Click to Open)";
	}

	@Override
	public String diagnose() {
		return null;
	}




	@Override
	public boolean allowInputFromOtherMorphs() {
		return true;
	}

	@Override
	public boolean allowOutputToOtherMorphs() {
		return false;
	}

	@Override
	protected boolean processAndSend(MessageWrapper message, String from, String to) {
		return super.defaultProcessAndSend(message, to);
	}

	@Override
	protected boolean processAndSendToOtherMorphs(MessageWrapper message) {
		return super.defaultProcessAndSendToOtherMorphs(message);
	}

	

}

