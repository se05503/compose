package com.example.compose_practice.ui.components.dialog.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.compose_practice.ui.components.buttons.PrimaryButton
import com.example.compose_practice.ui.components.buttons.SecondaryBorderlessButton
import com.example.compose_practice.ui.components.buttons.SecondaryButton
import com.example.compose_practice.ui.components.buttons.UnderlinedTextButton
import com.example.compose_practice.ui.models.dialog.DialogButtons
import com.example.compose_practice.ui.theme.Paddings

@Composable
fun DialogButtonColumn(buttons: List<DialogButtons>?) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons?.forEachIndexed { index, button ->
            if(index > 0) {
                Spacer(modifier = Modifier.height(Paddings.large))
            }
            when(button) {
                is DialogButtons.Primary -> {
                    PrimaryButton(
                        text = button.title,
                        leadingIconData = button.leadingIconData
                    ) {
                        button.action?.invoke()
                    }
                }
                is DialogButtons.Secondary -> {
                    SecondaryButton(
                        text = button.title
                    ) {
                        button.action?.invoke()
                    }
                }
                is DialogButtons.SecondaryBorderless -> {
                    SecondaryBorderlessButton(
                        text = button.title
                    ) {
                        button.action?.invoke()
                    }
                }
                is DialogButtons.UnderlinedText -> {
                    UnderlinedTextButton(
                        text = button.title
                    ) {
                        button.action?.invoke()
                    }
                }
            }
        }
    }
}