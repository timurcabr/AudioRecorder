package timurcodes.audiorecorder.record

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

class AndroidAudioRecorder(
	private val context: Context
) : AudioRecorder {
	
	private var recorder: MediaRecorder? = null
	
	private fun createRecorder(): MediaRecorder {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			MediaRecorder(context)
		} else MediaRecorder()
	}
	
	override fun startRecording(file: File) {
		createRecorder().apply {
			setAudioSource(MediaRecorder.AudioSource.MIC)
			setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
			setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
			setOutputFile(FileOutputStream(file).fd)
			prepare()
			start()
			
			recorder = this
		}
	}
	
	override fun stopRecording() {
		recorder?.stop()
		recorder?.reset()
		recorder = null
	}
}