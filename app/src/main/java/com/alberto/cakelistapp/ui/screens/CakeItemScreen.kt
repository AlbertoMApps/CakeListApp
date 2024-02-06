package com.alberto.cakelistapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alberto.cakelistapp.data.remote.model.Cake

@Composable
fun CakeItemScreen(cake: Cake) {
    var expand by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                cake.image?.takeIf { it.isNotEmpty() }.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = cake.image),
                        contentDescription = cake.title,
                        modifier = Modifier
                            .size(128.dp)
                            .weight(4f)
                    )
                }
                cake.title?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(4f)
                    )
                }
                IconButton(
                    onClick = {
                        expand = !expand
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "ArrowDropDown"
                    )
                }
            }
            AnimatedVisibility(visible = expand) {
                cake.desc?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CakeItemScreenPreview() {
    CakeItemScreen(
        cake = Cake(
            title = "Lemon cheesecake",
            desc = "A cheesecake made of lemon",
            image = "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
        )
    )
}