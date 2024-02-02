package com.alberto.cakelistapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.cakelistapp.presentation.CakeListState
import com.alberto.cakelistapp.presentation.CakeListViewModel
import com.alberto.cakelistapp.ui.theme.CakeListAppTheme

@Composable
fun CakeListScreen(
    viewModel: CakeListViewModel = hiltViewModel()
) {
    Text(
        text = viewModel.cakeListState.collectAsState(initial = CakeListState()).value.data[0].title
            ?: ""
    )
}

@Preview(showBackground = true)
@Composable
fun CakeListScreenPreview() {
    CakeListAppTheme {
        CakeListScreen()
    }
}