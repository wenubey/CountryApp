package com.wenubey.countryapp.ui.profile.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.countryapp.R
import com.wenubey.countryapp.ui.theme.CountryAppTheme
import com.wenubey.countryapp.utils.Constants.fakeCountryCode
import com.wenubey.countryapp.utils.components.EmailTextField


@Composable
fun UserInfoUpdateDialog(
    showDialog: MutableState<Boolean>,
    email: TextFieldValue,
    phoneNumberBody: TextFieldValue,
    countryCode: String,
    onEmailValueChange: (email: TextFieldValue) -> Unit,
    displayName: TextFieldValue,
    onDisplayNameValueChange: (displayName: TextFieldValue) -> Unit,
    onPhoneNumberValueChange: (phoneNumber: TextFieldValue) -> Unit,
    onCountryCodeValueChange: (phoneCode: String?) -> Unit,
    onClickConfirm: () -> Unit,
    countryCodeMap: Map<String?, String?>,
) {
    AlertDialogContent(
        showDialog = showDialog,
        email = email,
        phoneNumberBody = phoneNumberBody,
        onEmailValueChange = onEmailValueChange,
        displayName = displayName,
        onDisplayNameValueChange = onDisplayNameValueChange,
        onPhoneNumberValueChange = onPhoneNumberValueChange,
        onPhoneCodeValueChange = onCountryCodeValueChange,
        onClickConfirm = onClickConfirm,
        countryCodeMap = countryCodeMap,
        countryCode = countryCode,
    )
}

@Composable
private fun AlertDialogContent(
    showDialog: MutableState<Boolean> = mutableStateOf(false),
    email: TextFieldValue = TextFieldValue(stringResource(id = R.string.PREVIEW_EMAIL)),
    phoneNumberBody: TextFieldValue = TextFieldValue(stringResource(id = R.string.PREVIEW_PHONE)),
    countryCode: String = "",
    onEmailValueChange: (email: TextFieldValue) -> Unit = {},
    displayName: TextFieldValue = TextFieldValue(stringResource(id = R.string.PREVIEW_NAME)),
    onDisplayNameValueChange: (displayName: TextFieldValue) -> Unit = {},
    onPhoneNumberValueChange: (phoneNumber: TextFieldValue) -> Unit = {},
    onPhoneCodeValueChange: (phoneCode: String?) -> Unit = {},
    onClickConfirm: () -> Unit = {},
    countryCodeMap: Map<String?, String?> = fakeCountryCode,
) {
    val focusManager = LocalFocusManager.current
    var isButtonEnabled by remember {
        mutableStateOf(true)
    }

    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        title = {
            Text(
                text = stringResource(id = R.string.PROFILE_INFO),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailTextField(
                    email = email,
                    onEmailValueChange = { email, isError ->
                        onEmailValueChange(email)
                        isButtonEnabled = isError
                    },
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
                OutlinedTextField(
                    value = displayName,
                    onValueChange = onDisplayNameValueChange,
                    label = {
                        Text(
                            text = stringResource(id = R.string.DISPLAY_NAME_LABEL),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))
                PhonePicker(
                    countryData = countryCodeMap,
                    onSelectCountryCode = onPhoneCodeValueChange,
                    phoneNumberBody = phoneNumberBody,
                    countryCode = countryCode,
                    onPhoneCodeValueChange = onPhoneNumberValueChange
                )

            }
        },
        confirmButton = {
            Button(
                onClick = onClickConfirm,
                enabled = isButtonEnabled,
            ) {
                Text(
                    text = stringResource(id = R.string.SAVE),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
    )
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun AlertDialogContentPreview() {
    CountryAppTheme {
        Surface {
            AlertDialogContent()
        }
    }
}
