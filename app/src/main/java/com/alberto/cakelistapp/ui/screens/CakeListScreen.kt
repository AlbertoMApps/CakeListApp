package com.alberto.cakelistapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.cakelistapp.presentation.CakeListState
import com.alberto.cakelistapp.presentation.CakeListViewModel
import com.alberto.cakelistapp.ui.theme.CakeListAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CakeListScreen(
    viewModel: CakeListViewModel = hiltViewModel()
) {

    val cakeListState = viewModel.cakeListState.collectAsState(initial = CakeListState()).value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = cakeListState.isLoading)

    when {
        cakeListState.data.isNotEmpty() -> {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.getCakeList() }
            ) {
                CakeItemScreen(cakeListState.data[0])
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