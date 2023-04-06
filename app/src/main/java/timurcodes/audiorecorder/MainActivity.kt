package timurcodes.audiorecorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import timurcodes.audiorecorder.player.AndroidAudioPlayer
import timurcodes.audiorecorder.record.AndroidAudioRecorder
import java.io.File

class MainActivity : AppCompatActivity() {
	
	private val recorder by lazy { AndroidAudioRecorder(applicationContext) }
	
	private val player by lazy { AndroidAudioPlayer(applicationContext) }
	
	private var audioFile: File? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		ActivityCompat.requestPermissions(
			this,
			arrayOf(android.Manifest.permission.RECORD_AUDIO),
			0
		)
		
		setContent {
			MaterialTheme {
				Column(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center
				) {
					Button(onClick = {
						File(cacheDir, "audio.mp3").also {
							recorder.startRecording(it)
							audioFile = it
						}
					}) {
						Text(text = "Start recording")
					}
					Button(onClick = {
						recorder.stopRecording()
					}) {
						Text(text = "Stop recording")
					}
					Button(onClick = {
						audioFile?.let { player.playFile(it) }
					}) {
						Text(text = "Play record")
					}
					Button(onClick = {
						player.stop()
					}) {
						Text(text = "Stop record")
					}
				}
			}
		}
	}
}