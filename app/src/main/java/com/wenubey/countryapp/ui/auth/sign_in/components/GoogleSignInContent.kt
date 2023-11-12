package com.wenubey.countryapp.ui.auth.sign_in.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GoogleSignInContent(
    paddingValues: PaddingValues,
    oneTapSignIn: () -> Unit
) {
    Box(modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.BottomCenter) {
        GoogleSignInButton(onClick = oneTapSignIn)
    }
}