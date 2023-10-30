package cz.ackee.testtask.rm.ui.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import cz.ackee.testtask.rm.app.common.PaginationData
import cz.ackee.testtask.rm.app.common.Response
import cz.ackee.testtask.rm.repository.common.model.Character

@Composable
fun PaginationHandler(
    state: LazyListState,
    paginationData: PaginationData<Character>
) {
    val shouldStartPaginate = remember {
        derivedStateOf {
            !paginationData.endReached
                    && (state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -10) >= (state.layoutInfo.totalItemsCount - 1)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && paginationData.canPaginate) {
            paginationData.loadMore()
        }
    }
}