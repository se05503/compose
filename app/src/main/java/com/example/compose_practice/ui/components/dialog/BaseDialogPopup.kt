package com.example.compose_practice.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.compose_practice.ui.components.dialog.components.button.DialogButtonColumn
import com.example.compose_practice.ui.models.dialog.DialogButtons
import com.example.compose_practice.ui.models.dialog.DialogContent
import com.example.compose_practice.ui.models.dialog.DialogTitle
import com.example.compose_practice.ui.theme.Paddings

@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent: DialogContent? = null,
    buttons: List<DialogButtons>? = null
) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = Paddings.none,
            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                dialogTitle?.let {
                    DialogTitleWrapper(it)
                }
                Column(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(
                            start = Paddings.xlarge,
                            end = Paddings.xlarge,
                            bottom = Paddings.xlarge
                        )
                ) {
                    dialogContent?.let { DialogContentWrapper(it)}
                    buttons?.let{DialogButtonColumn(it)}
                }
            }
        }
}



