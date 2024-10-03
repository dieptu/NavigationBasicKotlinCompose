package sheridan.dieptu.navigationbasic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * NavigationBasic created by yukikodou
 * Student ID: 991548081
 * Name : Diep Tu Minh
 * on 2024-10-01
 */
@Composable
fun SecondScreen(selection: String?) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "You selected: $selection")
    }
}