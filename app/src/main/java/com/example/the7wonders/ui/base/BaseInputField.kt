package com.example.the7wonders.ui.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the7wonders.ui.theme.BaseColors
import com.example.the7wonders.ui.theme.Dimens
import com.example.the7wonders.ui.theme.Typography

@Composable
fun BaseInputField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    action: ImeAction = ImeAction.Done,
    icon: ImageVector? = null,
    onValueChange: (TextFieldValue) -> Unit
) {
    val fieldFocused = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier.onFocusChanged {
            fieldFocused.value = it.hasFocus
        },
        value = TextFieldValue(
            value, TextRange(value.length)
        ),
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = action),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = BaseColors.primary,
            focusedContainerColor = BaseColors.primary,
            unfocusedBorderColor = BaseColors.secondary,
            focusedBorderColor = BaseColors.backgroundSecondary,
            cursorColor = BaseColors.onSecondary
        ),
        placeholder = {
            Text(
                hint,
                style = Typography.labelMedium.copy(color = BaseColors.textSecondary)
            )
        },
        shape = RoundedCornerShape(Dimens.cornerRadiusMax),
        textStyle = Typography.labelMedium,
        prefix = if (icon != null) {
            {
                Icon(
                    imageVector = icon,
                    "text field icon",
                    modifier = Modifier
                        .padding(start = Dimens.paddingMedium, end = Dimens.paddingSmall)
                        .size(Dimens.textFieldIconSize),
                    tint = if (fieldFocused.value) BaseColors.textPrimary else BaseColors.textSecondary
                )
            }
        } else {
            {
                Spacer(modifier = Modifier.padding(horizontal = Dimens.paddingSmall))
            }
        }

    )
}

@Preview
@Composable
fun BaseInputFieldPreview() {
    val textFieldValue = remember { mutableStateOf("") }
    val onValueChange = fun(value : TextFieldValue) {
        textFieldValue.value = value.text
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        BaseInputField(
            value = textFieldValue.value,
            onValueChange = onValueChange,
            hint = "input field hint",
            icon = Icons.Outlined.Create
        )
    }
}