package com.jvcodingsolutions.echojournal.app.widget

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import com.jvcodingsolutions.echojournal.R
import com.jvcodingsolutions.echojournal.app.MainActivity
import com.jvcodingsolutions.echojournal.app.navigation.ACTION_CREATE_ECHO
import com.jvcodingsolutions.echojournal.app.navigation.createEchoDeepLinkUri

class RecordEchoWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = RecordEchoWidget()
}

class RecordEchoWidget: GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val recordNewEcho = context.getString(R.string.record_new_echo)

        // CLEAR_TASK guarantees a fresh MainActivity, so the deep link is always
        // handled in onCreate, even if the app is already open
        val createEchoIntent = Intent(context, MainActivity::class.java).apply {
            action = ACTION_CREATE_ECHO
            data = createEchoDeepLinkUri(startRecording = true).toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        provideContent {
            GlanceTheme {
                Column(
                    modifier = GlanceModifier
                        .clickable(actionStartActivity(createEchoIntent))
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.widget),
                        contentDescription = recordNewEcho
                    )
                }
            }
        }
    }
}
