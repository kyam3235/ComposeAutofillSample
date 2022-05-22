package jp.kyamlab.composeautofillsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kyamlab.composeautofillsample.ui.theme.ComposeAutofillSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    ComposeAutofillSampleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Password()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Password() {
    var password by remember { mutableStateOf("") }
    val autofillNode = AutofillNode(
        autofillTypes = listOf(AutofillType.Password),
        onFill = { password = it }
    )
    val autofill = LocalAutofill.current
    LocalAutofillTree.current += autofillNode
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .onGloballyPositioned {
                autofillNode.boundingBox = it.boundsInWindow()
            }
            .onFocusChanged { focusState ->
                autofill?.run {
                    if (focusState.isFocused) {
                        requestAutofillForNode(autofillNode)
                    } else {
                        cancelAutofillForNode(autofillNode)
                    }
                }
            },
        value = password,
        onValueChange = { password = it },
        label = { Text(text = "Password") },
        maxLines = 1
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAutofillSampleTheme {
        Password()
    }
}