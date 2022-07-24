package ir.alishayanpoor.dictionarywithcaching.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import ir.alishayanpoor.dictionarywithcaching.ui.theme.DictionaryWithCachingTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class DictionaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryWithCachingTheme {
                val viewModel by viewModels<WordInfoViewModel>()
                val scaffoldState = rememberScaffoldState()

                SubscribeObservers(viewModel, scaffoldState)

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(
                                value = viewModel.state.searchQuery,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(viewModel.state.wordInfoItems.size) { i ->
                                    val wordInfo = viewModel.state.wordInfoItems[i]
                                    if (i > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))

                                    }
                                    WordInfoItem(wordInfo = wordInfo)
                                    if (i < viewModel.state.wordInfoItems.size - 1)
                                        Divider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun SubscribeObservers(
        viewModel: WordInfoViewModel,
        scaffoldState: ScaffoldState,
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.event.receiveAsFlow().collect { event ->
                when (event) {
                    is WordInfoUiEvent.ShowSnackBar ->
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = event.message
                        )
                }
            }
        }
    }
}