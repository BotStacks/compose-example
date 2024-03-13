package service

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.Autofill
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun AutoFillRequestHandler(
    autofillTypes: List<AutofillType>,
    onFill: (String) -> Unit,
): AutoFillHandler {
    var isFillRecently = remember { false }
    val autoFillNode = remember {
        AutofillNode(
            autofillTypes = autofillTypes,
            onFill = {
                isFillRecently = true
                onFill(it)
            }
        )
    }

    val autofill = LocalAutofill.current
    LocalAutofillTree.current += autoFillNode
    return remember {
        object : AutoFillHandler {
            override val autoFill: Autofill?
                get() = autofill

            override val autoFillNode: AutofillNode
                get() = autoFillNode

            override fun requestVerifyManual() {
                if (isFillRecently) {
                    isFillRecently = false
                    requestManual()
                }
            }

            override fun requestManual() {
                // TODO:
            }

            override fun request() {
                autofill?.requestAutofillForNode(autofillNode = autoFillNode)
            }

            override fun cancel() {
                autofill?.cancelAutofillForNode(autofillNode = autoFillNode)
            }

            override fun Modifier.fillBounds(): Modifier {
                return Modifier
            }
        }
    }
}
