
import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.squareup.picasso.Picasso

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Material3SearchBar(images: List<String>, onSearch: (String) -> Unit, onClear: () -> Unit) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            onClear()
            onSearch(text.lowercase())
            active = false
            text = ""
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (active) {
                Icon(modifier = Modifier.clickable {
                    if (text.isNotEmpty()) text = "" else active = false
                }, imageVector = Icons.Default.Close, contentDescription = "Close Icon")
            }
        }) {

        if (images.isEmpty()) {
            Text(text = "No images found", modifier = Modifier.padding(16.dp).align(Alignment.Start)
                )
        } else {
            LazyColumn {

                items(images) { imageUrl ->
                    AndroidView(
                        factory = { context ->
                            ImageView(context).apply {
                                Picasso.get().load(imageUrl).into(this)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                    )
                }


            }
        }
    }
}
