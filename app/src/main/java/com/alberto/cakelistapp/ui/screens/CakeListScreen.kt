package com.alberto.cakelistapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.cakelistapp.presentation.CakeListViewModel
import com.alberto.cakelistapp.ui.theme.CakeListAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CakeListScreen(
    viewModel: CakeListViewModel = hiltViewModel()
) {

    val cakeListState = viewModel.cakeListState.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = cakeListState.isLoading)

    when {
        cakeListState.data.isNotEmpty() -> {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.getCakeList() }
            ) {
                LazyColumn {
                    items(cakeListState.data) { cake ->
                        CakeItemScreen(cake = cake)
                    }
                }
            }
        }

        cakeListState.errorMessage.isNotEmpty() -> {
            //TODO Error handling here...
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CakeListScreenPreview() {
    CakeListAppTheme {
        CakeListScreen()
    }
}