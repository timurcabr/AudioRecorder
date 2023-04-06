package timurcodes.audiorecorder.record

import java.io.File

interface AudioRecorder {

	fun startRecording(file: File)
	fun stopRecording()

}