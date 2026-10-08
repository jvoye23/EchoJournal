package com.jvcodingsolutions.echojournal.app.navigation

import android.content.Intent
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.deeplink.DeepLinkMatcher
import androidx.navigation3.runtime.deeplink.DeepLinkRequest
import androidx.navigation3.runtime.deeplink.DeepLinkUri
import androidx.navigation3.runtime.deeplink.UriDeepLinkMatcher
import androidx.navigation3.runtime.deeplink.actionFilter
import androidx.navigation3.runtime.deeplink.invoke
import kotlinx.serialization.serializer

const val ACTION_CREATE_ECHO = "com.jvcodingsolutions.CREATE_ECHO"
const val DEEPLINK_BASE_URL = "https://echojournal.com"
const val ECHOS_DEEPLINK_PATTERN = "$DEEPLINK_BASE_URL/echos/{startRecording}"

fun createEchoDeepLinkUri(startRecording: Boolean): String =
    "$DEEPLINK_BASE_URL/echos/$startRecording"

private val deepLinkMatchers: List<UriDeepLinkMatcher<NavKey>> = listOf(
    UriDeepLinkMatcher(
        uriPattern = DeepLinkUri(ECHOS_DEEPLINK_PATTERN),
        serializer = serializer<NavigationRoute.EchosNavKey>(),
        filters = listOf(DeepLinkMatcher.actionFilter(ACTION_CREATE_ECHO)),
    ),
)

fun Intent.toInitialNavKey(): NavKey {
    val request = DeepLinkRequest(this)
    return deepLinkMatchers
        .mapNotNull { it.match(request) }
        .maxOrNull()
        ?.key
        ?: NavigationRoute.EchosNavKey()
}
