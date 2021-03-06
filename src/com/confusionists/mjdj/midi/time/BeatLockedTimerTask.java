/*
Mjdj MIDI Morph - an extensible MIDI processor and translator.
Copyright (C) 2010 Confusionists, LLC (www.confusionists.com)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>. 

You may contact the author at mjdj_midi_morph [at] confusionists.com
*/
package com.confusionists.mjdj.midi.time;

import com.confusionists.mjdj.Universe;
import com.confusionists.mjdjApi.util.MidiTimerTask;

public class BeatLockedTimerTask {

	private MidiTimerTask task;
	private int beatsBeforeLaunch;
	private float afterBeat;

	public boolean setTask(MidiTimerTask task, int beatsBeforeLaunch, float afterBeat) {
		this.task = task;
		this.beatsBeforeLaunch = beatsBeforeLaunch;
		if (!(afterBeat < 1) || afterBeat < 0)
			return false;
		this.afterBeat = afterBeat;
		if (beatsBeforeLaunch > 0) {
			task.setAfterBeat(afterBeat);
			return Universe.instance.clockHandler.pleaseCallMeOnEachBeat(this);
		} else {
			scheduleInMilliseconds();
			return true;
		}
		
	}

	public boolean setTask(MidiTimerTask task, int beatsBeforeLaunch) {
		float afterBeat = Universe.instance.clockHandler.getSinceLastBeat();
		return this.setTask(task, beatsBeforeLaunch, afterBeat);
	}

	public void onBeat() {
		if (--beatsBeforeLaunch == 0) {
			task.runOnBeatBefore();
			scheduleInMilliseconds();
		}
	}
	
	private void scheduleInMilliseconds() {
		long msAfterBeat = (long) (afterBeat * Universe.instance.clockHandler.differenceInMs);
		msAfterBeat = Math.max(0, msAfterBeat - 2); // 2ms of latency
        MidiTimerTaskWrapper taskWrapper = new MidiTimerTaskWrapper(task);
		Universe.instance.centralTimer.schedule(taskWrapper, msAfterBeat);
	}

	boolean isDead() {
		return (beatsBeforeLaunch <= 0);
	}
}
